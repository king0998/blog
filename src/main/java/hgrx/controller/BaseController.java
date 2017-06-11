package hgrx.controller;

import hgrx.bean.Article;
import hgrx.bean.User;
import hgrx.dto.ArticleDetailVO;
import hgrx.dto.TagWithSize;
import hgrx.service.AdminService;
import hgrx.service.BaseService;
import hgrx.service.FollowService;
import hgrx.util.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by HGRX on 2017/5/11
 */
@Controller
public class BaseController {

    final
    BaseService baseService;

    final
    AdminService adminService;

    @Autowired
    FollowService followService;

    @Autowired
    public BaseController(BaseService baseService, AdminService adminService) {
        this.adminService = adminService;
        this.baseService = baseService;
    }


    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String getArticle(@PathVariable Long id, Model model, HttpSession session) {
        ArticleDetailVO advo = baseService.getAdvoById(id);
        User user = baseService.getUserById(advo.getUserId());
        model.addAttribute("advo", advo);
        model.addAttribute("user", user);
        model.addAttribute("isStar", adminService.hasStarArticle(getUser(session), id));
        model.addAttribute("pageView", CacheUtils.MyCache.increPageView(id));
        model.addAttribute("tags", baseService.listTagsWithSizeByUserId(user.getId()));
        model.addAttribute("latestAdvo", listLatestArticle(user.getId()));
        return "article";
    }

    @RequestMapping(value = {"/home-page/{id}", "/"}, method = RequestMethod.GET)
    public String homePage(@PathVariable Long id, Model model) {
        User user = baseService.getUserById(id);
        List<ArticleDetailVO> advoList = baseService.listAdvoWithPartContentByUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("advoList", advoList);
        model.addAttribute("tags", baseService.listTagsWithSizeByUserId(user.getId()));
        model.addAttribute("latestAdvo", listLatestArticle(user.getId()));
        return "home-page";
    }

    /**
     * 归档
     */
    @RequestMapping(value = "/archives/{id}", method = RequestMethod.GET)
    public String archives(@PathVariable Long id, Model model) {
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(id);
        User user = baseService.getUserById(id);
        Collections.sort(advoList);
        model.addAttribute("num", advoList.size());
        model.addAttribute("user", user);
        Map<String, List<ArticleDetailVO>> yearMap = getYearMap(advoList);
        model.addAttribute("yearMap", yearMap);
        model.addAttribute("tags", baseService.listTagsWithSizeByUserId(user.getId()));
        model.addAttribute("latestAdvo", listLatestArticle(user.getId()));
        return "article-list";
    }


    public Map<String, List<ArticleDetailVO>> getYearMap(List<ArticleDetailVO> advoList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        // 归档页面,需要年份,时间都是逆序
        Collections.sort(advoList);
        Map<String, List<ArticleDetailVO>> map = new TreeMap<>((a, b) -> -a.compareTo(b));
        advoList.forEach(it -> {
            String year = sdf.format(it.getTimestamp());
            if (!map.containsKey(year)) {
                map.put(year, new ArrayList<>());
            }
            map.get(year).add(it);
        });
        return map;
    }


    @RequestMapping(value = "about/{id}", method = RequestMethod.GET)
    public String about(@PathVariable Long id, Model model, HttpSession session) {
        User u = getUser(session);
        String about = baseService.getAboutMeByUserId(id);
        User user = baseService.getUserById(id);
        model.addAttribute("about", about);
        model.addAttribute("user", user);
        model.addAttribute("tags", baseService.listTagsWithSizeByUserId(user.getId()));
        model.addAttribute("latestAdvo", listLatestArticle(user.getId()));
        model.addAttribute("hasFollow", followService.hasFollow(u, user));
        return "about";
    }




    /**
     * archives?name={tagName}&userId={userId}
     *
     * @param name   tag 名称
     * @param userId tag所属的用户id
     */
    @RequestMapping(value = "archives", params = {"name", "userId"}, method = RequestMethod.GET)
    public String getTagsByName(@RequestParam String name, @RequestParam Long userId, Model model) {
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(userId);
        advoList.removeIf(
                it -> !it.getTags().contains(name)
        );
        Map<String, List<ArticleDetailVO>> yearMap = getYearMap(advoList);
        User user = baseService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("desc", name);
        model.addAttribute("yearMap", yearMap);
        model.addAttribute("tags", baseService.listTagsWithSizeByUserId(user.getId()));
        model.addAttribute("latestAdvo", listLatestArticle(user.getId()));
        return "article-list";
    }

    @RequestMapping(value = "archives/search", params = {"keyword", "userId"}, method = RequestMethod.POST)
    public String listAdvoWithKeyword(@RequestParam String keyword,
                                      @RequestParam Long userId, Model model) {
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserIdAndKeyword(keyword, userId);
        Map<String, List<ArticleDetailVO>> yearMap = getYearMap(advoList);
        User user = baseService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("desc", keyword);
        model.addAttribute("yearMap", yearMap);
        model.addAttribute("tags", baseService.listTagsWithSizeByUserId(user.getId()));
        model.addAttribute("latestAdvo", listLatestArticle(user.getId()));
        return "article-list";
    }


    @RequestMapping(value = "listTags", method = RequestMethod.GET)
    @ResponseBody
    public List<TagWithSize> listTagsByUserId(@RequestParam Long userId) {
        return baseService.listTagsWithSizeByUserId(userId);
    }


    @RequestMapping(value = "latestArticle", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> listLatestArticle(@RequestParam Long userId) {
        //TODO 缓存 key: latestArticle value : List<Article>
        //思路同上
        List<Article> list = baseService.listLatestArticleByUserId(userId);
        return list.subList(0, list.size() > 10 ? 10 : list.size());
    }

    private User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

}
