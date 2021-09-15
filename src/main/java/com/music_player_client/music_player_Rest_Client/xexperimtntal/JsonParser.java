package com.music_player_client.music_player_Rest_Client.xexperimtntal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity.Category;
import com.music_player_client.music_player_Rest_Client.xexperimtntal.jsonParser.entity.National_menu;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Slf4j
public class JsonParser {

    private National_menu readValues;


    public static void main(String[] args) throws IOException {
        JsonParser jsonParser = new JsonParser();
        jsonParser.jsonParser();
    }

    public void jsonParser() throws IOException {
        File json = new File("src/main/resources/National_Menu.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        readValues = objectMapper.readValue(json, National_menu.class);
        getMenuRootCategory();
    }

    private void getMenuRootCategory() {
        National_menu national_menu = readValues;//154
        ArrayList<Category> allCategories = national_menu.getCategories(); //get all categories
        String rootCategoryId = national_menu.getMenu().getRootCategoryId();
        List<Category> categoryList = allCategories//154
                .stream()
                .filter(x ->
                        Objects.equals(x.getCategoryId(), rootCategoryId))
                .collect(toList());

        Category menuRootCategory = categoryList.stream().findAny().get();
        getRecursion(menuRootCategory, allCategories);
    }

    Set<Category> finishCategoryList = new HashSet<>();
    Set<Category> currentCategoryList = new HashSet<>();
    private void getRecursion(Category menuRootCategory, ArrayList<Category> allCategories) {
        Map<String, Object> categoryMap = new HashMap<>();

//        ArrayList<Map<String, Object>> al = menuRootCategory.getCategories();
//        for (Category x : allCategories
//        ) {
//            if (x.getCategories() == null) {
//                System.out.println("All categories was iterated");
//            }
        ArrayList<Map<String, Object>> categories = menuRootCategory.getCategories();//список мар с категориями
//        List<Object>stringList = new ArrayList<>();
//       menuRootCategory.getCategories().forEach(x->
//               x.forEach((y,z)->stringList.add(z)));
//         System.out.println(stringList);


        for (int i = 0; i < categories.size(); i++) {
            //      System.out.println(  i+" "+  menuRootCategory.getCategories().get(i));
            menuRootCategory.getCategories().get(i).

                    ///перебираем мапы
                    //  y.
                            forEach((q, w) ->//перебираем конкретную мапу
                    {
                        Category currentCategory = allCategories  //получем у нее значение значения - номер в "categoryId": "10402"
                                .stream()
                                .filter(c ->
                                        Objects.equals(c.getCategoryId(), w)) //перебрав корневой список находим категрию с этим номером "10402"
                                .collect(toList())
                                .stream()
                                .findAny()
                                .get();

                        currentCategoryList.add(currentCategory);
                        getRecursion(currentCategory, allCategories);
//                 finishCategoryList.add(currentCategory);
                    });

                  System.out.println(currentCategoryList);
//        currentCategoryList.forEach(x-> System.out.println(x.getCategoryId()));


//                Collection<Object> values = y.values();
//

//                List<Category> categoryList1 = national_menu.getCategories()
//                        .stream()
//                        .filter(c ->c.getCategoryId()==z)
//                        .collect(toList());


        }
    }
}


//        System.out.println(collect.size());
//        System.out.println(collect.stream().findAny());


//        Category category = national_menu.getCategories().get(national_menu.getCategories()
//                        .stream()
//                        .filter(x->x.getCategoryId()==rootCategoryId));
//
//        System.out.println(category);


//        ArrayList<Product> products = national_menu.getProducts();    //products
//        ArrayList<Map<String, String>> products2 = new ArrayList<>(); //products
//
//        ArrayList<Category> categories = national_menu.getCategories();

//        Map<ArrayList<Map<String, String>>, List<Category>> collect =
//                categories.stream()
//                        .collect(
//                                Collectors.groupingBy(
//                                        Category::getProducts,
//                                        Collectors.collectingAndThen(
//                                                toList(),
//                                                e -> e.stream()
//                                                     //   .sorted(Comparator.comparing(Category::getCategoryId))
//                                                        .collect(toList()))));
//        collect.forEach((x,y)->
//                x.forEach(z->
//                {
//                    Map<String, String> z1 = z;
//                    products2.add(z1);
//                }));
//
//products2.forEach(z-> System.out.println(z));


//     categories.forEach(x -> new ArrayList<>(x.getProducts()));


//   System.out.println(readValues.toString());


//        Collection<Product> products = objectMapper.readValue(
//                json, new TypeReference<Collection<Product>>() { }
//        );
//        System.out.println(products);

//    National_menu national_menu = objectMapper.readValue(json, National_menu.class);


//        Map<String, Object> map
//                = objectMapper.readValue(json,  new TypeReference<Map<String,Object>>(){});
//       map.forEach((x,y)->
//               {
////                   if (x == "menu") {
//                       System.out.println(x+"+");
////                   }
//               });

//        JsonNode rootNode = objectMapper.readTree(json);   //its gives us array of "categories"
//        Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
//        while (fields.hasNext()) {
//            Map.Entry<String, JsonNode> entry = fields.next();
//            if (entry.getKey().equals("categories")   ) {
//                System.out.println(entry.getKey()+ " " + entry.getValue());
//            }
//        }
//        JsonNode rootNode = objectMapper.readTree(json);
//
//    Iterator<Map.Entry<String, JsonNode>> it = rootNode.get("categories").iterator().next().fields();
//           while (it.hasNext()){
//               Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) it.next();
//               System.out.println(entry.getKey()+ " "+ entry.getValue());
//
//        }


//        Iterator<JsonNode> categories = rootNode.findValue("menu").elements();
//        System.out.println(categories.toString());

//
//        JSONObject obj = new JSONObject(map);
//        JSONArray arr = obj.getJSONArray("number");
//        for (int i = 0; i < arr.length(); i++)
//            System.out.println(arr.getInt(i));
//        JsonObject object = Json.parse(input).asObject();
//        int orders = object.get("order").asInt();
//        JsonArray items = object.get("items").asArray();



