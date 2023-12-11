package com.example.bbteamgo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bbteamgo.databinding.FragmentManagerReservationBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManagerReservationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagerReservationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentManagerReservationBinding binding;

    public ManagerReservationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManagerReservationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManagerReservationFragment newInstance(String param1, String param2) {
        ManagerReservationFragment fragment = new ManagerReservationFragment();
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
        binding = FragmentManagerReservationBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<BookingManagementData> data = new ArrayList<BookingManagementData>();
        data.add(new BookingManagementData(1, "조민혁", "20분 30초"));
        data.add(new BookingManagementData(2, "공민기", "15분 21초"));
        data.add(new BookingManagementData(3, "이종은", "12분 30초"));

        binding.bookingManagementRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.bookingManagementRecyclerview.setAdapter(new BookingManagementAdapter(data));
    }

    /**
     * protected void onCreate(Bundle savedInstanceState) {
     *         super.onCreate(savedInstanceState);
     *         ActivityBookingManagementPageBinding binding = ActivityBookingManagementPageBinding.inflate(getLayoutInflater());
     *         setContentView(binding.getRoot());
     *         //setContentView(R.layout.activity_booking_management_page);
     *
     *         //RecyclerView recyclerView = findViewById(R.id.booking_management_recyclerview);
     *
     *         List<BookingManagementData> data = new ArrayList<BookingManagementData>();
     *         data.add(new BookingManagementData(1, "조민혁", "20분 30초"));
     *         data.add(new BookingManagementData(2, "공민기", "15분 21초"));
     *         data.add(new BookingManagementData(3, "이종은", "12분 30초"));
     *
     *
     *         /*추가하고 싶은 데이터를 위의 양식 (순서(int), 예약자명, 경과시간(타이머로 나중에 바꿔야됌) 을 적어주면 리스트에 추가된다.
     *
             *         //recyclerView.setLayoutManager(new LinearLayoutManager(this));
             *         //recyclerView.setAdapter(new BookingManagementAdapter(getApplicationContext(),data));
             *
             *binding.bookingManagementRecyclerview.setLayoutManager(new

    LinearLayoutManager(this));
     *binding.bookingManagementRecyclerview.setAdapter(new

    BookingManagementAdapter(data));
     **/
}