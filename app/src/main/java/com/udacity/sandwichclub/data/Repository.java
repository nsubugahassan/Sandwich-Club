package com.udacity.sandwichclub.data;

import com.udacity.sandwichclub.model.Sandwich;


public class Repository {
    private static final Object LOCK = new Object();
    private static Repository sInstance;
    private Sandwich mSandwich;

    private Repository(Sandwich sandwich) {
        this.mSandwich = sandwich;
    }

    public  synchronized static Repository getsInstance(Sandwich sandwich){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = new Repository(sandwich);
            }
        }

        return sInstance;
    }

    public Sandwich getSandwich(){
        return mSandwich;
    }
}
