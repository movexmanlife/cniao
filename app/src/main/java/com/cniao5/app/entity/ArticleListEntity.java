package com.cniao5.app.entity;

import java.util.List;

/**
 * 文章列表数据
 */
public class ArticleListEntity {
	private String avatarUrl;
	private String nickname;
	private String title;
	private String articleUrl;
	private String desc;
	private String tag;
	private String time;
	private int browse;

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getBrowse() {
		return browse;
	}

	public void setBrowse(int browse) {
		this.browse = browse;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	@Override
	public String toString() {
		return "ArticleListEntity{" +
				"avatarUrl='" + avatarUrl + '\'' +
				", nickname='" + nickname + '\'' +
				", title='" + title + '\'' +
				", articleUrl='" + articleUrl + '\'' +
				", desc='" + desc + '\'' +
				", tag='" + tag + '\'' +
				", time='" + time + '\'' +
				", browse=" + browse +
				'}';
	}
}
