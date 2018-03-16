package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;
import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {
    static Button HiddenButton;
    static Button Save;
    static Button Cancel;

    static Boolean IsHidden = true;

    public class Toggle implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view == HiddenButton) {
                IsHidden = !IsHidden;
                if (IsHidden) {
                    findViewById(R.id.ToHide).setVisibility(View.INVISIBLE);
                    HiddenButton.setText("Show Additional Fields");
                } else {
                    findViewById(R.id.ToHide).setVisibility(View.VISIBLE);
                    HiddenButton.setText("Hide content");
                }
            } else if (view == Save) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                String name = ((EditText) findViewById(R.id.Name)).getText().toString();
                String phone = ((EditText) findViewById(R.id.Number)).getText().toString();
                String email = ((EditText) findViewById(R.id.Email)).getText().toString();
                String address = ((EditText) findViewById(R.id.Postal)).getText().toString();
                String jobTitle = ((EditText) findViewById(R.id.Position)).getText().toString();
                String company = ((EditText) findViewById(R.id.Company)).getText().toString();
                String website = ((EditText) findViewById(R.id.Site)).getText().toString();
                String im = ((EditText) findViewById(R.id.Site)).getText().toString();

                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                }
                if (jobTitle != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            } else if (view == Cancel) {
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        HiddenButton = findViewById(R.id.Hide);
        Save = findViewById(R.id.Save);
        Cancel = findViewById(R.id.Cancel);

        HiddenButton.setOnClickListener(new Toggle());
        Save.setOnClickListener(new Toggle());
        Cancel.setOnClickListener(new Toggle());
    }
}
