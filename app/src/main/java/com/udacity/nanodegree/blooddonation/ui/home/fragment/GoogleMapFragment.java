package com.udacity.nanodegree.blooddonation.ui.home.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.udacity.nanodegree.blooddonation.R;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    private static final LatLng MARKER_POS_1 = new LatLng(37.8086406d, -122.4159991d);
    private static final LatLng MARKER_POS_2 = new LatLng(37.8067461d, -122.4220228d);
    private static final float ZOOM_LEVEL = 15f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_google_map, container, false);


        SupportMapFragment mapFragment = new SupportMapFragment();
        mapFragment.getMapAsync(this);

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, mapFragment)
                .commit();


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MARKER_POS_1, ZOOM_LEVEL));
        googleMap.addMarker(new MarkerOptions().position(MARKER_POS_1));
        googleMap.addMarker(new MarkerOptions().position(MARKER_POS_2));
    }
}
