package expence.ubiad.example.com.expence;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class Report extends Activity implements View.OnClickListener, ListView.OnItemClickListener{

    private EditText editTextId;
    private Button buttonGet;
    private ProgressDialog loading;
    private ListView listView;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        editTextId = (EditText) findViewById(R.id.editTextId);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        buttonGet.setOnClickListener(this);
        editTextId.setOnClickListener(this);
    }
    private void getData() {
        String id = editTextId.getText().toString().trim();
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);
        String url = Config.DATA_URL+editTextId.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Report.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response){

        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String com_id = jo.getString(Config.KEY_NAME);
                String cus_id = jo.getString(Config.KEY_ADDRESS);
                String complaints_massage = jo.getString(Config.KEY_VC);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.KEY_NAME,com_id);
                employees.put(Config.KEY_ADDRESS,cus_id);
                employees.put(Config.KEY_VC,complaints_massage);
                list.add(employees);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                Report.this, list, R.layout.list_item,
                new String[]{Config.KEY_NAME,Config.KEY_ADDRESS,Config.KEY_VC},
                new int[]{R.id.id, R.id.name, R.id.msg});
        listView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        if (v == buttonGet) {
            getData();
        }
        if(v == editTextId)
        {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            editTextId.setText(year + "-" +(monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
}