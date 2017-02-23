package com.shenchao.taotao.service;

import com.shenchao.taotao.pojo.TbItem;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
	TbItem getItemById(Long id);

	EasyUIDataGridResult getItemList(int page, int rows);

	TaotaoResult createItem(TbItem item,String desc,String itemParams);
}
