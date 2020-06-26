package com.fiek.ppmapp.Lista;

import com.fiek.ppmapp.Favorites.FavDB;
import com.fiek.ppmapp.R;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static com.fiek.ppmapp.R.drawable.ic_baseline_fshadow_24;
import static com.fiek.ppmapp.R.drawable.ic_baseline_red_24;
import static com.fiek.ppmapp.R.drawable.white_circle;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Banesa> mData;
    RequestOptions option;
    private FavDB favDB;

    public RecyclerViewAdapter(Context mContext, List<Banesa> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        favDB = new FavDB(mContext);
        View view;
        SharedPreferences prefs = mContext.getSharedPreferences("prefs",mContext.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if (firstStart){
            createTableOnFirstStart();
        }
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.banesa_row_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, BanesaActivity.class);
                i.putExtra("b_banesa", mData.get(viewHolder.getAdapterPosition()).getBanesa());
                i.putExtra("b_pershkrimi", mData.get(viewHolder.getAdapterPosition()).getPershkrimi());
                i.putExtra("b_lokacioni", mData.get(viewHolder.getAdapterPosition()).getLokacioni());
                i.putExtra("b_cmimi", mData.get(viewHolder.getAdapterPosition()).getCmimi());
                i.putExtra("b_siperfaqja", mData.get(viewHolder.getAdapterPosition()).getSiperfaqja());
                i.putExtra("b_dhoma", mData.get(viewHolder.getAdapterPosition()).getDhoma());
                i.putExtra("b_tel",mData.get(viewHolder.getAdapterPosition()).getTelefoni());
                i.putExtra("b_img", mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);
            }
        });

        return viewHolder;

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Banesa banesa = mData.get(position);
        readCursorData(banesa,holder);

        holder.tv_cmimi.setText(mData.get(position).getCmimi());
        holder.tv_banesa.setText(mData.get(position).getBanesa()+", "+mData.get(position).getDhoma()+"dhoma, "+mData.get(position).getSiperfaqja());
        holder.tv_lokacioni.setText(mData.get(position).getLokacioni());

        //Load image from the internet and set it into ImageView using Glide

        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cmimi;
        TextView tv_banesa;
        TextView tv_lokacioni;
        ImageView img_thumbnail;
        LinearLayout view_container;
        Button favBtn;



        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_cmimi = itemView.findViewById(R.id.cmimi);
            tv_banesa = itemView.findViewById(R.id.banesa);
            tv_lokacioni = itemView.findViewById(R.id.lokacioni);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            favBtn = itemView.findViewById(R.id.b_favBtn);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Banesa banesa = mData.get(position);

                    if (banesa.getFavStatus().equals("0")){
                        banesa.setFavStatus("1");
                        favDB.insertIntoTheDatabase(banesa.getKey_id(),banesa.getBanesa(),banesa.getPershkrimi(),banesa.getLokacioni(),banesa.getCmimi(),
                                banesa.getSiperfaqja(),banesa.getDhoma(),banesa.getTelefoni(),banesa.getImage_url(),banesa.getFavStatus());
                        favBtn.setBackgroundResource(ic_baseline_red_24);
                    }else{
                        banesa.setFavStatus("0");
                        favDB.remove_fav(banesa.getKey_id());
                        favBtn.setBackgroundResource(ic_baseline_fshadow_24);

                    }
                }
            });

        }
    }
    private void createTableOnFirstStart() {
        favDB.insertEmpty();
        SharedPreferences prefs = mContext.getSharedPreferences("prefs",mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

    private void readCursorData(Banesa banesa, RecyclerView.ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(banesa.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                banesa.setFavStatus(item_fav_status);

                if (item_fav_status != null && item_fav_status.equals("1")){
                    viewHolder.itemView.findViewById(R.id.b_favBtn).setBackgroundResource(ic_baseline_red_24);
                }else if (item_fav_status != null && item_fav_status.equals("0")){
                    viewHolder.itemView.findViewById(R.id.b_favBtn).setBackgroundResource(ic_baseline_fshadow_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

    }

}