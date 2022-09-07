package com.zzwei.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzwei.server.pojo.Department;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 查询所有部门
     *
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    void addDep(Department department);

    /**
     * 删除部门
     *
     * @param department
     * @return
     */
    void deleteDep(Department department);
}
