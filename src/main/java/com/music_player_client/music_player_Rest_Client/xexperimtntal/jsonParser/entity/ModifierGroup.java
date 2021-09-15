package com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifierGroup implements Serializable {

    private String modifierGroupId;
    private String modifierGroupName;
    private String type;
    private String contentId;
    private String effectiveDate;
    private String expirationDate;
}
//    {
//            "modifierGroupId": "4550",
//            "modifierGroupName": "Crave Sauce",
//            "type": "Sauces",
//            "contentId": "mmg-crave-sauce",
//            "effectiveDate": null,
//            "expirationDate": null
//            },