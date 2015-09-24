package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

public class ImageWithImageViewActivity extends Activity {

    private static final String TAG = ImageWithImageViewActivity.class.getSimpleName();
    private static final String URL = "http://4.bp.blogspot.com/_JSR8IC77Ub4/TATSBShS7AI/AAAAAAAAAhA/22ODme-65XA/s1600/Android_Wallpaper_by_clondike7.png";
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_with_image_view);

        ivImage = (ImageView) findViewById(R.id.activity_image_with_image_view_image);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        // If you are using normal ImageView
        imageLoader.get(URL, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // load image into imageview
                    ivImage.setImageBitmap(response.getBitmap());
                }
            }
        });
    }
}
