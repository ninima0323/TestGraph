package com.example.kimnahyeon.testgraph.fragment;

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

import com.example.kimnahyeon.testgraph.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {
    public StatisticsFragment() {
    }

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

        mChart = view.findViewById(R.id.pieChart1);
        mChart.getDescription().setEnabled(false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);
        mChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        mChart.setData(generatePieData());

        return view;
    }

    private PieData generatePieData() {
        int count = 5;//5;
        float range = 10;//100;
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
//        for (int i = 0; i < count ; i++) {
//            entries.add(new PieEntry((float) (i+100)*10,//((Math.random() * mult) + mult / 5)  ,
//                    mParties[i % 5]  )); //mParties[i % mParties.length],,
//                    //getResources().getDrawable(R.drawable.star)));
//        }
        //천원, 이천원, 삼천원, 사천, 오천 씀
        float total = (1+2+3+4+5)*1000;
//        for (int i = 0; i < count ; i++) {
//            entries.add(new PieEntry((float) (i+1)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
//                    mParties[i % 5]  ));
//        }
        entries.add(new PieEntry((float) (1)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
                "기타\n천원"  ));
        entries.add(new PieEntry((float) (2)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
                "교통\n이천원" ));
        entries.add(new PieEntry((float) (3)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
                "식사\n삼천원" ));
        entries.add(new PieEntry((float) (4)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
                "쇼핑\n사천원"));
        entries.add(new PieEntry((float) (5)*1000/total *100,//((Math.random() * mult) + mult / 5)  ,
                "관람\n오천원"  ));


        PieDataSet dataSet = new PieDataSet(entries, "카테고리별 비율");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        //data.setValueTypeface(mTfLight);

        return data;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("무슨여행\n전체지출" );
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }




}
