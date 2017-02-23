package com.shenchao.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * Created by shenchao on 2016/12/23.
 */
public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(Long parentId);

    TaotaoResult insertCategory(Long parentId, String name);
}
