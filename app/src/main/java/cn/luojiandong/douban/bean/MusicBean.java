package cn.luojiandong.douban.bean;

import java.util.List;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.bean
 * 文件名: MusicBean
 * 创建者: JackLuo
 * 创建时间: 2016-10-24 21:07
 * 描述：
 */
public class MusicBean<T> {
    private int count;
    private int start;
    private int total;
    private List<T> musics;

    public List<T> getMusics() {
        return musics;
    }

    public void setMusics(List<T> musics) {
        this.musics = musics;
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
