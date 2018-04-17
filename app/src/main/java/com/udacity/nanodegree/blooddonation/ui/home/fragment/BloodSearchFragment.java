package com.udacity.nanodegree.blooddonation.ui.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.udacity.nanodegree.blooddonation.R;
import com.udacity.nanodegree.blooddonation.adapter.BloodGroupAdapter;
import com.udacity.nanodegree.blooddonation.util.Util;
import com.udacity.nanodegree.blooddonation.widget.GridSpacingItemDecoration;

import java.util.Arrays;
import java.util.List;

public class BloodSearchFragment extends Fragment implements BloodGroupAdapter.OnItemClickListener,
        View.OnClickListener {

    private Context mContext;

    private Spinner mStateSelectSpinner;
    private Spinner mCitySelectSpinner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_blood, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_blood_groups);
        mStateSelectSpinner = view.findViewById(R.id.state_select_spinner);
        mCitySelectSpinner = view.findViewById(R.id.city_select_spinner);


        List<String> bloodGroupList = Arrays.asList(
                getResources().getStringArray(R.array.blood_groups));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(
                new GridSpacingItemDecoration(4, Util.dpToPx(mContext, 10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new BloodGroupAdapter(getActivity(), bloodGroupList));
    }

    @Override
    public void onItemClick(String bloodGroup, int position) {

    }

    @Override
    public void onClick(View v) {

    }
}
