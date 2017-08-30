package com.nick.chef.bean;

import java.util.List;

/**
 * Created by FG on 2016/10/27.
 */
/*
* url升级之后，不用这么麻烦地来获得详情的url了，只要传入tabId和item编号articleId即可获得url
* 这个bean类用不着了*/
public class HomePageBean {

    /**
     * code : 200
     * hasmore : true
     * page_total : 4
     * datas : {"article_list":[{"article_title":"奉上5部好吃的电影","article_id":"973","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/24/b8c0a3d0509078d2","article_publish_time":"1477292696","article_abstract":"你看电影我看吃啥","article_origin":"悦食中国","top":"5","video_length":""},{"article_title":"世界上最酷的九家美食博物馆","article_id":"977","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/21/5d250da50f9f126f","article_publish_time":"1477283321","article_abstract":"对食物深深的执着","article_origin":"悦食中国","top":"9","video_length":""},{"article_title":"必须吃好一点，不然没办法过冬","article_id":"978","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/21/3c204f572b7171fb","article_publish_time":"1477283283","article_abstract":"吃到半酣喝到微醉","article_origin":"悦食中国","top":"0","video_length":""},{"article_title":"西安学府周边小吃指南","article_id":"960","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/18/042e1429f98a1c77","article_publish_time":"1476946992","article_abstract":"吃是大学最快乐的事","article_origin":"悦食中国","top":"11","video_length":""},{"article_title":"没有零食，怎么能叫人生！","article_id":"958","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/17/578b7ea4c74bd87d","article_publish_time":"1476787984","article_abstract":"16个超治愈的故事","article_origin":"一晚日杂","top":"16","video_length":""},{"article_title":"切一个昭和风的蛋包饭","article_id":"950","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/12/920892bec3b5266c","article_publish_time":"1476692269","article_abstract":"漂亮蛋包饭的秘诀","article_origin":"完全预约制","top":"4","video_length":""},{"article_title":"明媚的柚色晃晃悠悠地暖了一个秋","article_id":"954","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/13/ccd744586a5e7f81","article_publish_time":"1476692245","article_abstract":"秋日之选","article_origin":"悦食中国","top":"1","video_length":""},{"article_title":"我要为老北京点心打抱不平","article_id":"953","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/13/ae9deee5677f3998","article_publish_time":"1476676832","article_abstract":"吃块点心迎秋冬","article_origin":"悦食中国","top":"9","video_length":""},{"article_title":"日本网红睡眠水Sleep Water","article_id":"951","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/12/1bae1bacf015a122","article_publish_time":"1476320804","article_abstract":"土產研究所","article_origin":"悦食中国","top":"1","video_length":""},{"article_title":"12本你值得拥有的料理书","article_id":"934","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/09/427a3e46fa8bec11","article_publish_time":"1476074173","article_abstract":"吃的好看","article_origin":"悦食中国","top":"31","video_length":""}],"qa":"Q&A"}
     */

    private int code;
    private boolean hasmore;
    private int page_total;
    /**
     * article_list : [{"article_title":"奉上5部好吃的电影","article_id":"973","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/24/b8c0a3d0509078d2","article_publish_time":"1477292696","article_abstract":"你看电影我看吃啥","article_origin":"悦食中国","top":"5","video_length":""},{"article_title":"世界上最酷的九家美食博物馆","article_id":"977","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/21/5d250da50f9f126f","article_publish_time":"1477283321","article_abstract":"对食物深深的执着","article_origin":"悦食中国","top":"9","video_length":""},{"article_title":"必须吃好一点，不然没办法过冬","article_id":"978","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/21/3c204f572b7171fb","article_publish_time":"1477283283","article_abstract":"吃到半酣喝到微醉","article_origin":"悦食中国","top":"0","video_length":""},{"article_title":"西安学府周边小吃指南","article_id":"960","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/18/042e1429f98a1c77","article_publish_time":"1476946992","article_abstract":"吃是大学最快乐的事","article_origin":"悦食中国","top":"11","video_length":""},{"article_title":"没有零食，怎么能叫人生！","article_id":"958","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/17/578b7ea4c74bd87d","article_publish_time":"1476787984","article_abstract":"16个超治愈的故事","article_origin":"一晚日杂","top":"16","video_length":""},{"article_title":"切一个昭和风的蛋包饭","article_id":"950","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/12/920892bec3b5266c","article_publish_time":"1476692269","article_abstract":"漂亮蛋包饭的秘诀","article_origin":"完全预约制","top":"4","video_length":""},{"article_title":"明媚的柚色晃晃悠悠地暖了一个秋","article_id":"954","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/13/ccd744586a5e7f81","article_publish_time":"1476692245","article_abstract":"秋日之选","article_origin":"悦食中国","top":"1","video_length":""},{"article_title":"我要为老北京点心打抱不平","article_id":"953","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/13/ae9deee5677f3998","article_publish_time":"1476676832","article_abstract":"吃块点心迎秋冬","article_origin":"悦食中国","top":"9","video_length":""},{"article_title":"日本网红睡眠水Sleep Water","article_id":"951","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/12/1bae1bacf015a122","article_publish_time":"1476320804","article_abstract":"土產研究所","article_origin":"悦食中国","top":"1","video_length":""},{"article_title":"12本你值得拥有的料理书","article_id":"934","article_video":"","article_image":"http://yueshi.b0.upaiyun.com/cms/2016/10/09/427a3e46fa8bec11","article_publish_time":"1476074173","article_abstract":"吃的好看","article_origin":"悦食中国","top":"31","video_length":""}]
     * qa : Q&A
     */

    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String qa;
        /**
         * article_title : 奉上5部好吃的电影
         * article_id : 973
         * article_video :
         * article_image : http://yueshi.b0.upaiyun.com/cms/2016/10/24/b8c0a3d0509078d2
         * article_publish_time : 1477292696
         * article_abstract : 你看电影我看吃啥
         * article_origin : 悦食中国
         * top : 5
         * video_length :
         */

        private List<ArticleListBean> article_list;

        public String getQa() {
            return qa;
        }

        public void setQa(String qa) {
            this.qa = qa;
        }

        public List<ArticleListBean> getArticle_list() {
            return article_list;
        }

        public void setArticle_list(List<ArticleListBean> article_list) {
            this.article_list = article_list;
        }

        public static class ArticleListBean {
            private String article_title;   //
            private String article_id;  //
            private String article_video;
            private String article_image;  //
            private String article_publish_time;
            private String article_abstract;  //
            private String article_origin;  //
            private String top;       //
            private String video_length;

            public String getArticle_title() {
                return article_title;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
            }

            public String getArticle_id() {
                return article_id;
            }

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            public String getArticle_video() {
                return article_video;
            }

            public void setArticle_video(String article_video) {
                this.article_video = article_video;
            }

            public String getArticle_image() {
                return article_image;
            }

            public void setArticle_image(String article_image) {
                this.article_image = article_image;
            }

            public String getArticle_publish_time() {
                return article_publish_time;
            }

            public void setArticle_publish_time(String article_publish_time) {
                this.article_publish_time = article_publish_time;
            }

            public String getArticle_abstract() {
                return article_abstract;
            }

            public void setArticle_abstract(String article_abstract) {
                this.article_abstract = article_abstract;
            }

            public String getArticle_origin() {
                return article_origin;
            }

            public void setArticle_origin(String article_origin) {
                this.article_origin = article_origin;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getVideo_length() {
                return video_length;
            }

            public void setVideo_length(String video_length) {
                this.video_length = video_length;
            }
        }
    }
}
