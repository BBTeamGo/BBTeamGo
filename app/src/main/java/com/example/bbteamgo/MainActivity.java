package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";

    private FirebaseAuth userAuth;
    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        userAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = userAuth.getCurrentUser();

        if (currentUser != null) {
            Log.d(TAG, "FastLogin");
            reload(currentUser);
        }
        else {
            Log.d(TAG, "Start Initial Screen");
            InitScreen();
        }
    }

    private void reload(FirebaseUser currentUser) {
        boolean isFastLogin = true, isManagerLogin = true;

        if (isFastLogin) {
            Intent intent = null;
            if (isManagerLogin) {
                intent = new Intent(this, ManagerSelectBoothActivity.class);
            } else {
                intent = new Intent(this, CustomerActivity.class);
            }

            Log.d(TAG, "fast Login email:" + currentUser.getEmail());
            Log.d(TAG, "fast Login Uid:" + currentUser.getUid());
            intent.putExtra("USER_PROFILE", "email: " + currentUser.getEmail() + "\n" + "uid: " + currentUser.getUid());
            startActivity(intent);
        } else {
            InitScreen();
        }

    }

    private void InitScreen() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, InitialScreenFragment.newInstance("param1", "param2"));
        fragmentTransaction.commit();
    }

}