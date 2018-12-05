package com.songxin.pyg.pojo;

import java.io.Serializable;

public class TbSpecification implements Serializable {
    private static final long serialVersionUID = -1816091119158196256L;
    private Long id;

    private String specName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }
}