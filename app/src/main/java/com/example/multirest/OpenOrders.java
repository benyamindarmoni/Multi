package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.multirest.ui.Dish;
import com.example.multirest.ui.order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OpenOrders extends AppCompatActivity {
    private  ListView orders;
    static LinkedList<order>  myOrders=new LinkedList<>();
    private ArrayList[] openorders = new ArrayList[6];
    ArrayAdapter<order> adptr;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);
        orders = (ListView) findViewById(R.id.openO);
        adptr = new ArrayAdapter<order>(this, android.R.layout.simple_list_item_1, myOrders);
        orders.setAdapter(adptr);
        adptr.notifyDataSetChanged();


        myRef.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    myOrders.add(child.getValue(order.class));
                    System.out.println("added!");
                    adptr.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

}