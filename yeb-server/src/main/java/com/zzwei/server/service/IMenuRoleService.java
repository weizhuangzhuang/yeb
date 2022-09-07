package com.zzwei.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzwei.server.pojo.MenuRole;
import com.zzwei.server.utils.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 根据角色id更新菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMidByRid(Integer rid, Integer[] mids);
}
