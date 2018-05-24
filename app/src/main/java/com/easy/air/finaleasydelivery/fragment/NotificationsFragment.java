package com.easy.air.finaleasydelivery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.easy.air.finaleasydelivery.R;
import com.easy.air.finaleasydelivery.adapter.ItemAdapter;
import com.easy.air.finaleasydelivery.model.Blogzone;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Blogzone> result;
    private ItemAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    private TextView emptyText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Blogzone");

        emptyText = getActivity().findViewById(R.id.no_data);

        result = new ArrayList<>();

        recyclerView = getActivity().findViewById(R.id.items_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lim = new LinearLayoutManager(getActivity());
        lim.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(lim);

        updateList();
        checkIfEmpty();
        adapter = new ItemAdapter(result , true);
        adapter.setActivity(getActivity());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                removeItem(item.getGroupId());
                break;
            case 1:
                Toast.makeText(getActivity(), "This feature is still unavailable!" , Toast.LENGTH_LONG).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void updateList(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Blogzone blogzone = dataSnapshot.getValue(Blogzone.class);
                blogzone.setKey(dataSnapshot.getKey());
                if(blogzone.getUid().equals(auth.getUid())) {
                    result.add(blogzone);
                    adapter.notifyDataSetChanged();
                    checkIfEmpty();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Blogzone blogzone = dataSnapshot.getValue(Blogzone.class);
                blogzone.setKey(dataSnapshot.getKey());
                int index = getItemIndex(blogzone);
                result.set(index , blogzone);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Blogzone blogzone = dataSnapshot.getValue(Blogzone.class);
                blogzone.setKey(dataSnapshot.getKey());
                int index = getItemIndex(blogzone);

                result.remove(index);
                adapter.notifyItemRemoved(index);
                checkIfEmpty();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private int getItemIndex(Blogzone blogzone){

        for(int i = 0 ; i < result.size();i++){
            if(result.get(i).getKey().equals(blogzone.getKey())){
                return i;
            }
        }

        return -1;
    }

    private void removeItem(int index){
        reference.child(result.get(index).getKey()).removeValue();
    }

    private void checkIfEmpty(){
        if(result.size()==0)
        {
            recyclerView.setVisibility(View.INVISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.INVISIBLE);
        }
    }
}