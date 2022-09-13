package com.zzwei.server.utils;

import com.zzwei.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 人员信息工具类
 */
public class AdminUtils {

    public static Admin getAdminInfo() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
