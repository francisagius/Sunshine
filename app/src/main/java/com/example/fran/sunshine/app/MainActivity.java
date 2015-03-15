package com.example.fran.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

            //
            Intent settingIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingIntent);

            //

            return true;
        }
        if (id == R.id.action_map_location) {

            //
            //Intent settingIntent = new Intent(this, SettingsActivity.class);
            //startActivity(settingIntent);
            System.out.println("map show me the map please!");
            //String geoLocation = "geo:0,0?q=11+Earlsway%2C+Euxton";
           // space %20
           // comma %2C
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String location = prefs.getString(getString(R.string.pref_location_key),getString(R.string.pref_location_default));
            System.out.println("map and the location is..."+location);
           String GEO_STUB = "geo:0,0?q=";
           String geoLocation = String.format("%s%s", GEO_STUB, location);
            System.out.println("map geoLocation str..."+geoLocation);
           showMap(Uri.parse(geoLocation));

            //

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
