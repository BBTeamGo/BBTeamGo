package com.example.bbteamgo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManagerTableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagerTableFragment extends Fragment {
    private static final String TAG = "ManagerActivity";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "UNIVERSITY_NAME";
    private static final String ARG_PARAM2 = "BOOTH_NAME";

    // TODO: Rename and change types of parameters
    private String university;
    private String booth;

    public ManagerTableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManagerTableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManagerTableFragment newInstance(String param1, String param2) {
        ManagerTableFragment fragment = new ManagerTableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private FirebaseFirestore database;
    private DocumentReference boothRef;

    List<Table> tables = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            university = getArguments().getString(ARG_PARAM1);
            booth = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        database = FirebaseFirestore.getInstance();
        boothRef = database.collection("University")
                .document(university)
                .collection("Booth")
                .document(booth);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manager_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boothRef.collection("Table")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();

                                int index = Integer.parseInt(data.get("index").toString());
                                Boolean isUsed = (Boolean) data.get("is_used");
                                Timestamp visitedTime = (Timestamp) data.get("visited_time");

                                long diffMinutes = (Timestamp.now().getSeconds() - visitedTime.getSeconds()) / 60;

                                tables.add(new Table(isUsed, index, (int)diffMinutes));
                            }
                            Log.d(TAG, "Success Load Table List");

                            Collections.sort(tables, (Table a, Table b) -> Integer.compare(a.getIndex(), b.getIndex()) );

                            RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            recyclerView.setAdapter(new TableAdapter(getActivity(), tables));
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}

class TableAdapter extends RecyclerView.Adapter<TableViewHolder> {
    Context context;
    List<Table> tables;

    public TableAdapter(Context context, List<Table> tables) {
        this.context = context;
        this.tables = tables;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TableViewHolder(LayoutInflater.from(context).inflate(R.layout.item_table, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        if (tables.get(position).isUsed()) {
            holder.tableState.setImageResource(R.drawable.used_table_background);

            int elapsedTime = tables.get(position).getElapsedTime();
            holder.elapsedTimeText.setText("경과 시간 " + String.valueOf(elapsedTime) + "분");
        } else {
            holder.tableState.setImageResource(R.drawable.unused_table_background);
            holder.elapsedTimeText.setText("");
        }
        holder.indexText.setText(String.valueOf(tables.get(position).getIndex()));
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }
}

class TableViewHolder extends RecyclerView.ViewHolder {
    ImageView tableState;
    TextView indexText;
    TextView elapsedTimeText;

    public TableViewHolder(@NonNull @org.jetbrains.annotations.NotNull View tableView) {
        super(tableView);

        tableState = tableView.findViewById(R.id.table_state);
        indexText = tableView.findViewById(R.id.table_index);
        elapsedTimeText = tableView.findViewById(R.id.table_elapsed_time);
    }
}

class Table {
    boolean isUsed;
    int index;
    int elapsedTime;

    public Table(boolean isUsed, int index, int elapsedTime) {
        this.isUsed = isUsed;
        this.index = index;
        this.elapsedTime = elapsedTime;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}