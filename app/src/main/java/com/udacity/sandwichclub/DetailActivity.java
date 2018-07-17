package com.udacity.sandwichclub;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.InjectorUtils;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.viewmodel.DetailsActivityViewModel;
import com.udacity.sandwichclub.viewmodel.DetailsActivityViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    DetailsActivityViewModel mDetailsActivityViewModel;


    ActivityDetailBinding mBinding;
    Sandwich sandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ImageView ingredientsIv = findViewById(R.id.image_iv);
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        DetailsActivityViewModelFactory factory = InjectorUtils
                .provideDetailsActivityViewModelFactory(this.getApplicationContext(),position);
        mDetailsActivityViewModel = ViewModelProviders.of(this,factory).get
                (DetailsActivityViewModel.class);
       sandwich = mDetailsActivityViewModel.getmSandwich();

//        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
//        String json = sandwiches[position];
//        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home){
            onBackPressed();
        }
        return true; //super.onOptionsItemSelected(item);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        if (sandwich != null) {
            mBinding.textViewSandwichName.setText(sandwich.getMainName());
            String otherNames = JsonUtils.getItemsFromList(sandwich.getAlsoKnownAs());
            mBinding.alsoKnownTv.setText(otherNames);
            mBinding.originTv.setText(sandwich.getPlaceOfOrigin());
            mBinding.descriptionTv.setText(sandwich.getDescription());
            String ingredients = JsonUtils.getItemsFromList(sandwich.getIngredients());
            mBinding.ingredientsTv.setText(ingredients);
        }
    }


}
