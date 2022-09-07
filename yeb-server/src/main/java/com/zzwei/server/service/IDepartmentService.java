package com.zzwei.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzwei.server.pojo.Department;
import com.zzwei.server.utils.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 查询所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    RespBean addDepartment(Department department);

    /**
     * 删除部门
     * @param did
     * @return
     */
    RespBean delDepartment(Integer did);
}
