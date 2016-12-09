package br.com.hisao.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edColor = (EditText) findViewById(R.id.ed_color);

        Button chooseColor = (Button) findViewById(R.id.btn_choose);
        chooseColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseColorActivity.class);
                String color = edColor.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, color);
                startActivity(intent);
            }
        });
    }
}
