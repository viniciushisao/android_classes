package br.com.hisao.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
public class ChooseColorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_color);
        Intent intent = getIntent();
        String color = intent.getStringExtra(EXTRA_MESSAGE);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_choose_color);
        linearLayout.setBackgroundColor(Color.parseColor("#" + color));

        Button btnClose = (Button) findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
