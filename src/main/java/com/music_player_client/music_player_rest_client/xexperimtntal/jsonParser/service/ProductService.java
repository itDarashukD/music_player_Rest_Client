package com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.service;

import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity.National_menu;
import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity.Product;
import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.jsonParser.Parser;

import java.io.IOException;
import java.util.List;

public class ProductService {

    public List<Product>getProduct() throws IOException {
        National_menu national_menu = Parser.jsonParsing();
        return national_menu.getProducts();
    }
}
