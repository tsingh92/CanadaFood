package com.example.taz.canadafood;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Names2 extends Activity{
    SQLiteDatabase db;
    String yz="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names2);
        //http://stackoverflow.com/questions/23869008/selected-value-from-spinner-into-new-activity
        Intent intent2 = getIntent();
        String y2= intent2.getStringExtra("année");
        yz=y2;
        ListView lv2 = (ListView) findViewById(R.id.listView2);
        final DBAdapter.DatabaseHelper myDbHelper = new DBAdapter.DatabaseHelper(this);
        db = myDbHelper.getWritableDatabase();
        final Cursor curs2 = db.rawQuery("SELECT * FROM french where YEAR="+yz+" ORDER BY COMMODITY", null);
        CAdapter2 adapt2 = new CAdapter2(this, curs2);
        lv2.setAdapter(adapt2);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_names, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
