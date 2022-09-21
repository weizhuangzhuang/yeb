package com.zzwei.server.controller;


import com.zzwei.server.pojo.Salary;
import com.zzwei.server.service.ISalaryService;
import com.zzwei.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Api(tags = "工资账套相关接口")
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;

    /**
     * 查询所有工资账套
     *
     * @return
     */
    @ApiOperation(value = "查询所有工资账套")
    @GetMapping("/")
    public List<Salary> queryAll() {
        return salaryService.list();
    }

    /**
     * 新增工资账套
     *
     * @param salary
     * @return
     */
    @ApiOperation(value = "新增工资账套")
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)) {
            return RespBean.success("工资账套添加成功");
        } else {
            return RespBean.error("工资账套添加失败");
        }
    }

    /**
     * 修改工资账套
     *
     * @param salary
     * @return
     */
    @ApiOperation(value = "修改工资账套")
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary) {
        if (salaryService.updateById(salary)) {
            return RespBean.success("工资账套修改成功");
        } else {
            return RespBean.error("工资账套修改失败");
        }
    }

    /**
     * 删除工资账套
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除工资账套")
    @DeleteMapping("/{id}")
    public RespBean delSalary(@PathVariable Integer id) {
        if (salaryService.removeById(id)) {
            return RespBean.success("工资账套删除成功");
        } else {
            return RespBean.error("工资账套删除失败");
        }
    }

}
