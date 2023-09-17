package expence.ubiad.example.com.expence;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

public class Balance  extends AppCompatActivity implements View.OnClickListener{
    Button bt1;
    EditText edt_balance,edt_detail;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    public static final String KEY_TEXT = "balance";
    public static final String KEY_TEXT1 = "detail";
    public static final String KEY_TEXT2 = "date";
    public static final String KEY_TEXT3 = "time";
    public static final String UPLOAD_URL =  "http://www.comsol.net.pk/qhse/balance.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        edt_balance = (EditText)findViewById(R.id.edt_balance);
        edt_detail = (EditText)findViewById(R.id.edt_desc);
        bt1 = (Button)findViewById(R.id.btn_save1);
        bt1.setOnClickListener(this);
        txtDate = (EditText)findViewById(R.id.edt_date);
        txtTime  = (EditText)findViewById(R.id.edt_time);
        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);
        Intent activityThatCalled = getIntent();
        String previousActivity = activityThatCalled.getExtras().getString("callingActivity2");
        EditText calling = (EditText) findViewById(R.id.edt_balance1);
        calling.append(" " + previousActivity);
    }
    @Override
    public void onClick(View view) {
        if (view == bt1) {
            if (edt_balance.getText().length() == 0) {
                edt_balance.setError("Please Enter Expence Amount");
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
                uploadImages();
                EditText et_balance = (EditText) findViewById(R.id.edt_balance);
                EditText et_balance1 = (EditText) findViewById(R.id.edt_balance1);
                int firstNumber = Integer.parseInt(et_balance.getText().toString());
                int secondNumber = Integer.parseInt(et_balance1.getText().toString());
                int sum1 = firstNumber + secondNumber;
                Intent goingbck = new Intent();
                goingbck.putExtra("RESULT", sum1);
                setResult(RESULT_OK, goingbck);
                //finish();
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
    private void uploadImages(){

        final String text = edt_balance.getText().toString().trim();
        final String text1 = edt_detail.getText().toString().trim();
        final String text2 = txtDate.getText().toString().trim();
        final String text3 = txtTime.getText().toString().trim();
        class UploadImages extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;
            //   RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Balance.this, "Balance Adding", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Balance.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params){
                RequestHandler rh = new RequestHandler();
                HashMap<String,String> param = new HashMap<String,String>();
                param.put(KEY_TEXT,text);
                param.put(KEY_TEXT1,text1);
                param.put(KEY_TEXT2,text2);
                param.put(KEY_TEXT3,text3);
                String result = rh.sendPostRequest(UPLOAD_URL, param);
                return result;
            }
        }
        UploadImages u = new UploadImages();
        u.execute();
    }

}