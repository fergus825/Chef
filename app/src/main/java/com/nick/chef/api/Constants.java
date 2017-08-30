package com.nick.chef.api;

/**
 * Created by FG on 2016/10/27.
 */

public class Constants {
    /**************************************主页的URL*******************************************/
    //首页 specialtype分别是2、3、4、5,对应榜单、知识、人文、地图四个tab
   // public static String HOME_BASEURL="http://interface.yueshichina.com/?act=app&op=special_programa&special_type=%d&key=&token=749a036dc06ae8b3a120848995a9f306&client=android&curpage=%d";
    public static String HOME_BASEURL="http://interface.yueshichina.com/?act=app&op=special_programa&v=2.1.2&key=&token=749a036dc06ae8b3a120848995a9f306&curpage=%d&client=android&req_time=1488465879815&special_type=%d&channel=xiaomi";
    //首页每个item的详情页  article_id就是每个item独有的属性
//    public static String HOME_ITEM_DETAIL="http://interface.yueshichina.com/?act=cms_index&op=cms_app_index&article_id=%d&key=&token=749a036dc06ae8b3a120848995a9f306&type_id=2&client=android";
    public static String HOME_ITEM_DETAIL="http://interface.yueshichina.com/?act=cms_index&op=article_content&type_id=%d&article_id=%d";
    /**************************************菜谱界面的URL****************************************/
    //菜谱加载界面的Url，刷新加载只需改变cid的值即可
    public static String RECIPE_BASEURL=" http://apis.juhe.cn/cook/index?key=d20b0be005e8977ee1322b92d48f06ad&cid=%d";
    //菜谱每个item的详情页的Url id就是每个菜品item特有的属性
    public static String RECIPE_DETAIL="http://apis.juhe.cn/cook/queryid?key=d20b0be005e8977ee1322b92d48f06ad&id=%d";
    //菜谱界面的搜索功能的Url menu就是要搜索的关键字
    public static String RECIPE_SEARCH="http://apis.juhe.cn/cook/query?key=d20b0be005e8977ee1322b92d48f06ad&menu=%s&rn=10&pn=1";
    /*************************************主页的方法*************************************************/
    //传入参数想要的tab编号和页号，获得完整的url
    public static String getHomeUrl(int tabId,int pageId){
        return String.format(HOME_BASEURL,pageId,tabId);
    }
    //传入想要获得详情的item的编号articleId,获得详情的Url
    public static String getHomeItemDetail(int typeId ,int articleId){
        return String.format(HOME_ITEM_DETAIL,typeId,articleId);
    }

    /********************************** 菜谱界面的方法*******************************************/
    //获取刷新页面后的新的Url，即改变cid后的Url,用在刷新加载的时候
    public static String getNewRecipeUrl(int pageId){
        return String.format(RECIPE_BASEURL,pageId);
    }
    //传入想要获得详情的item的编号id,获得详情的Url
    public static String getRecipeDetail(int id){
        return String.format(RECIPE_DETAIL,id);
    }
    //传入搜索的关键字，即menu的值
    public static String getRecipeSearchRst(String keyWord){
        return String.format(RECIPE_SEARCH,keyWord);
    }

    /********************************** 菜谱界面的方法*******************************************/



    /**************************************视频界面的URL****************************************/
    //http://api105.meishi.cc/v5/video_recipe.php?format=json/?lon=&source=android&sort=1&lat=&page=1&format=json&
    public static final String VIDEO_BASE_URL = "http://api105.meishi.cc/v5/video_recipe.php?format=json";
    // public void postAsnycData(Map<String, String> map, String url, final OnReusltListener listener)



    //视频频道
    //http://api.meishi.cc/v5/video_detail.php?format=json/?lon=&source=android&lat=&id=2&page=1&format=json&
    public static final String VIDEO_CHANNEL_URL = "http://api.meishi.cc/v5/video_detail.php?format=json";




}
