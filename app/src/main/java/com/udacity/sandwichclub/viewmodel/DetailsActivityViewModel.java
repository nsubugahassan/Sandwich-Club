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
    }

    public Sandwich getmSandwich(int position){
        mSandwich = mRepository.getSandwich(position);
        return mSandwich;
    }


}
