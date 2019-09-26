package com.thesis.biblegame.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.thesis.biblegame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LeaderBoardAdapter leaderBoardAdapter;
    private List<Score> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("score");
        Query query = ref.orderByChild("score");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Score score = postSnapshot.getValue(Score.class);
                    list.add(score);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("aaa", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        recyclerView  = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(LeaderBoardActivity.this, LinearLayoutManager.VERTICAL, false);
        ((LinearLayoutManager) mLayoutManager).setReverseLayout(true);
        ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        leaderBoardAdapter = new LeaderBoardAdapter(list , this);
        recyclerView.setAdapter(leaderBoardAdapter);


    }



    @Override
    public void onBackPressed() {


                startActivity(new Intent(LeaderBoardActivity.this, Menu.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }
}
