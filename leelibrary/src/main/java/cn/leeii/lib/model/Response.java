package cn.leeii.lib.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lee on 2016/12/14.
 */

public class Response implements Parcelable {

    public int returnCode;

    public String returnMsg;

    public String returnData;

    public String returnOther;

    public Response() {
    }

    protected Response(Parcel in) {
        returnCode = in.readInt();
        returnMsg = in.readString();
        returnData = in.readString();
        returnOther = in.readString();
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(returnCode);
        parcel.writeString(returnMsg);
        parcel.writeString(returnData);
        parcel.writeString(returnOther);
    }
}
