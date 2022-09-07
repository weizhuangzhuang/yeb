package com.zzwei.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzwei.server.mapper.DepartmentMapper;
import com.zzwei.server.pojo.Department;
import com.zzwei.server.service.IDepartmentService;
import com.zzwei.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询所有部门
     *
     * @return
     */
    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDep(department);
        if (1 == department.getResult()) {
            return RespBean.success("部门新增成功", department);
        } else {
            return RespBean.error("部门新增失败");
        }
    }

    /**
     * 删除部门
     *
     * @param did
     * @return
     */
    @Override
    public RespBean delDepartment(Integer did) {
        Department dep = new Department();
        dep.setId(did);
        departmentMapper.deleteDep(dep);
        if (1 == dep.getResult()) {
            return RespBean.success("部门删除成功");
        }
        if (-2 == dep.getResult()) {
            return RespBean.error("该部门下存在子部门,无法删除");
        }
        if (-1 == dep.getResult()) {
            return RespBean.error("该部门下存在员工数据,无法删除");
        }
        return RespBean.error("部门删除失败");
    }
}
