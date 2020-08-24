package com.example.dovizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class corinaAdapter extends RecyclerView.Adapter<corinaAdapter.MyViewHolder> implements Filterable {
        private Context context;
        private ArrayList<corinaModel> filteredCorinaList;
        private ArrayList<corinaModel> userModelArrayList;
        private CustomItemClickListener customItemClickListener;


        public corinaAdapter(Context context, ArrayList<corinaModel> userModelArrayList,CustomItemClickListener customItemClickListener){
            this.context = context;
            this.userModelArrayList = userModelArrayList;
            this.filteredCorinaList=userModelArrayList;
            this.customItemClickListener=customItemClickListener;

        }

        @NonNull
        @Override
        public corinaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.data,parent,false);
            final MyViewHolder myViewHolder = new MyViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // for click item listener
                    customItemClickListener.onItemClick(filteredCorinaList.get(myViewHolder.getAdapterPosition()),myViewHolder.getAdapterPosition());
                }
            });
            return myViewHolder;
            //return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull corinaAdapter.MyViewHolder holder, int position) {
            corinaModel user = filteredCorinaList.get(position);
            holder.country.setText(user.getCountry());
            holder.cases.setText("Toplam Vaka :"+user.getCases());
            holder.todayCases.setText("Bu gün kü Vaka :"+user.getTodayCases());
            holder.deaths.setText("Toplam Ölüm :"+user.getDeaths());
            holder.td.setText("Bu gün kü Ölüm :"+user.getTodayDeaths());
            holder.totalTest.setText("Toplam Test :"+user.getTotalTests());

        }

        @Override
        public int getItemCount() {
            return filteredCorinaList.size();
        }

        @Override
        public Filter getFilter() {

            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    String searchString = charSequence.toString();

                    if (searchString.isEmpty()) {

                        filteredCorinaList = userModelArrayList;

                    } else {

                        ArrayList<corinaModel> tempFilteredList = new ArrayList<>();

                        for (corinaModel user : userModelArrayList) {

                            // search for country name
                            if (user.getCountry().toLowerCase().contains(searchString)) {

                                tempFilteredList.add(user);
                            }
                        }

                        filteredCorinaList = tempFilteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values =  filteredCorinaList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filteredCorinaList = (ArrayList<corinaModel>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView country,cases,todayCases,deaths,td,totalTest;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                country =(TextView) itemView.findViewById(R.id.country);
                cases = (TextView) itemView.findViewById(R.id.cases);
                todayCases=itemView.findViewById(R.id.todayCases);
                deaths=itemView.findViewById(R.id.deaths);
                td=itemView.findViewById(R.id.todayDeaths);
                totalTest=itemView.findViewById(R.id.totalTest);
            }
        }
    }

