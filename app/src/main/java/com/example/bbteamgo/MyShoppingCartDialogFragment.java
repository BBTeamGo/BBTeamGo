package com.example.bbteamgo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbteamgo.R;

public class MyShoppingCartDialogFragment extends DialogFragment {

    private RecyclerView myShoppingCartRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_shopping_cart_dialog_box, container, false);

        // RecyclerView 초기화
        myShoppingCartRecyclerView = view.findViewById(R.id.my_shopping_cart_recyclerview);
        myShoppingCartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // TODO: RecyclerView 어댑터 설정 (추가로 구현 필요)

        return view;
    }
}