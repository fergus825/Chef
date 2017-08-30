package com.nick.chef.main.recipe.SQL;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ***********************************************************
 * author: alex
 * time: 16/9/30 下午2:30
 * name:
 * desc:
 * step:
 * *************************************************************
 */
public class RecipeDao {
    private Dao<Recipe, Integer> recipeDao;
    private MyHelper myHelper;



    /**
     * 构造方法
     * 获得数据库帮助类实例，通过传入Class对象得到相应的Dao
     * @param context
     */
    public RecipeDao(Context context) {
        try {
            myHelper = MyHelper.getInstance(context);
            recipeDao = myHelper.getDao(Recipe.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 添加一条记录
     * @param recipe
     */
    public void add(Recipe recipe) {
        try {
            recipeDao.create(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一条记录
     * @param recipe
     */
    public void delete(Recipe recipe) {
        try {
            recipeDao.delete(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 更新一条记录
     * @param recipe
     */
    public void update(Recipe recipe) {
        try {
            recipeDao.update(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询一条记录
     * @param id
     * @return
     */
    public Recipe queryForId(int id) {
       Recipe recipe= null;
        try {
            recipe = recipeDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }


    /**
     * 查询所有记录
     * @return
     */
    public List<Recipe> queryForAll() {
        List<Recipe> recipes = new ArrayList<Recipe>();
        try {
            recipes = recipeDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    /**
     * 查询是否存在
     * @return
     */
    public Boolean isExist(Recipe recipe) {
         boolean b = false;
        List<Recipe> recipeList = null;
        try {
            recipeList = recipeDao.queryForAll();
            for (int i = 0; i < recipeList.size(); i++) {
                String title = recipeList.get(i).getTitle();
//                b = title.equals(recipe.getTitle());
                if (title.equals(recipe.getTitle())){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

}
