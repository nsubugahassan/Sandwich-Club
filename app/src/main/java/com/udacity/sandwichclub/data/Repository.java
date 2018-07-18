package com.udacity.sandwichclub.data;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;


public class Repository {
    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private String[] mSandwiches;

    private Repository(String[] sandwiches) {
        this.mSandwiches = sandwiches;
    }

    public  synchronized static Repository getsInstance(String[] mSandwiches){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = new Repository(mSandwiches);
            }
        }

        return sInstance;
    }

    public Sandwich getSandwich(int position){
        String json = mSandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        return sandwich;
    }
}
