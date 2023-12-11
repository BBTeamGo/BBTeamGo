package com.example.bbteamgo;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.fragment.app.Fragment;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {
//
//    private GoogleMap googleMap;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_maps, container, false);
//
//        // SupportMapFragment를 추가
//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
//        if (mapFragment == null) {
//            mapFragment = SupportMapFragment.newInstance();
//            getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
//        }
//        mapFragment.getMapAsync(this);
//
//        return view;
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        this.googleMap = googleMap;
//
//        // 지도 초기 설정
//        LatLng defaultLocation = new LatLng( 37.2946, 126.5726); // 숭실대학교 좌표
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15));
//
//        // 마커 추가
//        googleMap.addMarker(new MarkerOptions().position(defaultLocation).title("Marker in San Francisco"));
//    }
//}

//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AutoCompleteTextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {
//
//    private GoogleMap googleMap;
//    private AutoCompleteTextView autoCompleteTextView;
//
//    MapView mapView;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_maps, container, false);
//
//        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
//        Log.i("cmh","여기가에러");
//        autoCompleteTextView.setAdapter(new PlaceAutocompleteAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line));
//        Log.i("cmh","여기가에러");
//
//        // SupportMapFragment를 추가
////        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
////        if (mapFragment == null) {
////            mapFragment = SupportMapFragment.newInstance();
////            getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
////            mapFragment.getMapAsync(this);
////            Log.i("cmh","여기 실행됨");
////        }
//
//        // Return은 여기서 수행되도록 변경
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        this.googleMap = googleMap;
//
//        // 지도 초기 설정
//        LatLng defaultLocation = new LatLng(37.2946, 126.5726); // 숭실대학교 좌표
//        Log.i("cmh","여기가에러");
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 20));
//        Log.i("cmh","여기가에러");
//        // 마커 추가
//        googleMap.addMarker(new MarkerOptions().position(defaultLocation).title("숭실대를 가리키는 중"));
//    }
//}
