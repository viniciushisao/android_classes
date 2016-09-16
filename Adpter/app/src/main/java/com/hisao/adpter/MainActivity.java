package com.hisao.adpter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lstNumbers =(ListView)findViewById(R.id.lst_numbers);

        ArrayList<Integer> anyNumber = new ArrayList<>();
        anyNumber.add(2);
        anyNumber.add(24);
        anyNumber.add(1);
        anyNumber.add(9);
        anyNumber.add(5);
        anyNumber.add(1);

        lstNumbers.setAdapter(new NumberAdapter(anyNumber, this));
    }
}
