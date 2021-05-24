package com.windacc.wind.user.service.impl;

import com.windacc.wind.user.entity.Department;
import com.windacc.wind.user.mapper.DepartmentMapper;
import com.windacc.wind.user.service.IDepartmentService;
import com.windacc.wind.mybatis.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门信息表 服务实现类
 * </p>
 *
 * @author codeGen
 * @since 2021-05-23
 */
@Service
public class DepartmentServiceImpl extends SuperServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
