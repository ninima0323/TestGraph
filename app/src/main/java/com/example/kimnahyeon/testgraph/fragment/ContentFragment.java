package com.example.kimnahyeon.testgraph.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimnahyeon.testgraph.CategoryAdapter;
import com.example.kimnahyeon.testgraph.ContentAdapter;
import com.example.kimnahyeon.testgraph.R;
import com.example.kimnahyeon.testgraph.RecyclerSectionItemDecoration;
import com.example.kimnahyeon.testgraph.data.Content;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends Fragment {

    public ContentFragment() {
    }
    ArrayList<Content> contentList = new ArrayList<>();
    ArrayList<String> taglist = new ArrayList<String>(){
        {
            add("교통");
            add("식사");
            add("쇼핑");
            add("관람");
            add("기타");
        }
    };
    ContentAdapter mAdapter;
    RecyclerView rv;

    FloatingActionButton fab;
    RecyclerView rvFab;
    CategoryAdapter fabAdapter;
    boolean isFabClicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        rvFab = (RecyclerView)view.findViewById(R.id.fab_rv);
        fabAdapter = new CategoryAdapter(this.getContext(),taglist);
        rvFab.setAdapter(fabAdapter);
        rvFab.bringToFront();
        rvFab.setVisibility(View.INVISIBLE);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFabClicked){
                    isFabClicked = false;
                    rvFab.setVisibility(View.INVISIBLE);
                }else{//폴스인상태에서 누르면 트루로 바뀌고 화면 뜸
                    isFabClicked = true;
                    rvFab.setVisibility(View.VISIBLE);
                }

            }
        });

        rv = (RecyclerView)view.findViewById(R.id.fgCont_rv);
        mAdapter = new ContentAdapter(this.getContext(), prepareData()); //스티키헤더때문에 텅빈리스트넣음
        rv.setAdapter(mAdapter);
        //prepareData(); 스티키헤더때문에 변경, 리스트 리턴하는걸로

        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
                        true,  //아이템이 헤더에 씹히는 경우가 있으면, 디멘션에서 dp늘려서 해결
                        getSectionCallback(prepareData())); //스티키헤더에서 리스트도 같이 뿌려주는거 같음
        rv.addItemDecoration(sectionItemDecoration);

        return view;
    }

    public ArrayList<Content> prepareData(){ //스티키해더때문에 리스트 리턴하게 변
        int i=0;
        ArrayList<Content> clist = new ArrayList<Content>();
        for(i=0; i<30; i++) {//날짜바꾸는거
            if(i<6){
                Content c = new Content(1,i*100,"원","기타");
                //contentList.add(c);
                clist.add(c);
            }else if(i<12){
                Content c = new Content(2,i*100,"원","교통");
                //contentList.add(c);
                clist.add(c);
            }else if(i<18){
                Content c = new Content(3,i*100,"원","식사");
                //contentList.add(c);
                clist.add(c);
            }else if(i<24){
                Content c = new Content(4,i*100,"원","쇼핑");
                //contentList.add(c);
                clist.add(c);
            }else{
                Content c = new Content(5,i*100,"원","관람");
                //contentList.add(c);
                clist.add(c);
            }

        }
        //mAdapter.notifyDataSetChanged();
        return clist;
    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final ArrayList<Content> people) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {   //디비에서 정렬해서 가져올테니 순서대로 비교
                return position == 0
                        || people.get(position)
                        .getDate() != people.get(position - 1)
                        .getDate();
            }

            @Override
            public String getSectionHeader(int position) {
                return Integer.toString(people.get(position).getDate());
            }
        };
    }


}
