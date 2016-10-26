package com.cniao5.app.entity;

import java.util.List;

/**
 * 文章详情数据
 */
public class ArticleEntity {
	private String title; // 文章标题
	private String datetime; // 文章发表时间
	private String datetext; // 文章发表时间时间 已计算
	private String context; // 文章内容
	private String headImage;  //文章头图片

	private List<ArticleTagEntity> articleTagEntities; // 文章搜索标签集合
	private AuthorEntity authorEntity; // 文章作者信息

	public ArticleEntity() {
		super();
	}

	public ArticleEntity(String title, String datetime, String datetext,
						 String context, String headImage, List<ArticleTagEntity> articleTagEntities,
						 AuthorEntity authorEntity) {
		super();
		this.title = title;
		this.datetime = datetime;
		this.datetext = datetext;
		this.context = context;
		this.headImage = headImage;
		this.articleTagEntities = articleTagEntities;
		this.authorEntity = authorEntity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getDatetext() {
		return datetext;
	}

	public void setDatetext(String datetext) {
		this.datetext = datetext;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public List<ArticleTagEntity> getArticleTagEntities() {
		return articleTagEntities;
	}

	public void setArticleTagEntities(List<ArticleTagEntity> articleTagEntities) {
		this.articleTagEntities = articleTagEntities;
	}

	public AuthorEntity getAuthorEntity() {
		return authorEntity;
	}

	public void setAuthorEntity(AuthorEntity authorEntity) {
		this.authorEntity = authorEntity;
	}


	@Override
	public String toString() {
		return "ArticleEntity{" +
				"title='" + title + '\'' +
				", datetime='" + datetime + '\'' +
				", datetext='" + datetext + '\'' +
				", context='" + context + '\'' +
				", headImage='" + headImage + '\'' +
				", articleTagEntities=" + articleTagEntities +
				", authorEntity=" + authorEntity +
				'}';
	}
}
