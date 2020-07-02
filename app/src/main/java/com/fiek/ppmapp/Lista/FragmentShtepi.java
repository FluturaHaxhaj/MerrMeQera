package com.fiek.ppmapp.Lista;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fiek.ppmapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentShtepi extends Fragment {


    private RecyclerView recyclerView;
    private RequestQueue requestQueue ;
    private List<BanesaShtepi> lstShtepi;



    View v;
    public FragmentShtepi(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.shtepi_fragment,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerviewshtepiid);

        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstShtepi = new ArrayList<>();
        RequestTask requestTask = new RequestTask();
        requestTask.execute();
    }

    private class RequestTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            jsonrequest();
            return null;
        }
    }

    private void jsonrequest(){
        String url = "https://gist.githubusercontent.com/FluturaHaxhaj/dab0be91b25b9a5e52dfc49c595c10e5/raw/badeeadde9d443bd6bbf7ae5a13f413d3a649abc/merrmeqira.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Shtepite");
                            for (int i = 0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                BanesaShtepi shtepi = new BanesaShtepi();

                                shtepi.setKey_id(jsonObject.getString("key_id"));
                                shtepi.setBanesaShtepi(jsonObject.getString("shtepi"));
                                shtepi.setPershkrimi(jsonObject.getString("pershkrimi"));
                                shtepi.setLokacioni(jsonObject.getString("lokacioni"));
                                shtepi.setCmimi(jsonObject.getString("cmimi"));
                                shtepi.setSiperfaqja(jsonObject.getString("siperfaqja"));
                                shtepi.setKateDhoma(jsonObject.getString("kate"));
                                shtepi.setTelefoni(jsonObject.getString("telefoni"));
                                shtepi.setImage_url(jsonObject.getString("image_url"));
                                shtepi.setFavStatus(jsonObject.getString("favStatus"));
                                shtepi.setLat(jsonObject.getString("lat"));
                                shtepi.setLng(jsonObject.getString("lng"));
                                shtepi.setImg2(jsonObject.getString("image_url2"));
                                shtepi.setImg3(jsonObject.getString("image_url3"));
                                shtepi.setImg4(jsonObject.getString("image_url4"));
                                lstShtepi.add(shtepi);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setuprecyclerview(lstShtepi);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request) ;
    }

    private void setuprecyclerview(List<BanesaShtepi> lstShtepi) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getContext(),lstShtepi) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
