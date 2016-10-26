package cn.luojiandong.douban.bean;

import java.util.List;

/**
 * 项目名：Douban
 * 包名：cn.luojiandong.douban.bean
 * 文件名: MusicsInfo
 * 创建者: JackLuo
 * 创建时间: 2016-10-24 21:09
 * 描述：
 */
public class MusicsInfo {

    private String alt;
    private String alt_title;
    private AttrsBean attrs;
    private String id;
    private String image;
    private String mobile_link;
    private RatingBean rating;
    private String title;
    private List<AuthorBean> author;
    private List<TagsBean> tags;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public AttrsBean getAttrs() {
        return attrs;
    }

    public void setAttrs(AttrsBean attrs) {
        this.attrs = attrs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile_link() {
        return mobile_link;
    }

    public void setMobile_link(String mobile_link) {
        this.mobile_link = mobile_link;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorBean> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorBean> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class AttrsBean {
        private List<String> discs;
        private List<String> media;
        private List<String> pubdate;
        private List<String> publisher;
        private List<String> singer;
        private List<String> title;
        private List<String> tracks;
        private List<String> version;

        public List<String> getDiscs() {
            return discs;
        }

        public void setDiscs(List<String> discs) {
            this.discs = discs;
        }

        public List<String> getMedia() {
            return media;
        }

        public void setMedia(List<String> media) {
            this.media = media;
        }

        public List<String> getPubdate() {
            return pubdate;
        }

        public void setPubdate(List<String> pubdate) {
            this.pubdate = pubdate;
        }

        public List<String> getPublisher() {
            return publisher;
        }

        public void setPublisher(List<String> publisher) {
            this.publisher = publisher;
        }

        public List<String> getSinger() {
            return singer;
        }

        public void setSinger(List<String> singer) {
            this.singer = singer;
        }

        public List<String> getTitle() {
            return title;
        }

        public void setTitle(List<String> title) {
            this.title = title;
        }

        public List<String> getTracks() {
            return tracks;
        }

        public void setTracks(List<String> tracks) {
            this.tracks = tracks;
        }

        public List<String> getVersion() {
            return version;
        }

        public void setVersion(List<String> version) {
            this.version = version;
        }
    }

    public static class RatingBean {
        private double average;
        private int max;
        private int min;
        private int numRaters;

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }
    }

    public static class AuthorBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TagsBean {
        private int count;
        private String name;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
