package br.com.hisao.mysqlitesample.MySql;

import java.util.LinkedList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PersonDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create person table
        String CREATE_BOOK_TABLE = "CREATE TABLE persons ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cpf TEXT, "+
                "name TEXT )";

        // create persons table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older persons table if existed
        db.execSQL("DROP TABLE IF EXISTS persons");

        // create fresh persons table
        this.onCreate(db);
    }


    // Persons table name
    private static final String TABLE_BOOKS = "persons";

    // Persons Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "cpf";
    private static final String KEY_AUTHOR = "name";

    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_AUTHOR};

    public void addPerson(Person person){
        Log.d("addPerson", person.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, person.getTitle()); // get cpf
        values.put(KEY_AUTHOR, person.getAuthor()); // get name

        // 3. insert
        db.insert(TABLE_BOOKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Person getPerson(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_BOOKS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build person object
        Person person = new Person();
        person.setId(Integer.parseInt(cursor.getString(0)));
        person.setTitle(cursor.getString(1));
        person.setAuthor(cursor.getString(2));

        Log.d("getPerson("+id+")", person.toString());

        // 5. return person
        return person;
    }

    // Get All Persons
    public List<Person> getAllPersons() {
        List<Person> persons = new LinkedList<Person>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BOOKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build person and add it to list
        Person person = null;
        if (cursor.moveToFirst()) {
            do {
                person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setTitle(cursor.getString(1));
                person.setAuthor(cursor.getString(2));

                // Add person to persons
                persons.add(person);
            } while (cursor.moveToNext());
        }

        Log.d("getAllPersons()", persons.toString());

        // return persons
        return persons;
    }

    // Updating single person
    public int updatePerson(Person person) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("cpf", person.getTitle()); // get cpf
        values.put("name", person.getAuthor()); // get name

        // 3. updating row
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(person.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single person
    public void deletePerson(Person person) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BOOKS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(person.getId()) });

        // 3. close
        db.close();

        Log.d("deletePerson", person.toString());

    }
}