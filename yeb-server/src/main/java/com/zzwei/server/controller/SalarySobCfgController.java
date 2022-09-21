package com.zzwei.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zzwei.server.pojo.Employee;
import com.zzwei.server.pojo.Salary;
import com.zzwei.server.service.IEmployeeService;
import com.zzwei.server.service.ISalaryService;
import com.zzwei.server.utils.RespBean;
import com.zzwei.server.utils.RespPageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "员工工资套账相关接口")
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 查询所有工资套账
     *
     * @return
     */
    @ApiOperation(value = "查询所有工资套账")
    @GetMapping("/salaries")
    public List<Salary> getAllSalary() {
        return salaryService.list();
    }

    /**
     * 分页查询所有员工工资套账
     *
     * @param currentPage
     * @param size
     * @param employee
     * @return
     */
    @ApiOperation(value = "查询所有员工工资套账")
    @GetMapping("/")
    public RespPageBean getEmpSalaryByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                           @RequestParam(defaultValue = "15") Integer size,
                                           Employee employee) {
        return employeeService.getEmpSalaryByPage(currentPage, size, employee);
    }

    /**
     * 修改员工工资套账
     *
     * @param sid
     * @param eid
     * @return
     */
    @ApiOperation(value = "修改员工工资套账")
    @PutMapping("/")
    public RespBean updateEmpSalary(Integer sid, Integer eid) {
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId", sid).eq("id", eid))) {
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }
}
