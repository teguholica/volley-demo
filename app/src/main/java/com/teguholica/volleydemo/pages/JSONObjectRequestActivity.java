package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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

public class JSONObjectRequestActivity extends Activity {

    private static String TAG = JSONObjectRequestActivity.class.getSimpleName();
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
                            Toast.makeText(JSONObjectRequestActivity.this, "Error JSON parsing", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(JSONObjectRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
