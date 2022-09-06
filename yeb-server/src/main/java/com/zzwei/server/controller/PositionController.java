package com.zzwei.server.controller;


import com.zzwei.server.pojo.Position;
import com.zzwei.server.service.IPositionService;
import com.zzwei.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Api(tags = "职位相关接口")
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    /**
     * 查询所有岗位
     *
     * @return
     */
    @ApiOperation(value = "查询所有职位")

    @GetMapping("/")
    public List<Position> queryPos() {
        //return positionService.queryPos();
        //也可以直接用
        return positionService.list();
    }

    /**
     * 新增职位
     *
     * @param position
     * @return
     */
    @ApiOperation(value = "新增职位")
    @PostMapping("/")
    public RespBean addPos(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.success("职位添加成功");
        }
        return RespBean.error("职位添加失败");
    }

    /**
     * 删除职位
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除职位")
    @DeleteMapping("/{id}")
    public RespBean delPos(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.success("职位删除成功");
        }
        return RespBean.error("职位删除失败");
    }

    /**
     * 修改职位
     *
     * @param position
     * @return
     */
    @ApiOperation(value = "修改职位")
    @PutMapping("/")
    public RespBean updatePos(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.success("职位修改成功");
        }
        return RespBean.error("职位修改失败");
    }

    /**
     * 批量删除职位
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除职位")
    @DeleteMapping("/")
    public RespBean delPosBatch(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("批量删除成功");
        }
        return RespBean.error("批量删除失败");
    }

}
