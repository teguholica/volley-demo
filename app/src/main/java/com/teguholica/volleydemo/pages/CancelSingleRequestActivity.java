package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

public class CancelSingleRequestActivity extends Activity {

    private static String TAG = CancelSingleRequestActivity.class.getSimpleName();
    private static final String URL = "http://volley.teguholica.com/cancel_single_request";
    private static final String URL_TAG = "forCancelRequest";
    private static final int TIMEOUT = 20000;

    private TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cancel_single_request);

        txtStatus = (TextView) findViewById(R.id.activity_cancel_single_request_status);
        Button btnStart = (Button) findViewById(R.id.activity_cancel_single_request_start);
        Button btnCancel = (Button) findViewById(R.id.activity_cancel_single_request_cancel);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRequest();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppController.getInstance().getRequestQueue().cancelAll(URL_TAG);
            }
        });
    }

    private void startRequest(){
        txtStatus.setText("Start Request");

        StringRequest strReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        txtStatus.setText(response);

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CancelSingleRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        ){
            @Override
            public void cancel() {
                txtStatus.setText("Request Canceled");
                super.cancel();
            }
        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, URL_TAG);
    }
}
