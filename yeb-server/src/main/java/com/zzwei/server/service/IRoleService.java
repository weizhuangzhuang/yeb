package com.zzwei.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzwei.server.pojo.Admin;
import com.zzwei.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface IRoleService extends IService<Role> {

    List<Admin> queryAdminByRid(Integer rid);

}
