package com.zzwei.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzwei.server.pojo.Employee;
import com.zzwei.server.utils.RespPageBean;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
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
}
