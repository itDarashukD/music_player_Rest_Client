package com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity;

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
