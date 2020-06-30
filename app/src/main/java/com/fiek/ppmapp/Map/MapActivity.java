package com.fiek.ppmapp.Map;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fiek.ppmapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private JSONArray jsonArray;
    private RequestQueue requestQueue ;
    private List<MarkerOptions> listMarkers = new ArrayList<>();
    private ClusterManager<MarkerClusterItem> clusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map);

        SupportMapFragment supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(this);
    }

    private class MapTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String url = "https://b002c54b86d0.ngrok.io";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("Banesat");
                                for (int i = 0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    double lat = jsonObject.getDouble("lat");
                                    double lng = jsonObject.getDouble("lng");
                                    String name = jsonObject.getString("lokacioni");
                                    LatLng latLng = new LatLng(lat, lng);
                                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(name);
                                    listMarkers.add(markerOptions);
                                    googleMap.addMarker(markerOptions);
                                }
                                JSONArray jsonArray2 = response.getJSONArray("Shtepite");
                                for (int i = 0;i<jsonArray2.length();i++){
                                    JSONObject jsonObject = jsonArray2.getJSONObject(i);

                                    double lat = jsonObject.getDouble("lat");
                                    double lng = jsonObject.getDouble("lng");
                                    String name = jsonObject.getString("lokacioni");
                                    LatLng latLng = new LatLng(lat, lng);
                                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(name);
                                    listMarkers.add(markerOptions);
                                    googleMap.addMarker(markerOptions);
                                }

                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(listMarkers.get(0).getPosition(), 13.0f));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request) ;

            return null;
        }
    }

    private void setupClusterManager() {
        setRenderer();
        addClusterItems();
        setClusterManagerClickListener();
        googleMap.setOnCameraIdleListener(clusterManager);
        googleMap.setOnMarkerClickListener(clusterManager);
        clusterManager.cluster();
    }

    private void setRenderer() {
        MarkerClusterRenderer<MarkerClusterItem> clusterRenderer = new MarkerClusterRenderer<>(this, googleMap, clusterManager);
        clusterManager.setRenderer(clusterRenderer);
    }

    private void setClusterManagerClickListener() {
        clusterManager.setOnClusterClickListener(cluster -> {
            Collection<MarkerClusterItem> listItems = cluster.getItems();
            List<String> listNames = new ArrayList<>();
            for (MarkerClusterItem item : listItems){
                listNames.add(item.getTitle());
            }
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(cluster.getPosition()), new GoogleMap.CancelableCallback() {
                @Override
                public void onFinish() {
                    ListViewDialog listViewDialog = new ListViewDialog(MapActivity.this, listNames);
                    listViewDialog.showDialog();
                }
                @Override
                public void onCancel() { }
            });
            return true;
        });
    }

    private void addClusterItems() {
        for(MarkerOptions markerOptions : listMarkers){
            MarkerClusterItem clusterItem = new MarkerClusterItem(markerOptions.getPosition(), markerOptions.getTitle());
            clusterManager.addItem(clusterItem);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        clusterManager = new ClusterManager<>(this, googleMap);
        MapTask mapTask = new MapTask();
        mapTask.execute();
        setupClusterManager();
    }
}