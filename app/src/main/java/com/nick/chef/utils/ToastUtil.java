package com.nick.chef.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/12/14.
 */
public class ToastUtil {

    private static String oldMsg;
    protected static Toast toast = null;
    private static long startTime = 0;
    private static long nextTime = 0;

    public static void showToast(Context context, String s) {
        if (toast == null) {
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.show();
            startTime = System.currentTimeMillis();
        } else {
            nextTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (nextTime - startTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        startTime = nextTime;
    }

    //方法的的重载?
    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }
}
