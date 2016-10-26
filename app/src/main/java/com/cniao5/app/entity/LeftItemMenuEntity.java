package com.cniao5.app.entity;

/**
 * 左侧功能菜单 item实体类
 */
public class LeftItemMenuEntity {
    private int leftIcon;
    private String title;

    public LeftItemMenuEntity() {
    }

    public LeftItemMenuEntity(int leftIcon, String title) {
        this.leftIcon = leftIcon;
        this.title = title;
    }

    public int getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(int leftIcon) {
        this.leftIcon = leftIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LeftItemMenuEntity{" +
                "leftIcon=" + leftIcon +
                ", title='" + title + '\'' +
                '}';
    }
}
