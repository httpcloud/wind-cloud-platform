package com.windacc.wind.mybatis.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * <p>分页对象   </p>
 *
 * @author windacc
 * @date 2021/5/14 22:27
 */
@Slf4j
@Data
@ApiModel(description = "分页数据")
@NoArgsConstructor
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = -8106721765821446377L;
    /**
     * 结果集
     */
    @ApiModelProperty(value = "结果集")
    private List<T> records;
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", required = true)
    private long total;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数", required = true)
    private int pages;
    /**
     * 每页的数量
     */
    @ApiModelProperty(value = "每页的数量", required = true)
    private int pageSize;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageNum;
    /**
     * 当前页的数量
     */
    @ApiModelProperty(value = "当前页的数量", required = true)
    private int size;

    public PageData(List<T> list) {
        this.records = list;
        if (list instanceof Page) {
            this.total = (int) ((Page<T>) list).getTotal();
        } else {
            this.total = list.size();
        }

        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum() > 0 ? page.getPageNum() : 1;
            this.pageSize = page.getPageSize() > 0 ? page.getPageSize() : page.size();
            this.pages = page.getPages() > 0 ? page.getPages() : 1;
            this.size = page.size();
        } else {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = this.pageSize > 0 ? 1 : 0;
            this.size = list.size();
        }
    }

    public PageData(List<T> list, int total, int pageNum, int pageSize) {
        this.records = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;

        this.size = list.size();
        int cnt = total / pageSize;

        this.pages = total > pageSize * cnt ? cnt + 1 : cnt;
    }

    public PageData(IPage<T> page) {
        this.records = page.getRecords();
        this.pageNum = (int) page.getCurrent();
        this.pageSize = (int) page.getSize();
        this.pages = (int) page.getPages();
        this.size = (int) page.getSize();
    }

    public static <T> PageData<T> of(List<T> list) {
        return new PageData<>(list);
    }

    public static <T> PageData<T> of(IPage<T> page) {
        return new PageData<>(page);
    }

    public static <T> PageData<T> of(List<T> list, int total, int pageNum, int pageSize) {
        return new PageData<>(list, total, pageNum, pageSize);
    }

}
