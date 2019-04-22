package com.lion.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * MenuTree
 * 菜单树
 *
 * @author Yanzheng
 * @date 2019/04/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class MenuTree implements Serializable {

    /**
     * 递归根节点
     *
     * @param nodes
     * @return
     */
    public static Set<Menu> genRoot(Set<Menu> nodes) {
        Set<Menu> root = new HashSet<>();
        //遍历数据
        nodes.forEach(menu -> {
            //当父id是0的时候应该是根节点
            System.out.println(menu.getPId());
            if (menu.getPId() == 0) {
                root.add(menu);
            }
        });
        //这里是子节点的创建方法
        root.forEach(menu -> genChildren(menu, nodes));
        //返回数据
        return root;
    }

    /**
     * 递归子节点
     *
     * @param menu
     * @param nodes
     * @return
     */
    private static Menu genChildren(Menu menu, Set<Menu> nodes) {
        //遍历传过来的数据
        for (Menu menu_1 : nodes) {
            //如果数据中的父id和上面的per_id一致应该就放children中去
            if (menu.getId().equals(menu_1.getPId())) {
                //如果当前节点的子节点是空的则初始化，如果不为空就加进去
                if (menu.getChildren() == null) {
                    menu.setChildren(new ArrayList());
                }
                menu.getChildren().add(genChildren(menu_1, nodes));
            }
        }
        //返回数据
        return menu;
    }

}
