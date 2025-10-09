package com.jmora.flagquizapp.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.jmora.flagquizapp.model.FlagsModel

class FlagsDao {

    fun getRandomTenRecords(helper: DatabaseCopyHelper) : ArrayList<FlagsModel>{
        val recordList = ArrayList<FlagsModel>();
        val database : SQLiteDatabase = helper.writableDatabase;
        val cursor : Cursor = database.rawQuery("SELECT * FROM flags ORDER BY RANDOM() LIMIT 10",null);

        val indexId = cursor.getColumnIndex("flag_id");
        val countryNameIndex = cursor.getColumnIndex("country_name");
        val countryFlagIndex = cursor.getColumnIndex("flag_name");

        while (cursor.moveToNext()){

            val record = FlagsModel(
                cursor.getInt(indexId),
                cursor.getString(countryNameIndex),
                cursor.getString(countryFlagIndex)
            );

            recordList.add(record);
        }

        cursor.close();
        return recordList;
    }

    fun getRandomThreeRecords(helper: DatabaseCopyHelper, id : Int) : ArrayList<FlagsModel>{
        val recordList = ArrayList<FlagsModel>();
        val database : SQLiteDatabase = helper.writableDatabase;
        val cursor : Cursor = database.rawQuery(
            "SELECT * FROM flags WHERE flag_id != ? ORDER BY RANDOM() LIMIT 3",
            arrayOf(id.toString())
        );

        val indexId = cursor.getColumnIndex("flag_id");
        val countryNameIndex = cursor.getColumnIndex("country_name");
        val countryFlagIndex = cursor.getColumnIndex("flag_name");

        while (cursor.moveToNext()){

            val record = FlagsModel(
                cursor.getInt(indexId),
                cursor.getString(countryNameIndex),
                cursor.getString(countryFlagIndex)
            );

            recordList.add(record);
        }

        cursor.close();
        return recordList;
    }
}