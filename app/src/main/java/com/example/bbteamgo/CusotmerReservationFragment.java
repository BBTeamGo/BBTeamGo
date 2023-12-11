package com.example.bbteamgo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bbteamgo.databinding.FragmentCusotmerReservationBinding;


public class CusotmerReservationFragment extends Fragment {


    public CusotmerReservationFragment() {
        // Required empty public constructor
    }

    public static CusotmerReservationFragment newInstance(String param1, String param2) {
        CusotmerReservationFragment fragment = new CusotmerReservationFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCusotmerReservationBinding binding = FragmentCusotmerReservationBinding.inflate(inflater,container,false);

        binding.addReservation.setOnClickListener(view -> {

            getActivity().
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view_tag,new CusotmerHomeFragment()).commit();
        }); //이거 이동은 되는데 바텀 네비게이션도 움직이게 할 수 있어야하네


        return binding.getRoot();
    }

}
//