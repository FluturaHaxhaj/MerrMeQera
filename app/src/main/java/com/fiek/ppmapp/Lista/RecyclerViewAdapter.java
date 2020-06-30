package com.fiek.ppmapp.Lista;

import com.fiek.ppmapp.MenuItems.Favorites.FavDB;
import com.fiek.ppmapp.R;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<BanesaShtepi> mData;
    RequestOptions option;
    private FavDB favDB;

    public RecyclerViewAdapter(Context mContext, List<BanesaShtepi> mData) {
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
                i.putExtra("banesa", mData.get(viewHolder.getAdapterPosition()).getBanesaShtepi());
                i.putExtra("pershkrimi", mData.get(viewHolder.getAdapterPosition()).getPershkrimi());
                i.putExtra("lokacioni", mData.get(viewHolder.getAdapterPosition()).getLokacioni());
                i.putExtra("cmimi", mData.get(viewHolder.getAdapterPosition()).getCmimi());
                i.putExtra("siperfaqja", mData.get(viewHolder.getAdapterPosition()).getSiperfaqja());
                i.putExtra("dhoma", mData.get(viewHolder.getAdapterPosition()).getKateDhoma());
                i.putExtra("tel",mData.get(viewHolder.getAdapterPosition()).getTelefoni());
                i.putExtra("img", mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);
            }
        });

        return viewHolder;

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BanesaShtepi banesaShtepi = mData.get(position);
        readCursorData(banesaShtepi,holder);

        holder.tv_cmimi.setText(mData.get(position).getCmimi());
        holder.tv_banesa.setText(mData.get(position).getBanesaShtepi()+", "+mData.get(position).getKateDhoma()+"dhoma, "+mData.get(position).getSiperfaqja());
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
                    BanesaShtepi banesaShtepi = mData.get(position);

                    if (banesaShtepi.getFavStatus().equals("0")){
                        banesaShtepi.setFavStatus("1");
                        favDB.insertIntoTheDatabase(banesaShtepi.getKey_id(),banesaShtepi.getBanesaShtepi(),banesaShtepi.getPershkrimi(),banesaShtepi.getLokacioni(),banesaShtepi.getCmimi(),
                                banesaShtepi.getSiperfaqja(),banesaShtepi.getKateDhoma(),banesaShtepi.getTelefoni(),banesaShtepi.getImage_url(),banesaShtepi.getFavStatus());
                        favBtn.setBackgroundResource(ic_baseline_red_24);
                    }else{
                        banesaShtepi.setFavStatus("0");
                        favDB.remove_fav(banesaShtepi.getKey_id());
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

    private void readCursorData(BanesaShtepi banesaShtepi, RecyclerView.ViewHolder viewHolder) {
        Cursor cursor = favDB.read_all_data(banesaShtepi.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while(cursor.moveToNext()){
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
                banesaShtepi.setFavStatus(item_fav_status);

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