package com.cn.ssm.service.impl;


import com.cn.ssm.dao.BaseDao;
import com.cn.ssm.domain.Item;
import com.cn.ssm.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {
    /* 重写该方法，注入指定的Dao对象 */
    @Resource(name = "itemDao")
    @Override
    public void setDao(BaseDao<Item> dao) {
        super.setDao(dao);
    }


}
