package com.shenchao.taotao.portal.service.impl;

import com.shenchao.taotao.pojo.TbItem;
import com.shenchao.taotao.portal.service.ItemService;
import com.shenchao.taotao.portal.service.StaticPageService;
import com.taotao.common.pojo.TaotaoResult;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenchao on 2017/1/2.
 */
@Service
public class StaticPageServiceImpl implements StaticPageService{
    @Autowired
    private ItemService itemService;

    @Value("${STATIC_PATH}")
    private String STATIC_PATH;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Override
    public TaotaoResult getItemHtml(Long itemId) throws IOException, TemplateException {
        TbItem item = itemService.getItemById(itemId);
        String itemDesc = itemService.getItemDescById(itemId);
        String param = itemService.getItemParamById(itemId);
        Configuration configuration = freeMarkerConfig.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");
        Map root = new HashMap<>();
        System.out.println(item);
        root.put("item", item);
        root.put("itemDesc", itemDesc);
        root.put("itemParam", param);
        Writer writer = new FileWriter(STATIC_PATH+itemId+".html");
        template.process(root,writer);
        writer.close();
        return TaotaoResult.ok();
    }
}
