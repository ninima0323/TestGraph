package com.example.kimnahyeon.testgraph.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kimnahyeon.testgraph.R;
import com.example.kimnahyeon.testgraph.list.BarChartItem;
import com.example.kimnahyeon.testgraph.list.ChartItem;
import com.example.kimnahyeon.testgraph.list.LineChartItem;
import com.example.kimnahyeon.testgraph.list.PieChartItem;
import com.example.kimnahyeon.testgraph.list.StackBarChartItem;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;


public class StatisticsFragment extends Fragment {


    public StatisticsFragment() {
        //this.context = context;
    }

    Context context;
    private PieChart mChart;
    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    protected String[] mParties = new String[] {
            "기타", "교통", "식사", "쇼핑", "관람", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
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
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //d.setHighLightAlpha(255);
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

        int total = (1+2+3+4+5)*1000; //총 가
        for (int i = 0; i < 5; i++) {
            //(분야별 가격/총가격*100 , 분야스트링) 으로 파이 앤트리 생성 (전체에서 분야 비율 직접 계산)
            entries.add(new PieEntry((float) (i+1)*1000/total*100, mParties[i]));
        }
        //파이앤트리로 파이데이터셋 생성
        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

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
            // 엑스축, 와이축 ==>날짜, 날별 총액
            entries.add(new BarEntry(i, new float[]{val1, val2, val3,val4, val5}));
        }

        BarDataSet d = new BarDataSet(entries, "일별 카테코리 총액"); //cnt+"일");
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);//색설정, 색이 5개면 카테고리 상관없이 1부터 5까지 다 쓰고 나서야 1이 다시나옴
        d.setHighLightAlpha(120);
        d.setStackLabels(new String[]{"1", "2", "3", "4", "5"});

        BarData cd = new BarData(d);
        //cd.setValueFormatter(new MyValueFormatter());
        cd.setBarWidth(0.3f);// 바 두께
        return cd;
    }

}
