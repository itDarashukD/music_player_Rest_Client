package com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modifier implements Serializable {

    private String modifierId;
    private String modifierPlu;
    private String modifierName;
    private String image;
    private String createdAt;
    private String updatedAt;
    private String modifierGroupId;
    private String modifierLevel;
    private String calories;
    private String contentId;
}
//      "modifierId": "50",
//              "modifierPlu": "5440",
//              "modifierName": "Add REESE\u2019S Peanut Butter",
//              "image": "https://urldefense.com/v3/__https://res.cloudinary.com/sonic-drive-in/image/upload/v150112800019/okc_menu/modifiers/peanut_butter.png__;!!GF_29dbcQIUBPA!lRFSIM9G6f7k9rqjTo1sG4D4Ja53S2w9QFVTQ9tJTdHaWU8FAfyFcKW-BFmXu3dGNA$ [res[.]cloudinary[.]com]",
//              "createdAt": "2017-06-29T20:36:56.722Z",
//              "updatedAt": "2021-08-11T14:44:17.662Z",
//              "modifierGroupId": "284",
//              "modifierLevel": "ADD",
//              "calories": "370",
//              "contentId": "mmg-peanut-butter"