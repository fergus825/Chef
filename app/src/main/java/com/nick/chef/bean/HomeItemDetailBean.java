package com.nick.chef.bean;

/**
 * Created by FG on 2016/10/27.
 */

public class HomeItemDetailBean {

    /**
     * code : 200
     * datas : {"article_video":"","article_id":"977","article_title":"世界上最酷的九家美食博物馆","article_image":"http://yueshi.b0.upaiyun.com//cms/2016/10/21/5d250da50f9f126f","article_abstract":"对食物深深的执着","type_id":"2","article_content":"http://interface.yueshichina.com/?act=cms_index&op=article_content&type_id=2&article_id=977","if_favorites":1}
     */

    private int code;
    /**
     * article_video :
     * article_id : 977
     * article_title : 世界上最酷的九家美食博物馆
     * article_image : http://yueshi.b0.upaiyun.com//cms/2016/10/21/5d250da50f9f126f
     * article_abstract : 对食物深深的执着
     * type_id : 2
     * article_content : http://interface.yueshichina.com/?act=cms_index&op=article_content&type_id=2&article_id=977
     * if_favorites : 1
     */

    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private String article_video;
        private String article_id;
        private String article_title;
        private String article_image;
        private String article_abstract;
        private String type_id;
        private String article_content;//点击展示条目跳转到的详情界面的链接
        private int if_favorites;

        public String getArticle_video() {
            return article_video;
        }

        public void setArticle_video(String article_video) {
            this.article_video = article_video;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
        }

        public String getArticle_image() {
            return article_image;
        }

        public void setArticle_image(String article_image) {
            this.article_image = article_image;
        }

        public String getArticle_abstract() {
            return article_abstract;
        }

        public void setArticle_abstract(String article_abstract) {
            this.article_abstract = article_abstract;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getArticle_content() {
            return article_content;
        }

        public void setArticle_content(String article_content) {
            this.article_content = article_content;
        }

        public int getIf_favorites() {
            return if_favorites;
        }

        public void setIf_favorites(int if_favorites) {
            this.if_favorites = if_favorites;
        }
    }
}
