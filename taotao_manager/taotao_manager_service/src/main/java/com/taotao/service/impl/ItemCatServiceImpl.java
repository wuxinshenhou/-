package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    private TbItemCatMapper mapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(Long parentId) {

        //1.注入mapper
        //2.创建example
        TbItemCatExample  example = new TbItemCatExample();
        //设置查询的条件
        example.createCriteria().andParentIdEqualTo(parentId);
        //4.执行查询 获取结果是list<tbitemCat>
        List<TbItemCat> tbItemCats = mapper.selectByExample(example);
        //5.转成 List<EasyUITreeNode>
        List<EasyUITreeNode>  nodes = new ArrayList<>();
        for(TbItemCat itemCat : tbItemCats){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setText(itemCat.getName());
            node.setId(itemCat.getId());
            node.setState(itemCat.getIsParent()?"closed":"open");
            nodes.add(node);
        }


        return nodes;
    }
}
