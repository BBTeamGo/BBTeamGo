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
        DocumentReference user = database.collection("User").document(currentUser.getUid());
        final String[] lastLoginMode = {null};

        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Log.d(TAG, "check2");
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();

                        lastLoginMode[0] = data.get("last_login_mode").toString();
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        if (lastLoginMode[0] != null) {
            Intent intent = null;
            if (Objects.equals(lastLoginMode[0], "Manager"))
                intent = new Intent(this, ManagerActivity.class);
            else if (Objects.equals(lastLoginMode[0], "Customer"))
                intent = new Intent(this, CustomerActivity.class);
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