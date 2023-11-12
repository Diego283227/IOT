package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserModel> userModelArrayList;

    public CustomAdapter(Context context, ArrayList<UserModel> userModelArrayList) {

        this.context = context;
        this.userModelArrayList = userModelArrayList;
    }


    @Override
    public int getCount() {
        return userModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvrut = (TextView) convertView.findViewById(R.id.rut);
            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvdesc= (TextView) convertView.findViewById(R.id.descripcion);
            holder.tvlab = convertView.findViewById(R.id.laboratorio);



            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        UserModel userModel = userModelArrayList.get(position);
        holder.tvlab.setText("Laboratorio: "+userModel.getLab());
        holder.tvrut.setText("Rut: "+userModel.getRut());
        holder.tvname.setText("Name: "+userModel.getName());
        holder.tvdesc.setText("Descripcion: "+userModel.getDescripcion());


        return convertView;
    }

    private class ViewHolder {

        protected TextView tvlab,tvname, tvdesc, tvrut;
    }

}