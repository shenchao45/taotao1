package com.shenchao.taotao.portal.service.impl;

import com.shenchao.common.utils.HttpClientUtil;
import com.shenchao.common.utils.JsonUtils;
import com.shenchao.taotao.pojo.TbItem;
import com.shenchao.taotao.pojo.TbItemDesc;
import com.shenchao.taotao.pojo.TbItemParamItem;
import com.shenchao.taotao.portal.pojo.PortalItem;
import com.shenchao.taotao.portal.service.ItemService;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by shenchao on 2017/1/2.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public TbItem getItemById(Long itemId) {
        String json = HttpClientUtil.doGet("http://localhost:8081/rest/item/base/" + itemId);
        TaotaoResult result = TaotaoResult.formatToPojo(json, PortalItem.class);
        return (TbItem) result.getData();

    }

    @Override
    public String getItemDescById(Long itemId) {
        //根据商品id调用taotao-rest的服务获得数据
        //http://localhost:8081/rest/item/desc/144766336139977
        String json = HttpClientUtil.doGet("http://localhost:8081/rest/item/desc/" + itemId);
        //转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
        //取商品描述
        TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
        String desc = itemDesc.getItemDesc();
        return desc;

    }

    @Override
    public String  getItemParamById(Long itemId) {
        // 根据商品id获得对应的规格参数
        String json = HttpClientUtil.doGet("http://localhost:8081/rest/item/param/" + itemId);
        // 转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
        // 取规格参数
        TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
        String paramJson = itemParamItem.getParamData();
        // 把规格参数的json数据转换成java对象
        // 转换成java对象
        List<Map> mapList = JsonUtils.jsonToList(paramJson, Map.class);
        // 遍历list生成html
        StringBuffer sb = new StringBuffer();

        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("	<tbody>\n");
        for (Map map : mapList) {
            sb.append("		<tr>\n");
            sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
            sb.append("		</tr>\n");
            // 取规格项
            List<Map> mapList2 = (List<Map>) map.get("params");
            for (Map map2 : mapList2) {
                sb.append("		<tr>\n");
                sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
                sb.append("			<td>" + map2.get("v") + "</td>\n");
                sb.append("		</tr>\n");
            }
        }
        sb.append("	</tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
}
