package com.example.danyue.h5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Timer;

public class MainActivity extends Activity {
    public static int activityNum = 0;
    final public static int readEnd = LoginActivity.Set_View_Long;


    private ListView listView;
    private EditText editText;
    private Button button;
    private WebListAdapter adapter;
    private ArrayList<String> data;
    private View vHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.weblist);
        data = new ArrayList<>();
        adapter = new WebListAdapter(this,data);
        initData();
        initHead();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,WebThreeActivity.class);
                intent.putExtra("link",data.get(position-1));
                startActivity(intent);
            }
        });
    }

    private void initHead(){
        vHead= View.inflate(this, R.layout.layout_main_head, null);
        editText = (EditText) vHead.findViewById(R.id.edit_input);
        button = (Button) vHead.findViewById(R.id.add_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addLists(editText.getText().toString().replace(" ",""));
                editText.setText("");
            }
        });
        listView.addHeaderView(vHead);
    }

    private void initData(){
        SharedPreferences sp = this.getSharedPreferences("SP", MODE_PRIVATE);
        int length = sp.getInt("length",0);
        if (length != 0){
            for (int len = length ; len > 0 ; len--){
                data.add(sp.getString(""+(len-1), "error"));
            }
        }
    }

    private void preData(){
        SharedPreferences sp = this.getSharedPreferences("SP", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("length",data == null ? 0 : data.size());
        for (int i = 0 ; i < (data == null ? 0 : data.size()) ; i++ ){
            editor.putString(""+i,data.get(i));
        }
        editor.commit();
    }

    @Override
    public void finish() {
        preData();
        super.finish();
    }
}
