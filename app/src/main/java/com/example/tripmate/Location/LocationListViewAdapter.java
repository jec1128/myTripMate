package com.example.tripmate.Location;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripmate.R;
import com.skt.Tmap.TMapTapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class LocationListViewAdapter extends BaseAdapter  {
    private ArrayList<LocationListViewItem> items = new ArrayList<>();
    Bitmap bitmap;

    public LocationListViewAdapter(){

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context= parent.getContext();

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.location_listview, parent, false);
        }

        TextView title = (TextView)convertView.findViewById(R.id.list_title);
        TextView addr = (TextView)convertView.findViewById(R.id.list_addr);
        TextView tel = (TextView)convertView.findViewById(R.id.list_tel);
        TextView dist = (TextView)convertView.findViewById(R.id.list_dist);

        ImageView firstimage = (ImageView)convertView.findViewById(R.id.list_firstimage);

        final LocationListViewItem item = items.get(position);
        title.setText(item.getTitle());
        addr.setText(item.getAddress1());
        tel.setText(item.getTel());
        dist.setText(Double.toString(item.getDist()/1000)+"km");


        Thread mThread = new Thread(){
            @Override
            public void run(){
                try{
                    URL url = new URL(item.getFirstimage());

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                }catch (IOException ex){

                }
            }
        };
        mThread.start();

        try{
            mThread.join();
            firstimage.setColorFilter(Color.parseColor("#c4b9b9"), PorterDuff.Mode.MULTIPLY);

            firstimage.setImageBitmap(bitmap);



        }catch (InterruptedException e){
        }

        //dist.setText(((int)item.getDist()*1000<1000) ? (String.valueOf((int)(item.getDist()*1000))+"m"):(String.valueOf(item.getDist())+"km"));
        return convertView;
    }

    public void addItem(String title, String address1, String firstimage, String tel,int dist, double mapx ,double mapy)
    {
        LocationListViewItem item = new LocationListViewItem();
        item.setTitle(title);
        item.setAddress1(address1);
        item.setFirstimage(firstimage);
        item.setTel(tel);
        item.setDist(dist);
        item.setMapx(mapx);
        item.setMapy(mapy);
        items.add(item);
    }

    public void sort()
    {
        Collections.sort(items);
    }

/*
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);

        TMapTapi tMapTapi = new TMapTapi();
        for(int i =0; i<items.size(); i++) {
            tMapTapi.invokeNavigate("목적지", (float) items.get(i).getItem_mapx(),(float) items.get(i).getItem_mapy(),0,true);
        }
    }
*/
}
