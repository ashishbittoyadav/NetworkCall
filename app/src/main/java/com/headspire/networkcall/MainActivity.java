package com.headspire.networkcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.headspire.networkcall.Adapter.DataAdapter;
import com.headspire.networkcall.Model.ModelClass;
import com.headspire.networkcall.networking.JsonHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @Version 1.0
 * @Author Ashish Yadav
 * @Objective: Json parsing and network call.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button clickToHttpCall;
    private ArrayList<ModelClass> jsonContent;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickToHttpCall=findViewById(R.id.download_data);
        jsonContent=new ArrayList<>();
        clickToHttpCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download_data:
                createArrayList();
                buildRecyclerView();
            break;
        }
    }

    /**
     *creating array list by parsing the json content*/
    public void createArrayList()
    {
        try {
            JSONArray jsonArray=new JSONArray((new JsonHolder().execute("https://jsonplaceholder.typicode.com/posts")
                    .get()));
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                ModelClass modelClass=new ModelClass();
                modelClass.setBody(jsonObject.getString("body"));
                modelClass.setTitle(jsonObject.getString("title"));
                modelClass.setUserId(jsonObject.getInt("userId"));
                modelClass.setId(jsonObject.getInt("id"));
                jsonContent.add(modelClass);
            }

        } catch (Exception e) {
        }
    }

    public void buildRecyclerView()
    {
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        dataAdapter=new DataAdapter(jsonContent);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
