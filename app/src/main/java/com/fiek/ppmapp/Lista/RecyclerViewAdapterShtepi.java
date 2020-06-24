package com.fiek.ppmapp.Lista;

import com.fiek.ppmapp.R;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapterShtepi extends RecyclerView.Adapter<RecyclerViewAdapterShtepi.MyViewHolder> {

    private Context mContext;
    private List<Shtepi> mData;
    RequestOptions option;

    public RecyclerViewAdapterShtepi(Context mContext, List<Shtepi> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.shtepi_row_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, ShtepiActivity.class);
                i.putExtra("sh_shtepi", mData.get(viewHolder.getAdapterPosition()).getShtepi());
                i.putExtra("sh_pershkrimi", mData.get(viewHolder.getAdapterPosition()).getPershkrimi());
                i.putExtra("sh_lokacioni", mData.get(viewHolder.getAdapterPosition()).getLokacioni());
                i.putExtra("sh_cmimi", mData.get(viewHolder.getAdapterPosition()).getCmimi());
                i.putExtra("sh_siperfaqja", mData.get(viewHolder.getAdapterPosition()).getSiperfaqja());
                i.putExtra("sh_kate", mData.get(viewHolder.getAdapterPosition()).getKate());
                i.putExtra("sh_tel",mData.get(viewHolder.getAdapterPosition()).getTelefoni());
                i.putExtra("sh_img", mData.get(viewHolder.getAdapterPosition()).getImage_url());


                mContext.startActivity(i);
            }
        });

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_cmimi.setText(mData.get(position).getCmimi());
        holder.tv_shtepi.setText(mData.get(position).getShtepi()+", "+mData.get(position).getKate()+"kate, "+mData.get(position).getSiperfaqja());
        holder.tv_lokacioni.setText(mData.get(position).getLokacioni());

        //Load image from the internet and set it into ImageView using Glide

        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cmimi;
        TextView tv_shtepi;
        TextView tv_lokacioni;
        ImageView img_thumbnail;
        LinearLayout view_container;



        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.containershtepi);
            tv_cmimi = itemView.findViewById(R.id.sh_cmimi);
            tv_shtepi = itemView.findViewById(R.id.sh_shtepi);
            tv_lokacioni = itemView.findViewById(R.id.sh_lokacioni);
            img_thumbnail = itemView.findViewById(R.id.sh_thumbnail);

        }
    }
}