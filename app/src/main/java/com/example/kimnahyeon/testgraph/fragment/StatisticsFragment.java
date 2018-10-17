package com.example.kimnahyeon.testgraph.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kimnahyeon.testgraph.R;
import com.example.kimnahyeon.testgraph.list.BarChartItem;
import com.example.kimnahyeon.testgraph.list.ChartItem;
import com.example.kimnahyeon.testgraph.list.PieChartItem;
import com.example.kimnahyeon.testgraph.list.StackBarChartItem;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;


public class StatisticsFragment extends Fragment {


    public StatisticsFragment() {
    }

    Context context;
    protected String[] mParties = new String[] {
            "기타", "교통", "식사", "쇼핑", "관람", "1", "2", "3",
            "4", "5", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    public final int[] BASIC_COLORS = {
            Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, 208, 140),
            Color.rgb(140, 234, 255), Color.rgb(255, 140, 157),

            rgb("#4BE8A9"),  rgb("#E7EE7C"),  rgb("#FDBE49"), rgb("#5CDCEC"), rgb("#D470E1")
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        ListView lv = (ListView)view.findViewById(R.id.listView1);
        ArrayList<ChartItem> list = new ArrayList<ChartItem>();

        list.add(new PieChartItem(generateDataPie(1), this.getContext()));
        list.add(new BarChartItem(generateDataBar(2), this.getContext()));
        list.add(new StackBarChartItem(generateDataStackBar(3), this.getContext()));

        ChartDataAdapter cda = new ChartDataAdapter(this.getContext(), list);
        lv.setAdapter(cda);

        return view;
    }

    /** adapter that supports 3 different item types */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            return getItem(position).getItemType();
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }

//    /**
//     * generates a random ChartData object with just one DataSet
//     *
//     * @return
//     */
//    private LineData generateDataLine(int cnt) {
//
//        ArrayList<Entry> e1 = new ArrayList<Entry>();
//
//        for (int i = 0; i < 12; i++) {
//            e1.add(new Entry(i, (int) (Math.random() * 65) + 40));
//        }
//
//        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");
//        d1.setLineWidth(2.5f);
//        d1.setCircleRadius(4.5f);
//        d1.setHighLightColor(Color.rgb(244, 117, 117));
//        d1.setDrawValues(false);
//
//        ArrayList<Entry> e2 = new ArrayList<Entry>();
//
//        for (int i = 0; i < 12; i++) {
//            e2.add(new Entry(i, e1.get(i).getY() - 30));
//        }
//
//        LineDataSet d2 = new LineDataSet(e2, "New DataSet " + cnt + ", (2)");
//        d2.setLineWidth(2.5f);
//        d2.setCircleRadius(4.5f);
//        d2.setHighLightColor(Color.rgb(244, 117, 117));
//        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
//        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
//        d2.setDrawValues(false);
//
//        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
//        sets.add(d1);
//        sets.add(d2);
//
//        LineData cd = new LineData(sets);
//        return cd;
//    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        ArrayList<Integer> l = new ArrayList<Integer>();

        for (int i = 1; i < 21; i++) {
            // 엑스축, 와이축 ==>날짜, 날별 총액
            entries.add(new BarEntry(i, i*1000));
        }

        BarDataSet d = new BarDataSet(entries, "일별 지출 총액"); //cnt+"일");
        d.setColors(Color.rgb(207, 248, 246));
        d.setHighLightAlpha(120);

        BarData cd = new BarData(d);
        //cd.setBarWidth(0.9f);
        cd.setBarWidth(0.3f);// 바 두께
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie(int cnt) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        int total = (1+2+3+4+5+6+7+8+9+10)*1000; //총 가
        for (int i = 0; i < 10; i++) {
            //(분야별 가격/총가격*100 , 분야스트링) 으로 파이 앤트리 생성 (전체에서 분야 비율 직접 계산)
            entries.add(new PieEntry((float) (i+1)*1000/total*100, mParties[i]));
        }
        //파이앤트리로 파이데이터셋 생성
        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(BASIC_COLORS);

        PieData cd = new PieData(d);
        return cd;
    }

    private BarData generateDataStackBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 1; i < 21; i++) {
            float val1 = (float) i*10;
            float val2 = (float) i*10;
            float val3 = (float) i*10;
            float val4 = (float) i*10;
            float val5 = (float) i*10;
            float val11 = (float) i*10;
            float val21 = (float) i*10;
            float val31 = (float) i*10;
            float val41 = (float) i*10;
            float val51 = (float) i*10;
            // 엑스축, 와이축 ==>날짜, 날별 총액
            entries.add(new BarEntry(i, new float[]{val1, val2, val3,val4, val5
                    ,val11, val21, val31,val41, val51}));
        }

        int[] lcolor = new int[5];//마지막 5에 카테고리 수가 들어가야함
        System.arraycopy(BASIC_COLORS, 0, lcolor, 0, 5);//마지막 5에 카테고리 수가 들어가야함

        BarDataSet d = new BarDataSet(entries, " ");
        d.setColors(BASIC_COLORS);//색설정, 색이 5개면 카테고리 상관없이 1부터 5까지 다 쓰고 나서야 1이 다시나옴
        //d.setColors(lcolor); //카테고리수에 맞춰 색을 정하는게 바로 이코드 위에껀 걍 10개 그대로 보여주느라..ㅎ
        d.setHighLightAlpha(120);
       // d.setStackLabels(new String[]{"교통", "식사", "관람", "쇼핑", "기타"});//카테고리리스트를 넣어야함
        d.setStackLabels(new String[10]);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.3f);// 바 두께
        return cd;
    }

}
