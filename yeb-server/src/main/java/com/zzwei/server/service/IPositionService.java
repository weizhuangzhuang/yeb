package com.zzwei.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzwei.server.pojo.Position;
import com.zzwei.server.utils.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
public interface IPositionService extends IService<Position> {
    /**
     * 查询所有岗位
     * @return
     */
    RespBean queryPos();

    /**
     * 新增职位
     * @param position
     * @return
     */
    RespBean addPos(Position position);

    /**
     * 删除职位
     * @param posId
     * @return
     */
    RespBean delPos(Integer posId);

    /**
     * 修改职位
     * @param position
     * @return
     */
    RespBean updatePos(Position position);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    RespBean delPosBatch(Integer[] ids);
}
