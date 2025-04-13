package com.example.contacte;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ContactViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<String>> contactsLiveData = new MutableLiveData<>();

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactsLiveData.setValue(new ArrayList<>());
        loadContacts();
    }

    public void loadContacts() {
        ArrayList<String> contactsList = new ArrayList<>();
        ContentResolver cr = getApplication().getContentResolver();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        if (phones != null) {
            int nameIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            while (phones.moveToNext()) {
                String name = phones.getString(nameIndex);
                String phoneNumber = phones.getString(numberIndex);
                contactsList.add(name + " : " + phoneNumber);
            }

            phones.close();
        }

        contactsLiveData.postValue(contactsList);
    }
}