package com.yasharth.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OtherRegion extends AppCompatActivity {

    String url = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";
    ListView listView;

    List<Details> DetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_region);

        listView = findViewById(R.id.records_view);
        DetailsList = new ArrayList<>();

        loadList();

    }

    private void loadList() {
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);

                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONArray detailsArray = obj.getJSONArray("regionData");

                            for (int i = 0; i< detailsArray.length(); i++)
                            {
                                JSONObject DetailsObject = detailsArray.getJSONObject(i);

                                Details details = new Details(DetailsObject.getString("region"), DetailsObject.getString("totalInfected"),
                                        DetailsObject.getString("recovered"), DetailsObject.getString("deceased"));
                                DetailsList.add(details);
                            }

                            ListViewAdapter adapter = new ListViewAdapter(DetailsList, getApplicationContext());
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OtherRegion.this, "Error" +error, Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }
}
