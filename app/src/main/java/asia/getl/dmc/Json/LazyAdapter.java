package asia.getl.dmc.Json;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asia.getl.dmc.R;
import asia.getl.dmc.model.Items;

public class LazyAdapter extends ArrayAdapter<Items>{


    private Context mContext;
    private List<Items> itemsArrayList;

    private Activity context;
    private ArrayList<Items> animalList = new ArrayList<>();

    public LazyAdapter(Context context, int textViewResourceId, ArrayList<Items> objects) {
        super(context, textViewResourceId, objects);
        animalList = objects;

    }


    @Override
    public int getCount() {
        return super.getCount();
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_row, null);
        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) v.findViewById(R.id.labelr);
        TextView valueView = (TextView) v.findViewById(R.id.valuer);
        TextView valueView1 = (TextView) v.findViewById(R.id.value1r);
        TextView valueView2 = (TextView) v.findViewById(R.id.value2r);
        TextView valueView3 = (TextView) v.findViewById(R.id.value3r);

        // 4. Set the text for textView
        labelView.setText(animalList.get(position).getTitle());
        valueView.setText(animalList.get(position).getRedvalue());
        valueView1.setText(animalList.get(position).getYvalue());
        valueView2.setText(animalList.get(position).getGvalue());
        valueView3.setText(animalList.get(position).getGpsvalue());

        // 5. retrn rowView
        return v;
    }
}
