package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();
    private static final String NAME = "name";
    private static final String SANDWICH_MAIN_NAME = "mainName";
    private static final String SANDWICH_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String SANDWICH_DESCRIPTION = "description";
    private static final String SANDWICH_IMAGE = "image";
    private static final String SANDWICH_INGREDIENTS = "ingredients";
    public static final String data_not_available = "Data not available";


    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich;

        try {
            JSONObject sandwichJsonObject = new JSONObject(json);
            JSONObject nameJsonObject = sandwichJsonObject.optJSONObject(NAME);

            String mainName = nameJsonObject.optString(SANDWICH_MAIN_NAME,data_not_available);

            List<String> otherSandwichNames = getOtherNamesFromJsonArray(nameJsonObject
                    .getJSONArray(SANDWICH_ALSO_KNOWN_AS));

            String placeOfOrigin = sandwichJsonObject.optString(SANDWICH_PLACE_OF_ORIGIN,data_not_available);
            String description = sandwichJsonObject.optString(SANDWICH_DESCRIPTION,data_not_available);
            String imageUrl = sandwichJsonObject.optString(SANDWICH_IMAGE,data_not_available);

            List<String> ingredientsList = getIngredientsList(sandwichJsonObject.getJSONArray
                    (SANDWICH_INGREDIENTS));

            sandwich = new Sandwich(mainName,otherSandwichNames,placeOfOrigin,description,
                    imageUrl,ingredientsList);


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return sandwich;
    }

    private static List<String> getOtherNamesFromJsonArray(JSONArray alsoKnownAs){
        List<String> allOtherNamesList = new ArrayList<>();
        if (alsoKnownAs != null && alsoKnownAs.length() > 0){

            try {

                for (int i=0; i < alsoKnownAs.length(); i++){
                    allOtherNamesList.add(alsoKnownAs.getString(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return allOtherNamesList;
        }else{
            return null;
        }
    }

    private static List<String> getIngredientsList(JSONArray ingredientsJsonArray){
        List<String> ingredientsList = new ArrayList<>();
        if (ingredientsJsonArray != null && ingredientsJsonArray.length() > 0){

            try {

                for (int i=0; i < ingredientsJsonArray.length(); i++){
                    ingredientsList.add(ingredientsJsonArray.getString(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            return null;
        }

        return ingredientsList;
    }

    public static String getItemsFromList(List<String> stringList){
        String extractedItems = "";
        if (stringList != null && stringList.size() > 0){
           for (int i=0; i < stringList.size(); i++){
                extractedItems = TextUtils.join(", ",stringList);
           }
           return extractedItems;
        }else {
            return extractedItems;
        }
    }
}
