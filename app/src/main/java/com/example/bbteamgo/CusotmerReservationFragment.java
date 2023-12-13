package com.example.bbteamgo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

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
    private  class ReservationViewHolder extends RecyclerView.ViewHolder{

        //여기에 바인딩을 할 내 목록과 관련된 xml 하나를 걸어두면 된다!

        public ReservationViewHolder(@NonNull View itemView) { //여기에 들어가는 매개변수도 binding형태로 넣고 얘가 어떤 애인지 알려주고
            super(itemView);

        }
        private void bind(){
            //내가 필요한 매개변수 같은 것들을 넣어서 설정해주면 되는 것이다.
            //firebase로 그 안에 있는 것까지 접근하게끔 코드를 작성하면 될듯
        }
    }
    private class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder>{

        @NonNull
        @Override
        public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {




            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
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