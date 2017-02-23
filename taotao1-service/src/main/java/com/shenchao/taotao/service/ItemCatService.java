package com.shenchao.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by shenchao on 2016/12/10.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
