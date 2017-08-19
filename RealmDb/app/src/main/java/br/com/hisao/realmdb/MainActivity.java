package br.com.hisao.realmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.hisao.realmdb.dbhelper.PersonHelper;
import br.com.hisao.realmdb.model.Person;
import br.com.hisao.realmdb.util.Log;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtMobile;
    private Button btnAdd;
    private Button btnList;
    private TextView txvList;


    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMobile = (EditText) findViewById(R.id.edtMobile);
        edtName = (EditText) findViewById(R.id.edtName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnList = (Button) findViewById(R.id.btnList);
        txvList = (TextView) findViewById(R.id.txvList);

        realm = Realm.getDefaultInstance();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void list() {
        RealmResults<Person> results = realm.where(Person.class).findAll();

        StringBuilder stringBuilder = new StringBuilder();

        for (Person p : results) {
            stringBuilder.append(p.getId() + " - " + p.getName() + " - " + p.getMobile() + "\n");
        }
        txvList.setText(stringBuilder.toString());
    }

    private void save() {
        final String mobile = edtMobile.getText().toString().trim();
        final String name = edtName.getText().toString().trim();
        if (mobile.length() > 0 && name.length() > 0) {
            realm.executeTransactionAsync(new Realm.Transaction() {
                                              @Override
                                              public void execute(Realm realm) {
                                                  long id = PersonHelper.getCurrentLastId(realm);
                                                  if (id == 0) {
                                                      return;
                                                  }
                                                  Person person = realm.createObject(Person.class, id + 1);
                                                  person.setName(name);
                                                  person.setMobile(mobile);
                                              }
                                          }, new Realm.Transaction.OnSuccess() {
                                              @Override
                                              public void onSuccess() {
                                                  Log.d("MainActivity:onSuccess:57 ");
                                                  list();
                                              }
                                          }, new Realm.Transaction.OnError() {
                                              @Override
                                              public void onError(Throwable error) {
                                                  Log.d("MainActivity:onError:62 " + error.getMessage());
                                              }
                                          }
            );
        } else {
            Toast.makeText(MainActivity.this, R.string.error_mandatory_fields, Toast.LENGTH_LONG).show();
        }
    }
}
