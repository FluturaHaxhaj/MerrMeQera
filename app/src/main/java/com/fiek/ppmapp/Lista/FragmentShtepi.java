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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fiek.ppmapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentShtepi extends Fragment {


    private final String JSON_URL = "https://gist.githubusercontent.com/FluturaHaxhaj/3c3effa3419fc8b5568085226fb0600a/raw/88d15f0bb84d6cd5a33e7f5027ab309747e76b3e/shtepia.json";
    private RecyclerView recyclerView;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Shtepi> lstShtepi;



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

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, response -> {

            JSONObject jsonObject = null;

            for(int i=0; i < response.length(); i++){

                try{
                    jsonObject = response.getJSONObject(i);
                    Shtepi shtepi = new Shtepi();

                    shtepi.setShtepi(jsonObject.getString("shtepi"));
                    shtepi.setPershkrimi(jsonObject.getString("pershkrimi"));
                    shtepi.setLokacioni(jsonObject.getString("lokacioni"));
                    shtepi.setCmimi(jsonObject.getString("cmimi"));
                    shtepi.setSiperfaqja(jsonObject.getString("siperfaqja"));
                    shtepi.setKate(jsonObject.getString("kate"));
                    shtepi.setTelefoni(jsonObject.getString("telefoni"));
                    shtepi.setImage_url(jsonObject.getString("image_url"));
                    lstShtepi.add(shtepi);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            setuprecyclerview(lstShtepi);

        }, error -> {

        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Shtepi> lstShtepi) {
        RecyclerViewAdapterShtepi myadapter = new RecyclerViewAdapterShtepi(getContext(),lstShtepi) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
