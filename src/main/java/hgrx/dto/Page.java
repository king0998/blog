package hgrx.dto;

/**
 * 分页对象
 */
public class Page {
    private int totalNumber;
    private int currentPage;
    private int totalPage;
    private int pageNumber;
    private int dbIndex;
    private int dbNumber;
    private static int PAGE_NUMBER = 10;

    /**
     * @param totalNumber totalNumber
     * @param currentPage currentPage
     */
    public Page(int totalNumber, int currentPage) {
        this.totalNumber = totalNumber;
        this.currentPage = currentPage;
        pageNumber = PAGE_NUMBER;
        count();
    }

    /**
     * @param totalNumber 总条数
     * @param currentPage 当前页码
     * @param pageNumber  每页多少条
     */
    public Page(int totalNumber, int currentPage, int pageNumber) {
        this.totalNumber = totalNumber;
        this.currentPage = currentPage;
        this.pageNumber = pageNumber;
        count();
    }

    public void count() {
        int totalPageTemp = totalNumber / pageNumber;
        int plus = (totalNumber % pageNumber) == 0 ? 0 : 1;
        totalPageTemp = totalPageTemp + plus;
        if (totalPageTemp <= 0) {
            totalPageTemp = 1;
        }
        this.totalPage = totalPageTemp;
        if (totalPage < currentPage) {
            currentPage = this.totalPage;
        }
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }
        dbIndex = (currentPage - 1) * pageNumber;
        dbNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalNumber=" + totalNumber +
                "， currentPage=" + currentPage +
                "， totalPage=" + totalPage +
                "， pageNumber=" + pageNumber +
                "， dbIndex=" + dbIndex +
                "， dbNumber=" + dbNumber +
                '}';
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        this.count();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(int dbNumber) {
        this.dbNumber = dbNumber;
    }
}