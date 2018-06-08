package asia.getl.dmc.model;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import asia.getl.dmc.R;

public class MyAdapter extends ArrayAdapter<Items> {


    private final Context context;
    private final ArrayList<Items> itemsArrayList;

    public MyAdapter(Context context, ArrayList<Items> itemsArrayList) {

        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.label);
        TextView valueView = (TextView) rowView.findViewById(R.id.value);
        TextView valueView1 = (TextView) rowView.findViewById(R.id.value1);
        TextView valueView2 = (TextView) rowView.findViewById(R.id.value2);
        TextView valueView3 = (TextView) rowView.findViewById(R.id.value3);

        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getTitle());
        valueView.setText(itemsArrayList.get(position).getRedvalue());
        valueView1.setText(itemsArrayList.get(position).getYvalue());
        valueView2.setText(itemsArrayList.get(position).getGvalue());
        valueView3.setText(itemsArrayList.get(position).getGpsvalue());

        // 5. retrn rowView
        return rowView;
    }
}
