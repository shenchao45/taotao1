package com.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenchao.taotao.mapper.TbItemMapper;
import com.shenchao.taotao.pojo.TbItem;
import com.shenchao.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by shenchao on 2016/12/10.
 */
public class TestPageHelper {

    @Test
    public void testPageHelper() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper bean = applicationContext.getBean(TbItemMapper.class);
        PageHelper.startPage(1, 100);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> list = bean.selectByExample(tbItemExample);
        PageInfo<TbItem> info = new PageInfo<>(list);
        long total = info.getTotal();
        int pages = info.getPages();
        int pageNum = info.getPageNum();
        int pageSize = info.getPageSize();
        System.out.println("total:"+total+",pages:"+pages+",pageNum:"+pageNum+",pageSize:"+pageSize);
        for (TbItem tbItem : info.getList()) {
            System.out.println(tbItem);
        }
    }
}
