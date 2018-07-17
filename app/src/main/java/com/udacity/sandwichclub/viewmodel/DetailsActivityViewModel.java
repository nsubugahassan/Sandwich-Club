package com.udacity.sandwichclub.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.udacity.sandwichclub.data.Repository;
import com.udacity.sandwichclub.model.Sandwich;

public class DetailsActivityViewModel extends ViewModel{
    private static final String LOG_TAG = DetailsActivityViewModel.class.getSimpleName();
    private Repository mRepository;
    private Sandwich mSandwich;

    public DetailsActivityViewModel(Repository repository) {
        mRepository = repository;
        mSandwich = mRepository.getSandwich();

    }

    public Sandwich getmSandwich(){
        Log.d(LOG_TAG, "the sandwich title is: " + mSandwich.getMainName() + " " + mSandwich
                .getDescription());
        return mSandwich;
    }


}
