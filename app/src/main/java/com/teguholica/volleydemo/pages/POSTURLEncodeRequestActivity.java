package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class POSTURLEncodeRequestActivity extends Activity {

    private static String TAG = POSTURLEncodeRequestActivity.class.getSimpleName();
    private static final String tag_json_obj = "json_obj_req";
    private static final String URL = "http://volley.teguholica.com/post_urlencode";

    private ProgressDialog pDialog;

    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPassword;
    private TextView txtResultName;
    private TextView txtResultEmail;
    private TextView txtResultPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_request);

        txtName = (EditText) findViewById(R.id.activity_post_request_name);
        txtEmail = (EditText) findViewById(R.id.activity_post_request_email);
        txtPassword = (EditText) findViewById(R.id.activity_post_request_password);
        txtResultName = (TextView) findViewById(R.id.activity_post_request_resultName);
        txtResultEmail = (TextView) findViewById(R.id.activity_post_request_resultEmail);
        txtResultPassword = (TextView) findViewById(R.id.activity_post_request_resultPassword);
        Button btnSend = (Button) findViewById(R.id.activity_post_request_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverRequest();
            }
        });
    }

    private void serverRequest(){
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, URL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            txtResultName.setText(response.getString("name"));
                            txtResultEmail.setText(response.getString("email"));
                            txtResultPassword.setText(response.getString("password"));
                        } catch (JSONException e) {
                            Toast.makeText(POSTURLEncodeRequestActivity.this, "Error JSON parsing", Toast.LENGTH_SHORT).show();
                        }
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(POSTURLEncodeRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }
        ) {

            @Override
            public byte[] getBody() {
                Map<String, String> params = new HashMap<>();
                params.put("name", txtName.getText().toString());
                params.put("email", txtEmail.getText().toString());
                params.put("password", txtPassword.getText().toString());

                ArrayList<String> result = new ArrayList<>();
                for(Map.Entry<String, String> entry : params.entrySet()){
                    result.add(entry.getKey()+"="+entry.getValue());
                }

                return TextUtils.join("&", result).getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            protected String getParamsEncoding() {
                return "utf-8";
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
