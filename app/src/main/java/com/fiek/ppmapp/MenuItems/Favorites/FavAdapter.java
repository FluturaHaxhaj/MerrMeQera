package com.fiek.ppmapp.MenuItems.Favorites;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fiek.ppmapp.Lista.BanesaActivity;
import com.fiek.ppmapp.R;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private Context context;
    private List<FavItem> favItemList;
    private FavDB favDB;
    RequestOptions option;

    public FavAdapter(Context context, List<FavItem> favItemList) {
        this.context = context;
        this.favItemList = favItemList;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item,
                parent, false);
        favDB = new FavDB(context);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, BanesaActivity.class);
                i.putExtra("banesa", favItemList.get(viewHolder.getAdapterPosition()).getTitulli());
                i.putExtra("pershkrimi", favItemList.get(viewHolder.getAdapterPosition()).getPershkrimi());
                i.putExtra("lokacioni", favItemList.get(viewHolder.getAdapterPosition()).getLokacioni());
                i.putExtra("cmimi", favItemList.get(viewHolder.getAdapterPosition()).getCmimi());
                i.putExtra("siperfaqja", favItemList.get(viewHolder.getAdapterPosition()).getSiperfaqja());
                i.putExtra("dhoma", favItemList.get(viewHolder.getAdapterPosition()).getDhoma());
                i.putExtra("tel", favItemList.get(viewHolder.getAdapterPosition()).getTelefoni());
                i.putExtra("img", favItemList.get(viewHolder.getAdapterPosition()).getImg());
                i.putExtra("lat", favItemList.get(viewHolder.getAdapterPosition()).getLat());
                i.putExtra("lng", favItemList.get(viewHolder.getAdapterPosition()).getLng());
                i.putExtra("img2", favItemList.get(viewHolder.getAdapterPosition()).getImg2());
                i.putExtra("img3", favItemList.get(viewHolder.getAdapterPosition()).getImg3());
                i.putExtra("img4", favItemList.get(viewHolder.getAdapterPosition()).getImg4());


                context.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_banesa.setText(favItemList.get(position).getTitulli());
        holder.tv_lokacioni.setText(favItemList.get(position).getLokacioni());
        holder.tv_cmimi.setText(favItemList.get(position).getCmimi());

        Glide.with(context).load(favItemList.get(position).getImg()).apply(option).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return favItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_cmimi;
        TextView tv_banesa;
        TextView tv_lokacioni;
        ImageView img_thumbnail;
        LinearLayout view_container;
        Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_banesa = itemView.findViewById(R.id.fav_shtepi);
            tv_cmimi = itemView.findViewById(R.id.fav_cmimi);
            tv_lokacioni = itemView.findViewById(R.id.fav_lokacioni);
            img_thumbnail = itemView.findViewById(R.id.fav_thumbnail);
            view_container = itemView.findViewById(R.id.fav_containershtepi);
            favBtn = itemView.findViewById(R.id.fav_btn);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final FavItem favItem = favItemList.get(position);
                    favDB.remove_fav(favItem.getKey_id());
                    removeItem(position);
                }
            });


        }
    }

    private void removeItem(int position) {
        favItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, favItemList.size());

    }
}
