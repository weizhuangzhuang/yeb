package com.zzwei.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzwei.server.pojo.Admin;
import com.zzwei.server.pojo.Role;
import com.zzwei.server.utils.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);


    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取所有人员信息
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmin(String keywords);

    /**
     * 根据用户id更新角色
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}
