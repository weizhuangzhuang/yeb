package com.zzwei.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzwei.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
