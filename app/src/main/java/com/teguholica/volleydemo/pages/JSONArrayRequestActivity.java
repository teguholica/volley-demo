package com.teguholica.volleydemo.pages;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.teguholica.volleydemo.AppController;
import com.teguholica.volleydemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONArrayRequestActivity extends Activity {

    private static String TAG = JSONArrayRequestActivity.class.getSimpleName();
    private static final String tag_json_obj = "json_array_req";
    private static final String URL = "http://volley.teguholica.com/json_array";

    private ProgressDialog pDialog;

    private ArrayList<String> listData;
    private ArrayAdapter<String> listAdapter;

    private ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_json_array_request);

        lvList = (ListView) findViewById(R.id.activity_json_array_request_list);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listData = new ArrayList<>();
                        for(int i = 0;i < response.length();i++) {
                            try {
                                JSONObject element = response.getJSONObject(i);
                                listData.add(element.getString("name"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        listAdapter = new ArrayAdapter<>(JSONArrayRequestActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, listData.toArray(new String[listData.size()]));
                        lvList.setAdapter(listAdapter);
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(JSONArrayRequestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        pDialog.hide();
                    }
                }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_obj);
    }
}
