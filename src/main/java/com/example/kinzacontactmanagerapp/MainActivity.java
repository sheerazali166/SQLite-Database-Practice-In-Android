package com.example.kinzacontactmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.kinzacontactmanagerapp.data.DatabaseHandler;
import com.example.kinzacontactmanagerapp.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);


        Log.d("Count", "onCreate: " + databaseHandler.getCount());


        Contact kinza = new Contact();

        kinza.setName("Kinza Amjad");
        kinza.setPhoneNumber("626-555-626");

       // databaseHandler.addContact(kinza);

        Contact rabia = new Contact();

        rabia.setName("Rabia Amjad");
        rabia.setPhoneNumber("555-555-555");

       // databaseHandler.addContact(rabia);


        Contact ramsha = new Contact();

        ramsha.setName("Ramsha Naseer");
        ramsha.setPhoneNumber("888-777-888");

        //databaseHandler.addContact(ramsha);


        // Get single contact
//        Contact singleContact = databaseHandler.getContact(2);
//
//        singleContact.setName("Ramsha Naseer");
//        singleContact.setPhoneNumber("777-777-777");

     //   databaseHandler.deleteContact(singleContact);

      //  int rowId = databaseHandler.updateContact(singleContact);

      //  Log.d("RowId", "onCreate: " + rowId);

//
//        Log.d("Main", "onCreate: " + singleContact.getName());
//        Log.d("Main", "onCreate: " + singleContact.getPhoneNumber());





        List<Contact> contactList = databaseHandler.getAllContacts();

        for (Contact contact: contactList) {

            Log.d("MainActivity", "onCreate: " + contact.getId());
            Log.d("MainActivity", "onCreate: " + contact.getName());
            Log.d("MainActivity", "onCreate: " + contact.getPhoneNumber());
        }

    }
}