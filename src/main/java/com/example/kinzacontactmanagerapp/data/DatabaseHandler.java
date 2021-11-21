package com.example.kinzacontactmanagerapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.kinzacontactmanagerapp.R;
import com.example.kinzacontactmanagerapp.model.Contact;
import com.example.kinzacontactmanagerapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = String.valueOf(R.string.db_drop);

        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        onCreate(db);

    }

    // Add contact
    public void addContact(Contact contact){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, contact.getName());

        contentValues.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //insert to row
        database.insert(Util.TABLE_NAME, null, contentValues);
        database.close(); // closing db contact

        Log.d("DBHandler", "addContact: " + "item added");
    }

    // Get contact
    public Contact getContact(int id){

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if(cursor != null)
            cursor.moveToFirst();


        Contact contact = new Contact();

        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;

    }

    // Get all contacts
    public List<Contact> getAllContacts(){

        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = database.rawQuery(selectAll, null);


        // Loop through our data
        if(cursor.moveToFirst()){

            do {

                Contact contact = new Contact();

                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                contactList.add(contact);

            } while (cursor.moveToNext());
        }


        return contactList;
    }


    // Update contact
    public int updateContact(Contact contact){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, contact.getName());
        contentValues.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());


        return database.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});


    }


    //Delete single contact
    public void deleteContact(Contact contact){

        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(Util.TABLE_NAME, Util.KEY_ID + "=?", new String[]{
                String.valueOf(contact.getId())});

        database.close();
    }


    // Get contacts count
    public int getCount(){

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(countQuery, null);

        return cursor.getCount();

    }


}
