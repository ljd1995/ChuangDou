package cn.luojiandong.douban.bean;

import java.util.List;

/**
 * Created by 95250 on 2016/10/18.
 */

public class MovieBean<T> {

    private int count;
    private int start;
    private int total;
    private String title;
    private List<T> subjects;

    public List<T> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<T> subjects) {
        this.subjects = subjects;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
