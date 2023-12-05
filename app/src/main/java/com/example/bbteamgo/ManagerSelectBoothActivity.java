package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ManagerSelectBoothActivity extends AppCompatActivity {
    private final static String TAG = "BoothSelect";

    private FirebaseFirestore database;

    List<BoothItem> booths = new ArrayList<>();
    String userid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_select_booth);

        database = FirebaseFirestore.getInstance();
        getUserId();

        final String[] userMembership = {""};
        database.collection("User").document(userid)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> data = document.getData();
                                userMembership[0] = data.get("membership").toString();
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }

                    }
                });



        database.collection("University").document(userMembership[0]).collection("Booth")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();
                                String pictureURL = Objects.requireNonNull(data.get("picture_url")).toString();
                                String title = Objects.requireNonNull(data.get("name")).toString();
                                String explainText = Objects.requireNonNull(data.get("explain_text")).toString();
                                int currentTable = (int)data.get("current_table");
                                int maxTable = (int)data.get("max_table");
                                booths.add(new BoothItem(pictureURL, title, explainText, maxTable, currentTable));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BoothAdapter(getApplicationContext(), booths));
    }

    private void getUserId() {
        Intent intent = getIntent();
        String userProfile = intent.getStringExtra("USER_PROFILE");

        if (userProfile != null) {
            String[] parts = userProfile.split("\n"); // 문자열을 분리하여 배열로 변환
            String uidPart = parts[1]; // 첫 번째 부분에는 email 정보가 있습니다.
            userid = uidPart.split(": ")[1]; // emailPart를 ": "으로 분리하여 email 정보만 추출
        }

        return;
    }
}