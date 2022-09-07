package com.zzwei.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzwei.server.mapper.MenuRoleMapper;
import com.zzwei.server.pojo.MenuRole;
import com.zzwei.server.service.IMenuRoleService;
import com.zzwei.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 根据角色id更新菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    @Transactional
    @Override
    public RespBean updateMidByRid(Integer rid, Integer[] mids) {
        //先清空菜单
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if (null == mids || 0 == mids.length) {
            return RespBean.success("角色列表更新成功");
        }
        //再插入菜单
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        if (result == mids.length) {
            return RespBean.success("角色列表更新成功");
        } else {
            return RespBean.error("角色列表更新失败");
        }
    }
}
