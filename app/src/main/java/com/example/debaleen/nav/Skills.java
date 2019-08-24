package com.example.debaleen.nav;

import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Skills extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    GridView gridView;

    Integer[] imageIDs = {R.drawable.c_language, R.drawable.java, R.drawable.python, R.drawable.html5,
            R.drawable.css3_alt,  R.drawable.php};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridView = (GridView)findViewById(R.id.gridView);

        gridView.setAdapter(new ImageAdapterGridView(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    Toast.makeText(getApplicationContext(), "I love C language!!", Toast.LENGTH_SHORT).show();
                }
                else if(i == 1)
                {
                    Toast.makeText(getApplicationContext(), "Java is my favourite OO Language!!", Toast.LENGTH_SHORT).show();
                }
                else if(i == 2)
                {
                    Toast.makeText(getApplicationContext(), "I am a beginer in Python!!", Toast.LENGTH_SHORT).show();
                }
                else if(i == 3)
                {
                    Toast.makeText(getApplicationContext(), "Let's make a modern web skeleton!", Toast.LENGTH_SHORT).show();
                }
                else if(i == 4)
                {
                    Toast.makeText(getApplicationContext(), "The web skeleton needs a good dress!", Toast.LENGTH_SHORT).show();
                }
                else if(i == 5)
                {
                    Toast.makeText(getApplicationContext(), "Brain in the server!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public class ImageAdapterGridView extends BaseAdapter {
        private Context context;

        public ImageAdapterGridView(Context c)
        {
            context = c;
        }

        @Override
        public int getCount()
        {
            return imageIDs.length;
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if(convertView == null)
            {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }
            else
            {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(imageIDs[position]);

            return imageView;
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
            Intent i = new Intent(getApplicationContext(), About.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(getApplicationContext(), Contact.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_skills) {
            Toast.makeText(getApplicationContext(),"You are already in Skills page.", Toast.LENGTH_SHORT).show();
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


