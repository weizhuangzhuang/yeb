package com.zzwei.server.controller;


import com.zzwei.server.pojo.Admin;
import com.zzwei.server.pojo.Role;
import com.zzwei.server.service.IAdminService;
import com.zzwei.server.service.IRoleService;
import com.zzwei.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Api(tags = "管理员相关接口")
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;

    /**
     * 获取所有人员信息
     *
     * @param keywords
     * @return
     */
    @ApiOperation(value = "获取所有管理员信息")
    @GetMapping("/")
    public List<Admin> getAllAdmin(String keywords) {
        return adminService.getAllAdmin(keywords);
    }

    @ApiOperation(value = "根据角色id查询人员")
    @GetMapping("/admin/{rid}")
    public List<Admin> queryAdminByRid(@PathVariable Integer rid){
        return roleService.queryAdminByRid(rid);
    }

    /**
     * 更新管理员信息
     *
     * @param admin
     * @return
     */
    @ApiOperation(value = "更新管理员信息")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("管理员更新成功");
        } else {
            return RespBean.error("管理员更新失败");
        }
    }

    /**
     * 删除管理员信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除管理员信息")
    @DeleteMapping("/{id}")
    public RespBean delAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.success("管理员删除成功");
        } else {
            return RespBean.error("管理员删除失败");
        }
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    /**
     * 根据用户id更新角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    @ApiOperation(value = "更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        return adminService.updateAdminRole(adminId, rids);
    }

}
