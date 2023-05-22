package Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Models.ArticleModel;
import Models.Team;
import Utils.Utils;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //********************* Create Article Table **********************//
        String CREATE_ARTICLE_TABLE ="CREATE TABLE " + Utils.TABLE_NAME_ARTICLE + " (" +
                Utils.ARTICLE_KEY_ID + " INTEGER PRIMARY KEY,"
                + Utils.ARTICLE_KEY_NAME + " TEXT,"
                + Utils.ARTICLE_KEY_WEIGHT + " INTEGER,"
                + Utils.ARTICLE_KEY_CADENCE + " INTERGER,"
                + Utils.ARTICLE_KEY_IMAGE + " TEXT"
                +")";
        db.execSQL(CREATE_ARTICLE_TABLE);

        //********************* Create Team Table **********************//
        String CREATE_TEAM_TABLE ="CREATE TABLE " + Utils.TABLE_NAME_TEAM + " (" +
                Utils.TEAM_KEY_ID + " INTEGER PRIMARY KEY,"
                + Utils.TEAM_KEY_NAME + " TEXT"
                +")";
        db.execSQL(CREATE_TEAM_TABLE);

        //********************* Create EMPLOYEE Table **********************//
        String CREATE_EMPLOYEE_TABLE ="CREATE TABLE " + Utils.TABLE_NAME_EMPLOYEE + " (" +
                Utils.EMPLOYEE_KEY_ID + " INTEGER PRIMARY KEY,"
                + Utils.EMPLOYEE_KEY_NAME + " TEXT,"
                + Utils.EMPLOYEE_KEY_FIRST_NAME + " TEXT,"
                + Utils.EMPLOYEE_KEY_PHONE + " INTEGER,"
                + Utils.EMPLOYEE_KEY_TEAM_ID + " TEXT,"
                +"FOREIGN KEY ("+Utils.EMPLOYEE_KEY_TEAM_ID+") REFERENCES "
                + Utils.TABLE_NAME_TEAM+ "("+Utils.TEAM_KEY_ID+")"
                +")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME_ARTICLE);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME_EMPLOYEE);

        onCreate(db);
    }
    //****************************************************************************//
    //********************* Insert data into article table **********************//

    public int addArticle(ArticleModel articleModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.ARTICLE_KEY_NAME , articleModel.getName_art());
        contentValues.put(Utils.ARTICLE_KEY_WEIGHT , articleModel.getWeight_art());
        contentValues.put(Utils.ARTICLE_KEY_CADENCE , articleModel.getCadence_art());
        contentValues.put(Utils.ARTICLE_KEY_IMAGE , articleModel.getImage_art());


        int id = (int) db.insert(Utils.TABLE_NAME_ARTICLE , null,contentValues);
        db.close();
        return id ;
    }



    //********************* Get all Article data *********************//

    @SuppressLint("Range")
    public List<ArticleModel> getAllData() {
        List<ArticleModel> allData = new ArrayList<>();
        String query = "SELECT * FROM "+ Utils.TABLE_NAME_ARTICLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( query ,null);
        if(cursor.moveToFirst())
            do{
                ArticleModel article = new ArticleModel();
                article.setId_art(cursor.getInt(cursor.getColumnIndex(Utils.ARTICLE_KEY_ID)));
                article.setName_art( cursor.getString(cursor.getColumnIndex(Utils.ARTICLE_KEY_NAME)));
                article.setWeight_art( cursor.getInt(cursor.getColumnIndex(Utils.ARTICLE_KEY_WEIGHT)));
                article.setCadence_art(cursor.getInt(cursor.getColumnIndex(Utils.ARTICLE_KEY_CADENCE)));
                article.setImage_art( cursor.getBlob(cursor.getColumnIndex(Utils.ARTICLE_KEY_IMAGE)));

                allData.add(article);

            }while (cursor.moveToNext());
        db.close();
        cursor.close();
        return allData;
    }


    //********************* Get one Article using id **********************//

    public  ArticleModel getArticle(int id){
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor = db.query(Utils.TABLE_NAME_ARTICLE ,
                new String[]{Utils.ARTICLE_KEY_ID,Utils.TABLE_NAME_ARTICLE,
                        Utils.ARTICLE_KEY_WEIGHT,
                        Utils.ARTICLE_KEY_CADENCE},
                Utils.ARTICLE_KEY_ID + "=?",new String[]{String.valueOf(id)},
                null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();
        ArticleModel article = new ArticleModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),Integer.parseInt(cursor.getString(2))
                ,Integer.parseInt(cursor.getString(3))
                ,cursor.getBlob(4));
        cursor.close();
        return article;
    }

    //********************* Update Article **********************//

    public  int updateArticle(ArticleModel article){
        SQLiteDatabase db = this.getWritableDatabase() ;

        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.ARTICLE_KEY_NAME , article.getName_art());
        contentValues.put(Utils.ARTICLE_KEY_WEIGHT , article.getWeight_art());
        contentValues.put(Utils.ARTICLE_KEY_CADENCE , article.getCadence_art());
        contentValues.put(Utils.ARTICLE_KEY_IMAGE , String.valueOf(article.getImage_art()) );

        int result = db.update(Utils.TABLE_NAME_ARTICLE , contentValues,Utils.ARTICLE_KEY_ID + "=?",
                new String[]{ String.valueOf(article.getId_art())});
        db.close();
        return result;

    }

    //********************* Delete Article **********************//

    public  void deleteArticle(int  articleID){
//        SQLiteDatabase db = getWritableDatabase() ;
//        db.delete(Utils.TABLE__ARTICLE_NAME , Utils.ARTICLE_KEY_ID + "=?",
//                    new String[]{ String.valueOf(article.getId_art())});
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Utils.TABLE_NAME_ARTICLE, "id=?", new String[]{String.valueOf(articleID)});
        db.close();
    }


    //********************* Get Number of Article in the table **********************//

    public  int getNumArticle(){
        String getAll = "SELECT * FROM "+ Utils.TABLE_NAME_ARTICLE;
        SQLiteDatabase database = this.getReadableDatabase() ;
        Cursor cursor = database.rawQuery(getAll ,null );

        // cursor.close();
        return cursor.getCount();

    }
    //*************************************************************************//
    //********************* Insert data into team table **********************//

    public void addTeam(Team team){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(Utils.TEAM_KEY_ID , team.getT_id());
        contentValues.put(Utils.TEAM_KEY_NAME , team.getT_name());

        //int id = (int) db.insert(Utils.TABLE_NAME_TEAM , null,contentValues);
        long teamId = db.insert(Utils.TABLE_NAME_TEAM, null, contentValues);
        team.setT_id((int) teamId);
        db.close();
    }

    //********************* Delete Team **********************//

    public  void deleteTeam(String  teamName){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Utils.TABLE_NAME_TEAM, Utils.TEAM_KEY_NAME + "=?", new String[]{String.valueOf(teamName)});
        db.close();
    }




}
