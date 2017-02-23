package com.taotao1.rest.controller;

import com.shenchao.common.utils.JsonUtils;
import com.taotao1.rest.pojo.ItemCatResult;
import com.taotao1.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenchao on 2016/12/22.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        ItemCatResult result = itemCatService.getItemCatList();
        if (StringUtils.isEmpty(callback)) {//字符串为空直接返回
            return JsonUtils.objectToJson(result);
        }else{//字符串为空，支持jsonp
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }
//    @RequestMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
//    @ResponseBody
//    public String getItemCatList(String callback) {
//        ItemCatResult result = itemCatService.getItemCatList();
//        if (StringUtils.isEmpty(callback)) {//字符串为空直接返回
//            return JsonUtils.objectToJson(result);
//        }else{//字符串为空，支持jsonp
//            return callback + "("+JsonUtils.objectToJson(result)+")";
//        }
//    }
}
