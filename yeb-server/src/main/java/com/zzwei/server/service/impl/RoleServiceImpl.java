package com.zzwei.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzwei.server.mapper.AdminMapper;
import com.zzwei.server.mapper.RoleMapper;
import com.zzwei.server.pojo.Admin;
import com.zzwei.server.pojo.Role;
import com.zzwei.server.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 查询某角色id下所有人员
     * @param rid
     * @return
     */
    @Override
    public List<Admin> queryAdminByRid(Integer rid) {
        return adminMapper.queryAdminByRid(rid);
    }
}
