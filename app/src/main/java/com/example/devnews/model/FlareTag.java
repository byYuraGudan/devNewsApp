package com.example.devnews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlareTag {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bg_color_hex")
    @Expose
    private String bgColorHex;
    @SerializedName("text_color_hex")
    @Expose
    private String textColorHex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgColorHex() {
        return bgColorHex;
    }

    public void setBgColorHex(String bgColorHex) {
        this.bgColorHex = bgColorHex;
    }

    public String getTextColorHex() {
        return textColorHex;
    }

    public void setTextColorHex(String textColorHex) {
        this.textColorHex = textColorHex;
    }

}