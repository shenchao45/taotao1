package com.taotao.common.pojo;

import java.util.List;

/**
 * Created by shenchao on 2016/12/10.
 */
public class EasyUIDataGridResult {
    private long total;
    private List<?> rows;

    public EasyUIDataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIDataGridResult() {

    }
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
