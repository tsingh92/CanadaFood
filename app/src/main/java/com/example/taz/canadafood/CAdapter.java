package com.example.taz.canadafood;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

// From Lab6
public class CAdapter extends CursorAdapter {
    public CAdapter(Context context, Cursor cursor) {
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_names, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.tvname);
        TextView value = (TextView) view.findViewById(R.id.textView3);
        TextView food = (TextView) view.findViewById(R.id.food);
        String body = cursor.getString(cursor.getColumnIndexOrThrow("COMMODITY"));
        String body2 = cursor.getString(cursor.getColumnIndexOrThrow("Valuess"));
        String body3 = cursor.getString(cursor.getColumnIndexOrThrow("FOOD"));
        name.setText("NAME: "+body);
        value.setText("VALUE: "+body2);
        food.setText("AVAILABILITY: "+body3);
    }


}
