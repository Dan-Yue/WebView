package com.example.danyue.h5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    public static int Set_View_Long = 70;
    public static int Set_View_Time = 200;

    EditText editText;

    EditText viewLong;
    Button viewLongButton;
    TextView viewLongText;

    EditText viewTime;
    Button viewTimeButton;
    TextView viewTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.exitText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("4533".equals(s.toString())) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

        viewLong = (EditText) findViewById(R.id.viewLong);
        viewLongButton = (Button) findViewById(R.id.viewLongButton);
        viewLongText = (TextView) findViewById(R.id.viewLongText);
        viewLongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(viewLong.getText().toString());
                Set_View_Long = (i < 70) ? 70 : i;
                viewLongText.setText("" + Set_View_Long);
            }
        });

        viewTime = (EditText) findViewById(R.id.viewTime);
        viewTimeButton = (Button) findViewById(R.id.viewTimeButton);
        viewTimeText = (TextView) findViewById(R.id.viewTimeText);
        viewTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(viewTime.getText().toString());
                Set_View_Time = (i < 200) ? 200 : i;
                viewTimeText.setText("" + Set_View_Time);
            }
        });
    }
}
