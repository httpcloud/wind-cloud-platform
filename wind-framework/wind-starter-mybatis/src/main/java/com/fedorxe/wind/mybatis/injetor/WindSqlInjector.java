package com.fedorxe.wind.mybatis.injetor;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.fedorxe.wind.mybatis.method.InsertIgnoreMethod;
import com.fedorxe.wind.mybatis.method.UpsertMethod;

import java.util.List;

/**
 * <p>Description desc   </p>
 *
 * @author fedorxe
 * @date 2021/5/14 22:27
 */
public class WindSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new InsertIgnoreMethod());
        methodList.add(new UpsertMethod());
        methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));
        return methodList;
    }
}
