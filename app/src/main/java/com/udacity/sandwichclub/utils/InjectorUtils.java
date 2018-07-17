package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.data.Repository;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.viewmodel.DetailsActivityViewModelFactory;

public class InjectorUtils {

    private static final String LOG_TAG = InjectorUtils.class.getSimpleName();

    public static Repository provideRepository(Context context, int position){
        String[] sandwiches = context.getApplicationContext().getResources().getStringArray(R
                .array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

//        Log.d(LOG_TAG, "the sandwich title is: " + sandwich.getMainName() + " " + sandwich
//                .getDescription());

        return Repository.getsInstance(sandwich);
    }

    public static DetailsActivityViewModelFactory provideDetailsActivityViewModelFactory(Context context, int position){

        Repository repository = provideRepository(context,position);

        return new DetailsActivityViewModelFactory(repository);

    }
}
