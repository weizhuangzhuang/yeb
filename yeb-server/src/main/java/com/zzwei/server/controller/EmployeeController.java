package com.zzwei.server.controller;


import com.zzwei.server.pojo.Employee;
import com.zzwei.server.service.IEmployeeService;
import com.zzwei.server.utils.RespPageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Api(tags = "员工相关接口")
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @ApiOperation(value = "获取所有员工(分页)")
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          Employee employee,
                                          LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }

}
