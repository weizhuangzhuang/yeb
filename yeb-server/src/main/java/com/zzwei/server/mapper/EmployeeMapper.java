package com.zzwei.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzwei.server.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 获取所有员工(分页)
     *
     * @param page
     * @param employee
     * @param beginDateScope
     * @return
     */
    IPage<Employee> getEmployeeByPage(@Param("page") Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 员工查询
     *
     * @param id
     * @return
     */
    List<Employee> getEmployee(Integer id);

    /**
     * 分页查询所有员工工资套账
     *
     * @param page
     * @param employee
     * @return
     */
    IPage<Employee> getEmpSalaryByPage(@Param("page") Page<Employee> page, @Param("employee") Employee employee);
}
