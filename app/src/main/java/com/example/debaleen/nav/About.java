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
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class About extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView facebook, linkedin, hackerrank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_about);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            facebook = (TextView)findViewById(R.id.textView9);
            linkedin = (TextView)findViewById(R.id.textView10);
            hackerrank = (TextView)findViewById(R.id.textView11);

            facebook.setMovementMethod(LinkMovementMethod.getInstance());
            linkedin.setMovementMethod(LinkMovementMethod.getInstance());
            hackerrank.setMovementMethod(LinkMovementMethod.getInstance());


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        } catch (Exception e) {
            Intent i = new Intent();
            i.putExtra("err", String.valueOf(e));
            setResult(0, i);
            finish();
        }
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
            Toast.makeText(getApplicationContext(),"You are already in About Me page.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(getApplicationContext(), Contact.class);
            startActivity(i);
            finish();


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
}
