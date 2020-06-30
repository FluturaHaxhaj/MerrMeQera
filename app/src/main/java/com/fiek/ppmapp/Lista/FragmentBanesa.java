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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fiek.ppmapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentBanesa extends Fragment {

    private final String JSON_URL = "https://gist.githubusercontent.com/FluturaHaxhaj/dab0be91b25b9a5e52dfc49c595c10e5/raw/bfd674542afe8beadaa7db1ecae8f10148e94561/apartament.json";
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


    private void jsonrequest(){
        String url = "https://20af3aa26edf.ngrok.io";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Banesat");
                            for (int i = 0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Banesa banesa = new Banesa();

                                banesa.setKey_id(jsonObject.getString("key_id"));
                                banesa.setBanesa(jsonObject.getString("banesa"));
                                banesa.setPershkrimi(jsonObject.getString("pershkrimi"));
                                banesa.setLokacioni(jsonObject.getString("lokacioni"));
                                banesa.setCmimi(jsonObject.getString("cmimi"));
                                banesa.setSiperfaqja(jsonObject.getString("siperfaqja"));
                                banesa.setDhoma(jsonObject.getString("dhoma"));
                                banesa.setTelefoni(jsonObject.getString("telefoni"));
                                banesa.setImage_url(jsonObject.getString("image_url"));
                                banesa.setFavStatus(jsonObject.getString("favStatus"));
                                lstBanesa.add(banesa);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setuprecyclerview(lstBanesa);
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

    private void setuprecyclerview(List<Banesa> lstBanesa) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getContext(),lstBanesa) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myadapter);

    }
}
