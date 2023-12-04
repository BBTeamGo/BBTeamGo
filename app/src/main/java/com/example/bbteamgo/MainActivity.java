package com.example.bbteamgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth userAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        userAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        checkLogin();
    }

    private void checkLogin() {
        FirebaseUser currentUser = userAuth.getCurrentUser();

        if (currentUser != null) {
            Intent intent = new Intent(this, ManagerActivity.class);
            intent.putExtra("USER_PROFILE", "email: " + currentUser.getEmail() + "\n" + "uid: " + currentUser.getUid());

            // 관리자냐 소비자냐인지 알기 위해 쿼리해서 액티비티 전환
            startActivity(intent);
        }
        else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_view_tag, InitialScreenFragment.newInstance("param1", "param2"));
            fragmentTransaction.commit();
        }

    }
}