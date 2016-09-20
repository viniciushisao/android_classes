package br.com.hisao.toast;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormalToast = (Button) findViewById(R.id.btn_normaltoast);
        Button btnPersonalizedToast = (Button) findViewById(R.id.btn_personalizedtoast);

        btnNormalToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "My first toast";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                //toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                toast.show();
            }
        });

        btnPersonalizedToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.toast_layout_root));
                ImageView image = (ImageView) layout.findViewById(R.id.image);
                image.setImageResource(android.R.drawable.alert_dark_frame);
                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("Hello! This is a custom toast!");
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });
    }
}

