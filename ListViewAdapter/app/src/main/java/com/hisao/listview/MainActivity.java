package com.hisao.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> namesList = new ArrayList<String>();
        namesList.add("Vinicius");
        namesList.add("Vanessa");
        namesList.add("Jessica");
        namesList.add("Amanda");
        namesList.add("Adriana");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                namesList );

        ListView lstNames = (ListView) findViewById(R.id.lst_names);
        lstNames.setAdapter(arrayAdapter);
        lstNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MainActivity", "MainActivity:onItemClick:36 CLICADO: " + namesList.get(i) );
            }
        });
    }
}
