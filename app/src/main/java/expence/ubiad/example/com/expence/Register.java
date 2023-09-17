package expence.ubiad.example.com.expence;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by ubiad on 01/06/2017.
 */
public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btn_register;
    private EditText edt_firstname;
    private EditText edt_lastname;
    EditText  edt_fahername, edt_gender, edt_dob, edt_age, edt_mobileno, edt_cnic, edt_address, edt_email;
    EditText edt_education,edt_experience,edt_teachingdetail,edt_interestteach,edt_timingteach,edt_majorsubject;
    protected Button btnCamera;
    private Bitmap bitmap;
    private ImageView capturedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_firstname = (EditText)findViewById(R.id.edt_frstname);
        edt_lastname = (EditText)findViewById(R.id.edt_lstname);
        edt_fahername = (EditText)findViewById(R.id.edt_fathername);
        edt_gender = (EditText)findViewById(R.id.edt_gender);
        edt_dob = (EditText)findViewById(R.id.edt_dob);
        edt_age = (EditText)findViewById(R.id.edt_age);
        edt_mobileno = (EditText)findViewById(R.id.edt_mobilenum);
        edt_cnic = (EditText)findViewById(R.id.edt_cnic);
        edt_address = (EditText)findViewById(R.id.edt_address);
        edt_email = (EditText)findViewById(R.id.edt_e_address);
        edt_education = (EditText)findViewById(R.id.edt_education);
        edt_experience = (EditText)findViewById(R.id.edt_experience);
        edt_teachingdetail = (EditText)findViewById(R.id.edt_tch_detail);
        edt_interestteach = (EditText)findViewById(R.id.edt_int_class);
        edt_timingteach = (EditText)findViewById(R.id.edt_timging_teach);
        edt_majorsubject = (EditText)findViewById(R.id.edt_major_subject);
        btnCamera = (Button) findViewById(R.id.btnCamera);
        capturedImage = (ImageView) findViewById(R.id.capturedImage);
        btn_register = (Button)findViewById(R.id.btn_regiter);
        btn_register.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
    }
    private void CreateUser() {
        final String firstname = edt_firstname.getText().toString().trim();
        final String lastname = edt_lastname.getText().toString().trim();
        final String fathername =  edt_fahername.getText().toString().trim();
        final String gender =  edt_gender.getText().toString().trim();
        final String dob =  edt_dob.getText().toString().trim();
        final String age = edt_age.getText().toString().trim();
        final String mobileno = edt_mobileno.getText().toString().trim();
        final String cnic =  edt_cnic.getText().toString().trim();
        final String address =  edt_address.getText().toString().trim();
        final String email =  edt_email.getText().toString().trim();
        final String image = getStringImage(bitmap);
        final String education =  edt_education.getText().toString().trim();
        final String experience = edt_experience.getText().toString().trim();
        final String teachingdetail = edt_teachingdetail.getText().toString().trim();
        final String interestteach =  edt_interestteach.getText().toString().trim();
        final String timingteach =  edt_timingteach.getText().toString().trim();
        final String majorsubject =  edt_majorsubject.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "REGISTERING...", "Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Register.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_FIRSTNAME,firstname);
                hashMap.put(Config.KEY_LASTNAME,lastname);
                hashMap.put(Config.KEY_FATHERNAME,fathername);
                hashMap.put(Config.KEY_GENDER,gender);
                hashMap.put(Config.KEY_DOB,dob);
                hashMap.put(Config.KEY_AGE,age);
                hashMap.put(Config.KEY_MOBILENO,mobileno);
                hashMap.put(Config.KEY_CNIC,cnic);
                hashMap.put(Config.KEY_ADDRESSS,address);
                hashMap.put(Config.KEY_EMAIL,email);
                hashMap.put(Config.KEY_IMAGE, image);
                hashMap.put(Config.KEY_EDUCATION,education);
                hashMap.put(Config.KEY_EXPERIENCE,experience);
                hashMap.put(Config.KEY_TEACHINGDETAIL,teachingdetail);
                hashMap.put(Config.KEY_INTERESTTEACH,interestteach);
                hashMap.put(Config.KEY_TIMINGTEACH,timingteach);
                hashMap.put(Config.KEY_MAJORSUBJECT,majorsubject);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.REGISTER_TUTOR, hashMap);
                return s;
            }
        }
        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }


    @Override
    public void onClick(View view) {
        if (view == btn_register) {
            if (bitmap == null) {
                Toast.makeText(this, "Please Select Image", Toast.LENGTH_LONG).show();
            } else {
                CreateUser();
            }
        } else {
            switch (view.getId()) {
                case R.id.btnCamera:
                    Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, 1);
                    break;
            }
        }
    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            // TODO Auto-generated method stub
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case 1:
                    if (resultCode == RESULT_OK) {
                        Bundle extras = data.getExtras();
                        bitmap = (Bitmap) extras.get("data");
                        capturedImage.setImageBitmap(bitmap);
                    }
                    break;
            }
        }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}