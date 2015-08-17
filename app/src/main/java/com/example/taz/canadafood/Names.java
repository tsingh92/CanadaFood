package com.example.taz.canadafood;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Names extends Activity{
    SQLiteDatabase db;
    String ye="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        //http://stackoverflow.com/questions/23869008/selected-value-from-spinner-into-new-activity
        Intent intent = getIntent();
        String y= intent.getStringExtra("Year");
        ye=y;
        ListView lv = (ListView) findViewById(R.id.listView);
        final DBAdapter.DatabaseHelper myDbHelper = new DBAdapter.DatabaseHelper(this);
        db = myDbHelper.getWritableDatabase();
        final Cursor curs = db.rawQuery("SELECT * FROM english where YEAR="+ye+" ORDER BY COMMODITY", null);
        CAdapter adapt = new CAdapter(this, curs);
        lv.setAdapter(adapt);

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
