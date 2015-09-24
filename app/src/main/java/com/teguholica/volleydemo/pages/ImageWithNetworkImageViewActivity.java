package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

import java.util.ArrayList;

public class ImageWithNetworkImageViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_with_network_image_view);

        GridView gvList = (GridView) findViewById(R.id.activity_image_with_network_image_view_list);

        ListAdapter listAdapter = new ListAdapter();
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp1.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp2.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp3.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp4.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp5.png");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp6.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp7.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp8.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp9.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp10.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp11.jpg");
        listAdapter.add("http://volley.teguholica.com/wallpaper/wp12.jpg");

        gvList.setAdapter(listAdapter);
    }

    private class ListAdapter extends BaseAdapter {

        private ImageLoader imageLoader;
        private ArrayList<String> rowData;

        public ListAdapter() {
            imageLoader = AppController.getInstance().getImageLoader();
            rowData = new ArrayList<>();
        }

        public void add(String src){
            rowData.add(src);
        }

        @Override
        public int getCount() {
            return rowData.size();
        }

        @Override
        public String getItem(int i) {
            return rowData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.activity_image_with_network_image_view_list_item, viewGroup, false);
            }

            String src = rowData.get(i);

            NetworkImageView nivImage = (NetworkImageView) view.findViewById(R.id.activity_image_with_network_image_view_list_item_image);

            nivImage.setImageUrl(src, imageLoader);

            return view;
        }
    }
}
