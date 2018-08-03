package com.kun3awnan.app.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kun3awnan.app.Models.food;
import com.kun3awnan.app.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater inflater;
    List<food> itemModelsList;
    ArrayList<food> itemModelArrayList;


    // constructor
    public ListViewAdapter (Context context, List<food> itemModelList) {
        this.mContext = context;
        this.itemModelsList = itemModelList;
        //inflater = LayoutInflater.from(mContext);
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemModelArrayList = new ArrayList<food>();
        this.itemModelArrayList.addAll(itemModelList);
    }


    //
    public class ViewHolder{
        TextView foodHeadingTV, foodDescriptionTV;
    }

    @Override
    public int getCount() {
        return itemModelsList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemModelsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewholder;
        if(view == null)
        {
            viewholder = new ViewHolder();
            view = inflater.inflate(R.layout.food_item, null);

            // locate the views in list_view_row.xml
            viewholder.foodHeadingTV = (TextView) view.findViewById(R.id.food_item_heading);
            viewholder.foodDescriptionTV = (TextView) view.findViewById(R.id.food_item_description);

            view.setTag(viewholder);
        }   else {
            viewholder = (ViewHolder) view.getTag();
        }

        //set the results into text views
        viewholder.foodHeadingTV.setText(itemModelsList.get(position).getFood_head());
        viewholder.foodDescriptionTV.setText(itemModelsList.get(position).getFood_decription());

        // listView item Click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = itemModelsList.get(position).getFood_head();
                Toast.makeText(mContext, selected + " has been selected", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}

