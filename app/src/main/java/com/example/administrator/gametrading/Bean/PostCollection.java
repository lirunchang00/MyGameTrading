package com.example.administrator.gametrading.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PostCollection implements Parcelable {
    private int postId;
    private String author;
    private String title;
    //private String stutus;
    private String Date;
    private String repeat;
    private boolean essence;

    public PostCollection(){}

    protected PostCollection(Parcel in) {
        postId = in.readInt();
        author = in.readString();
        title = in.readString();
        Date = in.readString();
        repeat = in.readString();
        essence = in.readByte() != 0;
    }

    public static final Creator<PostCollection> CREATOR = new Creator<PostCollection>() {
        @Override
        public PostCollection createFromParcel(Parcel in) {
            PostCollection postCollection  =new PostCollection();
            postCollection.postId = in.readInt();
            postCollection.author = in.readString();
            postCollection.title = in.readString();
            postCollection.Date = in.readString();
            postCollection.repeat = in.readString();
            postCollection.essence = in.readByte() != 0;
            return postCollection;
        }

        @Override
        public PostCollection[] newArray(int size) {
            return new PostCollection[size];
        }
    };

    @Override
    public String toString() {
        return "PostCollection{" +
                "postId=" + postId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", Date='" + Date + '\'' +
                ", repeat='" + repeat + '\'' +
                ", essence=" + essence +
                '}';
    }

    public boolean isEssence() {
        return essence;
    }

    public void setEssence(boolean essence) {
        this.essence = essence;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(postId);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(Date);
        dest.writeString(repeat);
        dest.writeByte((byte) (essence ? 1 : 0));
    }
}
