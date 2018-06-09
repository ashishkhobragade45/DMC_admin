package asia.getl.dmc.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asia.getl.dmc.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private  Context mContext;
    private  List<Items> itemsArrayList;


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView labelView;
        TextView valueView,valueView1,valueView2,valueView3;

        MyViewHolder(View itemView) {
            super(itemView);
            labelView = (TextView) itemView.findViewById(R.id.label);
            valueView = (TextView) itemView.findViewById(R.id.value);
            valueView1 = (TextView) itemView.findViewById(R.id.value1);
            valueView2 = (TextView) itemView.findViewById(R.id.value2);
            valueView3 = (TextView) itemView.findViewById(R.id.value3);

        }
    }

   public MyAdapter(List<Items> itemsArrayList) {

        //super(context, R.layout.row, itemsArrayList);

       // this.mContext = mContext;
        this.itemsArrayList = itemsArrayList;
    }

  /*  @Override
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
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Items items = itemsArrayList.get(position);
        holder.labelView.setText(items.getTitle());
        holder.valueView.setText(items.getRedvalue());
        holder.valueView1.setText(items.getYvalue());
        holder.valueView2.setText(items.getGvalue());
        holder.valueView3.setText(items.getGpsvalue());

    }




    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }
}
