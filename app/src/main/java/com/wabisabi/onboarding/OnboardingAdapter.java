package com.wabisabi.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {
    private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_onboarding_slide, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{

        private TextView onboardTitle, onboardDesc;
        private ImageView onboardImage;

        public OnboardingViewHolder(View itemView) {
            super(itemView);

            onboardTitle = itemView.findViewById(R.id.onboarding_title);
            onboardDesc = itemView.findViewById(R.id.onboarding_desc);
            onboardImage = itemView.findViewById(R.id.onboarding_img);
        }
        void setOnboardingData(OnboardingItem onboardingItem) {
            onboardTitle.setText(onboardingItem.getOnboardTitle());
            onboardDesc.setText(onboardingItem.getOnboarddescription());
            onboardImage.setImageResource(onboardingItem.getOnboardImage());

        }
    }
}
