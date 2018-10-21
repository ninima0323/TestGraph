package com.example.kimnahyeon.testgraph;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<String> categories;

    public CategoryAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.categories = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        CheckBox cb;

        public ViewHolder(View itemView){
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.fab_tv);
            cb = (CheckBox)itemView.findViewById(R.id.fab_checkbox);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fab, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final String cate = categories.get(position);
        ((ViewHolder)viewHolder).tv.setText(cate);
        ((ViewHolder) viewHolder).cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(context, cate+"선택됨", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
