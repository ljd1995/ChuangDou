package cn.luojiandong.douban.bean;

import java.util.List;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.bean
 * 文件名: BookBean
 * 创建者: JackLuo
 * 创建时间: 2016-10-23 11:18
 * 描述：
 */
public class BookBean<T> {

    private int count;
    private int start;
    private int total;
    private List<T> books;

    public List<T> getBooks() {
        return books;
    }

    public void setBooks(List<T> books) {
        this.books = books;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
