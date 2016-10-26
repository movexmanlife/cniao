package com.cniao5.app.biz;

import com.cniao5.app.entity.CategoriesEntity;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 分类Tab数据抓取服务
 */
public class CategoryDataManager {
    public CategoryDataManager() {
    }

    public List<CategoriesEntity> getCategoriesBeans(Document document) {
        List<CategoriesEntity> categoriesEntities = new ArrayList<CategoriesEntity>();
        Elements elements = document.select("div.categories").first().select("a");
        for (Element element : elements) {
            String title = element.text();
            String data_type = element.attr("data-type");
            String href = element.attr("abs:href");
            CategoriesEntity bean = new CategoriesEntity();
            bean.setTitle(title);
            bean.setHref(href);
            bean.setData_type(data_type);
            categoriesEntities.add(bean);
        }
        return categoriesEntities;
    }

    /**
     * 抓取文章分类数据
     *
     * @param document
     * @return
     */
    public List<CategoriesEntity> getCategoriesBeans_CNK(Document document) {
        List<CategoriesEntity> categoriesEntities = new ArrayList<CategoriesEntity>();
        Elements cateElement = document.select("div.categories").first().select("a");
        //System.out.println(cateElement);
        for (Element element : cateElement) {
            String title = element.text();
            String href = element.attr("abs:href");
            String data_type = element.attr("data-type");
            categoriesEntities.add(new CategoriesEntity(title, href, data_type));
        }
        return categoriesEntities;
    }
}
