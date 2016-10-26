package com.cniao5.app.biz;

import com.cniao5.app.common.Config;
import com.cniao5.app.entity.AuthorEntity;
import com.cniao5.app.entity.HomeNewsEntity;
import com.cniao5.app.util.ImageUtil;
import com.cniao5.app.util.CTextUtil;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 首页新闻数据抓取管理器
 */
public class HomeNewsDataManager {
    public HomeNewsDataManager() {
    }

    /**
     * 进行抓取首页信息数据
     *
     * @param document
     * @return
     */
    public List<HomeNewsEntity> getHomeNewsBeans(Document document) {
        List<HomeNewsEntity> homeNewsEntities = new ArrayList<HomeNewsEntity>();
        // TODO!!!ROBOT ADD
        if (document == null || document.select("div.articles") == null ||
                document.select("div.articles").first() == null) {
            return homeNewsEntities;
        }

        Elements elements = document.select("div.articles").first().select("article");
        // TODO!!!ROBOT ADD
        if (elements == null) {
            return homeNewsEntities;
        }
        for (Element element : elements) {
            //图标以及文章类型
            Element a_pic_element = element.select("a.pic").first();
            String imgurl = "";
            String mask = "";
            if (a_pic_element != null) {
                imgurl = ImageUtil.getCutImageUrl(a_pic_element.attr("data-lazyload"));
                mask = a_pic_element.text();
            }
            //desc信息 连接地址和标题
            Element desc_element = element.select("div.desc").first();
            String href = desc_element.select("a.title").first().attr("href");
            //进行href过滤 因为网站又文章列表无法点击 ，所以这边直接滤过了
            if (href.equals("javascript:void(0)")) {
                continue;
            }
            String tId = CTextUtil.getTitleId(href);
            href = Config.CRAWLER_URL + href;
            String title = desc_element.select("a.title").first().text();
            //作者信息
            Element author_element = desc_element.select("div.author").first();
            //查找只存在data-lazyload属性的a标签
            Element link = author_element.select("a").first();
            String author_href = Config.CRAWLER_URL + link.attr("href");
            String avatar = ImageUtil.getCutImageUrl(link.select("span.avatar").first().attr("data-lazyload"));
            String name = link.text();
            //时间
            Element time_element = author_element.select("time.timeago").first();
            String datetime = "";
            String datetext = "";
            if (time_element != null) {
                datetime = author_element.select("time.timeago").first().attr("title");
                datetext = author_element.select("time.timeago").first().text();
            } else {
                datetime = author_element.select("abbr.timeago").first().attr("title");
                datetext = author_element.select("abbr.timeago").first().text();
            }
            //文章简介
            String brief = desc_element.select("div.brief").first().text();
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setName(name);
            authorEntity.setAvatar(avatar);
            authorEntity.setHref(author_href);
            HomeNewsEntity bean = new HomeNewsEntity();
            bean.settId(tId);
            bean.setImgurl(imgurl);
            bean.setMask(mask);
            bean.setHref(href);
            bean.setTitle(title);
            bean.setAuthorEntity(authorEntity);
            bean.setDatetime(datetime);
            bean.setBrief(brief);
            bean.setDatetext(datetext);
            homeNewsEntities.add(bean);
        }
        return homeNewsEntities;
    }

    /**
     * 抓取文章类别数据 根据分类
     *
     * @param document
     * @return
     */
    public List<HomeNewsEntity> getHomeNewsBeans_CNK(Document document) {
        List<HomeNewsEntity> homeNewsEntities = new ArrayList<HomeNewsEntity>();
        // TODO!!!ROBOT ADD
        if (document == null || document.select("div.articles") == null ||
                document.select("div.articles").first() == null) {
            return homeNewsEntities;
        }
        Elements articleElement = document.select("div.articles").first().select("article");
        // TODO!!!ROBOT ADD
        if (articleElement == null) {
            return homeNewsEntities;
        }

        for (Element element : articleElement) {
            Element pic_element = element.select("a.pic").first();
            String imgurl = ImageUtil.getCutImageUrl(pic_element.attr("data-lazyload"));
            String href = pic_element.attr("abs:href");
            String mask = pic_element.text();
            String aId = CTextUtil.getArticleId(href);

            Element descElement = element.select("div.desc").first();
            String title = descElement.select("a.title").first().text();
            String brief = descElement.select("div.brief").first().text();

            Element authorElement = descElement.select("div.author").first();
            String datetime = authorElement.select("time.timeago").first().attr("datetime");
            String datetext = authorElement.select("time.timeago").first().text();

            Element a_Element = authorElement.select("a").first();
            String au_href = a_Element.attr("abs:href");
            Element avatarElement = a_Element.select("span.avatar").first();
            String avatar = avatarElement.attr("data-lazyload");
            String name = a_Element.select("span.name").first().text();
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setHref(au_href);
            authorEntity.setAvatar(avatar);
            authorEntity.setName(name);
            HomeNewsEntity bean = new HomeNewsEntity();
            bean.settId(aId);
            bean.setImgurl(imgurl);
            bean.setHref(href);
            bean.setMask(mask);
            bean.setTitle(title);
            bean.setBrief(brief);
            bean.setDatetime(datetime);
            bean.setDatetext(datetext);
            bean.setAuthorEntity(authorEntity);
            homeNewsEntities.add(bean);
        }

        return homeNewsEntities;
    }
}
