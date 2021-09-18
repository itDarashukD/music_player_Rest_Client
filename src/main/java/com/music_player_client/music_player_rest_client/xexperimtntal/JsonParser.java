package com.music_player_client.music_player_rest_client.xexperimtntal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity.Category;
import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity.ChildProduct;
import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity.National_menu;
import com.music_player_client.music_player_rest_client.xexperimtntal.jsonParser.entity.Product;

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
        List<Product> products = getProducts(list);
        products.forEach(System.out::println);
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

        List<ChildProduct> childProductList = list.stream()
                .map(Category::getProducts)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<String> childProductIdList = childProductList.stream()
                .map(childProduct -> childProduct.getProductId())
                .collect(Collectors.toList());

        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(
                        Product::getProductId, Function.identity()));

        return childProductIdList.stream()
                .map(childProductId -> productMap.get(childProductId)
                ).collect(Collectors.toList());
    }
}
