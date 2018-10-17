package com.example.kimnahyeon.testgraph;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kimnahyeon.testgraph.data.Content;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Content> contentList;

    public ContentAdapter(Context context, ArrayList<Content> contents) {
        this.context = context;
        this.contentList = contents;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TextHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Content c = contentList.get(position);

        final int pos = position;
        ((TextHolder)holder).date.setText(""+c.getDate());
        ((TextHolder)holder).title.setText(c.getTitle());
        ((TextHolder)holder).tag.setText(c.getTag());
        ((TextHolder)holder).price.setText(Float.toString(c.getPrice()));//float->string
        ((TextHolder)holder).concurrency.setText(c.getConcurrency());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("!!!!!!!!!!!!!!!!!!","hihi");
            }
        });
    }


    @Override
    public int getItemCount() {
        // Log.e("asdd", ""+cityList.size());
        return contentList.size();
    }

    public class TextHolder extends RecyclerView.ViewHolder {

                TextView date, title, tag, concurrency, price;

        public TextHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date_item);
            title = (TextView)view.findViewById(R.id.title_item);
            tag = (TextView)view.findViewById(R.id.tag_item);
            concurrency = (TextView)view.findViewById(R.id.concurrency_item);
            price = (TextView) view.findViewById(R.id.price_item);
        }
    }
}

