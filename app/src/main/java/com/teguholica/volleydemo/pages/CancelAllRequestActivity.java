package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

public class CancelAllRequestActivity extends Activity {

    private static String TAG = CancelAllRequestActivity.class.getSimpleName();
    private static final String URL = "http://volley.teguholica.com/cancel_single_request";
    private static final String URL_TAG_1 = "forCancelRequest1";
    private static final String URL_TAG_2 = "forCancelRequest2";
    private static final String URL_TAG_3 = "forCancelRequest3";
    private static final int TIMEOUT = 20000;

    private TextView txtStatus1;
    private TextView txtStatus2;
    private TextView txtStatus3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cancel_all_request);

        txtStatus1 = (TextView) findViewById(R.id.activity_cancel_all_request_status1);
        txtStatus2 = (TextView) findViewById(R.id.activity_cancel_all_request_status2);
        txtStatus3 = (TextView) findViewById(R.id.activity_cancel_all_request_status3);
        Button btnStart = (Button) findViewById(R.id.activity_cancel_all_request_start);
        Button btnCancel = (Button) findViewById(R.id.activity_cancel_all_request_cancel);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRequest1();
                startRequest2();
                startRequest3();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppController.getInstance().getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
                    @Override
                    public boolean apply(Request<?> request) {
                        return true;
                    }
                });
            }
        });
    }

    private void startRequest1(){
        txtStatus1.setText("Start Request");

        StringRequest strReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        txtStatus1.setText(response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CancelAllRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        ){
            @Override
            public void cancel() {
                txtStatus1.setText("Request Canceled");
                super.cancel();
            }
        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, URL_TAG_1);
    }

    private void startRequest2(){
        txtStatus2.setText("Start Request");

        StringRequest strReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        txtStatus2.setText(response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CancelAllRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        ){
            @Override
            public void cancel() {
                txtStatus2.setText("Request Canceled");
                super.cancel();
            }
        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, URL_TAG_2);
    }

    private void startRequest3(){
        txtStatus3.setText("Start Request");

        StringRequest strReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        txtStatus3.setText(response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CancelAllRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        ){
            @Override
            public void cancel() {
                txtStatus3.setText("Request Canceled");
                super.cancel();
            }
        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, URL_TAG_3);
    }
}
