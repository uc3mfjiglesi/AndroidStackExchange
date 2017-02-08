package es.cice.androidstackexchange.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import es.cice.androidstackexchange.model.Item;

/**
 * Created by cice on 7/2/17.
 */

public class QuestionsOpenHelper extends SQLiteOpenHelper {

    public static final String PK_COLUM="_id";
    public static final String OWNER_AVATAR_COLUM="PROFILE_IMAGE";
    public static final String QUESTION_TITLE_COLUMN="TITLE";
    public static final String QUESTION_ID_COLUMN="QUESTION_ID";
    public static final String QUESTION_LINK_COLUMN="LINK";
    public static final String OWNER_ID_COLUMN="USER_ID";
    public static final String QUESTION_TABLE="QUESTIONS";
    public static final String QUESTIONS_DB="questionsDB";
    public static final int VERSION=1;
    private static QuestionsOpenHelper qoh;
    private static Context ctx;

    private QuestionsOpenHelper(Context context) {
        super(context, QUESTIONS_DB, null, VERSION);
    }

    public static QuestionsOpenHelper getInstance(Context ctx){
        if(qoh==null){
            qoh=new QuestionsOpenHelper(ctx);
            QuestionsOpenHelper.ctx=ctx;
        }
        return qoh;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE QUESTIONS(" + PK_COLUM +  " INTEGER PRIMARY KEY AUTOINCREMENT," +
            QUESTION_ID_COLUMN + " INTEGER, " + QUESTION_TITLE_COLUMN + " TEXT," +
                QUESTION_LINK_COLUMN + " TEXT," + OWNER_ID_COLUMN + " INTEGER," +
                OWNER_AVATAR_COLUM + " INTEGER)";
        db.execSQL(sql);
    }

    public Cursor insert(List<Item> questions){
        String sql="insert into " + QUESTION_TABLE + "(" + QUESTION_ID_COLUMN + "," +
                QUESTION_TITLE_COLUMN + "," + QUESTION_LINK_COLUMN + "," +
                OWNER_ID_COLUMN + "," + OWNER_AVATAR_COLUM + ") VALUES(?,?,?,?,?)";
        SQLiteDatabase db=qoh.getWritableDatabase();
        db.beginTransaction();
        for(Item item:questions){
            db.execSQL(sql,new Object[]{item.questionId,item.title,item.link,item.owner.userId,
            item.owner.profileImage});
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return db.query(QUESTION_TABLE,new String[]{PK_COLUM,QUESTION_TITLE_COLUMN,OWNER_AVATAR_COLUM},
                null,null,null,null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        throw new RuntimeException("No es admitida la actualización de esta base de datos");
    }


}
