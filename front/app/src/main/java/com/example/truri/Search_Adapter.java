package com.example.truri;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Search_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Search_data> arrayList;
    int bookmark_click = 0;
    int grade_click = 0;
    int position=0;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private List<String> data;

    public Search_Adapter(List<String> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_items, parent, false);
            return new CustomViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }


    //infinite-scroll
    @Override
    public int getItemViewType(int position) {
        return data.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    //adapter
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CustomViewHolder) {
            populateItemRows((CustomViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
        /*
        holder.reliability_icon.setImageResource(arrayList.get(position).getReliability_icon());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.title.setTextColor(Color.parseColor(arrayList.get(position).getColor()));
        holder.content.setText(arrayList.get(position).getContent());
        holder.url.setText(arrayList.get(position).getLink());
        holder.date.setText(arrayList.get(position).getDate());


        this.position = holder.getAdapterPosition();

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //url연결
            }
        });

        holder.bookmark_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(bookmark_click == 0)
                {
                    holder.bookmark_icon.setSelected(true);
                    bookmark_click = 1;
                }
                else
                {
                    holder.bookmark_icon.setSelected(false);
                    bookmark_click = 0;
                }
            }
        });

        //평가버튼 클릭 설정
        holder.grade_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //평가버튼 페이지 이동
//        holder.grade_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), ReviewGradePage.class);
//                startActivity(intent);
//            }
//        });*/
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch(IndexOutOfBoundsException exception){
            exception.printStackTrace();
        }
    }

    void addItem(List<Search_data> data_list, int position) {
        try {
            for(Search_data data : data_list) {
                arrayList.add(data);
            }
            notifyItemRangeChanged(position, data_list.size());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected ImageView reliability_icon;
        protected TextView title, content, url, date;
        protected ImageButton bookmark_icon, grade_icon;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.reliability_icon=(ImageView) itemView.findViewById(R.id.reliability_icon);
            this.title = itemView.findViewById(R.id.title);
            this.content = itemView.findViewById(R.id.content);
            this.url = itemView.findViewById(R.id.url);
            this.date = itemView.findViewById(R.id.date);
            this.grade_icon = itemView.findViewById(R.id.grade_icon);
            this.bookmark_icon = (ImageButton)itemView.findViewById(R.id.bookmark_icon);
            this.grade_icon = (ImageButton)itemView.findViewById(R.id.grade_icon);
        }
    }


    private void showLoadingView(LoadingViewHolder holder, int position) {
    }

    private void populateItemRows(CustomViewHolder holder, int position) {
        holder.reliability_icon.setImageResource(arrayList.get(position).getReliability_icon());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.title.setTextColor(Color.parseColor(arrayList.get(position).getColor()));
        holder.content.setText(arrayList.get(position).getContent());
        holder.url.setText(arrayList.get(position).getLink());
        holder.date.setText(arrayList.get(position).getDate());


        this.position = holder.getAdapterPosition();

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //url연결
            }
        });

        holder.bookmark_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(bookmark_click == 0)
                {
                    holder.bookmark_icon.setSelected(true);
                    bookmark_click = 1;
                }
                else
                {
                    holder.bookmark_icon.setSelected(false);
                    bookmark_click = 0;
                }
            }
        });

        //평가버튼 클릭 설정
        holder.grade_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        protected ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
            this.progressBar = itemView.findViewById((R.id.progressBar));
        }
    }


}
