package com.fiek.ppmapp.Lista;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import com.fiek.ppmapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentBanesa extends Fragment {

    private final String JSON_URL = "https://gist.githubusercontent.com/FluturaHaxhaj/dab0be91b25b9a5e52dfc49c595c10e5/raw/ff12ac77f7476dbe56f9df8c2bd6d92960b306db/apartament.json";
    private RecyclerView recyclerView;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Banesa> lstBanesa;



    View v;
    public FragmentBanesa(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      v = inflater.inflate(R.layout.banesa_fragment,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerviewid);

    return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstBanesa = new ArrayList<>();
        RequestTask requestTask = new RequestTask();
        requestTask.execute();
    }

    private class RequestTask extends AsyncTask<Void,Void,Void>{

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
                    Banesa banesa = new Banesa();

                    banesa.setBanesa(jsonObject.getString("banesa"));
                    banesa.setPershkrimi(jsonObject.getString("pershkrimi"));
                    banesa.setLokacioni(jsonObject.getString("lokacioni"));
                    banesa.setCmimi(jsonObject.getString("cmimi"));
                    banesa.setSiperfaqja(jsonObject.getString("siperfaqja"));
                    banesa.setDhoma(jsonObject.getString("dhoma"));
//                    banesa.setTel(jsonObject.getString("tel"));
                    banesa.setImage_url(jsonObject.getString("image_url"));
                    lstBanesa.add(banesa);

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            setuprecyclerview(lstBanesa);

        }, error -> {

        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Banesa> lstBanesa) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getContext(),lstBanesa) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
