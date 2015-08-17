package com.example.taz.canadafood;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
// From lab 6
public class CAdapter2 extends CursorAdapter {
    public CAdapter2(Context context, Cursor cursor) {
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_names2, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name2 = (TextView) view.findViewById(R.id.textView7);
        TextView value = (TextView) view.findViewById(R.id.textView8);
        TextView food = (TextView) view.findViewById(R.id.textView10);
        String body3 = cursor.getString(cursor.getColumnIndexOrThrow("COMMODITY"));
        String body2 = cursor.getString(cursor.getColumnIndexOrThrow("Valuess"));
        String body = cursor.getString(cursor.getColumnIndexOrThrow("FOOD"));
        name2.setText("Nom: "+body3);
        value.setText("Valeur: "+body2);
        food.setText("Disponibilité: "+body);
    }


}
