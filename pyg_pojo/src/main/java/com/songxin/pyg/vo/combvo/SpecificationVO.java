package com.songxin.pyg.vo.combvo;

import com.songxin.pyg.pojo.TbSpecification;
import com.songxin.pyg.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * 规格&规格选项的组合【视图对象】
 * @author fishsx
 * @date 2018/12/5 下午7:37
 */
public class SpecificationVO implements Serializable {
    private static final long serialVersionUID = -8171075704827680165L;

    /** 规格 */
    private TbSpecification specification;
    /** 规格选项 */
    private List<TbSpecificationOption> specificationOptions;

    public TbSpecification getSpecification() {
        return specification;
    }

    public SpecificationVO() {
    }

    public SpecificationVO(TbSpecification specification, List<TbSpecificationOption> specificationOptions) {
        this.specification = specification;
        this.specificationOptions = specificationOptions;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOptions() {
        return specificationOptions;
    }

    public void setSpecificationOptions(List<TbSpecificationOption> specificationOptions) {
        this.specificationOptions = specificationOptions;
    }
}
