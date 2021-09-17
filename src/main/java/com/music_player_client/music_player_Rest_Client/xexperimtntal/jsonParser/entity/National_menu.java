package com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class National_menu implements Serializable {

    private Menu menu;
    private List<Category> categories ;
    private List<Modifier> modifiers ;
    private List<ModifierGroup> modifierGroups ;
    private List<Product> products ;
    private List<ChildCategory>childCategories;
}
