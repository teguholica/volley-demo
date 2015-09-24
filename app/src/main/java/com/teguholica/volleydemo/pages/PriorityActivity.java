package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

import org.json.JSONObject;

public class PriorityActivity extends Activity {

    private static final String TAG = PriorityActivity.class.getSimpleName();
    private static final String URL = "http://volley.teguholica.com/json_object";
    private static final String tag_json_obj = "json_obj_req";

    private TextView txtNormal;
    private TextView txtLow;
    private TextView txtImmediate;
    private TextView txtHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_priority);

        txtNormal = (TextView) findViewById(R.id.activity_priority_normal);
        txtLow = (TextView) findViewById(R.id.activity_priority_low);
        txtImmediate = (TextView) findViewById(R.id.activity_priority_immediate);
        txtHigh = (TextView) findViewById(R.id.activity_priority_high);
        Button btnStartTest = (Button) findViewById(R.id.activity_priority_button);

        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestNormal();
                requestLow();
                requestImmediate();
                requestHigh();
            }
        });
    }

    private void requestNormal() {
        txtNormal.setText("Loading...");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        txtNormal.setText("Finish");
                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }

                }
        ) {
            @Override
            public Priority getPriority() {
                return Priority.NORMAL;
            }
        };

        jsonObjReq.setShouldCache(false);

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void requestLow() {
        txtLow.setText("Loading...");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        txtLow.setText("Finish");
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        ){
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        };

        jsonObjReq.setShouldCache(false);

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void requestImmediate() {
        txtImmediate.setText("Loading...");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        txtImmediate.setText("Finish");
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        ){
            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };

        jsonObjReq.setShouldCache(false);

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void requestHigh() {
        txtHigh.setText("Loading...");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        txtHigh.setText("Finish");
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        ){
            @Override
            public Priority getPriority() {
                return Priority.HIGH;
            }
        };

        jsonObjReq.setShouldCache(false);

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
