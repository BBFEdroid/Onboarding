package com.wabisabi.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;


public class OnboardingMainActivity extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout onboardIndicators;
    private Button onboardingButtonAction;
    private TextView onboardSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_main);

        onboardIndicators = findViewById(R.id.onboardingIndicators);
        onboardingButtonAction = findViewById(R.id.onboarding_btn_action);
        onboardSkip = findViewById(R.id.onboarding_btn_skip);

        setUpOnboardingItems();
        setUpOnboardIndicators();
        setCurrentOnboarndicator(0);


        ViewPager2 onboardingViewPager = findViewById(R.id.onboarding_view_pager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboarndicator(position);
            }
        });
        onboardingButtonAction.setOnClickListener( v -> {
            if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1 );
            }else{
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        onboardSkip.setOnClickListener( v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
    private void setUpOnboardingItems() {

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemOnboardOne = new OnboardingItem();
        itemOnboardOne.setOnboardTitle("Onboarding Title One");
        itemOnboardOne.setOnboarddescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,");
        itemOnboardOne.setOnboardImage(R.drawable.onboard_illustration1);

        OnboardingItem itemOnboardTwo = new OnboardingItem();
        itemOnboardTwo.setOnboardTitle("Onboarding Title Two");
        itemOnboardTwo.setOnboarddescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ");
        itemOnboardTwo.setOnboardImage(R.drawable.onboard_illustration2);


        OnboardingItem itemOnboardThree = new OnboardingItem();
        itemOnboardThree.setOnboardTitle("Onboarding Title Three");
        itemOnboardThree.setOnboarddescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ");
        itemOnboardThree.setOnboardImage(R.drawable.onboard_illustration3);

        onboardingItems.add(itemOnboardOne);
        onboardingItems.add(itemOnboardTwo);
        onboardingItems.add(itemOnboardThree);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);


    }
    private void setUpOnboardIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8 ,0, 8,0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            onboardIndicators.addView(indicators[i]);
        }
    }
    private void setCurrentOnboarndicator(int index) {
        int childCount = onboardIndicators.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView)onboardIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount() - 1) {
            onboardingButtonAction.setText("Start");
        }else {
            onboardingButtonAction.setText("Next");
        }
    }
}