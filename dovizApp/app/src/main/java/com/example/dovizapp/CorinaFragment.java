package com.example.dovizapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CorinaFragment extends Fragment {
    corinaAdapter adapter;

    ArrayList<corinaModel> corinaModelArrayList;
    RecyclerView rvCoronaData;
    String URL = "https://coronavirus-19-api.herokuapp.com/countries";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_corona_data,container,false);
        rvCoronaData=view.findViewById(R.id.coronaList);
        setHasOptionsMenu(true);
        corinaModelArrayList=new ArrayList<>();
        adapter = new corinaAdapter(getActivity().getBaseContext(),corinaModelArrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(corinaModel corina, int position) {
               TastyToast.makeText(getActivity().getApplicationContext(),""+corina.getCountry(),TastyToast.LENGTH_SHORT,TastyToast.INFO).show();//RECYCLER VİEW İTEMLERNİE TIKLANDIĞI ZAMAN COUNRTY BİLGİSİ GOSTERİR.
                TastyToast.makeText(getActivity().getApplicationContext(), "Bu gün gelen vaka :"+corina.todayCases, TastyToast.LENGTH_LONG, TastyToast.WARNING);
            }
        });
        rvCoronaData.setAdapter(adapter);
        rvCoronaData.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        getUsers();

        return view;

    }
    private void getUsers(){

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Country = jsonObject.getString("country");
                        String Cases = jsonObject.getString("cases");
                        String todayCases=jsonObject.getString("todayCases");
                        String deaths=jsonObject.getString("deaths");
                        String todayDeaths=jsonObject.getString("todayDeaths");
                        String totalTests=jsonObject.getString("totalTests");
                        corinaModel corinaModel = new corinaModel(Country,Cases, todayCases,deaths,todayDeaths,totalTests);
                        corinaModelArrayList.add(corinaModel);
                    }
                    adapter.notifyDataSetChanged();
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);


    }
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        //inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

    }

}

