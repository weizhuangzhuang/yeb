package com.zzwei.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.zzwei.server.pojo.*;
import com.zzwei.server.service.*;
import com.zzwei.server.utils.RespBean;
import com.zzwei.server.utils.RespPageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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

    /**
     * 员工导出
     */
    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmp(HttpServletResponse response) {
        List<Employee> emp = employeeService.getEmployee(null);
        ExportParams exportParams = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Employee.class, emp);
        ServletOutputStream out = null;
        try {
            //流形式
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导入员工数据
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "导入员工数据")
    @PostMapping(value = "/import")
    public RespBean importEmp(MultipartFile file) {
        ImportParams params = new ImportParams();
        //去掉标题行
        params.setTitleRows(1);
        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatusList = politicsStatusService.list();
        List<Position> positionList = positionService.list();
        List<Joblevel> joblevelList = joblevelService.list();
        List<Department> departmentList = departmentService.list();

        try {
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            list.forEach(employee -> {
                //根据民族名查询民族id
                employee.setNationId(nationList.get(nationList.indexOf(new Nation(employee.getNation().getName()))).getId());
                //根据政治面貌名查询政治面貌id
                employee.setPoliticId(politicsStatusList.get(politicsStatusList.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                //根据职位名称查询职位id
                employee.setPosId(positionList.get(positionList.indexOf(new Position(employee.getPosition().getName()))).getId());
                //根据职称名查询职称id
                employee.setJobLevelId(joblevelList.get(joblevelList.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                //根据部门名查询部门id
                employee.setDepartmentId(departmentList.get(departmentList.indexOf(new Department(employee.getDepartment().getName()))).getId());
                //插入数据

            });
            if (employeeService.saveBatch(list)) {
                return RespBean.success("导入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败");
    }

}
