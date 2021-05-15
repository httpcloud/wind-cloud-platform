package com.windacc.wind.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/14 22:27
 */
public interface ISuperService<T> extends IService<T> {

    /**
     * <p> 批量插入 foreach 方式，仅适用mysql</p>
     *
     * @param entityList 实体
     * @return Integer 插入成功数量
     */
    default Integer insertBatchSomeColumn(List<T> entityList) {
        return insertBatchSomeColumn(entityList, 20000);
    }

    /**
     * <p> 批量插入 foreach 方式，仅适用mysql</p>
     *
     * @param entityList 实体
     * @param batchSize  每次foreach插入数量
     * @return Integer 插入成功数量
     */
    Integer insertBatchSomeColumn(List<T> entityList, int batchSize);

}
