package com.example.kimnahyeon.testgraph.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kimnahyeon.testgraph.R;
import com.example.kimnahyeon.testgraph.data.Memo;

import java.util.Calendar;
import java.util.Date;

public class MemoFragment extends Fragment implements  DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    public MemoFragment(){}
    int nYear, nMonth, nDay, nHour, nMinute;
    int  mYear, mMonth, mDay, mHour,mMinute, mSecond;
    TextView memo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, container, false);


        memo = (TextView)view.findViewById(R.id.tv_memo);
        TextView day = (TextView)view.findViewById(R.id.tv_date) ;
        TextView mi = (TextView)view.findViewById(R.id.tv_min);
        Calendar c = Calendar.getInstance();
         mYear = c.get(Calendar.YEAR); // current year
         mMonth = c.get(Calendar.MONTH); // current month
         mDay = c.get(Calendar.DAY_OF_MONTH); // current day
         mHour = c.get(Calendar.HOUR_OF_DAY) ;//current hour
         mMinute=c.get(Calendar.MINUTE);//current minute
         mSecond = c.get(Calendar.SECOND);

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        MemoFragment.this , mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        mi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        MemoFragment.this ,mHour, mMinute, true ); //시, 분, 24시간형식인지
                timePickerDialog.show();
            }
        });

//        TimePickerDialog timePickerDialog = new TimePickerDialog(this.getContext(),
//                this ,mHour, mMinute, true ); //시, 분, 24시간형식인지
//        timePickerDialog.show();
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(),
//                this , mYear, mMonth, mDay);
//        datePickerDialog.show();



        displayDate();

        return view;
    }

    private void displayDate(){
        memo.setText(getDate(nYear,nMonth,nDay,nHour,nMinute).toString());
    }

//    @Override
//    public void onClick(View v) {
//        switch(getId()) {
//            case R.id.tv_date:
//                DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(),
//                        this , mYear, mMonth, mDay);
//                datePickerDialog.show();
//                break;
//            case R.id.tv_min:
//                TimePickerDialog timePickerDialog = new TimePickerDialog(this.getContext(),
//                        this ,mHour, mMinute, true ); //시, 분, 24시간형식인지
//                timePickerDialog.show();
//                break;
//        }
//    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        nYear = year;
        nMonth = month;
        nDay = dayOfMonth;
        displayDate();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        nHour = hourOfDay;
        nMinute = minute;
        displayDate();
    }

    public Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        int sec = cal.get(Calendar.SECOND);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, sec);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}

