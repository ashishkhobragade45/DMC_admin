package asia.getl.dmc;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.Toast;


import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import asia.getl.dmc.model.Driver_history;
import asia.getl.dmc.model.Items;
import asia.getl.dmc.model.MyAdapter;
import java.io.PrintWriter;
import org.json.JSONObject;


public class Report extends AppCompatActivity {

    String company,zone,dmc;
    MaterialEditText edt_date;
    FirebaseDatabase database;
    DatabaseReference report,report1,ref_red,ref_yellow,ref_green,ref_gps;
    String dateenter;
    Button btn_report;
    FloatingActionButton fab_download;
    int i;
    String day;
    String redcount;
    String yellowcount;
    String greencount;
    String gpscount;

    String str_red,red1,red2;

    MyAdapter adapter;
    private RecyclerView recyclerView;
    String history_cdlno,history_cname,history_cplant, history_status,
    history_sharedtopics, history_tbt, history_ddi,history_ctrainername;
    private List<Items> albumList;
    FileWriter fileWriter;
    Driver_history driver_history;
    BufferedWriter fos = null;


    ArrayList<Driver_history> listfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //path selected on home
        final Bundle bundle = getIntent().getExtras();
        company=bundle.getString("company");
        zone=bundle.getString("zone");
        dmc=bundle.getString("dmc");

        //firebase init
        database = FirebaseDatabase.getInstance();

        Toast.makeText(Report.this, ""+company+zone+dmc, Toast.LENGTH_SHORT).show();

        findViewById();




        // 1. pass context and data to the custom adapter
       // adapter = new MyAdapter(this, generateData());

        // 2. Get ListView from activity_main.xml
       // ListView listView = (ListView) findViewById(R.id.listview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();


        adapter = new MyAdapter(albumList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        // 3. setListAdapter
        //listView.setAdapter(adapter);//very important


        // reportmethod();


        //report = database.getReference(edt_date.getText().toString().trim()+"/"+company+"/"+zone+"/"+dmc+"/");

      btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // dateenter = edt_date.getText().toString();

                setData();



            }
        });

      fab_download.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

         // new Mytask().execute();

           //DownloadFiles();
              exportfile();
          }
      });


    }


    private void exportfile() {



        String dateyear = edt_date.getText().toString();
        final String path = "daily_record_db"+"/"+edt_date.getText().toString()+"/"+company+"/"+zone+"/"+dmc;
        //report1 = database.getReference("daily_record_db"+"/"+edt_date.getText().toString()+"/"+company+"/"+zone+"/"+dmc);
        try {
            fos = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+"TestFinal5.txt",true));
        } catch (IOException e) {
            e.printStackTrace();
        }

            try {
                fos.write("DL number"+";"+"Driver Name"+";"+"Plant"+";"+"Status"+";"+"Topic"+";"+"TBT topic"+";"+"DDI topic"
                        +";"+"Trainer Name"+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


        for( i =0;i<30;i++)
        {


            report1 = database.getReference(path+"/"+i);
            report1.child("Red").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                   /* String test = dataSnapshot.getKey();
                    Log.d("test",test);
                    Driver_history driver_history = dataSnapshot.getValue(Driver_history.class);
                    String dlno = driver_history.getHistory_cplant();
                    Log.d("DL NO",dlno);*/
                    Driver_history driver_history = dataSnapshot.getValue(Driver_history.class);
                   String rdlno,name,plant,status,topic,tbt,ddi,trainer;
                   rdlno = driver_history.getHistory_cdlno();
                   name = driver_history.getHistory_cname();
                   plant = driver_history.getHistory_cplant();
                   status = driver_history.getHistory_status();
                   topic = driver_history.getHistory_sharedtopics();
                   tbt = driver_history.getHistory_tbt();
                   ddi = driver_history.getHistory_ddi();
                   trainer = driver_history.getHistory_ctrainername();

                   String test =rdlno+";"+name+";"+plant+";"+status+";"+topic+";"+tbt+";"+ddi+";"+trainer+"\n";
                   Log.d("Test123",test);


                   if(!test.isEmpty()) {
                      //listfile.add();
                       try {
                           fos = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+"TestFinal5.txt",true));
                       } catch (IOException e) {
                           e.printStackTrace();
                       }

                       try {
                           fos.write(test);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       try {
                           fos.close();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }



                       // listfile.add(rdlno+","+name+","+plant+","+status+","+topic+","+tbt+","+ddi+","+trainer+"\n");
                   }
                }


                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });





/*

            BufferedWriter fos = null;
                try {
                    fos = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+"File1.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try {
                    fos.write(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/

        }


        }













    private void setData() {
        report = database.getReference("daily_record_db"+"/"+edt_date.getText().toString()+"/"+company+"/"+zone+"/"+dmc);



        report.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                day = dataSnapshot.getKey();
                redcount = String.valueOf(dataSnapshot.child("Red").getChildrenCount());
                yellowcount = String.valueOf(dataSnapshot.child("Yellow").getChildrenCount());
                greencount = String.valueOf(dataSnapshot.child("Green").getChildrenCount());
                gpscount = String.valueOf(dataSnapshot.child("NON-IVMS Fitted").getChildrenCount());

               Items items = new Items(day,redcount,yellowcount,greencount,gpscount);
                albumList.add(items);

               // items.add(new Items(day,redcount,yellowcount,greencount,gpscount));


                adapter.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }


  /*  private ArrayList<Items> generateData(){
        report = database.getReference("daily_record_db"+"/"+"Jun2018"+"/"+company+"/"+zone+"/"+dmc);
        final ArrayList<Items> items = new ArrayList<Items>();


        report.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                day = dataSnapshot.getKey();
                redcount = String.valueOf(dataSnapshot.child("Red").getChildrenCount());
                yellowcount = String.valueOf(dataSnapshot.child("Yellow").getChildrenCount());
                greencount = String.valueOf(dataSnapshot.child("Green").getChildrenCount());
                gpscount = String.valueOf(dataSnapshot.child("NON-IVMS Fitted").getChildrenCount());

                items.add(new Items(day,redcount,yellowcount,greencount,gpscount));


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return items;
    }
*/




    private void reportmethod() {




        report = database.getReference("daily_record_db"+"/"+"Jun2018"+"/"+company+"/"+zone+"/"+dmc);




        report.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<BarEntry> rbar = new ArrayList<>();
                ArrayList<BarEntry> ybar = new ArrayList<>();
                ArrayList<BarEntry> gbar = new ArrayList<>();
                ArrayList<BarEntry> gpsbar = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<String>();


                for(DataSnapshot snap : dataSnapshot.getChildren())
                {

                    day = snap.getKey();
                    int dayint = Integer.parseInt(day);




                           /* domainLabels[dayint] = snap.getKey();
                            series1Numbers[dayint] = redcount;
                            series2Numbers[dayint] = yellowcount;
                            series3Numbers[dayint] = greencount;
                            series4Numbers[dayint] = gpscount;
*/
                   // data.add(new CustomDataEntry(day, redcount, yellowcount, greencount, gpscount));

                   /* data.add(new ValueDataEntry(day, redcount));
                    data.add(new ValueDataEntry(day,yellowcount));
                    data.add(new ValueDataEntry(day,greencount));
                    data.add(new ValueDataEntry(day,gpscount));*/
                   /* int day;
                    float st_red;
                    day = (int) Long.parseLong(snap.getKey());
                    st_red = (float) snap.child("Red").getChildrenCount();


                    Log.e(snap.getKey(),snap.child("Red").getChildrenCount()+"");//get chiled value logic*/
/*

                   dataModels.add(new Showreport(snap.getKey(),snap.child("Red").getChildrenCount(),snap.child("Yellow").getChildrenCount()
                   ,snap.child("Green").getChildrenCount(),snap.child("NON-IVMS Fitted").getChildrenCount()));
*/


                }






                /*CartesianSeriesColumn column = cartesian.column(data);

                *//*column.getTooltip()
                        .setTitleFormat("{X}")
                        .setPosition(Position.CENTER_BOTTOM)
                        .setAnchor(EnumsAnchor.CENTER_BOTTOM)
                        .setOffsetX(0d)
                        .setOffsetY(5d)
                ;*//*

                column.getTooltip().setFormat("Value: {%Value}");

                cartesian.setAnimation(true);
                cartesian.setTitle("Top 10 Cosmetic Products by Revenue");

                cartesian.getYScale().setMinimum(0d);
                cartesian.getYScale().setMaximumGap(0d);
                cartesian.getYScale().getTicks().setInterval(5d);
                cartesian.getYAxis().getLabels().setPadding(5d,5d,5d,5d);

                //cartesian.getYAxis().getLabels().setFormat("{Value}{groupsSeparator: }");

                cartesian.getTooltip().setPositionMode(TooltipPositionMode.POINT);
                cartesian.getInteractivity().setHoverMode(HoverMode.BY_X);

                cartesian.getXAxis().setTitle("Product");
                cartesian.getYAxis().setTitle("Revenue");

                anyChartView.setChart(cartesian);
*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void findViewById() {
        edt_date = (MaterialEditText)findViewById(R.id.edt_date);
        btn_report = (Button)findViewById(R.id.btn_report);
        fab_download = (FloatingActionButton)findViewById(R.id.fab_download);




    }


}

