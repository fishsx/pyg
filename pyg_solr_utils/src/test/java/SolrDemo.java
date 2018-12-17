import com.alibaba.fastjson.JSON;
import com.songxin.pyg.mapper.TbItemMapper;
import com.songxin.pyg.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试solr的Demo案例
 * @author fishsx
 * @date 2018/12/17 下午4:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-solr.xml","classpath*:spring/applicationContext-dao.xml"})
public class SolrDemo {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private TbItemMapper itemMapper;


    @Test
    public void add() {
        TbItem tbItem = new TbItem();
        tbItem.setId(1L);
        tbItem.setTitle("一加6T");
        tbItem.setCategory("手机");
        tbItem.setBrand("一加");
        tbItem.setGoodsId(1L);
        tbItem.setPrice(new BigDecimal(3000.00));
        solrTemplate.saveBean(tbItem);
        solrTemplate.commit();
    }

    @Test
    public void getById() {
        TbItem item = solrTemplate.getById(1L, TbItem.class);
        System.out.println(item.getTitle());
    }

    @Test
    public void delete() {
        solrTemplate.deleteById("1");
        solrTemplate.commit();
    }

    @Test
    public void deltetAll() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

    @Test
    public void queryByPage() {

        Query query = new SimpleQuery("*:*");
        query.setOffset(0); //起始索引(默认0)
        query.setRows(20); //每页条数(默认10)
        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        long totalElements = page.getTotalElements();
        List<TbItem> itemList = page.getContent();
        showList(itemList);
    }

    private void showList(List<TbItem> itemList) {
        for (TbItem item : itemList) {
            System.out.println(item.getTitle() + " " + item.getPrice());
        }
    }

    @Test
    public void queryByCriteria() {
        Query query = new SimpleQuery("*:*");
        //查询title中包含2和5的记录
        Criteria criteria = new Criteria("item_title").contains("2");
        criteria.and("item_title").contains("5");
        query.addCriteria(criteria);
        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        long totalElements = page.getTotalElements();
        List<TbItem> itemList = page.getContent();
        showList(itemList);
    }

    @Test
    public void importData() {
        List<TbItem> srcList = itemMapper.findAllGrounding();
        for (TbItem item : srcList) {
            Map<String,String> map = JSON.parseObject(item.getSpec(), Map.class);
            item.setSpecMap(map);
        }
        solrTemplate.saveBeans(srcList);
        solrTemplate.commit();

    }
}
