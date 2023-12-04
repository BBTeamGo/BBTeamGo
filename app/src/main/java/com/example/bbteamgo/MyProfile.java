package com.example.bbteamgo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bbteamgo.databinding.ActivityMyProfileBinding;

public class MyProfile extends AppCompatActivity {
    private ActivityMyProfileBinding binding;

    public TextView inputname, feelingMessage;

    public EditText eText, eText2;
    public Button sendButton, cancelButton, sendButton2, cancelButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//     `   backButton = binding.backButton;
//        backButton.setOnClickListener(view -> {
//            //전 화면이 없기 때문에 사실상 의미없지않나??그렇네
//
//
//        });`
        //여기는 이제 이름 바꾸는 다이얼로그 위치
        inputname = binding.namecard;
        Dialog nameDialog;
        nameDialog = new Dialog(MyProfile.this);
        nameDialog.setContentView(R.layout.namedialog);
        eText = nameDialog.findViewById(R.id.inputName);
        sendButton = nameDialog.findViewById(R.id.changeButton);
        cancelButton = nameDialog.findViewById(R.id.cancelButton);
        inputname.setOnClickListener(view ->
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
        feelingMessage = binding.feelingMessage;
        Dialog feelDialog;
        feelDialog = new Dialog(MyProfile.this);
        feelDialog.setContentView(R.layout.feelingdialog);
        eText2 = feelDialog.findViewById(R.id.feelingInput);
        sendButton2 = feelDialog.findViewById(R.id.changeButton);
        cancelButton2 = feelDialog.findViewById(R.id.cancelButton);
        feelingMessage.setOnClickListener(view -> {
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
        //비밀번호 변경은 서버와의 연결이 최우선이니까 이게 제일 중요할듯



    }

}