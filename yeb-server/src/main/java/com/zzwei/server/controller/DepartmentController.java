package com.zzwei.server.controller;


import com.zzwei.server.pojo.Department;
import com.zzwei.server.service.IDepartmentService;
import com.zzwei.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Api(tags = "部门相关接口")
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    /**
     * 查询所有部门
     *
     * @return
     */
    @ApiOperation(value = "查询所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }


    /**
     * 删除部门
     * @param did
     * @return
     */
    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{did}")
    public RespBean addDepartment(@PathVariable Integer did) {
        return departmentService.delDepartment(did);
    }
}
