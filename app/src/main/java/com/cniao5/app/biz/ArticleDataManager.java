package com.cniao5.app.biz;

import com.cniao5.app.entity.ArticleEntity;
import com.cniao5.app.entity.AuthorEntity;
import com.cniao5.app.entity.ArticleTagEntity;
import com.cniao5.app.util.CTextUtil;
import com.cniao5.app.util.HttpRequest;
import com.cniao5.app.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 文章详情数据 以及作者，搜索标签相关信息数据抓取服务器类
 */
public class ArticleDataManager {
    private String articleId;

    public ArticleDataManager(String articleId) {
        this.articleId = articleId;
    }

    /**
     * 进行根据HTML Document对象抓取文章相关信息
     *
     * @param document
     * @return
     */
    public ArticleEntity getArticleBean(Document document) {
        ArticleEntity articleEntity = new ArticleEntity();
        //首先获取局部的文章相关数据
        Element singleElement = document.select("article.single-post").first();
        //获取标题
        //single-post__title
        String title = singleElement.select("h1.single-post__title").first().text();
        //获取时间
        String datetime = singleElement.select("time.timeago").first().attr("datetime");
        String datetext = singleElement.select("time.timeago").first().text();
        //获取头图片
        String headImage = ImageUtil.getCutImageUrl(singleElement.select("div.single-post-header__headline").first().select("img[src]").first().attr("src"));
        //获取文章内容
        String context = Jsoup.parseBodyFragment(singleElement.select("section.article").first().toString()).toString();
        articleEntity.setTitle(title);
        articleEntity.setDatetime(datetime);
        articleEntity.setDatetext(datetext);
        articleEntity.setHeadImage(headImage);
        articleEntity.setContext(CTextUtil.replaceSSymbol(context));
        //抓取搜索标签
        Elements tagElements = singleElement.select("span.tag-item");
        if (tagElements != null && tagElements.size() > 0) {
            List<ArticleTagEntity> articleTagEntities = new ArrayList<ArticleTagEntity>();
            for (Element element : tagElements) {
                Element a_Element = element.select("a").first();
                String href = a_Element.attr("abs:href");
                String tagname = a_Element.text();
                articleTagEntities.add(new ArticleTagEntity(href, tagname));
            }
            articleEntity.setArticleTagEntities(articleTagEntities);
        }
        //开始抓取用户信息
        String author_Str = HttpRequest.sendPost("http://36kr.com/asynces/posts/author_info", "url_code=" + articleId);
        AuthorEntity bean = null;
        try {
            JSONObject authorObject = new JSONObject(author_Str);
            String name = authorObject.getString("name");
            String description = CTextUtil.replaceEmail(authorObject.getString("tagline"));
            String avatar = ImageUtil.getCutImageUrl(authorObject.getString("avatar"));
            String badge = authorObject.getString("role");
            String article_total = authorObject.getString("posts_count");
            String read_number = authorObject.getString("views_count");
            String href = "http:" + authorObject.getString("more_articles");
            bean = new AuthorEntity();
            bean.setName(name);
            bean.setDescription(description);
            bean.setAvatar(avatar);
            bean.setBadge(badge);
            bean.setArticle_total(article_total);
            bean.setRead_number(read_number);
            bean.setHref(href);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        articleEntity.setAuthorEntity(bean);
        return articleEntity;
    }

    /**
     * 进行抓取文章详情
     *
     * @param document
     * @return
     */
    public ArticleEntity getArticleBean_CNK(Document document) {
        ArticleEntity bean = new ArticleEntity();
        Element singleElement = document.select("article.single-post").first();
        String title = singleElement.select("h1.single-post__title").first().text();
        String datetime = singleElement.select("time.timeago").first().attr("datetime");
        String datetext = singleElement.select("time.timeago").first().text();
        Element head = singleElement.select("div.single-post-header__headline").first();
        String headImage = ImageUtil.getCutImageUrl(head.select("img").first().attr("src"));
        String context = singleElement.select("section.article").first().toString();
        bean.setTitle(title);
        bean.setDatetime(datetime);
        bean.setDatetext(datetext);
        bean.setHeadImage(headImage);
        bean.setContext(context);

        //开始抓取标签Tag数据
        Elements tagsElements = singleElement.select("section.single-post-tags").first().select("span.tag-item");
        List<ArticleTagEntity> articleTagEntities = new ArrayList<ArticleTagEntity>();
        for (Element element : tagsElements) {
            String href = element.select("a").first().attr("abs:href");
            String tagname = element.text();
            ArticleTagEntity articleTagEntity = new ArticleTagEntity();
            articleTagEntity.setHref(href);
            articleTagEntity.setTagname(tagname);
            articleTagEntities.add(articleTagEntity);
        }
        bean.setArticleTagEntities(articleTagEntities);
        //开始抓取作者信息
        AuthorEntity authorEntity = new AuthorEntity();
        String result = HttpRequest.sendPost("http://36kr.com/asynces/posts/author_info", "url_code=" + articleId);
        try {
            JSONObject authorObject = new JSONObject(result);
            String name = authorObject.getString("name");
            String avatar = authorObject.getString("avatar");
            String badge = authorObject.getString("role");
            String description = authorObject.getString("tagline");
            String href = "http:" + authorObject.getString("more_articles");
            String article_total = authorObject.getString("posts_count");
            String read_number = authorObject.getString("views_count");
            authorEntity.setName(name);
            authorEntity.setAvatar(avatar);
            authorEntity.setBadge(badge);
            authorEntity.setDescription(description);
            authorEntity.setHref(href);
            authorEntity.setArticle_total(article_total);
            authorEntity.setRead_number(read_number);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bean.setAuthorEntity(authorEntity);
        return bean;
    }
}
