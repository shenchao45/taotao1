package com.shenchao.taotao.controller;

import com.shenchao.taotao.pojo.TbItem;
import com.shenchao.taotao.service.ItemService;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem itemById = itemService.getItemById(itemId);
		System.out.println(itemById);
		return itemById;
	}
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult list(Integer page,Integer rows){
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	@RequestMapping(value = "/item/save",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult save(TbItem item,String desc,String itemParams) {
		return itemService.createItem(item,desc,itemParams);
	}

}
