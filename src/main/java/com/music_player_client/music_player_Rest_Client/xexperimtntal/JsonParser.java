package com.music_player_client.music_player_Rest_Client.xexperimtntal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity.Category;
import com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity.National_menu;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
public class JsonParser {

    private National_menu nationalMenu;

    public static void main(String[] args) throws IOException {
        JsonParser jsonParser = new JsonParser();
        jsonParser.jsonParser();
    }

    public void jsonParser() throws IOException {
        File json = new File("src/main/resources/National_Menu.json");
        ObjectMapper objectMapper = new ObjectMapper();

        nationalMenu = objectMapper.readValue(json, National_menu.class);
        System.out.println(beginRetrieveCategory());
    }

    public List<Category> beginRetrieveCategory() {
        Map<String, Category> context = createContext();
        return getChild(List.of(context.get(getRootCategoryId())), context);
    }

    public Map<String, Category> createContext() {
        List<Category> categoryList = nationalMenu.getCategories();
        Map<String, Category> contextMap = categoryList.stream()
                .collect(Collectors.toMap(Category::getCategoryId, Function.identity()));
        return (contextMap);
    }

    public String getRootCategoryId() {
        String rootCategoryId = nationalMenu.getMenu().getRootCategoryId();
        return rootCategoryId;
    }

    public List<Category> getChild(List<Category> currentCategoryList, Map<String, Category> context) {//154
        if (currentCategoryList.isEmpty()) {
            System.out.println("was stop");
            return currentCategoryList;
        }

        return currentCategoryList.stream()
                .map(child -> context.get(child.getCategoryId()))
                .map(child -> getChild(List.of(child), context))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
