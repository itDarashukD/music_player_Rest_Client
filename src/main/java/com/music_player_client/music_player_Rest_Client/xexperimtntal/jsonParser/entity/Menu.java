package com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    private ArrayList<String>menu = new ArrayList<>(){
    };
    private String menuId;
    private String menuName;
    private String rootCategoryId;
    private String createdAt;
    private String updatedAt;
//    "menuId": "151",
//            "menuName": "National Menu",
//            "rootCategoryId": "154",
//            "createdAt": "2017-07-06T19:06:30.662Z",
//            "updatedAt": "2021-06-14T16:55:59.830Z"
}
