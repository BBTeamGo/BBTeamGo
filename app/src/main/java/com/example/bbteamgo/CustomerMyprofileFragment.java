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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerMyprofileFragment extends Fragment {

    public TextView inputname, feelingMessage, feelingmsgChange, changePw, logOut;

    public EditText eText, eText2;
    public Button sendButton, cancelButton, sendButton2, cancelButton2;
    private FirebaseAuth auth;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    private MaterialButton resetPasswordButton;

    public CustomerMyprofileFragment() {
        // Required empty public constructor
    }

    public static CustomerMyprofileFragment newInstance(String param1, String param2) {
        CustomerMyprofileFragment fragment = new CustomerMyprofileFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("PROFILE", "OnCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_myprofile, container, false);
        auth = FirebaseAuth.getInstance();
        loadUserProfile();
        Log.d("PROFILE", "OnCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("PROFILE", "Start OnViewCreated()");

        inputname = view.findViewById(R.id.namecard);
        Dialog nameDialog;
        nameDialog = new Dialog(getActivity());
        nameDialog.setContentView(R.layout.namedialog);

        Log.d("PROFILE", "Name Dialog");

        eText = nameDialog.findViewById(R.id.inputName);
        sendButton = nameDialog.findViewById(R.id.changeButton);
        cancelButton = nameDialog.findViewById(R.id.cancelButton);
        inputname.setOnClickListener(view4 ->
        {
            sendButton.setOnClickListener(view1 -> {
                inputname.setText(eText.getText().toString());
                Log.d("cmh", eText.getText().toString());
                saveDisplayName();
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
        //사진 몇 개를 db에 올려두고 거기서 선택하거나 사진은 그냥 기본 이미지로 두는 식으로 해도 괜찮을 듯






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
                saveStatusMessage();
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
        logOut.setOnClickListener(view1 -> {
            logout();
        });
        //비밀번호 변경은 서버와의 연결이 최우선이니까 이게 제일 중요할듯
        changePw = view.findViewById(R.id.changePw);

        changePw.setOnClickListener(view1 ->
        {
            sendPasswordResetEmail();
        });

        Log.d("PROFILE", "OnViewCreated() End");
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

    private void loadUserProfile() {
        Log.d("PROFILE", "Start LoadUserProfile");
        // 현재 로그인된 사용자의 UID 가져오기
        String uid = auth.getCurrentUser().getUid().toString();
        Log.d("PROFILE", "Complete Get UID: " + uid);
        // Firestore에서 해당 사용자의 정보 불러오기
        DocumentReference userRef = database.collection("User").document(uid);

        Log.d("PROFILE", "Get Instance");

        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // 사용자 정보가 Firestore에 저장되어 있는 경우, UI에 표시
                        String displayName = document.getString("display_name");
                        String statusMessage = document.getString("status_message");

                        inputname.setText(displayName);
                        feelingMessage.setText(statusMessage);
                    }
                }
            }
        });

        Log.d("PROFILE", "Complete");
    }

    private void saveDisplayName() {
        String uid = auth.getCurrentUser().getUid();
        String displayName = eText.getText().toString();


        DocumentReference userRef = firestore.collection("User").document(uid);
        userRef.update("display_name", display_name)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // 업데이트 성공
                        } else {
                            // 업데이트 실패
                        }
                    }
                });
    }

    private void saveStatusMessage() {
        String uid = auth.getCurrentUser().getUid();
        String statusMessage = eText2.getText().toString();

        DocumentReference userRef = firestore.collection("User").document(uid);
        userRef.update("status_message", status_message)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // 업데이트 성공
                        } else {
                            // 업데이트 실패
                        }
                    }
                });
    }
    //이쪽은 종은이가 firebase 로그인쪽을 어떻게 건드는지에 따라서 수정하면 될 듯 이게 불러올 정보가 없으니까
    // load하는데 안에 있는게 없으니까 종료되는거네 ㅇㅋㅁ
    //그치 데이터 제대로 가져와서 만들 수 있도록 하자
    //그리고 프로필사진은 선택할 수 있도록 설정하는게 좋아보인다.
}
//이거 firebase 수정해야지만 돌아간다.