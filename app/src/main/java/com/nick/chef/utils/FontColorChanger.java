package com.nick.chef.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.WindowDecorActionBar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.nick.chef.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by FG on 2017/3/3.
 * 设置状态栏字体图标为深色，需要MIUIV6以上 *
 @param window 需要设置的窗口 *
 @param dark 是否把状态栏字体及图标颜色设置为深色 *
 @return boolean 成功执行返回true
 */
public class FontColorChanger {
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try{
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
          /*  Class clazz = window.getClass();
            try { int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if(dark){
                    extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
                }
                else{
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }*/
                result=true;
            }catch (Exception e){ }
        }
        return result;
    }

    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
               // window.setStatusBarColor(activity.getResources().getColor(colorResId));//现在可用，6.0及以上直接报错
                window.setStatusBarColor(ContextCompat.getColor(activity,colorResId));
                //getResources().getColor替代方法Color.parseColor("#FFFFFF")或ContextCompat.getColor(context, R.color.color_name)
              //  window.setStatusBarColor(colorResId);灰紫色
             //  window.setStatusBarColor(Color.parseColor(colorResId));//传入“#efff”还是黄色

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
