package com.example.administrator.gametrading.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Forum implements Parcelable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String author;
    private String time;
    private String repeat;
    private String content;
    private String image;
    private String head;
    private int active;
    private int awesome;
    private String floor;
    private boolean essence;
    private int postid;

    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", repeat='" + repeat + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", head='" + head + '\'' +
                ", active='" + active + '\'' +
                ", like=" + awesome +
                ", floor='" + floor + '\'' +
                ", essence=" + essence +
                ", postid=" + postid +
                '}';
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public Forum(){}

    public Forum(String title,String author,String time, String repeat){
        this.title = title;
        this.author  = author;
        this.time = time;
        this.repeat = repeat;
    }

    protected Forum(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        time = in.readString();
        repeat = in.readString();
        content = in.readString();
        image = in.readString();
        head = in.readString();
        active = in.readInt();
        awesome = in.readInt();
        floor = in.readString();
        postid = in.readByte();
        essence = in.readByte() != 0;
    }

    public static final Creator<Forum> CREATOR = new Creator<Forum>() {
        @Override
        public Forum createFromParcel(Parcel in) {
            Forum forum = new Forum();
            forum.id = in.readInt();
            forum.title = in.readString();
            forum.author = in.readString();
            forum.time = in.readString();
            forum.repeat = in.readString();
            forum.content = in.readString();
            forum.image = in.readString();
            forum.head = in.readString();
            forum.active = in.readInt();
            forum.awesome = in.readInt();
            forum.floor = in.readString();
            forum.postid = in.readInt();
            forum.essence = in.readByte() != 0;
            return forum;
        }

        @Override
        public Forum[] newArray(int size) {
            return new Forum[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAwesome() {
        return awesome;
    }

    public void setAwesome(int awesome) {
        this.awesome = awesome;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public boolean isEssence() {
        return essence;
    }

    public void setEssence(boolean essence) {
        this.essence = essence;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(time);
        dest.writeString(repeat);
        dest.writeString(content);
        dest.writeString(image);
        dest.writeString(head);
        dest.writeInt(active);
        dest.writeInt(awesome);
        dest.writeString(floor);
        dest.writeInt(postid);
        dest.writeByte((byte) (essence ? 1 : 0));
    }


}
