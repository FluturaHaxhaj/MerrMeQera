package com.fiek.ppmapp.MenuItems.Favorites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavDB extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "FavoritesDB";
    private static String TABLE_NAME = "FavoritesTable";
    public static String KEY_ID = "id";
    public static String TITULLI = "emri";
    public static String PERSHKRIMI = "pershkrimi";
    public static String LOKACIONI = "lokacioni";
    public static String CMIMI = "cmimi";
    public static String SIPERFAQJA = "siperfaqja";
    public static String KATE_DHOMA = "kateDhoma";
    public static String TELEFONI = "telefoni";
    public static String IMAGE_URL = "imageURL";
    public static String LAT = "lat";
    public static String LNG = "lng";
    public static String FAVORITE_STATUS = "fstatus";
    private static String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ "("
            +KEY_ID + " TEXT,"+ TITULLI+ " TEXT,"
            +PERSHKRIMI+" TEXT,"+ LOKACIONI+ " TEXT,"
            +CMIMI+" TEXT,"+SIPERFAQJA+" TEXT,"+KATE_DHOMA+" TEXT,"+TELEFONI+" TEXT,"
            +IMAGE_URL+" TEXT,"+FAVORITE_STATUS+" TEXT,"+LAT+" TEXT,"+LNG+" TEXT)";

    public FavDB(Context context){super(context,DATABASE_NAME,null,DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertEmpty(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (int x=1;x<11;x++){
            cv.put(KEY_ID,x);
            cv.put(FAVORITE_STATUS,"0");
            db.insert(TABLE_NAME,null,cv);
        }
    }

    public void insertIntoTheDatabase(String id,String titulli,String pershkrimi,String lokacioni,String cmimi,String siperfaqja,String kateDhoma,String telefoni,String imageUrl,String fav_status,String lat, String lng){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID,id);
        cv.put(TITULLI,titulli);
        cv.put(PERSHKRIMI,pershkrimi);
        cv.put(LOKACIONI,lokacioni);
        cv.put(CMIMI,cmimi);
        cv.put(SIPERFAQJA,siperfaqja);
        cv.put(KATE_DHOMA,kateDhoma);
        cv.put(TELEFONI,telefoni);
        cv.put(IMAGE_URL,imageUrl);
        cv.put(FAVORITE_STATUS,fav_status);
        cv.put(LAT,lat);
        cv.put(LNG,lng);
        db.insert(TABLE_NAME,null,cv);
        Log.d("FavDV Status", TITULLI+", favstatus - "+fav_status+" - . "+ cv);
    }

    public Cursor read_all_data(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from "+ TABLE_NAME+" where "+KEY_ID+ "="+id+"";
        return db.rawQuery(sql,null,null);
    }

    public void remove_fav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE "+ TABLE_NAME +" SET "+FAVORITE_STATUS+" ='0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove",id);
    }
    public Cursor select_all_favorite_list(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME+" WHERE "+FAVORITE_STATUS+" ='1'";
        return db.rawQuery(sql,null,null);
    }

}

