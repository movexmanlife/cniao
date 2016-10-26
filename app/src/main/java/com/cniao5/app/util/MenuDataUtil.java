package com.cniao5.app.util;


import com.cniao5.app.R;
import com.cniao5.app.entity.LeftItemMenuEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 左侧菜单Item数据构造
 */
public class MenuDataUtil {
    public static List<LeftItemMenuEntity> getItemMenus() {
        List<LeftItemMenuEntity> menus = new ArrayList<LeftItemMenuEntity>();
        menus.add(new LeftItemMenuEntity(R.drawable.icon_zhanghaoxinxi, "账号信息"));
        menus.add(new LeftItemMenuEntity(R.drawable.icon_wodeguanzhu, "我的关注"));
        menus.add(new LeftItemMenuEntity(R.drawable.icon_shoucang, "我的收藏"));
        menus.add(new LeftItemMenuEntity(R.drawable.icon_yijianfankui, "意见反馈"));
        menus.add(new LeftItemMenuEntity(R.drawable.icon_shezhi, "设置"));
        return menus;
    }

}
