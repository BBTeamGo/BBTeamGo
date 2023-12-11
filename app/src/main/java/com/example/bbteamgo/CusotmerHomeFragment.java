package com.example.bbteamgo;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CusotmerHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CusotmerHomeFragment extends Fragment {


    private FirebaseFirestore firestore;
    private RecyclerView recyclerView;
    private FestivalAdapter festivalAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public CusotmerHomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CusotmerHomeFragment newInstance(String param1, String param2) {
        CusotmerHomeFragment fragment = new CusotmerHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cusotmer_home, container, false);
        firestore = FirebaseFirestore.getInstance();

        // RecyclerView 초기화
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 축제 데이터를 가져오는 메서드 호출
        loadFestivalData();


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

        public FestivalViewHolder(@NonNull View itemView) {
            super(itemView);
            festivalNameTextView = itemView.findViewById(R.id.festival_name);
            festivalExplainTextView = itemView.findViewById(R.id.festival_exp);
            // 예시로 TextView를 사용했습니다.
        }

        public void bind(Festival festival) {
            festivalNameTextView.setText(festival.getFestivalName());
            festivalExplainTextView.setText(festival.getFestivalExplain());
            // 필요한 경우 다른 뷰에 대한 데이터 바인딩도 수행할 수 있습니다.
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
            return new FestivalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FestivalViewHolder holder, int position) {
            Festival festival = festivalList.get(position);
            holder.bind(festival);
        }

        @Override
        public int getItemCount() {
            return festivalList.size();
        }
    }

    private void addGoogleMapFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        GoogleMapFragment googleMapFragment = new GoogleMapFragment();
        fragmentTransaction.replace(R.id.map, googleMapFragment);
        fragmentTransaction.addToBackStack(null); // 백 스택에 추가하면 뒤로 가기 버튼으로 Fragment를 제거할 수 있음
        fragmentTransaction.commit();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

