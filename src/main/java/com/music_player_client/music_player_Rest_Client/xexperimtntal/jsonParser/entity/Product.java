package com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product  {

    private String productId;
    private String productType;
    private String productPlu;
    private String productName;
    private String productDescription;
    private String image;
    private String disclaimer;
    private String size;
    private String createdAt;
    private String updatedAt;
    private String effectiveDate;
    private String expirationDate;
    private ArrayList<Map<String, String >> modifiers = new ArrayList<>();
    private ArrayList<Map<String, Object>> sizes = new ArrayList<>();
    private ArrayList<Map<String, String>> buildRelatedModifierGroups = new ArrayList<>();
    private ArrayList<Map<String, String>> condiments = new ArrayList<>();
    private String calories;
    private String contentId;
    private String defaultUpsellComboProductId;
    private Map<String ,Integer>caloriesRange=new HashMap<>();
    private ArrayList<Map<String, String>> categories = new ArrayList<>();
}
//  "productId": "5",
//          "productType": "COMBO",
//          "productPlu": "1360",
//          "productName": "Footlong Quarter Pound Coney Combo",
//          "productDescription": "Footlong Quarter Pound Coney Combo",
//          "image": "https://urldefense.com/v3/__https://res.cloudinary.com/sonic-drive-in/v1500478672/okc_menu/food/Footlong_Chili_Cheese_Coney.png__;!!GF_29dbcQIUBPA!lRFSIM9G6f7k9rqjTo1sG4D4Ja53S2w9QFVTQ9tJTdHaWU8FAfyFcKW-BFnJbzfiEw$ [res[.]cloudinary[.]com]",
//          "disclaimer": "Prices and menu items vary according to locations. Prices are subject to change without notice.",
//          "size": null,
//          "createdAt": "2017-07-17T16:05:12.754Z",
//          "updatedAt": "2019-09-27T18:08:35.656Z",
//          "effectiveDate": null,
//          "expirationDate": null,
//          "modifiers":[],
//                  "sizes":[],
//                  "buildRelatedModifierGroups":[],
//                  "condiments":[],
//                  "calories":null,
//                  "contentId":"footlong-quarter-pound-coney-combo",
//                  "defaultUpsellComboProductId":null,
//                  "caloriesRange":{
//                  "min":1040,
//                  "max":3390
//                  },
//                  "categories":[
//                  {
//                  "type":"DRINK",
//                  "categoryId":"10700",
//                  "quantity":"1",
//                  "defaultSize":"REG/MED",
//                  "sizingRestriction":"ONLY_UPSIZING_ALLOWED"
//                  },
//                  {
//                  "type":"ENTREE",
//                  "categoryId":"194",
//                  "quantity":"1",
//                  "defaultSize":"REG/MED",
//                  "sizingRestriction":"NO_RESIZING_ALLOWED"
//                  },
//                  {
//                  "type":"SIDE",
//                  "categoryId":"12952",
//                  "quantity":"1",
//                  "defaultSize":"REG/MED",
//                  "sizingRestriction":"ONLY_UPSIZING_ALLOWED"
//                  }
//                  ]
//                  },