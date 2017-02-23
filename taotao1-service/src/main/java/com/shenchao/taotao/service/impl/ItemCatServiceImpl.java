package com.shenchao.taotao.service.impl;

import com.shenchao.taotao.mapper.TbItemCatMapper;
import com.shenchao.taotao.pojo.TbItemCat;
import com.shenchao.taotao.pojo.TbItemCatExample;
import com.shenchao.taotao.service.ItemCatService;
import com.taotao.common.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenchao on 2016/12/10.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
//        转换成树形列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbItemCat cat : tbItemCats) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }
}
