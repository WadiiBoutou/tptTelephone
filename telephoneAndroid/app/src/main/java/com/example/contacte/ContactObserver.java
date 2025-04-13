package com.example.contacte;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactObserver extends ContentObserver {

    private final Context context;

    public ContactObserver(Handler handler, Context context) {
        super(handler);
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);

        Log.d("ContactObserver", "Contact Changed: " + uri);

        if (uri != null && uri.toString().contains("contacts/")) {

            Cursor cursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);

            if (cursor != null && cursor.moveToLast()) {  // Récupérer seulement le dernier (new contact)

                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String name = cursor.getString(nameIndex);
                String number = cursor.getString(numberIndex);
                Long userId = cursor.getLong(numberIndex);

                cursor.close();

                Log.d("ContactObserver", "New contact detected: " + name + " - " + number);

                sendContactToBackend(name, number,userId);
            }
        }
    }

    private void sendContactToBackend(String name, String phoneNumber, Long userId) {
        ContactModel contact = new ContactModel(name, phoneNumber, userId);

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        api.addContact(contact).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("ContactObserver", "Contact sent successfully: " + name);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ContactObserver", "Failed to send contact: " + name, t);
            }
        });
    }

}