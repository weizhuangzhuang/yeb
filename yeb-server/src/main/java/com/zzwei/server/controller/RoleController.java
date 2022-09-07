package com.zzwei.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzwei.server.pojo.Menu;
import com.zzwei.server.pojo.MenuRole;
import com.zzwei.server.pojo.Role;
import com.zzwei.server.service.IMenuRoleService;
import com.zzwei.server.service.IMenuService;
import com.zzwei.server.service.IRoleService;
import com.zzwei.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Api(tags = "角色相关接口")
@RestController
@RequestMapping("/system/basic/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    /**
     * 查询所有角色
     *
     * @return
     */
    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> queryall() {
        return roleService.list();
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "新增角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("角色新增成功");
        } else {
            return RespBean.error("角色新增失败");
        }
    }

    /**
     * 删除角色
     *
     * @param rid
     * @return
     */
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/{rid}")
    public RespBean delRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("角色删除成功");
        } else {
            return RespBean.error("角色删除失败");
        }
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid) {
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid)).stream().map(MenuRole::getMid).collect(Collectors.toList());
    }

    /**
     * 根据角色id更新菜单
     * @param rid
     * @param mids
     * @return
     */
    @ApiOperation(value = "根据角色id更新菜单")
    @PutMapping("/")
    public RespBean updateMidByRid(Integer rid , Integer[] mids){
        return menuRoleService.updateMidByRid(rid,mids);
    }

}
