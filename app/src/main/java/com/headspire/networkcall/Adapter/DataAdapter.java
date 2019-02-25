package com.headspire.networkcall.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.headspire.networkcall.Model.ModelClass;
import com.headspire.networkcall.R;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {


    private ArrayList<ModelClass> jsonContent;
    public DataAdapter(ArrayList<ModelClass> jsonContent)
    {
        this.jsonContent=jsonContent;
    }
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.data_layout,viewGroup,false);
        DataHolder dataHolder=new DataHolder(view);
        return dataHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder dataHolder, int i) {
        dataHolder.id.setText(jsonContent.get(i).getId()+"");
        dataHolder.title.setText(jsonContent.get(i).getTitle());
        dataHolder.userId.setText(jsonContent.get(i).getUserId()+"");
        dataHolder.body.setText(jsonContent.get(i).getBody());
    }

    @Override
    public int getItemCount() {
        return jsonContent.size();
    }

    static class DataHolder extends RecyclerView.ViewHolder{
        private TextView id;
        private TextView userId;
        private TextView title;
        private TextView body;
        public DataHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.jsonid);
            userId=itemView.findViewById(R.id.jsonuserid);
            title=itemView.findViewById(R.id.jsontitle);
            body=itemView.findViewById(R.id.jsonbody);
        }
    }
}
