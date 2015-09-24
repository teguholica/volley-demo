package com.teguholica.volleydemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.teguholica.volleydemo.pages.CancelAllRequestActivity;
import com.teguholica.volleydemo.pages.CancelSingleRequestActivity;
import com.teguholica.volleydemo.pages.HeaderRequestActivity;
import com.teguholica.volleydemo.pages.ImageWithImageViewActivity;
import com.teguholica.volleydemo.pages.ImageWithNetworkImageViewActivity;
import com.teguholica.volleydemo.pages.ImageWithPlaceHolderActivity;
import com.teguholica.volleydemo.pages.InvalidateCacheActivity;
import com.teguholica.volleydemo.pages.JSONArrayRequestActivity;
import com.teguholica.volleydemo.pages.JSONObjectRequestActivity;
import com.teguholica.volleydemo.pages.LoadFromCacheActivity;
import com.teguholica.volleydemo.pages.POSTRequestActivity;
import com.teguholica.volleydemo.pages.POSTURLEncodeRequestActivity;
import com.teguholica.volleydemo.pages.PriorityActivity;
import com.teguholica.volleydemo.pages.StringRequestActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView lvList = (ListView) findViewById(R.id.activity_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
        }

        String[] listMenu = new String[]{
                "JSON Object Request",
                "JSON Array Request",
                "String Request",
                "POST with JSON Request",
                "POST with URLEncode Request",
                "Header Request",
                "Image with NetworkImageView",
                "Image with ImageView",
                "Image with PlaceHolder",
                "Load From Cache",
                "Invalidate Cache",
                "Turning Off Cache",
                "Turning On Cache",
                "Deleting Particular Cache",
                "Deleting all Cache",
                "Cancel Single Request",
                "Cancel All Request",
                "Priority"

        };

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listMenu);
        lvList.setAdapter(listAdapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nextPage(i);
            }
        });
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void nextPage(int position){
        switch (position){
            case 0:
                Intent jsonObjectRequestActivity = new Intent(this, JSONObjectRequestActivity.class);
                startActivity(jsonObjectRequestActivity);
                break;
            case 1:
                Intent jsonArrayRequestActivity = new Intent(this, JSONArrayRequestActivity.class);
                startActivity(jsonArrayRequestActivity);
                break;
            case 2:
                Intent stringRequestActivity = new Intent(this, StringRequestActivity.class);
                startActivity(stringRequestActivity);
                break;
            case 3:
                Intent postRequestActivity = new Intent(this, POSTRequestActivity.class);
                startActivity(postRequestActivity);
                break;
            case 4:
                Intent postURLEncodeRequestActivity = new Intent(this, POSTURLEncodeRequestActivity.class);
                startActivity(postURLEncodeRequestActivity);
                break;
            case 5:
                Intent headerRequestActivity = new Intent(this, HeaderRequestActivity.class);
                startActivity(headerRequestActivity);
                break;
            case 6:
                Intent imageWithNetworkImageViewActivity = new Intent(this, ImageWithNetworkImageViewActivity.class);
                startActivity(imageWithNetworkImageViewActivity);
                break;
            case 7:
                Intent imageWithImageViewActivity = new Intent(this, ImageWithImageViewActivity.class);
                startActivity(imageWithImageViewActivity);
                break;
            case 8:
                Intent imageWithPlaceHolderActivity = new Intent(this, ImageWithPlaceHolderActivity.class);
                startActivity(imageWithPlaceHolderActivity);
                break;
            case 9:
                Intent loadFromCacheActivity = new Intent(this, LoadFromCacheActivity.class);
                startActivity(loadFromCacheActivity);
                break;
            case 10:
                Intent invalidateCacheActivity = new Intent(this, InvalidateCacheActivity.class);
                startActivity(invalidateCacheActivity);
                break;
            case 11:
                AppController.getInstance().enableCache(false);
                Toast.makeText(this, "Disable Cache", Toast.LENGTH_SHORT).show();
                break;
            case 12:
                AppController.getInstance().enableCache(true);
                Toast.makeText(this, "Enable Cache", Toast.LENGTH_SHORT).show();
                break;
            case 13:
                AppController.getInstance().getRequestQueue().getCache().remove("http://4.bp.blogspot.com/_JSR8IC77Ub4/TATSBShS7AI/AAAAAAAAAhA/22ODme-65XA/s1600/Android_Wallpaper_by_clondike7.png");
                Toast.makeText(this, "Remove Some Image Cache", Toast.LENGTH_SHORT).show();
                break;
            case 14:
                AppController.getInstance().getRequestQueue().getCache().clear();
                Toast.makeText(this, "Remove All Cache", Toast.LENGTH_SHORT).show();
                break;
            case 15:
                Intent cancelSingleRequestActivity = new Intent(this, CancelSingleRequestActivity.class);
                startActivity(cancelSingleRequestActivity);
                break;
            case 16:
                Intent cancelAllRequestActivity = new Intent(this, CancelAllRequestActivity.class);
                startActivity(cancelAllRequestActivity);
                break;
            case 17:
                Intent priorityActivity = new Intent(this, PriorityActivity.class);
                startActivity(priorityActivity);
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent aboutActivity = new Intent(this, AboutActivity.class);
            startActivity(aboutActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
