package com.windacc.wind.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/14 22:27
 */
public interface SuperMapper<T> extends BaseMapper<T> {

    /**
     * <p>插入或更新数据 duplicate key update 方式， 仅适用于mysql</p>
     *
     * @param entity 实体
     * @return Integer 插入返回1，更新返回2
     */
    Integer upsert(T entity);

    /**
     * <p> 插入如果中已经存在相同的记录，则忽略当前新数据, 仅适用于mysql </p>
     *
     * @param entity 实体对象
     * @return 更改的条数
     */
    Integer insertIgnore(T entity);

    /**
     * <p>批量插入 foreach 方式，仅适用mysql</p>
     *
     * @param entityList  实体链表
     * @return Integer 插入成功数量
     */
    Integer insertBatchSomeColumn(List<T> entityList);


}
