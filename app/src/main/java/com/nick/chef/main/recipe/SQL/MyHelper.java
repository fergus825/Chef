package com.nick.chef.main.recipe.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * ***********************************************************
 * author: alex
 * time: 16/10/18 下午4:13
 * name: 数据库的帮助类
 * desc:
 * step:
 * *************************************************************
 */
public class MyHelper extends OrmLiteSqliteOpenHelper {

    public static final String DB_NAME = "i cooking";

    private static final int DB_VERSION = 1;


    public MyHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //初始数据库,创建化表
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //创建了一张Person表
            TableUtils.createTable(connectionSource, Recipe.class);
            //如果有多张表,可以多次调用createTable()方法去创建
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }


    private static MyHelper mInstance = null;

    /**
     * 单例化 帮助类
     */
    public static synchronized MyHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MyHelper.class) {
                if (mInstance == null)
                    mInstance = new MyHelper(context);
            }
        }
        return mInstance;
    }

    public Dao<Recipe, Long> getPersonDao() throws SQLException {
        //获得某个表的DAO类。。。 DAO也叫作数据库中的业务类,这个类里边会封装CURD的方法
        //第一个: 表示你要操作哪张表 对应的 object对象
        //第二个: 当前表对应的Object对象中id对应的类型
        return getDao(Recipe.class);
    }
//    /**
//     * 通过类来获得指定的Dao
//     */
//    public synchronized Dao getDao(Class clazz) throws SQLException {
//        Dao dao = null;
//        String className = clazz.getSimpleName();
//        if (daos.containsKey(className)) {
//            dao = super.getDao(clazz);
//            daos.put(className, dao);
//        }
//        return dao;
//    }

}
