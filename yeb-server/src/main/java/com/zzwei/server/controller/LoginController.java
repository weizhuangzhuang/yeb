package com.zzwei.server.controller;

import com.zzwei.server.pojo.Admin;
import com.zzwei.server.pojo.AdminLoginParam;
import com.zzwei.server.service.IAdminService;
import com.zzwei.server.utils.RSAUtils;
import com.zzwei.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * 用户登录
 */
@Api(tags = "登录相关接口")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    /**
     * 登录前生成公钥，用于加密
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "登录前生成公钥")
    @PostMapping("/getPublicKey")
    public RespBean getPublicKey(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        String publicKey = RSAUtils.generateBase64PublicKey();
        return RespBean.success("获取公钥信息成功",publicKey);
    }


    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(), request);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功");
    }
}
