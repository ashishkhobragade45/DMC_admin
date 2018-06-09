package asia.getl.dmc;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import android.widget.ListView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.GraphViewXML;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.rengwuxian.materialedittext.MaterialEditText;


import java.util.ArrayList;
import java.util.List;

import asia.getl.dmc.model.Items;
import asia.getl.dmc.model.MyAdapter;
import asia.getl.dmc.model.MyValueFormatter;
import com.github.mikephil.charting.data.Entry;


public class Report extends AppCompatActivity {

    String company,zone,dmc;
    MaterialEditText edt_date;
    FirebaseDatabase database;
    DatabaseReference report;
    String dateenter;
    Button btn_report;

    int i;
    String day;
    String redcount;
    String yellowcount;
    String greencount;
    String gpscount;

    MyAdapter adapter;
    private RecyclerView recyclerView;

    private List<Items> albumList;


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

        //test
        Items items = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items1 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items2 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items3 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items4 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items5 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items6 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items7 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items8 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items9 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items10 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);
        Items items11 = new Items("31","30000","30000","30000","30000");
        albumList.add(items);

        adapter = new MyAdapter(albumList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        // 3. setListAdapter
        //listView.setAdapter(adapter);//very important


        // reportmethod();

       setData();
        //report = database.getReference(edt_date.getText().toString().trim()+"/"+company+"/"+zone+"/"+dmc+"/");

    /*    btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateenter = edt_date.getText().toString();

                reportmethod();



            }
        });
*/

    }

    private void setData() {
        report = database.getReference("daily_record_db"+"/"+"Jun2018"+"/"+company+"/"+zone+"/"+dmc);

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



    }


}
