package com.nick.chef.bean;

import java.util.List;

/**
 * *********************************************************************
 * Author: Nick
 * Created on 2016/10/28  9:47
 * desc:
 * <p>
 * *********************************************************************
 */

public class VideoGridBean {

    private ObjBean obj;
    /**
     * obj : {"recommend_list":[{"img_video":"http://site.meishij.net/article/video_img/20160620/20160620173215_913.png","name":"微在涨姿势","id":"1","play_amount":"共29个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160516163203146338752389891.jpg","name":"日食记","id":"2","play_amount":"共18个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160517124438146346027897823.jpg","name":"味蕾时光","id":"10","play_amount":"共18个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160930/20160930153525_892.png","name":"饭贰","id":"79","play_amount":"共1个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160607143107146528106785870.jpg","name":"美食杰网","id":"32","play_amount":"共47个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160620/20160620175436_534.png","name":"厨娘物语","id":"5","play_amount":"共24个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160620/20160620160935_432.png","name":"i烘焙","id":"6","play_amount":"共10个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160620/20160620181519_862.png","name":"美食台","id":"7","play_amount":"共28个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160819/20160819095635_356.png","name":"咕噜减脂餐","id":"19","play_amount":"共14个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160725/20160725103957_612.jpg","name":"Freesiaa Made","id":"44","play_amount":"共12个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160620/20160620173605_584.png","name":"日日煮","id":"4","play_amount":"共22个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160517120018146345761831105.jpg","name":"小羽私厨","id":"8","play_amount":"共22个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160517120708146345802885431.jpg","name":"巧厨烘焙","id":"9","play_amount":"共5个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160621/20160621123312_482.png","name":"企鹅吃喝指南","id":"11","play_amount":"共17个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160523130030146397963028347.jpg","name":"饭合Foodlink","id":"13","play_amount":"共22个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160518143809146355348921496.jpg","name":"迷迭香Rosenmary","id":"14","play_amount":"共27个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160626/20160626131635_572.jpg","name":"开心食塘","id":"15","play_amount":"共19个视频"},{"img_video":"http://site.meishij.net/article/video_img/20160518144246146355376658937.jpg","name":"微体社区","id":"16","play_amount":"共28个视频"}]}
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
        /**
         * img_video : http://site.meishij.net/article/video_img/20160620/20160620173215_913.png
         * name : 微在涨姿势
         * id : 1
         * play_amount : 共29个视频
         */

        private List<RecommendListBean> recommend_list;

        public List<RecommendListBean> getRecommend_list() {
            return recommend_list;
        }

        public void setRecommend_list(List<RecommendListBean> recommend_list) {
            this.recommend_list = recommend_list;
        }

        public static class RecommendListBean {
            private String img_video;
            private String name;
            private String id;
            private String play_amount;

            public String getImg_video() {
                return img_video;
            }

            public void setImg_video(String img_video) {
                this.img_video = img_video;
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

            public String getPlay_amount() {
                return play_amount;
            }

            public void setPlay_amount(String play_amount) {
                this.play_amount = play_amount;
            }
        }
    }
}
