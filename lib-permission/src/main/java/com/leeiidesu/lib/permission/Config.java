package com.leeiidesu.lib.permission;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by leeiidesu on 2017/7/14.
 */

public class Config implements Parcelable {
    String rationaleText;
    String rationaleCancelText;
    String rationaleConfirmText;


    String deniedText;
    String deniedCancelText;
    String deniedConfirmText;

    /**
     * use Config.Builder()
     */
    private Config(String rationaleText, String rationaleCancelText, String rationaleConfirmText, String deniedText, String deniedCancelText, String deniedConfirmText) {
        this.rationaleText = rationaleText;
        this.rationaleCancelText = rationaleCancelText;
        this.rationaleConfirmText = rationaleConfirmText;
        this.deniedText = deniedText;
        this.deniedCancelText = deniedCancelText;
        this.deniedConfirmText = deniedConfirmText;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Config config;

        private Builder() {

            config = new Config(null, "取消", "我知道了",
                    null, "取消", "去设置");
        }

        public Config build() {
            return config;
        }

        /**
         * 设置需求权限说明文本
         * 如果不设置该值，将不会在shouldShowRequestPermissionRationale的时候弹出对话框
         *
         * @param rationaleText 需求权限说明文本
         */
        public Builder setRationaleText(String rationaleText) {
            config.rationaleText = rationaleText;
            return this;
        }

        /**
         * 设置弹出需求权限说明对话框的 Negative 按钮的值
         *
         * @param rationaleCancelText Negative按钮的值 默认：取消
         */
        public Builder setRationaleCancelText(String rationaleCancelText) {
            config.rationaleCancelText = rationaleCancelText;
            return this;
        }

        /**
         * 设置弹出需求权限说明对话框的 Positive 按钮的值
         *
         * @param rationaleConfirmText Positive 按钮的值 默认：我知道了
         */
        public Builder setRationaleConfirmText(String rationaleConfirmText) {
            config.rationaleConfirmText = rationaleConfirmText;
            return this;
        }

        /**
         * 设置用户拒绝权限之后的文本信息
         *
         * @param deniedText 用户拒绝权限之后的文本信息 if null 不显示对话框
         */
        public Builder setDeniedText(String deniedText) {
            config.deniedText = deniedText;
            return this;
        }

        /**
         * 设置用户拒绝权限之后弹出的说明对话框的 Negative 按钮的值
         *
         * @param deniedCancelText Negative 按钮的值 默认：取消
         */
        public Builder setDeniedCancelText(String deniedCancelText) {
            config.deniedCancelText = deniedCancelText;
            return this;
        }

        /**
         * 设置用户拒绝权限之后弹出的说明对话框的 Positive 按钮的值
         *
         * @param deniedConfirmText Positive 按钮的值 默认：去设置
         */
        public Builder setDeniedConfirmText(String deniedConfirmText) {
            config.deniedConfirmText = deniedConfirmText;
            return this;
        }
    }
}
