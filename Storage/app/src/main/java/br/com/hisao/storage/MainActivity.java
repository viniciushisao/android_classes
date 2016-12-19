package br.com.hisao.storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.hisao.storage.MySql.Book;
import br.com.hisao.storage.MySql.MySQLiteHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MySQLiteHelper db = new MySQLiteHelper(this);

        Button btnSaveInternal = (Button) findViewById(R.id.btn_saveinternal);
        final EditText edtText = (EditText) findViewById(R.id.edt_texttosave);
        final TextView txtText = (TextView) findViewById(R.id.txv_text);

        btnSaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorePrivate.writeToFile(edtText.getText().toString(), getApplicationContext());
                txtText.setText(StorePrivate.readFromFile(getApplicationContext()));
            }
        });

        Button btnSaveExternal = (Button) findViewById(R.id.btn_saveexternal);
        btnSaveExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorePublic.writeToSDFile(edtText.getText().toString());
                txtText.setText(StorePublic.readRaw(getApplicationContext()));
            }
        });

        Button btnSaveSql = (Button) findViewById(R.id.btn_savesql);
        btnSaveSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Book book = new Book(edtText.getText().toString(),edtText.getText().toString());
                db.addBook(book);

                // get all books
                List<Book> list = db.getAllBooks();

                String allBooks = "";
                int cont = 0;
                for (Book bookUnique : list){
                    allBooks = allBooks + " register #" + cont++ + " " + bookUnique.toString();
                }

                // delete one book
                //db.deleteBook(list.get(0));

                // get all books
                //db.getAllBooks();
                //https://developer.android.com/training/basics/data-storage/databases.html

                txtText.setText(allBooks);
            }
        });



    }
}
