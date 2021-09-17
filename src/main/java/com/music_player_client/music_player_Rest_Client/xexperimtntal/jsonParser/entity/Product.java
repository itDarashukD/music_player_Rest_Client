package com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

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
    private List<Map<String, String>> modifiers;
    private List<Map<String, Object>> sizes;
    private List<Map<String, String>> buildRelatedModifierGroups;
    private List<Map<String, String>> condiments;
    private String calories;
    private String contentId;
    private String defaultUpsellComboProductId;
    private Map<String, Integer> caloriesRange;
    private List<Map<String, String>> categories;
}
