package com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.service;

import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity.Category;
import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.jsonParser.Parser;

import java.io.IOException;
import java.util.List;

public class CategoryService {

    public List<Category> getCategories() throws IOException {
        return Parser.jsonParsing().getCategories();
    }
}
