package com.example.Kafeshahrpackage.Kafeshahr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by malihe on 11/7/2017.
 */

public class mydatabasehandler extends SQLiteOpenHelper {
    private static Context myContext;
    public static final String namedatabase="Kafeshahr.db";
    public static final String table="favorites";
    public static final String table2="managetile";
    public static final  String key="key";
    public static final String title="title";
    public static final String folder = Environment.getExternalStorageDirectory() + File.separator + "Kafeshahr/"+namedatabase;
    public static final String des="des";
    public static final String status="status";
    public static final String image="image";
    public static final String date="date";
    public static final String notif="notif";
    public static final String time="time";
    public static final String commentcount="comment_count";
    public static final String visit="visit";
    public static final String id="id";
    public static final String pos="pos";
    public static final String idcat="idcat";
    public static final String is_video="is_video";
    public static final String is_gallery="is_gallery";
    public static final String images_count="images_count";

    public static final  String keytile="key";
    public static final String titletile="title";
    public static final Integer version=1;
    String sqlst = "CREATE TABLE " + table
            + "("
            + key + " Integer primary key autoincrement not null,"
            + id + " TEXT,"
            + idcat + " TEXT,"
            + title + " TEXT,"
            + des + " TEXT,"
            + image + " TEXT,"
            + date + " TEXT,"
            + time + " TEXT,"
            + is_video + " TEXT,"
            + commentcount + " TEXT,"
            + visit + " TEXT,"
            + is_gallery + " TEXT,"
            + images_count + " TEXT)"
            ;


    String sqlsttile = "CREATE TABLE " + table2
            + "("
            +keytile + " Integer primary key autoincrement not null,"
            +id+" TEXT,"
            +pos+" TEXT,"
            +image+" TEXT,"
            +notif+" TEXT,"
            +titletile + " TEXT)"

            ;
    public mydatabasehandler(Context context) {

        super(context, namedatabase, null, version);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlst);
        db.execSQL(sqlsttile);
      }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF exists"+table);
        db.execSQL("DROP TABLE IF exists"+table2);
        onCreate(db);
    }

    public boolean delall()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(table,null,null);
        db.delete(table2,null,null);

       // db.deleteDatabase(new File(path));
        boolean b = myContext.deleteDatabase(namedatabase);
        return  b;
    }
    public long additem(Posts fav)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cont=new ContentValues();
        cont.put(id,fav.getid());
        cont.put(idcat,fav.getcategory());
        cont.put(title,fav.gettitle());
        cont.put(des,fav.getdes());
        cont.put(image,fav.getImage());
        cont.put(date,fav.getdate());
        cont.put(time,fav.gettime());
        cont.put(is_video,fav.getisvideo());
        cont.put(commentcount,fav.getcomment_count());
        cont.put(visit,fav.getvisit());
         cont.put(is_gallery,fav.getIsgallery());
        cont.put(images_count,fav.getimages_count());




       // cont.put("status",fav.getisFav());
       // cont.put("pos",fav.getid());
        long todo_id =db.insert(table,null,cont);
        return  todo_id;
    }
    public void deluser(String st)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(table ,id +"=?",new String[]{String.valueOf(st)});
        db.close();

    }

     public List<Posts>  getfavoriteslist() {
         final List<String> list = new ArrayList<String>();
         List<Posts> posts = new ArrayList<>();
         String selectAllQuery = " SELECT * FROM " + table;
         SQLiteDatabase sql = this.getReadableDatabase();
         // Cursor c = sql.rawQuery(selectAllQuery, null);
         Cursor c = sql.rawQuery(selectAllQuery,null);

         if (c != null && c.getCount() > 0) {

             try {
                 while (c.moveToNext()) {
                     Posts post = new Posts();
                     post.setid(c.getString(c.getColumnIndex(id)));
                     post.setcategory(c.getString(c.getColumnIndex(idcat)));
                     post.settitle(c.getString(c.getColumnIndex(title)));
                     post.setdes(c.getString(c.getColumnIndex(des)));
                     post.setImage(c.getString(c.getColumnIndex(image)));
                     post.setdate(c.getString(c.getColumnIndex(date)));
                     post.settime(c.getString(c.getColumnIndex(time)));
                     post.setisvideo(c.getString(c.getColumnIndex(is_video)));
                      post.setcomment_count(c.getString(c.getColumnIndex(commentcount)));
                     post.setvisit(c.getString(c.getColumnIndex(visit)));
                        post.setIsgallery(c.getString(c.getColumnIndex(is_gallery)));
                     post.setimages_count(c.getString(c.getColumnIndex(images_count)));
                    // list.add(c.getString(c.getColumnIndex("name")));
                    posts.add(post);
                 }
             } finally {
                 c.close();
             }

         }
         return posts;
     }
    public List<Posts>  getfavoriteslistcat(String idcategory) {
        final List<String> list = new ArrayList<String>();
        List<Posts> posts = new ArrayList<>();


        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c=db.query(table,new String[]{id,idcat,title,des,image,date,time,is_video,commentcount,visit,is_gallery,images_count},idcat +"=?",
                new String[]{String.valueOf(idcategory)},null,null,null,null);





        if (c != null && c.getCount() > 0) {

            try {
                while (c.moveToNext()) {
                    Posts post = new Posts();
                    post.setid(c.getString(c.getColumnIndex(id)));
                    post.setcategory(c.getString(c.getColumnIndex(idcat)));
                    post.settitle(c.getString(c.getColumnIndex(title)));
                    post.setdes(c.getString(c.getColumnIndex(des)));
                    post.setImage(c.getString(c.getColumnIndex(image)));
                    post.setdate(c.getString(c.getColumnIndex(date)));
                    post.settime(c.getString(c.getColumnIndex(time)));
                    post.setisvideo(c.getString(c.getColumnIndex(is_video)));
                    post.setcomment_count(c.getString(c.getColumnIndex(commentcount)));
                    post.setvisit(c.getString(c.getColumnIndex(visit)));
                    post.setIsgallery(c.getString(c.getColumnIndex(is_gallery)));
                    post.setimages_count(c.getString(c.getColumnIndex(images_count)));
                    // list.add(c.getString(c.getColumnIndex("name")));
                    posts.add(post);
                }
            } finally {
                c.close();
            }

        }
        return posts;
    }
    public String checkuser(String keyid)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c=db.query(table,new String[]{id,title},id +"=?",
                new String[]{String.valueOf(keyid)},null,null,null,null);

        if(c!=null && c.getCount()>0) {
            c.moveToNext();
            return "true";



           // u = new user(Integer.parseInt(c.getString(0)), String.valueOf(c.getString(1)));
        }


        return "false";


    }

    public String getpath()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        return  String.valueOf(db.getPath());
    }











    public long addmanage_tile(String id_tile,String position,String imagetile,String itemnotif,String item)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cont=new ContentValues();
        cont.put(id,id_tile);
        cont.put(pos,position);
        cont.put(image,imagetile);
        cont.put(notif,itemnotif);
        cont.put(titletile,item);

        long todo_id =db.insert(table2,null,cont);
        return  todo_id;
    }
    public void delmanage_tile(String item)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(table2 ,titletile +"=?",new String[]{String.valueOf(item)});
        db.close();
    }

    public ArrayList<Posts>  gettilelist() {
        final ArrayList<String> list = new ArrayList<String>();
        List<Posts> posts = new ArrayList<>();
        String selectAllQuery = " SELECT * FROM " + table2;
        SQLiteDatabase sql = this.getReadableDatabase();
        // Cursor c = sql.rawQuery(selectAllQuery, null);
        Cursor c = sql.rawQuery(selectAllQuery,null);

        if (c != null && c.getCount() > 0) {

            try {
                while (c.moveToNext()) {
                    Posts post = new Posts();
                    list.add(c.getString(c.getColumnIndex(titletile)));
                    post.setid(c.getString(c.getColumnIndex(id)));
                    post.setposition(c.getString(c.getColumnIndex(pos)));
                    post.setImage(c.getString(c.getColumnIndex(image)));
                    post.setnotif(c.getString(c.getColumnIndex(notif)));
                    post.settitle(c.getString(c.getColumnIndex(titletile)));
                    posts.add(post);
                }
            } finally {
                c.close();
            }

        }
        else
        {
            return null;
            //list.add("all");
        }
        return (ArrayList<Posts>) posts;
    }
    public int getTilesCount() {
        String countQuery = "SELECT  * FROM " + table2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
     /*
    public void delmultiuser(ArrayList fav)
    { SQLiteDatabase db=this.getWritableDatabase();
        int itemCount=fav.size();
        for(int i=itemCount-1; i >= 0; i--){
            db.delete(table ,name +"=?",new String[]{String.valueOf(fav.get(i))});
        }

      //  db.delete(table ,name +"=?",new String[]{String.valueOf(fav.getName())});
        db.close();


        String selectAllQuery = "DELETE FROM"+ table +"WHERE EXISTS( SELECT * FROM positions WHERE positions.position_id = employees.position_id )";

        SQLiteDatabase sql = this.getReadableDatabase();
        // Cursor c = sql.rawQuery(selectAllQuery, null);
        Cursor c = sql.query(table, new String[]{name, status}, status + "=?",
                new String[]{String.valueOf(1)}, null, null, null, null);

        if (c != null && c.getCount() > 0) {

            try {
                while (c.moveToNext()) {

                }
            } finally {
                c.close();
            }

        }
    }
public Integer updateuser(Posts fav)
{
    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cont=new ContentValues();
    cont.put("name",fav.gettitle());
    cont.put("status",fav.getisFav());
    return db.update(table,cont,name +"=? ",new String[]{String.valueOf(fav.gettitle())});
}
public String checkuser(Posts fav)
{
    SQLiteDatabase db=this.getReadableDatabase();

    Cursor c=db.query(table,new String[]{name,status},name +"=?",
           new String[]{String.valueOf(fav.gettitle())},null,null,null,null);

    if(c!=null && c.getCount()>0) {
        c.moveToNext();
        return "true";



         u = new user(Integer.parseInt(c.getString(0)), String.valueOf(c.getString(1)));
    }


        return "false";


}
    public String getstatususer(Posts fav)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c=db.query(table,new String[]{name,status},name +"=?",
                new String[]{String.valueOf(fav.gettitle())},null,null,null,null);

        if(c!=null && c.getCount()>0) {
            c.moveToNext();


            if(c.getString(1).equals("1"))
            {

                return "1";
            }
            else  if(c.getString(1).equals("0")){
                return "0";


            }

         u = new user(Integer.parseInt(c.getString(0)), String.valueOf(c.getString(1)));
        }


        return "0";


    }*/
}

