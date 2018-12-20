package com.songxin.pyg.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.songxin.pyg.pojo.TbItem;
import com.songxin.pyg.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品搜索业务实现类
 *
 * @author fishsx
 * @date 2018/12/18 下午3:24
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;


    /**
     * 商品搜索
     *
     * @param searchMap
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @author fishsx
     * @date 2018/12/18 下午3:23
     */
    @Override
    public Map<String, Object> search(Map searchMap) {
        Map<String, Object> resultMap = new HashMap<>(100);
        Criteria criteria = null;
        HighlightPage<TbItem> page;
        //获取搜索的关键字
        String keywords = (String) (searchMap == null ? "" : searchMap.get("keywords"));
        if (searchMap == null || "".equals(keywords)) {
            //没有输入关键字,默认查询所有
            criteria = new Criteria().expression("*:*");
            //设置FQ过滤器查询并返回查询结果
            page = setFilterQuery(searchMap, criteria);
        } else {
            //a.关键字全匹配搜索
            criteria = new Criteria("item_keywords").is(keywords);
            //设置FQ过滤器查询并返回查询结果
            page = setFilterQuery(searchMap, criteria);
        }

        //封装结果集
        long totalElements = page.getTotalElements();
        System.out.println(totalElements);
        int totalPages = page.getTotalPages();
        resultMap.put("hitList", page.getContent());
        resultMap.put("totalPages", page.getTotalPages());
        resultMap.put("pageNumber", searchMap.get("pageNumber"));
        return resultMap;
    }

    /**
     * 设置查询过滤
     *
     * @param searchMap
     * @param criteria
     * @return org.springframework.data.solr.core.query.result.HighlightPage<com.songxin.pyg.pojo.TbItem>
     * @author fishsx
     * @date 2018/12/18 下午10:04
     */
    private HighlightPage<TbItem> setFilterQuery(Map searchMap, Criteria criteria) {
        //高亮搜索关键字显示
        HighlightQuery query = new SimpleHighlightQuery(criteria);
        //根据关键字查询索引库
        //获取品牌
        String brand = (String) searchMap.get("brand");
        //获取分类
        String category = (String) searchMap.get("category");
        //获取价格
        String price = (String) searchMap.get("price");
        //获取规格map
        Map<String, Object> specMap = (Map<String, Object>) searchMap.get("spec");
        //获取排序方式
        String sort = (String) searchMap.get("sort");
        //获取排序字段
        String sortField = (String) searchMap.get("sortField");
        //获取当前页
        Integer pageNumber = (Integer) searchMap.get("pageNumber");
        //获取每页条数
        Integer pageSize = (Integer) searchMap.get("pageSize");


        //b.品牌全匹配搜索
        if (!"".equals(brand)) {
            FilterQuery fq = new SimpleFilterQuery(new Criteria("item_brand").is(brand));
            query.addFilterQuery(fq);
        }
        //c.分类全匹配搜索
        if (!"".equals(category)) {
            FilterQuery fq = new SimpleFilterQuery(new Criteria("item_category").is(category));
            query.addFilterQuery(fq);
        }
        //d.价格区间搜索
        if (!"".equals(price)) {
            String[] priceArr = price.split("-");
            if (!"0".equals(priceArr[0])) {
                FilterQuery fq = new SimpleFilterQuery(
                        new Criteria("item_price").greaterThanEqual(priceArr[0]));
                query.addFilterQuery(fq);
            }
            if (!"*".equals(priceArr[1])) {
                FilterQuery fq = new SimpleFilterQuery(
                        new Criteria("item_price").lessThanEqual(priceArr[1]));
                query.addFilterQuery(fq);
            }
        }

        //e.规格选项搜索
        if (!specMap.isEmpty()) {
            for (String key : specMap.keySet()) {
                Object value = specMap.get(key);
                FilterQuery fq = new SimpleFilterQuery(
                        new Criteria("item_spec_" + key).is(value));
                query.addFilterQuery(fq);
            }
        }
        //f.排序搜索
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(sortField)) {

            //ASC排序
            if (Sort.Direction.ASC.toString().equals(sort)) {
                Sort sortParam = new Sort(Sort.Direction.ASC, "item_" + sortField);
                query.addSort(sortParam);
            }
            //DESC排序
            if (Sort.Direction.DESC.toString().equals(sort)) {
                Sort sortParam = new Sort(Sort.Direction.DESC, "item_" + sortField);
                query.addSort(sortParam);
            }
        }
        //g.分页
        pageSize = (pageSize == null ? 60 : pageSize);
        pageNumber = (pageNumber == null ? 1 : pageNumber);
        //设置分页参数
        query.setOffset((pageNumber - 1) * pageSize);
        query.setRows(pageSize);


        //设置高亮效果
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.setSimplePrefix("<em style='color:red'>");
        highlightOptions.setSimplePostfix("</em>");
        query.setHighlightOptions(highlightOptions);
        query.addCriteria(criteria);
        HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query, TbItem.class);
        //使用高亮的title替换原来的title
        //example:  title 替换为 <em style='color:red'>title</em>
        List<TbItem> hitList = page.getContent();
        for (TbItem item : hitList) {
            List<HighlightEntry.Highlight> highlights = page.getHighlights(item);
            for (HighlightEntry.Highlight highlight : highlights) {
                if ("item_title".equals(highlight.getField().getName())) {
                    item.setTitle(highlight.getSnipplets().get(0));
                }
            }
        }
        return page;
    }


}
