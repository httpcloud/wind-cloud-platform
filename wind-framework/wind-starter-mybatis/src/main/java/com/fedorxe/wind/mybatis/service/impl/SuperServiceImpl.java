package com.fedorxe.wind.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fedorxe.wind.mybatis.mapper.SuperMapper;
import com.fedorxe.wind.mybatis.service.ISuperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/14 22:27
 */
public class SuperServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements ISuperService<T> {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    protected M superMapper;

    @Override
    public Integer insertBatchSomeColumn(List<T> entityList, int batchSize) {
        int ret = 0;
        int batchLastIndex = batchSize;
        for (int index = 0; index < entityList.size(); ) {
            if (batchLastIndex >= entityList.size()) {
                batchLastIndex = entityList.size();
                ret += superMapper.insertBatchSomeColumn(entityList.subList(index, batchLastIndex));
                break;
            } else {
                ret += superMapper.insertBatchSomeColumn(entityList.subList(index, batchLastIndex));
                index = batchLastIndex;
                batchLastIndex = index + batchSize;
            }
        }
        return ret;
    }

}
