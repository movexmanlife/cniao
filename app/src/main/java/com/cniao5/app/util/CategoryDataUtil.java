package com.cniao5.app.util;

import com.cniao5.app.entity.CategoriesEntity;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CategoryDataUtil {
    public static List<CategoriesEntity> getCategoryBeans() {
        List<CategoriesEntity> beans = new ArrayList<>();
        beans.add(new CategoriesEntity("全部", "http://www.36kr.com/", "全部"));
        beans.add(new CategoriesEntity("氪TV", "http://www.36kr.com/columns/tv", "tv"));
        beans.add(new CategoriesEntity("O2O", "http://www.36kr.com/columns/o2o", "o2o"));
        beans.add(new CategoriesEntity("新硬件", "http://www.36kr.com/columns/hardware", "hardware"));
        beans.add(new CategoriesEntity("Fun!!", "http://www.36kr.com/columns/fun", "fun"));
        beans.add(new CategoriesEntity("企业服务", "http://www.36kr.com/columns/enterprise", "enterprise"));
        beans.add(new CategoriesEntity("Fit&Health", "http://www.36kr.com/columns/sports", "sports"));
        beans.add(new CategoriesEntity("在线教育", "http://www.36kr.com/columns/edu", "edu"));
        beans.add(new CategoriesEntity("互联网金融", "http://www.36kr.com/columns/finance", "finance"));
        beans.add(new CategoriesEntity("大公司", "http://www.36kr.com/columns/company", "company"));
        beans.add(new CategoriesEntity("专栏", "http://www.36kr.com/columns/column", "column"));
        return beans;
    }
}
