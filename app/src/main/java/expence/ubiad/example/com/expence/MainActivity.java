package expence.ubiad.example.com.expence;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button)findViewById(R.id.btn_balance);
        final TextView Text1=(TextView)findViewById(R.id.txt_advance);
        final TextView Text2=(TextView)findViewById(R.id.txt_expence);
        final TextView Text3=(TextView)findViewById(R.id.txt_balance);
        Text1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0 && Text2.getText().length() != 0)
                    Text3.setText(""+(Integer.parseInt(s.toString()) - Integer.parseInt(Text2.getText().toString())));
            }
        });
        Text2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0 && Text1.getText().length() != 0)
                    Text3.setText(""+( Integer.parseInt(Text1.getText().toString()) - Integer.parseInt(s.toString())));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Balance.class);
                final int result = 2;
                intent.putExtra("callingActivity2",Text1.getText().toString());
                startActivityForResult(intent,result);
            }
        });
        Button btn2 = (Button)findViewById(R.id.btn_expence);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Expence.class);
                final int result = 1;
                intent.putExtra("callingActivity",Text2.getText().toString());
                startActivityForResult(intent,result);
            }
        });
        Button btn3 = (Button)findViewById(R.id.btn_report);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Report.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    TextView textView = (TextView) findViewById(R.id.txt_expence);
                    int sum = data.getIntExtra("RESULT_SUM", 0);
                    textView.setText(String.valueOf(sum));
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    TextView textView1 = (TextView) findViewById(R.id.txt_advance);
                    int sum1 = data.getIntExtra("RESULT", 0);
                    textView1.setText(String.valueOf(sum1));
                }
                break;
        }
    }
}
