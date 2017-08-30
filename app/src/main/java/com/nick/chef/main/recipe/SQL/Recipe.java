package com.nick.chef.main.recipe.SQL;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * ***********************************************************
 * author: alex
 * time: 16/10/18 下午4:19
 * name:
 * desc:
 * step:
 * 主要有两个注解:
 * 1. DatabaseTable : 用于表名的设置。
 * 2. DatabaseField : 用于设置表中的字段
 * 注意,必须要有无参数的构造方法。。。
 * *************************************************************
 */
@DatabaseTable(tableName = "person")
public class Recipe {
    //列名:为_id , 设置为表的id,并且自增长
    @DatabaseField(columnName = "_id", generatedId = true)
    private Long _id;

    @DatabaseField(columnName = "title", dataType = DataType.STRING)
    private String title;

//    @DatabaseField(columnName = "tags", dataType = DataType.STRING)
//    private String tags;

    @DatabaseField(columnName = "position", dataType = DataType.STRING)
    private String position;
    @DatabaseField(columnName = "albums", dataType = DataType.STRING)
    private String albums;

    //注意,必须要有无参数的构造方法。。。
    public Recipe() {
    }

    public Recipe(String title, String tags, String position, String albums) {
        this.title = title;
//        this.tags = tags;
        this.position = position;
        this.albums = albums;

    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getTags() {
//        return tags;
//    }
//
//    public void setTags(String tags) {
//        this.tags = tags;
//    }


    @Override
    public String toString() {
        return "Recipe{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", position='" + position + '\'' +
                ", albums='" + albums + '\'' +
                '}';
    }
}
