package com.zzwei.server.controller;


import com.zzwei.server.pojo.Menu;
import com.zzwei.server.service.IMenuService;
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
@Api(tags = "菜单相关接口")
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "根据用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId() {
        return menuService.getMenusByAdminId();
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @ApiOperation(value = "添加菜单")
    @PostMapping("/addmenu")
    public RespBean addMenu(@RequestBody Menu menu) {
        return menuService.addDepartment(menu);
    }

    @ApiOperation(value = "根据菜单id删除菜单")
    @DeleteMapping("/menu/{id}")
    public RespBean delMenuById(@PathVariable Integer id) {
//        if (menuService.removeById(id)) {
//            return RespBean.success("菜单删除成功");
//
//        } else {
//            return RespBean.error("菜单删除失败");
//        }
        return menuService.delMenuById(id);

    }

}
