package com.example.taz.canadafood;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

//http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/

public class CanadaFood extends Activity {
    DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canada_food);
        new PrefetchData().execute();
        DBAdapter db=new DBAdapter(this);
    }
    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls

        }

        @Override
        protected Void doInBackground(Void... arg0) {
        /*
         * Will make http call here This call will download required data
         * before launching the app
         * example:
         * 1. Downloading and storing in SQLite
         * 2. Downloading images
         * 3. Fetching and parsing the xml / json
         * 4. Sending device information to server
         * 5. etc.,
         */ try {
                db.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(!db.doesTableExist()) {
                db.drop();
                URL url = null;
                try {
                    // url=new URL("https://drive.google.com/uc?export=download&id=0B8xssHi6toRnenFmOTBNSWcwOTA");
                    url = new URL("https://drive.google.com/uc?export=download&id=0B8xssHi6toRneWt6MngxMmE1Vmc");
                    Log.d("Get", " URL " + url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection c = null;
                try {
                    c = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;

                try {
                    c.setRequestMethod("GET");
                    Log.d("Set ", "GET method");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }
                try {
                    c.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("Connect ", " C" + c);

                InputStream is = null;
                try {
                    is = c.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;

                try {
                    while ((line = br.readLine()) != null) {
                        String[] v = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                        String year = v[0];
                        String geo = v[1];
                        String food = v[2];
                        String commodity = v[3];
                        String vec = v[4];
                        String coor = v[5];
                        String val = v[6];
                        db.insertValues(year, geo, food, commodity, vec, coor, val);
                        Log.i("msg", line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("Finished", "FINISHED");
            }

            //http://stackoverflow.com/questions/8142335/download-csv-in-android
            //using try and catch to avoid errors
            if(!db.doesTableExist2()) {
                db.drop2();
                URL url2 = null;
                try {
                    url2 = new URL("https://drive.google.com/uc?export=download&id=0B8xssHi6toRnV2tsR2stSlZIT2c");
                    Log.d("Get", " URL " + url2);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection c2 = null;
                try {
                    c2 = (HttpURLConnection) url2.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;

                try {
                    c2.setRequestMethod("GET");
                    Log.d("Set ", "GET method");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }
                try {
                    c2.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("Connect ", " C" + c2);

                InputStream is2 = null;
                try {
                    is2 = c2.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
                String line2 = null;
                String[] v1 = null;
                try {
                    while ((line2 = br2.readLine()) != null) {
                        v1 = line2.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                        String year2 = v1[0];
                        String geo2 = v1[1];
                        String food2 = v1[2];
                        String commodity2 = v1[3];
                        String vec2 = v1[4];
                        String coor2 = v1[6];
                        String val2 = v1[5];
                        db.insertValues2(year2, geo2, food2, commodity2, vec2, coor2, val2);
                        Log.i("msg", line2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                db.close();
                Log.d("Finished for french", "FINISHED for french");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // After completing http call
            // will close this activity and lauch main activity
            Intent i = new Intent(CanadaFood.this, MainActivity.class);//Here Main activity is the splash screen.
            startActivity(i);

            // close this activity
            finish();
        }

    }

}
