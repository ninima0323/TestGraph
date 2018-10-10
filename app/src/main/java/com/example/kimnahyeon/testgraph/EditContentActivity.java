package com.example.kimnahyeon.testgraph;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kimnahyeon.testgraph.data.Content;

import java.util.Calendar;

public class EditContentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText titleEt, priceEt, detailEt;
    TextView dateTv;
    Content content = new Content();
    Button saveBtn;
    String title, detail, tag, concurrency;
    float price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_content);

        content.setTid(1);

        titleEt = (EditText)findViewById(R.id.title_et);
        priceEt = (EditText)findViewById(R.id.price_et);
        detailEt = (EditText)findViewById(R.id.detail_et);
        saveBtn = (Button)findViewById(R.id.save_btn);

        dateTv = (TextView)findViewById(R.id.date_tv);
        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditContentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateTv.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                                //.set(String.valueOf(year) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(dayOfMonth));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Spinner tagS = (Spinner)findViewById(R.id.tag_spinner);
        Spinner concurrencyS = (Spinner)findViewById(R.id.concurrency_spinner);


        tagS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //tv.setText("position : " + position +parent.getItemAtPosition(position));
//                content.setTag(parent.getItemAtPosition(position).toString());
                tag = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        concurrencyS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // tv.setText("position : " + position +parent.getItemAtPosition(position));
                //content.setConcurrency(parent.getItemAtPosition(position).toString());
                concurrency = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        title = titleEt.getText().toString();
        detail = detailEt.getText().toString();
        price = Float.parseFloat(priceEt.getText().toString());
        if(title != "") content.setTitle(title);
//                if(detail != "") content.setConcurrency(detail);
        content.setPrice(Float.parseFloat(priceEt.getText().toString()));
        content.setTag(tag);
        content.setConcurrency(concurrency);
        content.setPrice(price);

        finish();
    }
}
