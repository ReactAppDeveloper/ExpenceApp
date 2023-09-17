package expence.ubiad.example.com.expence;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Expence extends AppCompatActivity implements View.OnClickListener {
    Button bt;
    private EditText edt_expence;
    private EditText edt_detail;
    private  Spinner spinner ;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String[] spinnerItems = new String[]{
            "BreakFast",
            "Lunch",
            "Dinner",
            "Hotel Rent",
            "Mobile Balance",
            "CNG Expence",
            "Petrol Expence",
            "Tools Expence",
            "Convenience",
            "Other Expence"
    };
    List<String> stringlist;
    ArrayAdapter<String> arrayadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expence);
        txtDate = (EditText)findViewById(R.id.edt_date);
        txtTime  = (EditText)findViewById(R.id.edt_time);
        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);
        Intent activityThatCalled = getIntent();
        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");
        EditText calling = (EditText) findViewById(R.id.edt_expence1);
        calling.append(" " + previousActivity);
        spinner = (Spinner)findViewById(R.id.spinner);
        edt_expence = (EditText)findViewById(R.id.edt_expence);
        edt_detail = (EditText)findViewById(R.id.edt_desc);


        stringlist = new ArrayList<>(Arrays.asList(spinnerItems));
        arrayadapter = new ArrayAdapter<String>(Expence.this,R.layout.textview,stringlist );
        arrayadapter.setDropDownViewResource(R.layout.textview);
        spinner.setAdapter(arrayadapter);
        bt = (Button)findViewById(R.id.btn_save);
        bt.setOnClickListener(this);
    }

    private void CreateUser() {
        final String expence = edt_expence.getText().toString().trim();
        final String category = spinner.getSelectedItem().toString().trim();
        final String detail =  edt_detail.getText().toString().trim();
        final String date =  txtDate.getText().toString().trim();
        final String time =  txtTime.getText().toString().trim();


        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Expence.this, "Expence Adding...", "Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Expence.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KE_Expence,expence);
                hashMap.put(Config.KE_CATEGORY,category);
                hashMap.put(Config.KE_DETAIL,detail);
                hashMap.put(Config.KE_DATE,date);
                hashMap.put(Config.KE_TIME,time);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.CREATE_Expence, hashMap);
                return s;
            }
        }
        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    @Override
    public void onClick(View view) {
        if (view == bt) {
            if (edt_expence.getText().length() == 0) {
                edt_expence.setError("Please Enter Expence Amount");
            }
            if (edt_detail.getText().length() == 0) {
                edt_detail.setError("Please Enter Detail");
            }
            if (txtDate.getText().length() == 0) {
                txtDate.setError("Please Enter Detail");
            }
            if (txtTime.getText().length() == 0) {
                txtTime.setError("Please Enter Detail");
            }else {
                CreateUser();
                EditText et_name = (EditText) findViewById(R.id.edt_expence);
                EditText et_name1 = (EditText) findViewById(R.id.edt_expence1);
                int firstNumber = Integer.parseInt(et_name.getText().toString());
                int secondNumber = Integer.parseInt(et_name1.getText().toString());
                int sum = firstNumber + secondNumber;
                Intent goingbck = new Intent();
                goingbck.putExtra("RESULT_SUM", sum);
                setResult(RESULT_OK, goingbck);
//                finish();
            }
        }
        if (view == txtDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText(year + "-" +(monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == txtTime) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String AM_PM = " AM";
                            String mm_precede = "";
                            if (hourOfDay >= 12) {
                                AM_PM = " PM";
                                if (hourOfDay >=13 && hourOfDay < 24) {
                                    hourOfDay -= 12;
                                }
                                else {
                                    hourOfDay = 12;
                                }
                            } else if (hourOfDay == 0) {
                                hourOfDay = 12;
                            }
                            if (minute < 10) {
                                mm_precede = "0";
                            }
                            txtTime.setText(hourOfDay + " : " + mm_precede + minute + " " + AM_PM );
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }


}