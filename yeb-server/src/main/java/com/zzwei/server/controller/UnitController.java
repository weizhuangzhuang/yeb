package com.zzwei.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzwei.server.pojo.Unit;
import com.zzwei.server.service.IUnitService;
import com.zzwei.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "unit接口")
@RestController
@RequestMapping("/system/cfg/unit")
public class UnitController {

    @Autowired
    private IUnitService unitService;

    @ApiOperation("查询所有unit")
    @GetMapping("/listByType/{type}")
    public List<Unit> listByType(@PathVariable String type) {
        return unitService.list(new QueryWrapper<Unit>().like("type", type));
    }

    @ApiOperation("查询单位类别")
    @GetMapping("dic/v_unit_type")
    public RespBean getUnitType() {
        return null;
    }

}
