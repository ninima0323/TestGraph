package com.example.kimnahyeon.testgraph.list;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;
import java.util.Date;



public class DayAxisValueFormatter implements IAxisValueFormatter {
    long now = System.currentTimeMillis();
    Date ndate= new Date(now);

    public String getDate(Date date, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days-1);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1 ;
        int day = c.get(Calendar.DATE);
        String d = Integer.toString(month)+"/"+Integer.toString(day);
        return d;
    }

    private BarLineChartBase<?> chart;

    public DayAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int days = (int) value;
        return getDate(ndate, days);
    }
}
