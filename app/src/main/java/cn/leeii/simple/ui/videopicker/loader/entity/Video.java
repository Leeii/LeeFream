package cn.leeii.simple.ui.videopicker.loader.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * _ Video _ Created by dgg on 2017/7/26.
 */

public class Video implements Parcelable {
    private int id;
    private long duration;
    private String path;
    private long size;

    public Video(int id, String path, long duration, long size) {
        this.id = id;
        this.path = path;
        this.duration = duration;
        this.size = size;
    }

    public Video() {
    }

    protected Video(Parcel in) {
        id = in.readInt();
        duration = in.readLong();
        path = in.readString();
        size = in.readLong();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video)) return false;

        Video video = (Video) o;

        return id == video.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(duration);
        dest.writeString(path);
        dest.writeLong(size);
    }
}
