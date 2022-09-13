package com.zzwei.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzwei.server.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 根据用户id更新角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer updateAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
