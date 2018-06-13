package asia.getl.dmc;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import asia.getl.dmc.Json.LazyAdapter;
import asia.getl.dmc.Json.MyRecyclerItemClickListener;
import asia.getl.dmc.Json.RecyclerViewClickListener;
import asia.getl.dmc.model.Items;
import asia.getl.dmc.model.MyAdapter;

public class Reportcompany extends AppCompatActivity  {

    private List<Items> albumList;
    MyAdapter adapter;
    LazyAdapter lazyAdapter;
    private ListView recyclerView;
    FirebaseDatabase database;
    DatabaseReference report,report1,ref_red,ref_yellow,ref_green,ref_gps;
    int i;
    String day,day1;
    String redcount;
    String yellowcount;
    String greencount;
    String gpscount;
    long rcount,gcount,ycount,gpcount;
    ArrayList<Items> animalList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportcompany);

        //firebase init
        database = FirebaseDatabase.getInstance();
        recyclerView = (ListView) findViewById(R.id.recycler_view);

        firebasemethod();

        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = animalList.get(position).getTitle();
                Log.d("Test1234",""+text);
            }
        });

    }

    private void firebasemethod() {
        albumList = new ArrayList<>();


       // adapter = new MyAdapter(albumList);
       // RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 4);
      // recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setAdapter(adapter);
       // albumList.clear();
        report = database.getReference("daily_record_db");
        report.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                day1 = dataSnapshot.getKey();
               // Log.d("Demo",day);

                for (DataSnapshot snap : dataSnapshot.getChildren())
                {
                    day = snap.getKey();
                    Log.d("Demo1",day);
                    for (DataSnapshot snap1 : snap.getChildren())
                    {
                        day = snap1.getKey();
                        Log.d("Demo2",day);
                        for (DataSnapshot snap2 : snap1.getChildren())
                        {
                            day = snap2.getKey();
                            Log.d("Demo3",day);

                            for (DataSnapshot snap3 : snap2.getChildren())
                            {
                                day = snap3.getKey();
                                Log.d("Demo2",day);
                                rcount = rcount + snap3.child("Red").getChildrenCount();
                                ycount = ycount + snap3.child("Yellow").getChildrenCount();
                                gcount = gcount + snap3.child("Green").getChildrenCount();
                                gpcount = gpcount + snap3.child("NON-IVMS Fitted").getChildrenCount();

                            }

                        }

                    }

                }


               /* redcount = String.valueOf(dataSnapshot.child("Red").getChildrenCount());
                yellowcount = String.valueOf(dataSnapshot.child("Yellow").getChildrenCount());
                greencount = String.valueOf(dataSnapshot.child("Green").getChildrenCount());
                gpscount = String.valueOf(dataSnapshot.child("NON-IVMS Fitted").getChildrenCount());*/

               redcount = String.valueOf(rcount);
                yellowcount = String.valueOf(ycount);
                greencount = String.valueOf(gcount);
                gpscount = String.valueOf(gpcount);

                animalList.add(new Items(day1,redcount,yellowcount,greencount,gpscount));

              /*  Items items = new Items(day1,redcount,yellowcount,greencount,gpscount);
                albumList.add(items);
*/
                // items.add(new Items(day,redcount,yellowcount,greencount,gpscount));


                lazyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                lazyAdapter.notifyDataSetChanged();
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

        lazyAdapter = new LazyAdapter(this,R.layout.list_row, animalList);
        recyclerView.setAdapter(lazyAdapter);

    }


}
