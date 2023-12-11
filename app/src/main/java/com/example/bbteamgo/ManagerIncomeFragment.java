package com.example.bbteamgo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManagerIncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagerIncomeFragment extends Fragment {
    private static final String TAG = "ManagerActivity";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "UNIVERSITY_NAME";
    private static final String ARG_PARAM2 = "BOOTH_NAME";

    private String university;
    private String booth;

    private FirebaseFirestore database;
    private DocumentReference boothRef;
    private List<Order> orders = new ArrayList<>();

    public ManagerIncomeFragment() {
        // Required empty public constructor
    }

    public static ManagerIncomeFragment newInstance(String university, String booth) {
        ManagerIncomeFragment fragment = new ManagerIncomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, university);
        args.putString(ARG_PARAM2, booth);
        fragment.setArguments(args);
        return fragment;
    }

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
        return inflater.inflate(R.layout.fragment_manager_income, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boothRef.collection("Order")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();

                                int tableIndex = Integer.parseInt(data.get("table_index").toString());
                                int amount = Integer.parseInt(data.get("amount").toString());
                                Timestamp orderTime = (Timestamp) data.get("time");

                                orders.add(new Order(tableIndex, orderTime, amount));
                            }
                            Log.d(TAG, "Success Load Order List");

                            Collections.sort(orders, (Order a, Order b) -> a.getTime().compareTo(b.getTime()) );

                            RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(new OrderAdapter(getActivity(), orders));
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}

class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    Context context;
    List<Order> orders;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.orderAmount.setText(orders.get(position).getAmount() + "원");
        holder.tableIndex.setText(orders.get(position).getTableIndex() + "번 테이블");

        Timestamp orderTime = orders.get(position).getTime();
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        String formattedDate = f.format(orderTime.toDate());
        holder.orderTime.setText(formattedDate + " 주문");
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}

class OrderViewHolder extends RecyclerView.ViewHolder {
    TextView tableIndex;
    TextView orderTime;
    TextView orderAmount;

    public OrderViewHolder(@NonNull View orderView) {
        super(orderView);

        tableIndex = orderView.findViewById(R.id.table_text);
        orderTime = orderView.findViewById(R.id.order_time_text);
        orderAmount = orderView.findViewById(R.id.amount_text);
    }
}

class Order {
    int tableIndex;
    Timestamp time;
    int amount;

    public Order(int tableIndex, Timestamp time, int amount) {
        this.tableIndex = tableIndex;
        this.time = time;
        this.amount = amount;
    }

    public int getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}