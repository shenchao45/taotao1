package com.shenchao.taotao.portal.pojo;

import com.shenchao.taotao.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by shenchao on 2017/1/2.
 */
public class PortalItem extends TbItem {
    public String[] getImages(){
        if (StringUtils.isNotBlank(getImage())) {
            return getImage().split(",");
        }
        return null;
    }

}
