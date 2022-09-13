package com.zzwei.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzwei.server.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取所有人员信息
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmin(@Param("id") Integer id, @Param("keywords") String keywords);

}
