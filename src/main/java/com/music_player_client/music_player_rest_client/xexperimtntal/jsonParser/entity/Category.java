package com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    private String categoryName;
    private String type;
    private String quantity;
    private String defaultSize;
    private String sizingRestriction;
    private String categoryId;
    private String image;
    private String thumbnail;
    private String backgroundColor;
    private String textColor;
    private String isHeading;
    private String createdAt;
    private String updatedAt;
    private String contentId;
    private String showAtCheckout;
    private List<ChildCategory> categories;
    private List<ChildProduct> products;
}
