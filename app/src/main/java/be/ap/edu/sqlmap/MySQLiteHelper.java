package be.ap.edu.sqlmap;

/**
 * Created by schel on 5/10/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    String[] coordinates = new String[2];
    private static final String DATABASE_NAME = "examenmapSQL.db";
    private static final String TABLE_MARKERS = "markersexamen";
    private static final int DATABASE_VERSION = 5;

    public MySQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Android expects _id to be the primary key
        String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_MARKERS + "(_id INTEGER PRIMARY KEY, latitude REAL, longitude REAL, beschrijving TEXT)";
        db.execSQL(CREATE_LOCATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKERS);
        onCreate(db);
    }

    public void addLocation(double lat, double lon, String beschrijving) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_LOCATIONS, null, null);

        ContentValues values = new ContentValues();
        values.put("latitude", lat);
        values.put("longitude", lon);
        values.put("beschrijving", beschrijving);

        db.insert(TABLE_MARKERS, null, values);
        db.close();
    }

    public List<Marker> getAllMarkers() {
        List<Marker> markers = new LinkedList<Marker>();

        String query = "SELECT  * FROM " + TABLE_MARKERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Marker marker = null;
        if (cursor.moveToFirst()) {
            do {
                marker = new Marker();
                marker.setId(Integer.parseInt(cursor.getString(0)));
                marker.setLat(Double.parseDouble(cursor.getString(1)));
                marker.setLon(Double.parseDouble(cursor.getString(2)));
                marker.setDescription(cursor.getString(3));

                markers.add(marker);
            } while (cursor.moveToNext());
        }
        return markers;
    }

    public void deleteAllRecords() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_MARKERS);
    }
}