package com.taotao1.rest.service.impl;

import com.shenchao.taotao.mapper.TbItemCatMapper;
import com.shenchao.taotao.pojo.TbItemCat;
import com.shenchao.taotao.pojo.TbItemCatExample;
import com.taotao1.rest.pojo.CatNode;
import com.taotao1.rest.pojo.ItemCatResult;
import com.taotao1.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenchao on 2016/12/21.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public ItemCatResult getItemCatList() {
        List result = getItemCatList(0l);
        ItemCatResult result1 = new ItemCatResult();
        result1.setData(result);
        return result1;
    }

    private List getItemCatList(Long parentId) {
        int count = 0;
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> result = itemCatMapper.selectByExample(example);
        List list = new ArrayList();
        for (TbItemCat cat : result) {
            if (cat.getIsParent()) {
                count++;
                if (count>14) {
                    break;
                }
                CatNode node = new CatNode();
                node.setUrl("/products/" + cat.getId() + ".html");
                if (cat.getParentId() == 0) {
                    node.setName("<a href='/products/" + cat.getId() + ".html'>" + cat.getName() + "</a>");
                } else {
                    node.setName(cat.getName());
                }
                node.setItems(getItemCatList(cat.getId()));
                list.add(node);
            } else {
                String item = "/products/" + cat.getId() + ".html|" + cat.getName();
                list.add(item);
            }
        }
        return list;
    }
}
