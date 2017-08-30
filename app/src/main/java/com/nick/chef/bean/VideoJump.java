package com.nick.chef.bean;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/31  16:28
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoJump {

    /**
     * type : 4
     * class_name : MSJWebAdvViewController
     * property : {"urlString":"http://api.meishi.cc/v5/ykPlayer.php?sid=3106&udid=&user_id=0&vid=XMTc0MzQyOTQxMg==","goodsSource":""}
     */

    private String type;
    private String class_name;
    /**
     * urlString : http://api.meishi.cc/v5/ykPlayer.php?sid=3106&udid=&user_id=0&vid=XMTc0MzQyOTQxMg==
     * goodsSource :
     */

    private PropertyBean property;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public PropertyBean getProperty() {
        return property;
    }

    public void setProperty(PropertyBean property) {
        this.property = property;
    }

    public static class PropertyBean {
        private String urlString;
        private String goodsSource;

        public String getUrlString() {
            return urlString;
        }

        public void setUrlString(String urlString) {
            this.urlString = urlString;
        }

        public String getGoodsSource() {
            return goodsSource;
        }

        public void setGoodsSource(String goodsSource) {
            this.goodsSource = goodsSource;
        }
    }
}
