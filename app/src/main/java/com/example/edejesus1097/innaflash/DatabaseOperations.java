package com.example.edejesus1097.innaflash;
import com.example.edejesus1097.innaflash.Subject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by edejesus1097 on 12/7/16.
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Flash_Db";

    public String CREATE_QUERY = "CREATE TABLE " + FlashCard.FlashInfo.TABLE_NAME + "(" + FlashCard.FlashInfo.CONCEPT + " TEXT," + FlashCard.FlashInfo.DEFINITION + " TEXT ," + FlashCard.FlashInfo.CARD_SUB + " TEXT);";


    public DatabaseOperations(Context context) {
        super(context, FlashCard.FlashInfo.DATEABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b) {
        db.execSQL("DROP TABLE IF EXISTS " + FlashCard.FlashInfo.TABLE_NAME);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //updates the database
    public void updateCard(DatabaseOperations db, String concept, String def, String oldcon, String olddef) {
        SQLiteDatabase SQ = db.getWritableDatabase();
        String selection = FlashCard.FlashInfo.CONCEPT + " LIKE ? AND " + FlashCard.FlashInfo.DEFINITION + " LIKE ?;";
        String args[] = {oldcon, olddef};
        ContentValues values = new ContentValues();
        values.put(FlashCard.FlashInfo.CONCEPT, concept);
        values.put(FlashCard.FlashInfo.DEFINITION, def);
        SQ.update(FlashCard.FlashInfo.TABLE_NAME, values, selection, args);
    }

    //inserts info into database
    public void putInformation(String content, String def, String card_sub) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FlashCard.FlashInfo.CONCEPT, content);
        cv.put(FlashCard.FlashInfo.DEFINITION, def);
        cv.put(FlashCard.FlashInfo.CARD_SUB, card_sub);
        long k = SQ.insert(FlashCard.FlashInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One raw inserted");
    }

    public void addSub(String sub) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Subject.Subject_info.Subject, sub);
        long k = SQ.insert(Subject.Subject_info.TABLE_NAME, null, cv);
        Log.d("Database operations", "Subject inserted");

    }

    public Cursor getInformation() {
        SQLiteDatabase SQ = this.getReadableDatabase();
        // String[] colomns = {FlashCard.FlashInfo.CONCEPT,FlashCard.FlashInfo.DEFINITION, FlashCard.FlashInfo.CARD_SUB};
        Cursor CR = SQ.rawQuery("select * from " + FlashCard.FlashInfo.TABLE_NAME, null);
        Log.d("Database operations", "Reading");
        return CR;
    }

    public Cursor getSub() {
        SQLiteDatabase SQ = this.getReadableDatabase();
        Cursor CR = SQ.rawQuery("select * from " + Subject.Subject_info.TABLE_NAME, null);
        Log.d("Database operations", "Getting Subject");
        return CR;
    }


    public boolean deleteSub(String sub, DatabaseOperations dop) {
        Cursor CR = dop.getInformation();
        CR.moveToFirst();
        if (CR.getCount() == 0)
            return false;
        else {
            do {
                if (CR.getString(2).equals(sub)) {
                    deleteCard(dop, CR.getString(0), CR.getString(1), CR.getString(2));
                }
            } while (CR.moveToNext());
        }
        CR.close();
        SQLiteDatabase SQ = this.getWritableDatabase();
        String selection = Subject.Subject_info.Subject + " LIKE ?";
        String args[] = {sub};
        SQ.delete(Subject.Subject_info.Subject, selection, args);
        return true;
    }

    public void deleteCard(DatabaseOperations dop, String concept, String def, String card_sub) {

        String selection = FlashCard.FlashInfo.CONCEPT + " LIKE ? AND " + FlashCard.FlashInfo.DEFINITION + " LIKE ? AND " + FlashCard.FlashInfo.CARD_SUB + " LIKE ? ";
        //String colomns[] = {FlashCard.FlashInfo.DEFINITION};
        String args[] = {concept, def, card_sub};
        SQLiteDatabase SQ = this.getWritableDatabase();
        SQ.delete(FlashCard.FlashInfo.TABLE_NAME, selection, args);
    }

    public void deleteSubject(SQLiteDatabase dop, String sub) {


        dop.execSQL("DELETE FROM " + FlashCard.FlashInfo.TABLE_NAME + " WHERE " + FlashCard.FlashInfo.CARD_SUB + " = '" + sub + "';");
    }

    public Cursor findsubcard(String sub) {

        SQLiteDatabase SQ = this.getReadableDatabase();
        String[] projections = {FlashCard.FlashInfo.CONCEPT, FlashCard.FlashInfo.DEFINITION};
        String selection = FlashCard.FlashInfo.CARD_SUB+" LIKE ?";
        String[] seletion_args={sub};
        Cursor cursor=SQ.query(FlashCard.FlashInfo.TABLE_NAME,projections,selection,seletion_args,null,null,null);
        return cursor;
    }
}



