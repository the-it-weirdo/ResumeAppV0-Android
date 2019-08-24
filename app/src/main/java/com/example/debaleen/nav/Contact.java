package com.example.debaleen.nav;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;

public class Contact extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    Button email, call;
    EditText editText;
    SupportMapFragment mapFragment;

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       try {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_contact);

           Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
           setSupportActionBar(toolbar);

           mapFragment = (SupportMapFragment) getSupportFragmentManager()
                   .findFragmentById(R.id.map);
           mapFragment.getMapAsync(this);

           editText = (EditText) findViewById(R.id.editText);
           email = (Button) findViewById(R.id.button2);
           call = (Button) findViewById(R.id.button3);

           email.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String name = editText.getText().toString();
                   if (name.trim().compareTo("") != 0) {
                       String email = "ddsmegh18@gmail.com";
                       String earr[] = {email};
                       Intent i = new Intent(Intent.ACTION_SEND);
                       //i.setDataAndType(Uri.parse("email"), "message/rfc822");
                       /*i.setData(Uri.parse("email"));*/
                       i.setType("message/rfc822");
                       i.putExtra(Intent.EXTRA_EMAIL, earr);
                       i.putExtra(Intent.EXTRA_SUBJECT, "New message from APP. Sent by " + name + ".");
                       Intent chooser = Intent.createChooser(i, "Launch your favourite Email app");
                       startActivity(chooser);
                   } else {
                       Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
                   }
               }
           });

           call.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent i = new Intent(Intent.ACTION_DIAL);
                   i.setData(Uri.parse("tel:7478755667"));
                   startActivity(i);
               }
           });

           DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
           ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                   this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
           drawer.addDrawerListener(toggle);
           toggle.syncState();

           NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
           navigationView.setNavigationItemSelectedListener(this);
       }catch(Exception e)
       {
           Intent i = new Intent();
           i.putExtra("err", String.valueOf(e));
           setResult(0, i);
           finish();
       }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng loc = new LatLng(23.521265, 87.341215);
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);
        //mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext(), 0));
        CameraPosition.Builder camBuilder = CameraPosition.builder();
        camBuilder.bearing(45);
        camBuilder.tilt(30);
        camBuilder.target(loc);
        camBuilder.zoom(15);

        CameraPosition cp = camBuilder.build();

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(loc).title("Bengal College of Engineering & Technology"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(getApplicationContext(), Home.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(getApplicationContext(), About.class);
            startActivity(i);
            finish();
            /*Intent i = new Intent(getApplicationContext(), About.class);
            startActivityForResult(i, 0);*/
        } else if (id == R.id.nav_contact) {
            Toast.makeText(getApplicationContext(),"You are already in Contact Me page.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_skills) {
            Intent i = new Intent(getApplicationContext(), Skills.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_projects) {
            Intent i = new Intent(getApplicationContext(), Projects.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_share) {
            if(Build.VERSION.SDK_INT>=24)
            {
                //Toast.makeText(getApplicationContext(),"API >=24 not supported right now. Coming soon!!", Toast.LENGTH_SHORT).show();
                ApplicationInfo app = getApplicationContext().getApplicationInfo();
                String filepath = app.sourceDir;
                /*Uri uri = FileProvider.getUriForFile(getBaseContext(),
                        BuildConfig.APPLICATION_ID + ".provider",
                        new File(Environment.getExternalStorageDirectory(), "icm/" + ));*/

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("application/vnd.android.package-archive"); //MIME for app is "application/vnd.android.package-archive". But bluetooth doesnt't support it. so using "*/*"
                Uri uri = FileProvider.getUriForFile(getBaseContext(), BuildConfig.APPLICATION_ID+".fileprovider", new File(filepath));
                i.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(i, "Share via app"));
            }
            else {
                ApplicationInfo app = getApplicationContext().getApplicationInfo();
                String filepath = app.sourceDir;

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("application/vnd.android.package-archive"); //MIME for app is "application/vnd.android.package-archive". But bluetooth doesnt't support it. so using "*/*"
                i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filepath)));
                startActivity(Intent.createChooser(i, "Share via app"));
            }

        } else if (id == R.id.nav_send) {
            Toast.makeText(getApplicationContext(),"Coming Soon!!", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_rate) {
            Intent i = new Intent(getApplicationContext(), Rate.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 0)
        {
            String e = data.getStringExtra("err");
            EditText tv = (EditText)findViewById(R.id.editText);
            tv.setText(e);
        }
    }
}
