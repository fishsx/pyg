package com.songxin.pyg.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果数据【视图对象】
 * @author fishsx
 * @date 2018/12/4 下午4:28
 */
public class PageResultVO implements Serializable {
    private static final long serialVersionUID = 877231169952161262L;
    private Long total;
    private List data;

    public PageResultVO(Long total, List data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
