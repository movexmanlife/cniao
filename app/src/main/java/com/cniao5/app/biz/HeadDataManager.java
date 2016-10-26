package com.cniao5.app.biz;

import com.cniao5.app.entity.AdHeadEntity;
import com.cniao5.app.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 首页顶部广告数据抓取工具类
 */
public class HeadDataManager {
    public HeadDataManager() {

    }

    /**
     * 进行根据地址抓取顶部广告数据
     *
     * @param document
     * @return
     */
    public List<AdHeadEntity> getHeadBeans(Document document) {
        List<AdHeadEntity> adHeadEntities = new ArrayList<AdHeadEntity>();
        // class等于masthead的div标签
        // Elements elements = document.select("div.masthead");
        Elements elements = document.select("ul.J_navList");
        // TODO!!!ROBOT ADD
        if (elements == null) {
            return null;
        }
        // TODO!!!ROBOT ADD
        if (elements.first() == null) {
            return null;
        }
        Elements links = elements.first().select("a[data-lazyload]"); //带有data-lazyload属性的a元素
        // TODO!!!ROBOT ADD
        if (links == null) {
            return null;
        }

//        for (Element element : links) {
//            String title = element.text();
//            String href = element.attr("href");
//            String imgurl = ImageUtil.getCutImageUrl(element.attr("data-lazyload"));
//            String mask = element.select("span").first().text();
            AdHeadEntity bean = new AdHeadEntity();
            bean.setTitle("泳帽、摩托车和热钱，中国创投圈在印尼的真实故事");
            bean.setImgurl("https://pic.36krcnd.com/avatar/201610/25084614/6wvsh8i87zmykttq.jpg");
            bean.setHref("http://36kr.com/p/5055135.html");
            bean.setMask("深度报道");
            adHeadEntities.add(bean);
//        }

        AdHeadEntity bean1 = new AdHeadEntity();
        bean1.setTitle("小米发布VR头显正式版");
        bean1.setImgurl("https://pic.36krcnd.com/avatar/201610/26035711/l073wu6ttclb2ih7.jpg");
        bean1.setHref("http://36kr.com/topics/131");
        bean1.setMask("热点专题");
        adHeadEntities.add(bean1);

        AdHeadEntity bean2 = new AdHeadEntity();
        bean2.setTitle("网友在美国“贴吧”就火星殖民计划“发难”马斯克，看他如何应战？");
        bean2.setImgurl("https://pic.36krcnd.com/avatar/201610/26025508/etkcou1z401icq34.jpg");
        bean2.setHref("http://36kr.com/p/5055164.html");
        bean2.setMask("热点专题");
        adHeadEntities.add(bean2);

        AdHeadEntity bean3 = new AdHeadEntity();
        bean3.setTitle("在锤子内部孵化两年，VR项目团队或要解散了");
        bean3.setImgurl("https://pic.36krcnd.com/avatar/201610/26025222/m1wg8y62m87yl5j4.jpg");
        bean3.setHref("http://36kr.com/p/5055176.html");
        bean3.setMask("明星公司");
        adHeadEntities.add(bean3);
        return adHeadEntities;
    }


    /**
     * 进行抓取广告轮播信息
     *
     * @param document
     * @return
     */
    public List<AdHeadEntity> getHeadBeans_CNK(Document document) {
        List<AdHeadEntity> adHeadEntities = new ArrayList<AdHeadEntity>();
        Elements headElements = document.select("div.head-images").first().select("a[data-lazyload]");
        //System.out.println(headElements);
        for (Element element : headElements) {
            String imgurl = ImageUtil.getCutImageUrl(element.attr("data-lazyload"));
            String href = element.attr("href");
            String mask = element.select("span").first().text();
            String title = element.text();
            adHeadEntities.add(new AdHeadEntity(title, imgurl, href, mask));
        }
        return adHeadEntities;
    }

}
