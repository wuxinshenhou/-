package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper mapper;
    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
         //设置分页的条件
        PageHelper.startPage(page,rows);
        //创建example
        TbItemExample example =new TbItemExample();
        //执行查询 获取查询的结果
        List<TbItem> tbItemList  = mapper.selectByExample(example);
        //封装分页信息
        PageInfo<TbItem> info  = new PageInfo<TbItem>(tbItemList );

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(info.getTotal());
        result.setRows(info.getList());


        return result;
    }
}
