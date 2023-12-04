package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

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

        checkLogin();
    }

    private void checkLogin() {
        FirebaseUser currentUser = userAuth.getCurrentUser();

        if (currentUser != null) {
            DocumentReference user = database.collection("User").document(currentUser.getUid());
            final Class[] targetActivity = {null};
            user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Map<String, Object> data = document.getData();

                            if ((boolean) data.get("is_customer")) {
                                targetActivity[0] = CustomerActivity.class;
                            }
                            else if ((boolean) data.get("is_manager")) {
                                targetActivity[0] = ManagerActivity.class;
                            }

                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

            Intent intent = new Intent(this, targetActivity[0]);
            intent.putExtra("USER_PROFILE", "email: " + currentUser.getEmail() + "\n" + "uid: " + currentUser.getUid());

            startActivity(intent);
        }
        else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_view_tag, InitialScreenFragment.newInstance("param1", "param2"));
            fragmentTransaction.commit();
        }

    }
}