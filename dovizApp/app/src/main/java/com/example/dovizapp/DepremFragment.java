package com.example.dovizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DepremFragment extends Fragment {
    depremAdapter adapter;
    ArrayList<depremModel> depremModelArrayList;
    RecyclerView depremList;
    String URL = "https://deprem.odabas.xyz/api/pure_api.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_favori,container,false);
        depremList=view.findViewById(R.id.depremRv);
        depremModelArrayList=new ArrayList<>();
        adapter = new depremAdapter(getActivity().getBaseContext(),depremModelArrayList);
        depremList.setAdapter(adapter);
        depremList.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        getUsers();

        return  view;
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
                        String tarih = jsonObject.getString("tarih");
                        String saat = jsonObject.getString("saat");
                        String derinlik=jsonObject.getString("derinlik");
                        String siddet=jsonObject.getString("siddet");
                        String yer=jsonObject.getString("yer");
                        String tur=jsonObject.getString("tip");
                        depremModel depremModel = new depremModel(tarih,saat,yer,siddet,derinlik,tur);//buradaki sıraya gore dolduruyoruz verileri
                       depremModelArrayList.add(depremModel);
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

}
