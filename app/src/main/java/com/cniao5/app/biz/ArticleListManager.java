package com.cniao5.app.biz;

import com.cniao5.app.entity.ArticleListEntity;
import com.cniao5.app.entity.ArticleTagEntity;
import com.cniao5.app.entity.AuthorEntity;
import com.cniao5.app.util.CTextUtil;
import com.cniao5.app.util.HttpRequest;
import com.cniao5.app.util.ImageUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * 文章列表数据
 */
public class ArticleListManager {

    public ArticleListManager() {
    }

    /**
     * <dl class="blog_list clearfix">
     <dt><a href="http://blog.csdn.net/fengjie_123"><img src="http://avatar.csdn.net/5/5/2/3_fengjie_123.jpg" class="head"></a>
     <a href="http://blog.csdn.net/fengjie_123" class="nickname">fengjie_123</a>
     </dt>
     <dd>
     <h3 class="tracking-ad" data-mod="popu_254"><a href="http://blog.csdn.net/fengjie_123/article/details/52933199" target="_blank">Zygote</a></h3>
     <div class="blog_list_c">（一）Zygote是什么
     从字面上看，Zygote是“受精卵、结合子”的意思。在Android中，Zygote是Android系统中相当重要的进程。它的主要功能是执行Android应用程序。在And...</div>
     <div class="blog_list_b clearfix">
     <div class="blog_list_b_l fl">

     <span><i class="fa fa-list-ul"></i><a href="/mobile/newarticle.html" target="_blank">移动开发</a></span>
     </div>
     <div class="blog_list_b_r fr">
     <label>1小时前</label><span><i class="fa fa-eye"></i><em>27</em></span>
     </div>
     </div>
     </dd>
     </dl>
     * 进行根据HTML Document对象抓取文章相关信息
     *
     * @param document
     * @return
     */
    public List<ArticleListEntity> getArticleListBean(Document document) {
        List<ArticleListEntity> articleListEntityList = new ArrayList<>();
        // id等于listBot的div标签
        // Elements elements = document.select("div#listBot");
        Element singleElement = document.select("div#listBot").first();
        Elements listElements = singleElement.select("dl.blog_list");
        if (listElements != null && listElements.size() > 0) {
            for (Element element : listElements) {
                ArticleListEntity articleListEntity = new ArticleListEntity();
                // 头像地址
                String avatar = element.select("img[src]").first().attr("src");
                articleListEntity.setAvatarUrl(avatar); // 设置头像

                // 昵称
                String nickname = element.select("a.nickname").first().text();
                articleListEntity.setNickname(nickname);

                // 文章标题
                Element aElement =  element.select("h3.tracking-ad").first().getElementsByTag("a").first();
                articleListEntity.setArticleUrl(aElement.attr("abs:href"));
                articleListEntity.setTitle(aElement.text());

                // 文章简介
                String desc = element.select("div.blog_list_c").text();
                articleListEntity.setDesc(desc);

                // tag标签
                Element tagElement = element.select("div.blog_list_b_l").first().getElementsByTag("a").first();
                articleListEntity.setTag(tagElement.text());

                Element browseElement = element.select("div.blog_list_b_r").first();
                // 时间
                String time = browseElement.getElementsByTag("label").first().text();
                articleListEntity.setTime(time);

                // 浏览数
                String browse = browseElement.getElementsByTag("em").first().text();
                articleListEntity.setBrowse(Integer.valueOf(browse));

                articleListEntityList.add(articleListEntity);
            }
        }

        return articleListEntityList;
    }
}
