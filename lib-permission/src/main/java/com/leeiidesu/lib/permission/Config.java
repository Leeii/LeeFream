package com.leeiidesu.lib.permission;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leeiidesu on 2017/7/14.
 */

class Config implements Parcelable {
    String rationaleText;
    String rationaleCancelText;
    String rationaleConfirmText;


    String deniedText;
    String deniedCancelText;
    String deniedConfirmText;


    Config() {

    }


    protected Config(Parcel in) {
        rationaleText = in.readString();
        rationaleCancelText = in.readString();
        rationaleConfirmText = in.readString();
        deniedText = in.readString();
        deniedCancelText = in.readString();
        deniedConfirmText = in.readString();
    }

    public static final Creator<Config> CREATOR = new Creator<Config>() {
        @Override
        public Config createFromParcel(Parcel in) {
            return new Config(in);
        }

        @Override
        public Config[] newArray(int size) {
            return new Config[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rationaleText);
        dest.writeString(rationaleCancelText);
        dest.writeString(rationaleConfirmText);
        dest.writeString(deniedText);
        dest.writeString(deniedCancelText);
        dest.writeString(deniedConfirmText);
    }
}
