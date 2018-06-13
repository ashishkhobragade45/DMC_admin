package asia.getl.dmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Spinner sp_company,sp_zone,sp_dmc;
    ArrayAdapter<CharSequence> adp_company,adp_zone,adp_dmc;
    String str_company,str_zone,str_dmc;
    Button btn_counselling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Reportcompany.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FindViewById();
        spcompanymethod();//for spinner create and called activity

        btn_counselling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_company.equals("Select Company"))
                {
                    Toast.makeText(Home.this, "Hey...You missed to Select Company...!", Toast.LENGTH_SHORT).show();
                }else if(str_zone.equals("Select Zone"))
                {
                    Toast.makeText(Home.this, "Hey...You missed to Select Zone...!", Toast.LENGTH_SHORT).show();
                }else
                {
                    Intent intent = new Intent(Home.this,Report.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("company",str_company);
                    bundle.putString("zone",str_zone);
                    bundle.putString("dmc",str_dmc);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });



    }


    private void spcompanymethod() {

        adp_company = ArrayAdapter.createFromResource(this,R.array.company_home,R.layout.spinner_textview);
        adp_company.setDropDownViewResource(R.layout.spinner_textview);
        sp_company.setAdapter(adp_company);

        sp_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_company =String.valueOf(parent.getItemAtPosition(position));

                switch(str_company)
                {
                    case "ACC":

                        sp_zone.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACC)));
                        break;
                    case "ACL":
                        sp_zone.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACL)));
                        break;
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_zone = String.valueOf(parent.getItemAtPosition(position));

                switch (str_zone)
                {
                    case "ACC_EAST" :
                        sp_dmc.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACC_EAST)));
                        break;
                    case "ACC_WEST" :
                        sp_dmc.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACC_WEST)));
                        break;
                    case "ACC_NORHT" :
                        sp_dmc.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACC_NORHT)));
                        break;
                    case "ACC_SOUTH" :
                        sp_dmc.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACC_SOUTH)));
                        break;

                    // dive ACC and ACL

                    case "ACL_EAST" :
                        sp_dmc.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACL_EAST)));
                        break;

                    case "ACL_WEST" :
                        sp_dmc.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACL_WEST)));
                        break;

                    case "ACL_NORHT" :
                        sp_dmc.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACL_NORHT)));
                        break;

                    case "ACL_SOUTH" :
                        sp_dmc.setAdapter(new ArrayAdapter<String>(Home.this,R.layout.spinner_textview,
                                getResources().getStringArray(R.array.ACL_SOUTH)));
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_dmc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_dmc = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void FindViewById() {
        sp_company = (Spinner)findViewById(R.id.sp_company);
        sp_zone = (Spinner)findViewById(R.id.sp_zone);
        sp_dmc = (Spinner)findViewById(R.id.sp_dmc);
        btn_counselling = (Button)findViewById(R.id.btn_counselling);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
