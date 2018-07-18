package com.udacity.sandwichclub.utils;

import android.content.Context;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.data.Repository;
import com.udacity.sandwichclub.viewmodel.DetailsActivityViewModelFactory;

public class InjectorUtils {

    public static Repository provideRepository(Context context) {
        String[] sandwiches = context.getApplicationContext().getResources().getStringArray(R
                .array.sandwich_details);
        return Repository.getsInstance(sandwiches);
    }

    public static DetailsActivityViewModelFactory provideDetailsActivityViewModelFactory(Context context) {

        Repository repository = provideRepository(context);

        return new DetailsActivityViewModelFactory(repository);

    }
}
