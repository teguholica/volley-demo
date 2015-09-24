package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

public class ImageWithPlaceHolderActivity extends Activity {

    private static final String TAG = ImageWithPlaceHolderActivity.class.getSimpleName();
    private static final String URL = "http://4.bp.blogspot.com/_JSR8IC77Ub4/TATSBShS7AI/AAAAAAAAAhA/22ODme-65XA/s1600/Android_Wallpaper_by_clondike7.png";
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_with_image_view);

        ivImage = (ImageView) findViewById(R.id.activity_image_with_image_view_image);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        // If you are using normal ImageView
        imageLoader.get(URL, ImageLoader.getImageListener(ivImage, R.mipmap.ic_download, R.mipmap.ic_error));
    }
}
