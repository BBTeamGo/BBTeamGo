package com.example.bbteamgo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NowFestivalFragement extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private TextView textView;

    public NowFestivalFragement() {
        // Required empty public constructor
    }


    public static NowFestivalFragement newInstance(String param1, String param2) {
        NowFestivalFragement fragment = new NowFestivalFragement();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_now_festival_fragement, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        textView = view.findViewById(R.id.returntxt);
        textView.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container_view_tag, new CusotmerHomeFragment()).commit();
        });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng SoongSil = new LatLng(37.2946, 126.5726);
        mMap.addMarker(new MarkerOptions().position(SoongSil).title("Marker in SoongSil"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SoongSil, 14));

    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}


