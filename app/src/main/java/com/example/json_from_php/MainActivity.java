package com.example.json_from_php;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    Button bttn_sqlConn;
    Button bttn_Display;
    TextView textview;
    phpConn phpC = new phpConn();
    final String fetch = "http://192.168.100.82:8080/sqli/fetch2json.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview=(TextView)findViewById(R.id.textView);
        bttn_sqlConn= (Button)findViewById(R.id.bttnSQLCon);
        bttn_Display= (Button)findViewById(R.id.bttnDisplay);

        bttn_sqlConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    phpC.urlCon(fetch);
                    //getting an array back
                    JSONArray js_array = new JSONArray(phpC.getAnswer());
                    textview.setText(js_array.toString());

                    /* reading the JSON array line by line */
                    for (int i = 0; i < js_array.length(); i++) {
                        String value=js_array.getString(i);
                        Log.v("george json", i+"="+value);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
            }
        });

        bttn_Display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),DisplayListView.class);
                startActivity(intent);
            }
        });

    }
}
