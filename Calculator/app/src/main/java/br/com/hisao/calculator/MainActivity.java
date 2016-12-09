package br.com.hisao.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edShow;

    private int var1;
    private int operation;

    private final static int ADD = 1;
    private final static int MINUS = 2;
    private final static int MULTIPLY = 3;
    private final static int DIVIDE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView result = (TextView) findViewById(R.id.tv_result);
        edShow = (EditText) findViewById(R.id.ed_show);

        Button number1 = (Button) findViewById(R.id.btn_number_1);
        Button number3 = (Button) findViewById(R.id.btn_number_3);
        Button number4 = (Button) findViewById(R.id.btn_number_4);
        Button number5 = (Button) findViewById(R.id.btn_number_5);
        Button number6 = (Button) findViewById(R.id.btn_number_6);
        Button number7 = (Button) findViewById(R.id.btn_number_7);
        Button number8 = (Button) findViewById(R.id.btn_number_8);
        Button number9 = (Button) findViewById(R.id.btn_number_9);
        Button number0 = (Button) findViewById(R.id.btn_number_0);

        final Button add = (Button) findViewById(R.id.btn_add);
        Button equals = (Button) findViewById(R.id.btn_equals);
        Button minus = (Button) findViewById(R.id.btn_minus);
        Button multiply = (Button) findViewById(R.id.btn_multiply);
        Button divide = (Button) findViewById(R.id.btn_divide);
        Button clean = (Button) findViewById(R.id.btn_clean);

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edShow.setText("");
            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "1");
            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "3");
            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "4");
            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "5");
            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "6");
            }
        });

        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "7");
            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "8");
            }
        });

        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "9");
            }
        });

        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edShow.getText().toString().trim();
                edShow.setText(text + "0");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var1 = Integer.parseInt(edShow.getText().toString());
                operation = ADD;
                edShow.setText("");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var1 = Integer.parseInt(edShow.getText().toString());
                operation = MINUS;
                edShow.setText("");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var1 = Integer.parseInt(edShow.getText().toString());
                operation = MULTIPLY;
                edShow.setText("");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                var1 = Integer.parseInt(edShow.getText().toString());
                operation = DIVIDE;
                edShow.setText("");
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int var2 = Integer.parseInt(edShow.getText().toString());
                int r = 0;
                switch (operation){
                    case ADD:
                         r = var1 + var2;
                        result.setText(Integer.toString(r));
                        break;
                    case MINUS:
                        r = var1 - var2;
                        result.setText(Integer.toString(r));
                        break;
                    case MULTIPLY:
                        r = var1 * var2;
                        result.setText(Integer.toString(r));
                        break;
                    case DIVIDE:
                        r = var1 / var2;
                        result.setText(Integer.toString(r));
                        break;
                }
                edShow.setText("");
            }
        });

    }

    public void onClickButton2(View view) {
        String text = edShow.getText().toString().trim();
        edShow.setText(text + "2");
    }
}
