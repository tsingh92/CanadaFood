package com.example.taz.canadafood;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.SQLException;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBAdapter db=new DBAdapter(this);
        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //http://stackoverflow.com/questions/16439587/android-os-networkonmainthreadexception-with-android-4-2
        // to remove the error android.os.NetworkOnMainThreadException and import strict mode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.année, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        Button btn1=(Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                startActivity(intent);
            }
        });
        Button btn2=(Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Detail2.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myyear=(TextView) view;
        String selectedValue=parent.getItemAtPosition(position).toString();
        String selectedValue2=parent.getItemAtPosition(position).toString();
        //http://stackoverflow.com/questions/23869008/selected-value-from-spinner-into-new-activity
        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        Spinner spinner2=(Spinner) findViewById(R.id.spinner2);
        String value=spinner.getSelectedItem().toString();
        String value2=spinner2.getSelectedItem().toString();
        switch (parent.getId()) {
            case R.id.spinner:
                if(selectedValue.equals("Year")){
                    }
                else {
                    Toast.makeText(this, "You selected " + myyear.getText(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Names.class);
                    //http://stackoverflow.com/questions/23869008/selected-value-from-spinner-into-new-activity
                    intent.putExtra("Year", value);
                    startActivity(intent);
                }
                break;
            case R.id.spinner2:
                if(selectedValue.equals("année")){
                   }
                else {
                    Toast.makeText(this, "vous avez sélectionné " + myyear.getText(), Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(this, Names2.class);
                    //http://stackoverflow.com/questions/23869008/selected-value-from-spinner-into-new-activity
                    intent2.putExtra("année", value2);
                    startActivity(intent2);
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
