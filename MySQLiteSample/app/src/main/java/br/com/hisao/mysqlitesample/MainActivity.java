package br.com.hisao.mysqlitesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.hisao.mysqlitesample.MySql.DbHelper;
import br.com.hisao.mysqlitesample.MySql.Person;

public class MainActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private TextView tvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edCpf = (EditText) findViewById(R.id.et_cpf);
        final EditText edName = (EditText) findViewById(R.id.et_name);
        Button btnAdd = (Button) findViewById(R.id.bt_add);
        tvList = (TextView) findViewById(R.id.tv_list);
        Button btnDeleteLast = (Button) findViewById(R.id.btn_deletelast);
        Button btnDeleteAll = (Button) findViewById(R.id.btn_deleteall);

        dbHelper = new DbHelper(getApplicationContext());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cpf = edCpf.getText().toString();
                String name = edName.getText().toString();

                Person person = new Person(cpf, name);
                dbHelper.addPerson(person);
                listAll();
            }
        });

        btnDeleteLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = dbHelper.getLastInsertedPerson();
                if (person != null) {
                    dbHelper.deletePerson(person.getId());
                }
                listAll();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAllPerson();
                listAll();
            }
        });
    }

    private void listAll() {
        List<Person> persons = dbHelper.getAllPersons();
        String list = "";
        for (Person person : persons) {
            list += (person.toString() + '\n');
            Log.d("person", person.toString());
        }

        list += '\n';

        Person person = dbHelper.getLastInsertedPerson();

        if (person != null) {
            list += "LAST: " + person + '\n';
        }
        tvList.setText(list);
    }
}
