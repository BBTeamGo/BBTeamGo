package com.example.bbteamgo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerMyprofileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class CustomerMyprofileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public TextView inputname, feelingMessage,feelingmsgChange,changePw,logOut;

    public EditText eText, eText2;
    public Button sendButton, cancelButton, sendButton2, cancelButton2;
    private FirebaseAuth auth;

    private MaterialButton resetPasswordButton;

    public CustomerMyprofileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CustomerMyprofileFragment newInstance(String param1, String param2) {
        CustomerMyprofileFragment fragment = new CustomerMyprofileFragment();
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
       View view = inflater.inflate(R.layout.fragment_customer_myprofile, container, false);
        auth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        inputname = view.findViewById(R.id.namecard);
        Dialog nameDialog;
        nameDialog = new Dialog(getActivity());
        nameDialog.setContentView(R.layout.namedialog);
        eText = nameDialog.findViewById(R.id.inputName);
        sendButton = nameDialog.findViewById(R.id.changeButton);
        cancelButton = nameDialog.findViewById(R.id.cancelButton);
        inputname.setOnClickListener( view4 ->
        {
            sendButton.setOnClickListener(view1 -> {
                inputname.setText(eText.getText().toString());
                Log.d("cmh", eText.getText().toString());
                nameDialog.dismiss();
            });
            cancelButton.setOnClickListener(view2 -> {
                nameDialog.dismiss();
            });
            inputname.setText(eText.getText().toString());
            nameDialog.show();

        });

        //프로필 사진 변경하는 부분
        // 이거는 일단 외부 갤러리에서 데이터를 가져와서 사용해야하기 떄문에 좀 미루고
        //상태메세지를 변경하는 부분

        feelingMessage = view.findViewById(R.id.feelingMessage);
        Dialog feelDialog;
        feelDialog = new Dialog(getActivity());
        feelDialog.setContentView(R.layout.feelingdialog);
        feelingmsgChange = view.findViewById(R.id.feelingmsgChange);
        eText2 = feelDialog.findViewById(R.id.feelingInput);
        sendButton2 = feelDialog.findViewById(R.id.changeButton);
        cancelButton2 = feelDialog.findViewById(R.id.cancelButton);
        feelingmsgChange.setOnClickListener(view5 -> {
            sendButton2.setOnClickListener(view1 -> {
                feelingMessage.setText(eText2.getText().toString());
                Log.d("cmh", eText2.getText().toString());
                feelDialog.dismiss();
            });
            cancelButton2.setOnClickListener(view2 -> {
                feelDialog.dismiss();
            });
            feelingMessage.setText(eText2.getText().toString());
            feelDialog.show();
        });

        //로그아웃 기능은 버튼 눌렀을 때 그냥 초기화면으로 보내면 되니까 쉽고

        logOut = view.findViewById(R.id.logOut);
        logOut.setOnClickListener(view1->{
          logout();
        });


        //비밀번호 변경은 서버와의 연결이 최우선이니까 이게 제일 중요할듯
        changePw = view.findViewById(R.id.changePw);

        changePw.setOnClickListener(view1->
        {
            sendPasswordResetEmail();
        });

    }

    private void logout() {
        auth.signOut();

        // 로그아웃 후 MainActivity로 이동
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }


    private void sendPasswordResetEmail() {
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            String email = currentUser.getEmail();

            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(requireActivity(), task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "비밀번호 재설정 이메일이 전송되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "비밀번호 재설정 이메일 전송에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(requireContext(), "로그인된 사용자가 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }





}


