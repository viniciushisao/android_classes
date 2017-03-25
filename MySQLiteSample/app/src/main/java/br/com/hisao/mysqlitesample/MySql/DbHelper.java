package br.com.hisao.mysqlitesample.MySql;

import java.util.LinkedList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "PeopleDB";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create person table
        String CREATE_TABLE = "CREATE TABLE " + TABLE_PEOPLE + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_CPF + " TEXT, " +
                KEY_NAME + " TEXT )";

        // create persons table
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older persons table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);

        // create fresh persons table
        this.onCreate(db);
    }


    // Persons table name
    private static final String TABLE_PEOPLE = "people";

    // Persons Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CPF = "cpf";
    private static final String KEY_NAME = "name";

    private static final String[] COLUMNS = {KEY_ID, KEY_CPF, KEY_NAME};

    public void addPerson(Person person) {

        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_CPF, person.getCpf()); // get cpf
        values.put(KEY_NAME, person.getName()); // get name

        // 3. insert
        db.insert(TABLE_PEOPLE, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Person getPerson(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_PEOPLE, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
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
        person.setCpf(cursor.getString(1));
        person.setName(cursor.getString(2));

        // 5. return person
        return person;
    }


    public Person getLastInsertedPerson() {
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_PEOPLE, // a. table
                        COLUMNS, // b. column names
                        null, // c. selections
                        null, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToLast();
        } else if (cursor == null) {
            return null;
        }

        // 4. build person object
        Person person = new Person();
        person.setId(Integer.parseInt(cursor.getString(0)));
        person.setCpf(cursor.getString(1));
        person.setName(cursor.getString(2));

        // 5. return person
        return person;

    }

    // Get All Persons
    public List<Person> getAllPersons() {
        List<Person> persons = new LinkedList<Person>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PEOPLE;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build person and add it to list
        Person person = null;
        if (cursor.moveToFirst()) {
            do {
                person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setCpf(cursor.getString(1));
                person.setName(cursor.getString(2));

                // Add person to persons
                persons.add(person);
            } while (cursor.moveToNext());
        }

        // return persons
        return persons;
    }

    // Updating single person
    public int updatePerson(Person person) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_CPF, person.getCpf());
        values.put(KEY_NAME, person.getName());

        // 3. updating row
        int i = db.update(TABLE_PEOPLE, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{String.valueOf(person.getId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    public int deleteAllPerson() {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        int a = db.delete(TABLE_PEOPLE, "1", null);
        return a;
    }

    // Deleting single person
    public void deletePerson(int id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_PEOPLE,
                KEY_ID + " = ?",
                new String[]{String.valueOf(id)});

        // 3. close
        db.close();

    }
}