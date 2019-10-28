package com.example.jobv_2_1.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jobv_2_1.JobAdapter;
import com.example.jobv_2_1.JobItem;
import com.example.jobv_2_1.R;
import com.example.jobv_2_1.utils.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

//    private HomeViewModel homeViewModel;
private final String JSON_URL = "https://www.wikway.de/companies/offers-json?password=ain1018";


    private static final String TAG = "Home Fragment";
    private List<JobItem> mJobList;
    private RequestQueue mRequestQueue;
    private InitImageBitmaps initialization;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mJobList = new ArrayList<>();

        Log.d("Testing", "OncreateView");

        if (initialization == null){
            initialization = new InitImageBitmaps();
        }
        initialization.execute();
//        RecyclerView mRecyclerView = (RecyclerView) root.findViewById(R.id.recycle_view);
//        mRecyclerView.setAdapter(mJobAdapter);
//        mRecyclerView.setHasFixedSize(true);
//        mJobAdapter = new JobAdapter(getActivity(),mJobList);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    private class InitImageBitmaps extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response

            String jsonStr = sh.makeServiceCall(JSON_URL);
            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String lbewerber = hit.getString("Bewerberkontakt Firma");
                        String lbundesland = hit.getString("Bundesland");
                        String leinsatzort = hit.getString("Einsatzort");
                        String laktivbis = hit.getString("Stelle aktiv bis (Publikationsende)");
                        String lanschreiben = hit.getString("Anschreiben zur Stelle");
                        String lbezeichnung = hit.getString("Bezeichnung der Stelle");
                        String llogo = hit.getString("Logo");
//                                String lemail = hit.getString("E-Mail");
//                                String lstrasse = hit.getString("Straße");
//                                String lansprech = hit.getString("Ansprechpartner");
//                                String lumfang = hit.getString("Umfang");
//                                String labteilung = hit.getString("Abteilung");

                        mJobList.add(new JobItem(lbewerber, lbundesland, leinsatzort,
                                laktivbis, lanschreiben, lbezeichnung,
                                llogo));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            initRecyclerView();
        }
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = getView().findViewById(R.id.recycle_view);
        JobAdapter mJobAdapter = new JobAdapter(getContext(),mJobList);
        recyclerView.setAdapter(mJobAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }



//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        mRequestQueue = Volley.newRequestQueue(getActivity());
//        Log.d("Testing", "OnCreate");
//        parseJSON();
//
//
//    }

    private void parseJSON(){

        Log.d("Testing", "VonParse");
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("Testing", "VorStart");
                        try {
                            JSONArray jsonArray = response;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String lbewerber = hit.getString("Bewerberkontakt Firma");
                                String lbundesland = hit.getString("Bundesland");
                                String leinsatzort = hit.getString("Einsatzort");
                                String laktivbis = hit.getString("Stelle aktiv bis (Publikationsende)");
                                String lanschreiben = hit.getString("Anschreiben zur Stelle");
                                String lbezeichnung = hit.getString("Bezeichnung der Stelle");
                                String llogo = hit.getString("Logo");
//                                String lemail = hit.getString("E-Mail");
//                                String lstrasse = hit.getString("Straße");
//                                String lansprech = hit.getString("Ansprechpartner");
//                                String lumfang = hit.getString("Umfang");
//                                String labteilung = hit.getString("Abteilung");

                                mJobList.add(new JobItem(lbewerber, lbundesland, leinsatzort,
                                        laktivbis, lanschreiben, lbezeichnung,
                                        llogo));
//                                        , lemail, lstrasse, lansprech, lumfang,
//                                        labteilung

                            }


                            Log.d("Testing", jsonArray.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Testing", error.toString());
                Log.d("Testing", error.getMessage());
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }
}