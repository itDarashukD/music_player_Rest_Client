package com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity;

import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    private ArrayList<Map<String, Object>>categories = new ArrayList<>();
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
    private ArrayList<Map<String, String>> categoryWithId = new ArrayList<>();
    private ArrayList<Map<String, String>> products = new ArrayList<>();
}
//"categoryId": "55",
//        "categoryName": "Slushes",
//        "image": "https://urldefense.com/v3/__https://lh3.googleusercontent.com/T1cbKFr_m-vdqKRpKNqKuxJISgRexTNAcHfnTmy00Dn9aB7zKEakYs3_VgYxTCQCRjNE2G6j7V8TxVRKYrwQ__;!!GF_29dbcQIUBPA!lRFSIM9G6f7k9rqjTo1sG4D4Ja53S2w9QFVTQ9tJTdHaWU8FAfyFcKW-BFlGbXkA0A$ [lh3[.]googleusercontent[.]com]",
//        "thumbnail": "https://urldefense.com/v3/__https://lh3.googleusercontent.com/T1cbKFr_m-vdqKRpKNqKuxJISgRexTNAcHfnTmy00Dn9aB7zKEakYs3_VgYxTCQCRjNE2G6j7V8TxVRKYrwQ__;!!GF_29dbcQIUBPA!lRFSIM9G6f7k9rqjTo1sG4D4Ja53S2w9QFVTQ9tJTdHaWU8FAfyFcKW-BFlGbXkA0A$ [lh3[.]googleusercontent[.]com]",
//        "backgroundColor": "#666666",
//        "textColor": "#333333",
//        "isHeading": "false",
//        "createdAt": "2017-07-06T19:24:29.844Z",
//        "updatedAt": "2020-06-29T20:11:14.804Z",
//        "contentId": "menucategory-frozen-drinks-ntl-menu",
//        "showAtCheckout": "false",
//        "categories": [
//        {
//        "categoryId": "163"
//        },
//        {
//        "categoryId": "61"
//        }
//        ],
//        "products": []