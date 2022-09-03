package com.zzwei.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzwei.server.mapper.PositionMapper;
import com.zzwei.server.pojo.Position;
import com.zzwei.server.service.IPositionService;
import com.zzwei.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;

    /**
     * 查询所有岗位
     * @return
     */
    @Override
    public RespBean queryPos() {
        return RespBean.success("success",positionMapper.selectList(null));
    }

    /**
     * 新增职位
     * @param position
     * @return
     */
    @Override
    public RespBean addPos(Position position) {

        return null;
    }

    /**
     * 删除职位
     * @param posId
     * @return
     */
    @Override
    public RespBean delPos(Integer posId) {
        return null;
    }

    /**
     * 修改职位
     * @param position
     * @return
     */
    @Override
    public RespBean updatePos(Position position) {
        return null;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public RespBean delPosBatch(Integer[] ids) {
        return null;
    }
}
