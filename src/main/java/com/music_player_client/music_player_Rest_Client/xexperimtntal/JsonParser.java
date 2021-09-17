package com.music_player_client.music_player_Rest_Client.xexperimtntal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity.Category;
import com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity.ChildProduct;
import com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity.National_menu;
import com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity.Product;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Category> list = beginRetrievingCategory();
        System.out.println(getProducts(list));
//        System.out.println(list.stream().map(Category::getCategoryId).collect(Collectors.toList()));
    }

    public List<Category> beginRetrievingCategory() {
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
        return nationalMenu.getMenu().getRootCategoryId();
    }

    public List<Category> getChild(List<Category> currentCategory, Map<String, Category> context) {//154
        if (currentCategory.isEmpty()) {
            return currentCategory;
        }
        return Stream.concat(currentCategory.stream(), currentCategory.stream()
                        .map(child -> getChild(child
                                        .getCategories()
                                        .stream()
                                        .map(arg ->
                                                {
                                                    String categoryId = arg.getCategoryId();
                                                    Category category = context.get(categoryId);
                                                    return category;
                                                }
                                        ).collect(Collectors.toList())
                                , context))
                        .flatMap(Collection::stream))
                .collect(Collectors.toList());
    }

    public List<Product> getProducts(List<Category> list) {
        List<Product> products = nationalMenu.getProducts();
        return list.stream()
                .map(Category::getProducts)
                .flatMap(Collection::stream)
                .map(ChildProduct::getProductId)
                .map(childProductId ->
                        products.get(Integer.parseInt(childProductId)))
                .collect(Collectors.toList());
//        List<String> childProductIdList = childProductList.stream()
//                .map(childProduct -> childProduct.getProductId())
//                .collect(Collectors.toList());
//
//
//        List<Product> collect = childProductIdList.stream()
//                .map(childProductId ->
//                        products.get(Integer.parseInt(childProductId))
//                ).collect(Collectors.toList());
//
//        collect.forEach(System.out::println);
    }
}
