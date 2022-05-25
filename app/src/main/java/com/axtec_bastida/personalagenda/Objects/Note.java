package com.axtec_bastida.personalagenda.Objects;

public class Note {

    //Note Attributes
    String note_id, uid_user,user_mail,actualdateandtime,title,description,note_date,condition;

    public Note(){

    }

    public Note(String note_id, String uid_user, String user_mail, String actualdateandtime, String title, String description, String note_date, String condition) {
        this.note_id = note_id;
        this.uid_user = uid_user;
        this.user_mail = user_mail;
        this.actualdateandtime = actualdateandtime;
        this.title = title;
        this.description = description;
        this.note_date = note_date;
        this.condition = condition;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getUid_user() {
        return uid_user;
    }

    public void setUid_user(String uid_user) {
        this.uid_user = uid_user;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getActualdateandtime() {
        return actualdateandtime;
    }

    public void setActualdateandtime(String actualdateandtime) {
        this.actualdateandtime = actualdateandtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
