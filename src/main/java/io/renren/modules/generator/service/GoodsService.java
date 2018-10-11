package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.GoodsEntity;

import java.util.Map;

/**
 * 商品管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-10 10:59:00
 */
public interface GoodsService extends IService<GoodsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

