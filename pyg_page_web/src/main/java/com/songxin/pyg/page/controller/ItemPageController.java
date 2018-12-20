package com.songxin.pyg.page.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songxin.pyg.page.service.ItemPageService;
import com.songxin.pyg.pojo.TbItem;
import com.songxin.pyg.vo.combvo.GoodsVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品详情页的控制器
 * @author fishsx
 * @date 2018/12/19 下午4:16
 */
@RestController
@RequestMapping("page")
public class ItemPageController {

    @Reference
    private ItemPageService itemPageService;

    @Autowired
    private FreeMarkerConfig freemarkerConfig;
    /**
     * 生成商品详情页html
     * @param goodsId
     * @return java.lang.String
     * @author fishsx
     * @date 2018/12/19 下午4:18
     */
    @RequestMapping("generateItemHtml")
    public String generateItemHtml(Long goodsId) {
        try {
            //使用freemarker生成html模板
            Configuration config = freemarkerConfig.getConfiguration();
            Template template = config.getTemplate("item.ftl");

            GoodsVO goodsVO = itemPageService.findOne(goodsId);
            List<TbItem> itemList = goodsVO.getItemList();
            Writer out = null;
            for (TbItem item : itemList) {
                Map map = new HashMap<String,Object>();
                map.put("goodsVO", goodsVO);
                map.put("item", item);
                map.put("categoryNameList", goodsVO.getCategoryNameList());
                //生成请求的SPU下所有的SKU的html页面
                out = new FileWriter(new File("/Users/sx/Desktop/ftl_generate_files/" + item.getId() + ".html"));
                template.process(map, out);
            }
            if (out != null) {
                out.close();
            }
            return "generate html success";
        } catch (Exception e) {
            e.printStackTrace();
            return "generate html fail";
        }
    }
}
