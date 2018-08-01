package item.com.demo.bean;

import java.util.List;

/**
 * Created by wuzongjie on 2018/8/1
 */
public class MovieDetailBean {

    /**
     * reviews_count : 1768
     * wish_count : 119848
     * douban_site :
     * year : 2018
     * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.jpg"}
     * alt : https://movie.douban.com/subject/27605698/
     * id : 27605698
     * mobile_url : https://movie.douban.com/subject/27605698/mobile
     * title : 西虹市首富
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/27605698
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/27605698/cinema/
     * episodes_count : null
     * countries : ["中国大陆"]
     * genres : ["喜剧"]
     * collect_count : 185925
     * casts : [{"alt":"https://movie.douban.com/celebrity/1325700/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg"},"name":"沈腾","id":"1325700"},{"alt":"https://movie.douban.com/celebrity/1341903/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.jpg"},"name":"宋芸桦","id":"1341903"},{"alt":"https://movie.douban.com/celebrity/1322777/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1413261818.41.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1413261818.41.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1413261818.41.jpg"},"name":"张一鸣","id":"1322777"},{"alt":"https://movie.douban.com/celebrity/1275270/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11764.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11764.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11764.jpg"},"name":"张晨光","id":"1275270"}]
     * current_season : null
     * original_title : 西虹市首富
     * summary : 《西虹市首富》的故事发生在《夏洛特烦恼》中的“特烦恼”之城“西虹市”。混迹于丙级业余足球队的守门员王多鱼（沈腾饰演），因比赛失利被开除离队。正处于人生最低谷的他接受了神秘台湾财团“一个月花光十亿资金”的挑战。本以为快乐生活就此开始，王多鱼却第一次感到“花钱特烦恼”！想要人生反转走上巅峰，真的没有那么简单。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1350410/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg"},"name":"闫非","id":"1350410"},{"alt":"https://movie.douban.com/celebrity/1350409/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437031053.5.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437031053.5.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437031053.5.jpg"},"name":"彭大魔","id":"1350409"}]
     * comments_count : 64942
     * ratings_count : 145952
     * aka : ["资本接班人","Hello Mr. Billionaire"]
     */

    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<DirectorsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;
    /**
     * 获取其它名称字符串
     *
     * @return 类型字符串 A/B/C..
     */
    public String getAkaString() {
        return sListToString(getAka());
    }

    /**
     * 格式化list为字符串
     *
     * @param list 类型list
     * @return 字符串 A/B/C..
     */
    private String sListToString(List<String> list) {
        String str = "";
        if (list.size() == 0)
            return str;
        for (int i = 0; i < list.size(); i++) {
            str = str + list.get(i);
            if (i < list.size() - 1)
                str += " / ";
        }
        return str;
    }
    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<DirectorsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<DirectorsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class ImagesBean {
        /**
         * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.jpg
         * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.jpg
         * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1325700/
         * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg"}
         * name : 沈腾
         * id : 1325700
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg
             * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg
             * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356510694.28.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1350410/
         * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg"}
         * name : 闫非
         * id : 1350410
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg
             * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg
             * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
