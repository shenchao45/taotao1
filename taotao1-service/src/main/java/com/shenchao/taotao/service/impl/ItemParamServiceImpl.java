package com.shenchao.taotao.service.impl;

import com.shenchao.taotao.mapper.TbItemParamMapper;
import com.shenchao.taotao.pojo.TbItemParam;
import com.shenchao.taotao.pojo.TbItemParamExample;
import com.shenchao.taotao.service.ItemParamService;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by shenchao on 2016/12/13.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{
    @Autowired
    private TbItemParamMapper itemParamMapper;

    /**
     * 根据cid查询实例
     * @param cid
     * @return
     */
    @Override
    public TaotaoResult getItemParamByCid(Long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData) {
        TbItemParam param = new TbItemParam();
        param.setItemCatId(cid);
        param.setCreated(new Date());
        param.setUpdated(new Date());
        param.setParamData(paramData);
        itemParamMapper.insert(param);
        return TaotaoResult.ok();
    }

    /**
     * 根据商品id查询规格参数
     * @param itemId
     * @return
     */
    @Override
    public String getItemParamHtml(Long itemId) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemId);
        List<TbItemParam> tbItemParams = itemParamMapper.selectByExampleWithBLOBs(example);
        if (tbItemParams != null && tbItemParams.size() > 0) {
            TbItemParam param = tbItemParams.get(0);

        }
        return "";
    }

}
