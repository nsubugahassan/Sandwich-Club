package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {
        Log.d(LOG_TAG, "The Json string is: " + json);

        Sandwich sandwich;

        try {
            JSONObject sandwichJsonObject = new JSONObject(json);
            JSONObject nameJsonObject = sandwichJsonObject.getJSONObject("name");
            String mainName = nameJsonObject.getString("mainName");

            List<String> otherSandwichNames = getOtherNamesFromJsonArray(nameJsonObject
                    .getJSONArray("alsoKnownAs"));
            String placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");
            String description = sandwichJsonObject.getString("description");
            String imageUrl = sandwichJsonObject.getString("image");

            List<String> ingredientsList = getIngredientsList(sandwichJsonObject.getJSONArray
                    ("ingredients"));

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
              extractedItems = extractedItems + stringList.get(i);
              if (i != stringList.size() - 1){
                  extractedItems = extractedItems + ", ";
              }
           }
           return extractedItems;
        }else {
            return extractedItems;
        }
    }
}
