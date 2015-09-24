package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoadFromCacheActivity extends Activity {

    private static String TAG = LoadFromCacheActivity.class.getSimpleName();
    private static final String tag_json_obj = "json_obj_req";
    private static final String URL = "http://volley.teguholica.com/json_object";

    private ProgressDialog pDialog;

    private TextView txtName;
    private TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_json_object_request);

        txtName = (TextView) findViewById(R.id.activity_json_object_request_name);
        txtEmail = (TextView) findViewById(R.id.activity_json_object_request_email);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL);
        if (entry != null) {
            Toast.makeText(this, "Load From Cache", Toast.LENGTH_SHORT).show();
            try {
                // handle data, like converting it to xml, json, bitmap etc.,
                pDialog.hide();
                String data = new String(entry.data, "UTF-8");
                JSONObject response = new JSONObject(data);
                txtName.setText(response.getString("name"));
                txtEmail.setText(response.getString("email"));
            } catch (UnsupportedEncodingException | JSONException e) {
                e.printStackTrace();
            }
        } else {
            // Cached response doesn't exists. Make network call here
            Toast.makeText(this, "Load From Server", Toast.LENGTH_SHORT).show();
            serverRequest();
        }
    }

    private void serverRequest(){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        try {
                            txtName.setText(response.getString("name"));
                            txtEmail.setText(response.getString("email"));
                        } catch (JSONException e) {
                            Toast.makeText(LoadFromCacheActivity.this, "Error JSON parsing", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        // hide the progress dialog
                        pDialog.hide();
                        // show toast
                        Toast.makeText(LoadFromCacheActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
