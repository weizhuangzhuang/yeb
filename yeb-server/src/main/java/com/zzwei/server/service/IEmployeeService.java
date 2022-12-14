package com.zzwei.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzwei.server.pojo.Employee;
import com.zzwei.server.utils.RespBean;
import com.zzwei.server.utils.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 查询最大工号
     *
     * @return
     */
    RespBean getMaxWorkID();

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    RespBean addEmployee(Employee employee);

    /**
     * 员工查询
     *
     * @param id
     */
    List<Employee> getEmployee(Integer id);

    /**
     * 分页查询所有员工工资套账
     *
     * @param currentPage
     * @param size
     * @param employee
     * @return
     */
    RespPageBean getEmpSalaryByPage(Integer currentPage, Integer size, Employee employee);
}
