package expence.ubiad.example.com.expence;

import android.app.ProgressDialog;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by ubiad on 02/06/2017.
 */
public class GetTutor extends AppCompatActivity implements View.OnClickListener{
    Button btn_gettutor;
    EditText edt_st_name;
    EditText edt_st_phone;
    EditText  edt_st_email, edt_st_address, edt_st_school, edt_st_timing, edt_st_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gettutor);
        edt_st_name = (EditText)findViewById(R.id.edt_st_name);
        edt_st_phone = (EditText)findViewById(R.id.edt_st_phone);
        edt_st_email = (EditText)findViewById(R.id.edt_st_email);
        edt_st_address = (EditText)findViewById(R.id.edt_st_address);
        edt_st_school = (EditText)findViewById(R.id.edt_school);
        edt_st_timing = (EditText)findViewById(R.id.edt_st_timing);
        edt_st_class = (EditText)findViewById(R.id.edt_st_class);
        btn_gettutor = (Button)findViewById(R.id.btn_gettutor);
        btn_gettutor.setOnClickListener(this);
    }
    private void CreateUser() {
        final String studentname = edt_st_name.getText().toString().trim();
        final String studentphone = edt_st_phone.getText().toString().trim();
        final String studentemail =  edt_st_email.getText().toString().trim();
        final String studentaddress =  edt_st_address.getText().toString().trim();
        final String studentschool =  edt_st_school.getText().toString().trim();
        final String studenttiming = edt_st_timing.getText().toString().trim();
        final String studentclass = edt_st_class.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GetTutor.this, "REGISTERING...", "Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(GetTutor.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_STUDENT_NAME,studentname);
                hashMap.put(Config.KEY_STUDENT_PHONENO,studentphone);
                hashMap.put(Config.KEY_STUDENT_EMAIL,studentemail);
                hashMap.put(Config.KEY_STUDENT_ADDRESS,studentaddress);
                hashMap.put(Config.KEY_STUDENT_SCHOOL,studentschool);
                hashMap.put(Config.KEY_STUDENT_TIMING,studenttiming);
                hashMap.put(Config.KEY_STUDENT_CLASS,studentclass);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.GET_TUTOR, hashMap);
                return s;
            }
        }
        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    @Override
    public void onClick(View view) {
        if (view == btn_gettutor) {
            CreateUser();
        }
    }
}