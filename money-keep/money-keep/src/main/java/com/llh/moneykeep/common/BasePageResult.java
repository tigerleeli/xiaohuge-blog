package com.llh.moneykeep.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回包装类
 */
@Data
@AllArgsConstructor
public class BasePageResult<T> implements Serializable {

    private static final long serialVersionUID = -4955025980523015968L;

    /**
     * 每页数量
     */
    private int pageSize = 1;

    /**
     * 页码
     */
    private int pageNum = 10;

    /**
     * 记录列表
     */
    private List<T> recordList = new ArrayList<>();

    /**
     * 总数量
     */
    private int totalNum = 0;

    public BasePageResult() {
    }

    public <E> BasePageResult(IPage<E> page, List<T> recordList) {
        this.totalNum = (int) page.getTotal();
        this.pageNum = (int) page.getCurrent();
        this.pageSize = (int) page.getSize();
        this.recordList = recordList;
    }

    public BasePageResult(IPage<T> page) {
        this.totalNum = (int) page.getTotal();
        this.pageNum = (int) page.getCurrent();
        this.pageSize = (int) page.getSize();
        this.recordList = page.getRecords();
    }

}
