package demopayment.krishna.com.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jaydeeprana on 01-07-2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String KEY_ROWID = "_id";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jumpsum.db";
    private static final String USER_TABLE = "User";
    private static final String LOCATIONS_TABLE = "Locations";
    private static final String TABLE_ALL_SPORTS = "All_Sports";
    private static final String TABLE_USER_SPORTS = "player_sports";
    private static final String TABLE_NEWS_FEEDS = "news_feed";
    public static final String TABLE_ALL_PLAYERS = "All_Players";
    public static final String TABLE_ALL_MEMBERSHIP_LIST = "Membership_list";
    public static final String TABLE_ALL_ACHIEVEMENT_LIST = "Achievement_list";
    public static final String TABLE_PLAYER_ALL_SPORT_DETAILS = "Player_all_sport_details";

    private static final String DATABASE_PATH = "/data/data/com.webmyne.android.jumpsum/databases/";
    private Context context;
    private SQLiteDatabase myDataBase = null;

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();
        if (dbExist) {
//            Log.v("log_tag", "database does exist");
        } else {
//            Log.v("log_tag", "database does not exist");
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    private boolean checkDataBase() {

        File folder = new File(DATABASE_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
        return dbFile.exists();
    }

    public boolean openDataBase() throws SQLException {
        String mPath = DATABASE_PATH + DATABASE_NAME;
        //Log.v("mPath", mPath);
        myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return myDataBase != null;

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    // Constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    public boolean isUserExists() {
        boolean isExists = false;
        myDataBase = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM User";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if (cursor == null || cursor.getCount() == 0) {
            isExists = false;
        } else {
            isExists = true;
        }
        myDataBase.close();


        return isExists;
    }



    public boolean isPincodeExists(String pincode) {
        boolean isExists = false;
        myDataBase = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM Locations where pincode =" + "\"" + pincode + "\"";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();


        if (cursor == null || cursor.getCount() == 0) {
            isExists = false;
        } else {
            isExists = true;
        }
        myDataBase.close();
        return isExists;
    }



    public Cursor getPlayerList (String inputText)  {

        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = myDataBase.query(TABLE_ALL_PLAYERS, new String[] {KEY_ROWID,"user_id","name", "last_name", "mobile", "city"},
                    null, null, null, null, null);

        }
        else {
            mCursor = myDataBase.query(true, TABLE_ALL_PLAYERS, new String[] {KEY_ROWID,"user_id","name", "last_name", "mobile", "city"},
                    "name" + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;


       /* SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(
                "All_Players"
        );




        String asColumnsToReturn[] = {
                "All_Players" + "."
                        + "name" + "," +
                        "All_Players" + "."
                        + "last_name" + "," +
                        "All_Players" + "."
                        + "mobile" + "," +
                        "All_Players" + "."
                        + "city"
        };

        if (constraint == null  ||  constraint.length () == 0)  {
            //  Return the full list
            return queryBuilder.query(myDataBase, asColumnsToReturn, null, null,
                    null, null, "name"+ " ASC");
        } else  {
            String value = "%"+constraint.toString()+"%";

            return queryBuilder.query(myDataBase, asColumnsToReturn, "name like ? ", new String[]{value}, null, null, null);
        }*/
    }




    public boolean isNewsFeedExists(String sportId) {
        boolean isExists = false;
        myDataBase = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM news_feed where sport_id =" + "\"" + sportId + "\"";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();


        if (cursor == null || cursor.getCount() == 0) {
            isExists = false;
        } else {
            isExists = true;
        }
        myDataBase.close();
        return isExists;
    }




    public Cursor getAllPlayersFromDatabase() {
        myDataBase = this.getWritableDatabase();
     //   ArrayList<GetAllPlayers> allPlayers = new ArrayList<>();

        String selectQuery = "SELECT * FROM All_Players";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) ;

        return cursor;
    }


    public boolean isExistAllPlayerTable() {
        boolean hasTables = false;
        myDataBase = this.getWritableDatabase();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM All_Players", null);

        if (cursor.getCount() == 0) {
            hasTables = false;
            if (cursor.getCount() > 0) {
                hasTables = true;
            }

            cursor.close();
        }
        return hasTables;


    }



    public Cursor getAllMemberShipList() {
        myDataBase = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM Membership_list";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) ;

        return cursor;
    }





    public Cursor getAllAchievementList() {
        myDataBase = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM Achievement_list";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) ;

        return cursor;
    }



    public Cursor getPlayerAllSportDetails() {
        myDataBase = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM Player_all_sport_details";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) ;

        return cursor;
    }




    public void add_user_sport(String sportId) {
        myDataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ps_sport_id", "");
        values.put("sport_id", sportId);
        values.put("player_id", "");
        values.put("play_duration", "");
        values.put("play_level", "");
        values.put("play_frequency", "");
        values.put("description", "");
        values.put("favourite_team_id", "");
        values.put("favourite_idol_id", "");
        values.put("share_profile", "");
        values.put("is_player", "");
        values.put("is_coach", "");
        values.put("is_refree", "");
        values.put("is_filled", "");
        values.put("added_on", "");
        values.put("updated_on", "");
        values.put("is_active", "");
        myDataBase.insert(TABLE_USER_SPORTS, null, values);
        myDataBase.close(); // Closing database connection
    }

    public void deleteUserSport(String sportId) {

        myDataBase = this.getWritableDatabase();
        myDataBase.delete(TABLE_USER_SPORTS,"sport_id = ?",new String[]{sportId});
        myDataBase.close();
    }



    public ArrayList<String> getAllSportIds() {
        myDataBase = this.getWritableDatabase();
        ArrayList<String> allSports = new ArrayList<>();
        String selectQuery = "SELECT * FROM All_Sports";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        do {
            String id = new String();
            id = "" + cursor.getInt(cursor.getColumnIndex("sports_id"));
            allSports.add(id);
        } while (cursor.moveToNext());

        return allSports;
    }

    public String getSportNameFromMySports(String spID) throws SQLException
    {
        String selectQuery = "SELECT * FROM All_Sports where sports_id = "+spID;
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String spName = cursor.getString(2);
        return spName;
    }

    public ArrayList<String> getAllUserSportIds() {
        myDataBase = this.getWritableDatabase();
        ArrayList<String> allSports = new ArrayList<>();
        String selectQuery = "SELECT * FROM player_sports";
        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        do {
            String id = new String();
            id = "" + cursor.getInt(cursor.getColumnIndex("sport_id"));
            allSports.add(id);
        } while (cursor.moveToNext());

        return allSports;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllContacts() {
        String selectQuery = "SELECT  * FROM All_Players";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

}
