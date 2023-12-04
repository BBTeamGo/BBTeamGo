package com.example.bbteamgo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManagerOrderlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagerOrderlistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ManagerOrderlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerOrderlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManagerOrderlistFragment newInstance(String param1, String param2) {
        ManagerOrderlistFragment fragment = new ManagerOrderlistFragment();
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
        return inflater.inflate(R.layout.fragment_manager_orderlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    /**
     * Dialog dialog;
     *     ImageButton btnDialogCancel;
     *     @Override
     *     protected void onCreate(Bundle savedInstanceState) {
     *         super.onCreate(savedInstanceState);
     *         ActivityOrderListBinding binding = ActivityOrderListBinding.inflate(getLayoutInflater());
     *         setContentView(binding.getRoot());
     *
     *         List<OrderListData> data = new ArrayList<>();
     *         data.add(new OrderListData("1-2", "1-2번 테이블", "주문경과 시간: 30분 28초", "순대볶음", "오뎅탕", "소주", 16000, 15000, 4500, 55000, false));
     *         data.add(new OrderListData("2-2", "2-2번 테이블", "주문경과 시간: 12분 28초", "퀘사디아", "오뎅탕", "맥주", 10000, 15000, 4000, 29000, false));
     *
     *
     *         binding.orderListRecyclerview.setLayoutManager(new LinearLayoutManager(this));
     *         binding.orderListRecyclerview.setAdapter(new OrderListAdapter(data));
     *
     *         binding.addOrderButton.setOnClickListener(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View v) {
     *                 dialog.show();
     *             }
     *         });
     *
     *
     *         dialog = new Dialog(OrderListActivity.this);
     *         dialog.setContentView(R.layout.add_order_dialog_box);
     *         btnDialogCancel =dialog.findViewById(R.id.cancel_btn);
     *         dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
     *         dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_box_background));
     *         dialog.setCancelable(false);
     *
     *         btnDialogCancel.setOnClickListener(view ->{
     *                 dialog.dismiss();
     *             }
     *         );
     *     }
     */
}