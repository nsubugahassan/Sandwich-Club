package com.udacity.sandwichclub.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udacity.sandwichclub.data.Repository;

public class DetailsActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final Repository mRepository;

    public DetailsActivityViewModelFactory(Repository repository) {
        mRepository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailsActivityViewModel(mRepository);
    }
}
