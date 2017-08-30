package com.nick.chef.bean;

import java.util.List;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/28  9:50
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoChannelBean {

    /**
     * img : http://site.meishij.net/article/video_img/20160516163203146338752389891.jpg
     * describtion : 温暖治愈你的心和胃。【微信公众号：日食记（rishi-ji）， 微博：@日食记】
     * name : 日食记
     * video_list : [{"describtion":"酸辣粉x棒冰","play_times":"17558次播放 / 6'34\"","img_video":"http://site.meishij.net/article/video_img/20160829/20160829162520_898.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=3003&udid=&user_id=0&vid=XMTY5OTYwMDg5Ng==","show_type":"1"},{"describtion":"海鲜饼x泡菜","play_times":"10904次播放 / 5'43\"","img_video":"http://site.meishij.net/article/video_img/20160823/20160823101436_273.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=2982&udid=&user_id=0&vid=XMTY5MTE2Mjc4OA==","show_type":"1"},{"describtion":"冒菜","play_times":"30029次播放 / 5'35\"","img_video":"http://site.meishij.net/article/video_img/20160722/20160722175722_753.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=2895&udid=&user_id=0&vid=XMTY1MjY2MTAxMg==","show_type":"1"},{"describtion":"肉夹馍","play_times":"20176次播放 / 5'33\"","img_video":"http://site.meishij.net/article/video_img/20160712/20160712101643_731.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=2861&udid=&user_id=0&vid=XMTYzOTc0ODk4NA==","show_type":"1"},{"describtion":"泡菜冷汤面×烤肉","play_times":"133196次播放 / 6'32\"","img_video":"http://site.meishij.net/article/video_img/20160623/20160623110520_236.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=227&udid=&user_id=0&vid=XMTU2NjExOTM4MA==","show_type":"1"},{"describtion":"又快又香的拌面","play_times":"8203次播放 / 4'16\"","img_video":"http://site.meishij.net/article/video_img/20160622/20160622112021_475.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=2783&udid=&user_id=0&vid=XMTYxNTg1MDY4NA==","show_type":"1"},{"describtion":"第二十八回 电饭煲排骨x溏心卤蛋","play_times":"47860次播放 / 3'44\"","img_video":"http://site.meishij.net/article/video_img/20160621/20160621133823_277.png","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=226&udid=&user_id=0&vid=XMTU3Mjc0ODc4OA==","show_type":"1"},{"describtion":"新年旧味之【南乳猪手】","play_times":"757079次播放 / 3'09\"","img_video":"http://r4.ykimg.com/0541040856B762C86A0A430458F2B26E","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=248&udid=&user_id=0&vid=XMTQ2NjM3OTM5Mg==","show_type":"1"},{"describtion":"第十四回 白火锅X红火锅","play_times":"553919次播放 / 7'02\"","img_video":"http://r1.ykimg.com/05410108568E16A26A0A410458B1CA13","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=249&udid=&user_id=0&vid=XMTQzNzIyNDcxNg==","show_type":"1"},{"describtion":"第十三回 酸奶拿破仑","play_times":"322792次播放 / 7'59\"","img_video":"http://r4.ykimg.com/0541010156716CE36A0A440B09F099DB","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=250&udid=&user_id=0&vid=XMTQxNTQyMzk4MA==","show_type":"1"}]
     */

    private ObjBean obj;
    /**
     * obj : {"img":"http://site.meishij.net/article/video_img/20160516163203146338752389891.jpg","describtion":"温暖治愈你的心和胃。【微信公众号：日食记（rishi-ji）， 微博：@日食记】","name":"日食记","video_list":[{"describtion":"酸辣粉x棒冰","play_times":"17558次播放 / 6'34\"","img_video":"http://site.meishij.net/article/video_img/20160829/20160829162520_898.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=3003&udid=&user_id=0&vid=XMTY5OTYwMDg5Ng==","show_type":"1"},{"describtion":"海鲜饼x泡菜","play_times":"10904次播放 / 5'43\"","img_video":"http://site.meishij.net/article/video_img/20160823/20160823101436_273.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=2982&udid=&user_id=0&vid=XMTY5MTE2Mjc4OA==","show_type":"1"},{"describtion":"冒菜","play_times":"30029次播放 / 5'35\"","img_video":"http://site.meishij.net/article/video_img/20160722/20160722175722_753.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=2895&udid=&user_id=0&vid=XMTY1MjY2MTAxMg==","show_type":"1"},{"describtion":"肉夹馍","play_times":"20176次播放 / 5'33\"","img_video":"http://site.meishij.net/article/video_img/20160712/20160712101643_731.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=2861&udid=&user_id=0&vid=XMTYzOTc0ODk4NA==","show_type":"1"},{"describtion":"泡菜冷汤面×烤肉","play_times":"133196次播放 / 6'32\"","img_video":"http://site.meishij.net/article/video_img/20160623/20160623110520_236.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=227&udid=&user_id=0&vid=XMTU2NjExOTM4MA==","show_type":"1"},{"describtion":"又快又香的拌面","play_times":"8203次播放 / 4'16\"","img_video":"http://site.meishij.net/article/video_img/20160622/20160622112021_475.jpg","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=2783&udid=&user_id=0&vid=XMTYxNTg1MDY4NA==","show_type":"1"},{"describtion":"第二十八回 电饭煲排骨x溏心卤蛋","play_times":"47860次播放 / 3'44\"","img_video":"http://site.meishij.net/article/video_img/20160621/20160621133823_277.png","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=226&udid=&user_id=0&vid=XMTU3Mjc0ODc4OA==","show_type":"1"},{"describtion":"新年旧味之【南乳猪手】","play_times":"757079次播放 / 3'09\"","img_video":"http://r4.ykimg.com/0541040856B762C86A0A430458F2B26E","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=248&udid=&user_id=0&vid=XMTQ2NjM3OTM5Mg==","show_type":"1"},{"describtion":"第十四回 白火锅X红火锅","play_times":"553919次播放 / 7'02\"","img_video":"http://r1.ykimg.com/05410108568E16A26A0A410458B1CA13","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=249&udid=&user_id=0&vid=XMTQzNzIyNDcxNg==","show_type":"1"},{"describtion":"第十三回 酸奶拿破仑","play_times":"322792次播放 / 7'59\"","img_video":"http://r4.ykimg.com/0541010156716CE36A0A440B09F099DB","vurl":"http://api.meishi.cc/v5/ykPlayer.php?sid=250&udid=&user_id=0&vid=XMTQxNTQyMzk4MA==","show_type":"1"}]}
     * code : 1
     * msg : 成功
     */

    private String code;
    private String msg;

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ObjBean {
        private String img;
        private String describtion;
        private String name;
        /**
         * describtion : 酸辣粉x棒冰
         * play_times : 17558次播放 / 6'34"
         * img_video : http://site.meishij.net/article/video_img/20160829/20160829162520_898.jpg
         * vurl : http://api.meishi.cc/v5/ykPlayer.php?sid=3003&udid=&user_id=0&vid=XMTY5OTYwMDg5Ng==
         * show_type : 1
         */

        private List<VideoListBean> video_list;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDescribtion() {
            return describtion;
        }

        public void setDescribtion(String describtion) {
            this.describtion = describtion;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<VideoListBean> getVideo_list() {
            return video_list;
        }

        public void setVideo_list(List<VideoListBean> video_list) {
            this.video_list = video_list;
        }

        public static class VideoListBean {
            private String describtion;
            private String play_times;
            private String img_video;
            private String vurl;
            private String show_type;

            public String getDescribtion() {
                return describtion;
            }

            public void setDescribtion(String describtion) {
                this.describtion = describtion;
            }

            public String getPlay_times() {
                return play_times;
            }

            public void setPlay_times(String play_times) {
                this.play_times = play_times;
            }

            public String getImg_video() {
                return img_video;
            }

            public void setImg_video(String img_video) {
                this.img_video = img_video;
            }

            public String getVurl() {
                return vurl;
            }

            public void setVurl(String vurl) {
                this.vurl = vurl;
            }

            public String getShow_type() {
                return show_type;
            }

            public void setShow_type(String show_type) {
                this.show_type = show_type;
            }
        }
    }
}
