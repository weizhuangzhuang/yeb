package com.zzwei.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzwei.server.mapper.MenuRoleMapper;
import com.zzwei.server.pojo.Admin;
import com.zzwei.server.pojo.MenuRole;
import com.zzwei.server.service.IMenuRoleService;
import com.zzwei.server.service.IRoleService;
import com.zzwei.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RedisTemplate redisTemplate;

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
        //清空redis
        List<Admin> admins = roleService.queryAdminByRid(rid);
        if (!CollectionUtils.isEmpty(admins)) {
            for (Admin admin : admins) {
                redisTemplate.delete("menu_" + admin.getId());
            }
        }
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
