package com.shenchao.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenchao.common.utils.IDUtils;
import com.shenchao.taotao.mapper.TbItemDescMapper;
import com.shenchao.taotao.mapper.TbItemMapper;
import com.shenchao.taotao.mapper.TbItemParamItemMapper;
import com.shenchao.taotao.pojo.*;
import com.shenchao.taotao.pojo.TbItemExample.Criteria;
import com.shenchao.taotao.service.ItemService;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(Long id) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		System.out.println(id);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> tbItems = itemMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo(tbItems);
		return new EasyUIDataGridResult(pageInfo.getTotal(), pageInfo.getList());
	}

	@Override
	public TaotaoResult createItem(TbItem item,String desc,String itemParams) {
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		itemDescMapper.insert(tbItemDesc);

		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParams);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}
}
