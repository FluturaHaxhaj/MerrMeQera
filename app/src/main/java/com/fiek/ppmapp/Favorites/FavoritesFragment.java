package com.fiek.ppmapp.Favorites;

import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiek.ppmapp.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel mViewModel;
    private RecyclerView recyclerView;
    private FavDB favDB;
    private List<FavItem> favItemList = new ArrayList<>();
    private FavAdapter favAdapter;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.favorites_fragment, container, false);
        favDB = new FavDB(getActivity());
        recyclerView = root.findViewById(R.id.fav_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();

        return root;
    }

    private void loadData() {
        if (favAdapter != null){
            favItemList.clear();
        }
        SQLiteDatabase db = favDB.getReadableDatabase();
        Cursor cursor = favDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndex(FavDB.TITULLI));
                String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
                String image = cursor.getString(cursor.getColumnIndex(FavDB.IMAGE_URL));
                String cmimi = cursor.getString(cursor.getColumnIndex(FavDB.CMIMI));
                String pershkrimi = cursor.getString(cursor.getColumnIndex(FavDB.PERSHKRIMI));
                String siperfaqja = cursor.getString(cursor.getColumnIndex(FavDB.SIPERFAQJA));
                String dhoma = cursor.getString(cursor.getColumnIndex(FavDB.KATE_DHOMA));
                String telefoni = cursor.getString(cursor.getColumnIndex(FavDB.TELEFONI));
                String lokacioni = cursor.getString(cursor.getColumnIndex(FavDB.LOKACIONI));
                FavItem favItem = new FavItem(id,title,pershkrimi,lokacioni,cmimi,siperfaqja,dhoma,telefoni,image);
                favItemList.add(favItem);

            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
        favAdapter = new FavAdapter(getActivity(),favItemList);
        recyclerView.setAdapter(favAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);
        // TODO: Use the ViewModel
    }

}