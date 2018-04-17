package com.udacity.nanodegree.blooddonation.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udacity.nanodegree.blooddonation.R;

import java.util.List;

/**
 * Created by bloodybadboy on Apr, 2018.
 */

public class BloodGroupAdapter extends RecyclerView.Adapter<BloodGroupAdapter.WinnerViewHolder> {
    private final Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<String> mBloodGroupsList;
    private BloodGroupAdapter.OnItemClickListener mListener;
    private int selectedPosition = -1;

    public static interface OnItemClickListener {
        void onItemClick(String bloodGroup, int position);
    }

    public BloodGroupAdapter(Activity activity, List<String> bloodGroupsList) {
        this.mActivity = activity;
        mLayoutInflater = LayoutInflater.from(mActivity);
        this.mBloodGroupsList = bloodGroupsList;
    }

    @NonNull
    @Override
    public WinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WinnerViewHolder(
                mLayoutInflater.inflate(R.layout.list_item_blood_group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final WinnerViewHolder viewHolder, int position) {
        String winner = mBloodGroupsList.get(position);

        viewHolder.mBloodGroupView.setText(winner);
        if (selectedPosition == position) {
            viewHolder.mBloodGroupLayout.setBackgroundResource(R.drawable.bg_blood_group_selected);
            viewHolder.mBloodGroupView.setTextColor(
                    ContextCompat.getColor(mActivity, R.color.material_red_500));
        } else {
            viewHolder.mBloodGroupLayout.setBackgroundResource(R.drawable.bg_blood_group);
            viewHolder.mBloodGroupView.setTextColor(
                    ContextCompat.getColor(mActivity, R.color.material_gray_500));
        }
    }

    @Override
    public int getItemCount() {
        return mBloodGroupsList != null ? mBloodGroupsList.size() : 0;
    }

    public void setListener(BloodGroupAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    class WinnerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout mBloodGroupLayout;
        TextView mBloodGroupView;

        WinnerViewHolder(View itemView) {
            super(itemView);
            mBloodGroupLayout = itemView.findViewById(R.id.list_item_blood_group_layout);
            mBloodGroupView = itemView.findViewById(R.id.tv_list_item_blood_group);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            selectedPosition = getAdapterPosition();
            if (mListener != null) {
                mListener.onItemClick(mBloodGroupsList.get(selectedPosition), selectedPosition);
            }
            notifyDataSetChanged();
        }
    }
}
