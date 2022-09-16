package com.zzwei.server.controller;


import com.zzwei.server.pojo.*;
import com.zzwei.server.service.*;
import com.zzwei.server.utils.RespBean;
import com.zzwei.server.utils.RespPageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    @Autowired
    private INationService nationService;
    @Autowired
    private IPoliticsStatusService politicsStatusService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IPositionService positionService;
    @Autowired
    private IJoblevelService joblevelService;

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
                                          @RequestParam(defaultValue = "15") Integer size,
                                          Employee employee,
                                          LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "新增员工")
    @PostMapping("/addEmployee")
    public RespBean addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    /**
     * 员工更新
     *
     * @param employee
     * @return
     */
    @ApiOperation(value = "更新员工")
    @PutMapping("/updateEmp")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateById(employee)) {
            return RespBean.success("员工更新成功");
        } else {
            return RespBean.error("员工更新失败");
        }
    }

    /**
     * 删除员工
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除员工")
    @DeleteMapping("/delEmp/{id}")
    public RespBean delEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return RespBean.success("员工删除成功");
        } else {
            return RespBean.error("员工删除失败");
        }
    }

    /**
     * 查询所有民族
     *
     * @return
     */
    @ApiOperation(value = "查询所有民族")
    @GetMapping("/nation")
    public List<Nation> getAllNation() {
        return nationService.list();
    }

    /**
     * 查询所有政治面貌
     *
     * @return
     */
    @ApiOperation(value = "查询所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.list();
    }

    /**
     * 查询所有部门
     *
     * @return
     */
    @ApiOperation(value = "查询所有部门")
    @GetMapping("/department")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartments();
    }

    /**
     * 查询所有职位
     *
     * @return
     */
    @ApiOperation(value = "查询所有职位")
    @GetMapping("/position")
    public List<Position> getAllPosition() {
        return positionService.list();
    }

    /**
     * 查询所有职称
     *
     * @return
     */
    @ApiOperation(value = "查询所有职称")
    @GetMapping("/joblevel")
    public List<Joblevel> getAllJoblevel() {
        return joblevelService.list();
    }

    /**
     * 查询最大工号
     *
     * @return
     */
    @ApiOperation(value = "查询最大工号")
    @GetMapping("/maxWorkID")
    public RespBean getMaxWorkID() {
        return employeeService.getMaxWorkID();
    }

}
