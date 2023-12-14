package com.example.bbteamgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
        String email;

        ArrayList<Chat> chatArrayList;
        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;

        private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        chatArrayList =new ArrayList<>();
        email = getIntent().getStringExtra("email");
    Button btn_submit =  findViewById(R.id.btn_submit);
    EditText edt_message = findViewById(R.id.edt_message);
    recyclerView = findViewById(R.id.recycler_messages);
    recyclerView.setHasFixedSize(true);
        String[] myDataset = {"test1","test2"};
        mAdapter = new ChatAdapter(chatArrayList,email);
    recyclerView.setAdapter(mAdapter);

    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                // A new comment has been added, add it to the displayed list
                Chat chat = dataSnapshot.getValue(Chat.class);
                String commentKey = dataSnapshot.getKey();
                String email = chat.getEmail();
                String stText = chat.getText();
                Log.d(TAG, "email: "+ email);
                Log.d(TAG, "stText "+ stText    );
                chatArrayList.add(chat);
                mAdapter.notifyDataSetChanged();

                
                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.

                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.


                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());

                Toast.makeText(ChatActivity.this, "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        DatabaseReference myRef = database.getReference("message");
        myRef.addChildEventListener(childEventListener);


    btn_submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String stText = edt_message.getText().toString();
            Toast.makeText(ChatActivity.this,"MSG :"+stText,Toast.LENGTH_LONG).show();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
            String datetime = dateformat.format(c.getTime() );
            System.out.println(datetime);

            DatabaseReference myRef = database.getReference("message");
            Hashtable<String, String>numbers
                    = new Hashtable<String, String>();
            numbers.put("email",email);
            numbers.put("text",stText);
            
            myRef.setValue(numbers);}
    });


    }
}