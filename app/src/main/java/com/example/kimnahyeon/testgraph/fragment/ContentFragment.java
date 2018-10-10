package com.example.kimnahyeon.testgraph.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimnahyeon.testgraph.ContentAdapter;
import com.example.kimnahyeon.testgraph.R;
import com.example.kimnahyeon.testgraph.data.Content;

import java.util.ArrayList;

public class ContentFragment extends Fragment {

    public ContentFragment() {
    }
    ArrayList<Content> contentList = new ArrayList<>();
    ContentAdapter mAdapter;
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);


        rv = (RecyclerView)view.findViewById(R.id.fgCont_rv);
        mAdapter = new ContentAdapter(this.getContext(), contentList);
        rv.setAdapter(mAdapter);
        prepareData();

        return view;
    }

    public void prepareData(){
        int i=0;
        for(i=0; i<30; i++) {//날짜바꾸는거
            if(i%5==0){
                Content c = new Content(i,i*100,"원","기타");
                contentList.add(c);
            }else if(i%5==1){
                Content c = new Content(i,i*100,"원","교통");
                contentList.add(c);
            }else if(i%5==2){
                Content c = new Content(i,i*100,"원","식사");
                contentList.add(c);
            }else if(i%5==3){
                Content c = new Content(i,i*100,"원","쇼핑");
                contentList.add(c);
            }else{
                Content c = new Content(i,i*100,"원","관람");
                contentList.add(c);
            }

        }
        mAdapter.notifyDataSetChanged();
    }
}
