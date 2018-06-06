package asia.getl.dmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Report extends AppCompatActivity {

    String company,zone,dmc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //path selected on home
        final Bundle bundle = getIntent().getExtras();
        company=bundle.getString("company");
        zone=bundle.getString("zone");
        dmc=bundle.getString("dmc");

        Toast.makeText(Report.this, ""+company+zone+dmc, Toast.LENGTH_SHORT).show();
    }
}
