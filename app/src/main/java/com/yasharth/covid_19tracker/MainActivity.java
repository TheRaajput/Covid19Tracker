package com.yasharth.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    TextView tvCases, tvRecovered, tvActive,tvTotalDeaths, updateTime;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvActive = findViewById(R.id.tvActive);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        updateTime = findViewById(R.id.tv_time);
        btn = findViewById(R.id.otherRegionBtn);

        getData();


        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),
                R.anim.button
        );
    }

    private void getData()
    {

        String url = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";

        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        try {

                            JSONObject jsonObject
                                    = new JSONObject(
                                    response);

                            tvCases.setText(
                                    jsonObject.getString(
                                            "totalCases"));
                            tvRecovered.setText(
                                    jsonObject.getString(
                                            "recovered"));
                            tvActive.setText(
                                    jsonObject.getString(
                                            "activeCases"));
                            tvTotalDeaths.setText(
                                    jsonObject.getString(
                                            "deaths"));

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss aa");
                            Date d = sdf.parse(jsonObject.getString("lastUpdatedAtApify"));
                            assert d != null;
                            String formattedTime = output.format(d);

                            updateTime.setText(formattedTime);
                        }
                        catch (JSONException | ParseException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(
                                MainActivity.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

}
