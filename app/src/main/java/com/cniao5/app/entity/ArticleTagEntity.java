package com.cniao5.app.entity;

/**
 * 
 * 文章标签信息实体类
 */
public class ArticleTagEntity {
	private String href;
	private String tagname;

	public ArticleTagEntity() {
		super();
	}

	public ArticleTagEntity(String href, String tagname) {
		super();
		this.href = href;
		this.tagname = tagname;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}


	@Override
	public String toString() {
		return "ArticleTagEntity{" +
				"href='" + href + '\'' +
				", tagname='" + tagname + '\'' +
				'}';
	}
}
