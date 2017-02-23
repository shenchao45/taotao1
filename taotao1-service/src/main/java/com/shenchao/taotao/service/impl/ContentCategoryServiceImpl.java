package com.shenchao.taotao.service.impl;

import com.shenchao.taotao.mapper.TbContentCategoryMapper;
import com.shenchao.taotao.pojo.TbContentCategory;
import com.shenchao.taotao.pojo.TbContentCategoryExample;
import com.shenchao.taotao.service.ContentCategoryService;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shenchao on 2016/12/23.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbContentCategory contentCategory:list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(contentCategory.getId());
            node.setState(contentCategory.getIsParent()?"closed":"open");
            node.setText(contentCategory.getName());
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public TaotaoResult insertCategory(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setStatus(1);
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        contentCategory.setUpdated(new Date());
        contentCategory.setCreated(new Date());
        contentCategoryMapper.insert(contentCategory);
        //判断父节点的isParent属性
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        Long id = contentCategory.getId();
        return TaotaoResult.ok(id);
    }
}
