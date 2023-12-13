package com.example.bbteamgo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NowFestivalFragement extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private TextView textView;

    private DetailAdapter detailAdapter;

    private RecyclerView recyclerView;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public NowFestivalFragement() {
        // Required empty public constructor
    }


    public static NowFestivalFragement newInstance(String param1, String param2) {
        NowFestivalFragement fragment = new NowFestivalFragement();

        return fragment;
    }

    private class Detail {
        public Detail(String detail, String name, String total) {
            this.detail = detail;
            this.name = name;
            this.total = total;
        }


        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }


        private String detail;
        private String name;


        private String total;

    }


    private void loadDetailData() {
        // Firestore의 "festivals" 컬렉션에서 데이터를 가져옴
        firestore.collection("University").document().collection("Pubs")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Detail> detailList = new ArrayList<>();

                        // 가져온 데이터를 Festival 객체로 변환하여 리스트에 추가
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String festivalId = document.getId();
                            String detailName = document.getString("name");
                            String detailTxt = document.getString("detail");
                            String TotalPeople = document.getString("Total");

                            NowFestivalFragement.Detail detail = new NowFestivalFragement.Detail(detailName, detailTxt, TotalPeople);
                            detailList.add(detail);
                        }
                        //여기 부분을 detail과 관련된 아이템들로 바꿔야겠네
                        // 가져온 데이터를 RecyclerView에 표시
                        detailAdapter = new DetailAdapter(detailList);
                        recyclerView.setAdapter(detailAdapter);
                    } else {
                        // 실패한 경우 에러 처리
                        Log.e("FirestoreExample", "Error getting documents.", task.getException());
                    }
                });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_festival_fragement, container, false);

        recyclerView = view.findViewById(R.id.detailRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadDetailData();

        return view;
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

    private class DetailViewHolder extends RecyclerView.ViewHolder {
        private TextView  detailNameTextView;
        private TextView  detailTextView;
        private TextView countTextView;
        private NowFestivalFragement nowFestivalFragement;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            detailNameTextView = itemView.findViewById(R.id.detailName);
            detailTextView = itemView.findViewById(R.id.detailTxt);
            countTextView = itemView.findViewById(R.id.count);

        }

        private void bind(Detail detail) {
            detailNameTextView.setText(detail.getName());
            detailTextView.setText(detail.getDetail());
            countTextView.setText(detail.getTotal());
        }
    }

    private class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder> {

        public DetailAdapter(List<Detail> detailList) {
            this.detailList = detailList;
        }

        private List<Detail> detailList;


        @NonNull
        @Override
        public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_festival_detail, parent, false);
            return new DetailViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
            Detail detail = detailList.get(position);
            holder.bind(detail);
        }

        @Override
        public int getItemCount() {
            return detailList.size();
        }
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


