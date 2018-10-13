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

//        mChart = view.findViewById(R.id.pieChart1);
//        mChart.getDescription().setEnabled(false);
//
//        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
//        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
//
//        mChart.setCenterTextTypeface(tf);
//        mChart.setCenterText(generateCenterText());
//        mChart.setCenterTextSize(10f);
//        mChart.setCenterTextTypeface(tf);
//
//        // radius of the center hole in percent of maximum radius
//        mChart.setHoleRadius(45f);
//        mChart.setTransparentCircleRadius(50f);
//
//        Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//
//        mChart.setData(generatePieData());

        ListView lv = (ListView)view.findViewById(R.id.listView1);
        ArrayList<ChartItem> list = new ArrayList<ChartItem>();

        list.add(new PieChartItem(generateDataPie(1), this.getContext()));
        list.add(new BarChartItem(generateDataBar(2), this.getContext()));
       // list.add(new LineChartItem(generateDataLine(3), this.getContext()));

        ChartDataAdapter cda = new ChartDataAdapter(this.getContext(), list);
        lv.setAdapter(cda);

        return view;
    }

//    private PieData generatePieData() {
//        int count = 5;//5;
//        float range = 10;//100;
//        float mult = range;
//
//        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
//
//        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
//        // the chart.
////        for (int i = 0; i < count ; i++) {
////            entries.add(new PieEntry((float) (i+100)*10,//((Math.random() * mult) + mult / 5)  ,
////                    mParties[i % 5]  )); //mParties[i % mParties.length],,
////                    //getResources().getDrawable(R.drawable.star)));
////        }
//        //천원, 이천원, 삼천원, 사천, 오천 씀
//        float total = (1+2+3+4+5)*1000;
////        for (int i = 0; i < count ; i++) {
////            entries.add(new PieEntry((float) (i+1)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
////                    mParties[i % 5]  ));
////        }
//        entries.add(new PieEntry((float) (1)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
//                "기타\n천원"  ));
//        entries.add(new PieEntry((float) (2)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
//                "교통\n이천원" ));
//        entries.add(new PieEntry((float) (3)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
//                "식사\n삼천원" ));
//        entries.add(new PieEntry((float) (4)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
//                "쇼핑\n사천원"));
//        entries.add(new PieEntry((float) (5)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
//                "관람\n오천원"  ));
//
//
//        PieDataSet dataSet = new PieDataSet(entries, "카테고리별 비율");
//
//        dataSet.setDrawIcons(false);
//
//        dataSet.setSliceSpace(3f);
//        dataSet.setIconsOffset(new MPPointF(0, 40));
//        dataSet.setSelectionShift(5f);
//
//        // add a lot of colors
//
//        ArrayList<Integer> colors = new ArrayList<Integer>();
//
//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);
//
//        colors.add(ColorTemplate.getHoloBlue());
//
//        dataSet.setColors(colors);
//        //dataSet.setSelectionShift(0f);
//
//        PieData data = new PieData(dataSet);
//        data.setValueFormatter(new PercentFormatter());
//        data.setValueTextSize(11f);
//        data.setValueTextColor(Color.WHITE);
//        //data.setValueTypeface(mTfLight);
//
//        return data;
//    }
//
//    private SpannableString generateCenterText() {
//        SpannableString s = new SpannableString("무슨여행\n전체지출" );
//        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
//        return s;
//    }


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
            //return 3; // we have 3 different item-types
            return 2;
        }
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<Entry> e2 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e2.add(new Entry(i, e1.get(i).getY() - 30));
        }

        LineDataSet d2 = new LineDataSet(e2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        sets.add(d2);

        LineData cd = new LineData(sets);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar(int cnt) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        ArrayList<Integer> l = new ArrayList<Integer>();
//        for (int i = 1; i < 11; i++) {
//            l.add(920+i);
//        }
//        for(int i=1;i<11;i++){
//            l.add(1000+i);
//        }


        for (int i = 1; i < 21; i++) {
            // 엑스축, 와이축 ==>날짜, 날별 총액
            entries.add(new BarEntry(i+31, (int) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "일별 통계"); //cnt+"일");
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

}
