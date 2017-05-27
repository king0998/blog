package hgrx.controller;

import hgrx.bean.Article;
import hgrx.bean.Tag;
import hgrx.bean.User;
import hgrx.dto.ArticleDetailVO;
import hgrx.dto.TagWithSize;
import hgrx.service.AdminService;
import hgrx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public BaseController(BaseService baseService, AdminService adminService) {
        this.adminService = adminService;
        this.baseService = baseService;
    }


    @RequestMapping("/article/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleDetailVO advo = baseService.getAdvoById(id);
        User user = baseService.getUserById(advo.getUserId());
        //TODO 处理没有该文章的情况
        model.addAttribute("advo", advo);
        model.addAttribute("user", user);
        return "article";
    }

    @RequestMapping("/home-page/{id}")
    public String homePage(@PathVariable Long id, Model model) {
        //TODO id不为数字
        User user = baseService.getUserById(id);
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("advoList", advoList);
        return "home-page";
    }

    /**
     * 归档
     */
    @RequestMapping("/archives/{id}")
    public String archives(@PathVariable Long id, Model model) {
        //TODO 不考虑分页
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(id);
        User user = baseService.getUserById(id);
        Collections.sort(advoList);
        model.addAttribute("num", advoList.size());
        model.addAttribute("user", user);

        Map<String, List<ArticleDetailVO>> yearMap = getYearMap(advoList);
        model.addAttribute("yearMap", yearMap);
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

    @RequestMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model) {
        List<Tag> tagList = baseService.listTagsByUserId(id);
        User user = baseService.getUserById(id);
        model.addAttribute("tagList", tagList);
        model.addAttribute("user", user);
        return "tags";
    }

    @RequestMapping("about/{id}")
    public String about(@PathVariable Long id, Model model) {
        String about = baseService.getAboutMeByUserId(id);
        User user = baseService.getUserById(id);
        model.addAttribute("about", about);
        model.addAttribute("user", user);
        return "about";
    }

    @RequestMapping("square")
    public String gotoSquare(Model model) {
        //TODO 计划在这里还要放上个人follow和收藏的文章,暂时先放所有文章

        List<ArticleDetailVO> advoList = baseService.listAllAdvo();
        model.addAttribute("advoList", advoList);
        return "square_page";
    }


    /**
     * archives?name={tagName}&userId={userId}
     *
     * @param name   tag 名称
     * @param userId tag所属的用户id
     */
    @RequestMapping(value = "archives", params = {"name", "userId"})
    public String getTagsByName(@RequestParam String name, @RequestParam Long userId, Model model) {
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserId(userId);
        advoList.removeIf(
                it -> !it.getTags().contains(name)
        );
        Map<String, List<ArticleDetailVO>> yearMap = getYearMap(advoList);
        User user = baseService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("yearMap", yearMap);
        return "article-list";
    }

    @RequestMapping(value = "archives", params = {"keyword", "userId"})
    public String listAdvoWithKeyword(@RequestParam String keyword,
                                      @RequestParam Long userId, Model model) {
        List<ArticleDetailVO> advoList = baseService.listAdvoByUserIdAndKeyword(keyword, userId);
        Map<String, List<ArticleDetailVO>> yearMap = getYearMap(advoList);
        User user = baseService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("yearMap", yearMap);
        return "article-list";

    }

    @RequestMapping(value = "listTags")
    @ResponseBody
    public List<TagWithSize> listTagsByUserId(@RequestParam Long userId) {
        //TODO 缓存
        return baseService.listTagsWithSizeByUserId(userId);
    }


    @RequestMapping(value = "latestArticle")
    @ResponseBody
    public List<Article> listLatestArticle(@RequestParam Long userId) {
        //TODO 缓存
        List<Article> list = baseService.listLatestArticleByUserId(userId);
        return list.subList(0, list.size() > 10 ? 10 : list.size());
    }


}
