package com.zzwei.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzwei.server.pojo.Admin;
import com.zzwei.server.utils.RespBean;

import javax.servlet.http.HttpServletRequest;

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
}
