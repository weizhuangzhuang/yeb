package com.zzwei.server.controller;


import com.zzwei.server.pojo.Joblevel;
import com.zzwei.server.service.IJoblevelService;
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
@Api(tags = "职称相关接口")
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    /**
     * 获取所有职称
     *
     * @return
     */
    @GetMapping("/")
    @ApiOperation(value = "查询所有职称")
    public List<Joblevel> queryall() {
        return joblevelService.list();
    }


    /**
     * 新增职称
     *
     * @param joblevel
     * @return
     */
    @ApiOperation(value = "新增职称")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return RespBean.success("职称新增成功");
        } else {
            return RespBean.error("职称新增失败");
        }
    }

    /**
     * 更新职称
     *
     * @param joblevel
     * @return
     */
    @ApiOperation(value = "更新职称")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return RespBean.success("职称更新成功");
        } else {
            return RespBean.error("职称更新失败");
        }
    }

    /**
     * 职位删除
     *
     * @param jobLevelId
     * @return
     */
    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{jobLevelId}")
    public RespBean delJobLevel(@PathVariable Integer jobLevelId) {
        if (joblevelService.removeById(jobLevelId)) {
            return RespBean.success("职位删除成功");
        } else {
            return RespBean.error("职位删除失败");
        }
    }

    /**
     * 职位删除
     *
     * @param jobLevelIds
     * @return
     */
    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    public RespBean delJobLevels(Integer[] jobLevelIds) {
        if (joblevelService.removeByIds(Arrays.asList(jobLevelIds))) {
            return RespBean.success("批量删除职称成功");
        } else {
            return RespBean.error("批量删除职称失败");
        }
    }

}
