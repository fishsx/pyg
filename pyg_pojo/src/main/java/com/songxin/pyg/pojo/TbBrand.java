package com.songxin.pyg.pojo;

import java.io.Serializable;

/**
 * 品牌的实体类
 * @author fishsx
 * @date 2018/12/3 下午5:22
 */
public class TbBrand implements Serializable {
    private static final long serialVersionUID = 8461488696541403468L;

    private int id;
    private String name;
    private String firstChar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }
}
