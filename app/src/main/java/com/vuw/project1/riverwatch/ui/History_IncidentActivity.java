package com.vuw.project1.riverwatch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vuw.project1.riverwatch.R;
import com.vuw.project1.riverwatch.objects.Incident_Object;

import java.util.ArrayList;

public class History_IncidentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterHistory_Incident mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        ArrayList<Incident_Object> incidents = addDummyData();
        mAdapter = new AdapterHistory_Incident(this, incidents, new AdapterHistory_Incident.Callback() {
            @Override
            public void open(Incident_Object obj) {
                Intent intent = new Intent(History_IncidentActivity.this, History_IncidentActivity_Item.class);
                intent.putExtra("name", obj.name);
                intent.putExtra("location", obj.location);
                intent.putExtra("date", obj.date);
                intent.putExtra("description", obj.description);
                intent.putExtra("image", obj.image);
                startActivity(intent);
            }
        });
        GridLayoutManager llm = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
    }

    public ArrayList<Incident_Object> addDummyData(){
        ArrayList<Incident_Object> incidents = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            incidents.add(new Incident_Object(i, "test name"+i, "test location", "DD/MM/YYYY", "test description\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n", "https://cathyqmumford.files.wordpress.com/2010/12/connecticut-river-cow.jpg"));
        }
        return incidents;
    }
}