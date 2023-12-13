package com.example.bbteamgo;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;

import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CusotmerHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CusotmerHomeFragment extends Fragment implements OnMapReadyCallback {


    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private FestivalAdapter festivalAdapter;


    private GoogleMap mMap;


    public CusotmerHomeFragment() {
        // Required empty public constructor
    }


    public static CusotmerHomeFragment newInstance(String param1, String param2) {
        CusotmerHomeFragment fragment = new CusotmerHomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cusotmer_home, container, false);

        // RecyclerView 초기화
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 축제 데이터를 가져오는 메서드 호출
        loadFestivalData();
//
        EditText search = (EditText) view.findViewById(R.id.search);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String location = search.getText().toString();
                    List<Address> addressList = null;

                    if (location != null || !location.equals("")) {
                        Geocoder geocoder = new Geocoder(getActivity());
                        try {
                            addressList = geocoder.getFromLocationName(location, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    }
                }
                return false;
            }
        });


//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync((OnMapReadyCallback) this);
//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapContainer);
//        if (mapFragment == null) {
//            mapFragment = SupportMapFragment.newInstance();
//            getChildFragmentManager().beginTransaction().replace(R.id.mapContainer, mapFragment).commit();
//            mapFragment.getMapAsync((OnMapReadyCallback) this);
//            Log.i("cmh", "여기 실행됨");
//        }
        return view;
        //여기서 return 시킨 view를 띄우는 onViewCreated에 띄우는거네 ㅇㅋ
    }

    private void loadFestivalData() {
        // Firestore의 "festivals" 컬렉션에서 데이터를 가져옴
        firestore.collection("University")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Festival> festivalList = new ArrayList<>();

                        // 가져온 데이터를 Festival 객체로 변환하여 리스트에 추가
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String festivalId = document.getId();
                            String festivalName = document.getString("festival_name");
                            String festivalExplain = document.getString("explain_text");
                            String festivalImg = document.getString("picture_url");

                            Festival festival = new Festival(festivalId, festivalName, festivalExplain, festivalImg);
                            festivalList.add(festival);
                        }

                        // 가져온 데이터를 RecyclerView에 표시
                        festivalAdapter = new FestivalAdapter(festivalList);
                        recyclerView.setAdapter(festivalAdapter);
                    } else {
                        // 실패한 경우 에러 처리
                        Log.e("FirestoreExample", "Error getting documents.", task.getException());
                    }
                });
    }

    public class Festival {
        private String festivalId;
        private String festivalName;
        private String festivalExplain;
        private String festivalImg;

        public Festival(String festivalId, String festivalName, String festivalExplain, String festivalImg) {
            this.festivalId = festivalId;
            this.festivalName = festivalName;
            this.festivalExplain = festivalExplain;
            this.festivalImg = festivalImg;
        }

        public String getFestivalId() {
            return festivalId;
        }

        public void setFestivalId(String festivalId) {
            this.festivalId = festivalId;
        }

        public String getFestivalName() {
            return festivalName;
        }

        public void setFestivalName(String festivalName) {
            this.festivalName = festivalName;
        }

        public String getFestivalExplain() {
            return festivalExplain;
        }

        public void setFestivalExplain(String festivalExplain) {
            this.festivalExplain = festivalExplain;
        }

        public String getFestivalImg() {
            return festivalImg;
        }

        public void setFestivalImg(String festivalImg) {
            this.festivalImg = festivalImg;
        }
    }

    public class FestivalViewHolder extends RecyclerView.ViewHolder {
        private TextView festivalNameTextView;
        private TextView festivalExplainTextView;
        private CusotmerHomeFragment customerHomeFragment;

        public FestivalViewHolder(@NonNull View itemView) {
            super(itemView);

            festivalNameTextView = itemView.findViewById(R.id.festival_name);
            festivalExplainTextView = itemView.findViewById(R.id.festival_exp);

            NowFestivalFragement nowFestivalFragment = new NowFestivalFragement();
            //얘가 왜 정상적으로 넘어가지 않는 건지 진짜 알 수가 없네
            itemView.setOnClickListener(view -> {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_view_tag,new NowFestivalFragement()).commit();
            });
        }
        public void bind(Festival festival) {
            festivalNameTextView.setText(festival.getFestivalName());
            festivalExplainTextView.setText(festival.getFestivalExplain());
            // 필요한 경우 추가적인 데이터 바인딩 수행
        }
    }
    public class FestivalAdapter extends RecyclerView.Adapter<FestivalViewHolder> {
        private List<Festival> festivalList;

        public FestivalAdapter(List<Festival> festivalList) {
            this.festivalList = festivalList;
        }

        @NonNull
        @Override
        public FestivalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_festival, parent, false);
            return new FestivalViewHolder(view); // CusotmerHomeFragment.this를 전달

        }

        //    @Override
//    public void onBindViewHolder(@NonNull FestivalViewHolder holder, int position) {
//        Festival festival = festivalList.get(position);
//        holder.bind(festival);
//    }
        public void onBindViewHolder(@NonNull FestivalViewHolder holder, int position) {
            Festival festival = festivalList.get(position);
            holder.bind(festival);
        }

        @Override
        public int getItemCount() {
            return festivalList.size();
        }

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng SoongSil = new LatLng(37.2946, 126.5726);
        mMap.addMarker(new MarkerOptions().position(SoongSil).title("Marker in SoongSil"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SoongSil, 14));

    }

    private FragmentManager getSupportFragmentManager() {
        return null;
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}


