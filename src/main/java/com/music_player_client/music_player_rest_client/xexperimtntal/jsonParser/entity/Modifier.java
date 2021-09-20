package com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity;

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
