package com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.jsonParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity.National_menu;

import java.io.File;
import java.io.IOException;

public class Parser {

    public static National_menu jsonParsing() throws IOException {
        File json = new File("src/main/resources/National_Menu.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, National_menu.class);
    }
}
