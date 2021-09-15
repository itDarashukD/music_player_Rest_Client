package com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class National_menu implements Serializable {

    private Menu menu;
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Modifier> modifiers = new ArrayList<>();
    private ArrayList<ModifierGroup> modifierGroups = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
}
