package com.example.Kafeshahrpackage.Kafeshahr;

import org.json.JSONArray;

/**
 * Created by malihe on 5/15/2018.
 */

public class Posts {
    String id;
    String title;
    String des;
    String date;
    String time;
    String isvideo;
    String isgallery;
    String isReply;
    String isFav;
    String comment_count;
    String visit;
    String pin;
    String pos;
    String category;
    JSONArray replies;
    String images_count;
    String notif;
    private String image;

    public static final int comment_card=0;
    public static final int commenttocomment_card=1;




    public String text;
    public int type;
    public Posts()
    {
      /*  this.name=name;*/

    }
    public Posts(int type)
    {
        this.type=type;


    }

    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }


    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


    public void settitle(String Title)

    {
        this.title=Title;
    }
    public String gettitle()
    {
        return this.title;
    }


    public void setdes(String des)

    {
        this.des=des;
    }
    public String getdes()
    {
        return this.des;
    }


    public void setdate(String date)

    {
        this.date=date;
    }
    public String getdate()
    {
        return this.date;
    }


    public void settime(String time)

    {
        this.time=time;
    }
    public String gettime()
    {
        return this.time;
    }


    public void setisvideo(String isvideo)

    {
        this.isvideo=isvideo;
    }
    public String getisvideo()
    {
        return this.isvideo;
    }


    public void setreplies(JSONArray replies)

    {
        this.replies=replies;
    }
    public JSONArray getreplies()
    {
        return this.replies;
    }


    public void setisReply(String isReply)

    {
        this.isReply=isReply;
    }
    public String getisReply()
    {
        return this.isReply;
    }


    public void setisFav(String isFav)

    {
        this.isFav=isFav;
    }
    public String getisFav()
    {
        return this.isFav;
    }



    public void setvisit(String visit)

    {
        this.visit=visit;
    }
    public String getvisit()
    {
        return this.visit;
    }


    public void setcategory(String category)

    {
        this.category=category;
    }
    public String getcategory()
    {
        return this.category;
    }


    public void setIsgallery(String isgallery)

    {
        this.isgallery=isgallery;
    }
    public String getIsgallery()
    {
        return this.isgallery;
    }


    public void setimages_count(String images_count)

    {
        this.images_count=images_count;
    }
    public String getimages_count()
    {
        return this.images_count;
    }


    public void setnotif(String notif)

    {
        this.notif=notif;
    }
    public String getnotif()
    {
        return this.notif;
    }


    public String getispin()
    {
        return this.pin;
    }
    public void setispin(String pin)

    {
        this.pin=pin;
    }


    public void setcomment_count(String comment_count)

    {
        this.comment_count=comment_count;
    }
    public String getcomment_count()
    {
        return this.comment_count;
    }


    public void setposition(String position)

    {
        this.pos=position;
    }
    public String getposition()
    {
        return this.pos;
    }
}
