package com.dyc.test.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by win7 on 2016/7/23.
 */
public class TestEntity implements Parcelable{
    public String  name;
    public String value;

    public TestEntity(){

    }
    public TestEntity(Parcel in) {
        name = in.readString();
        value = in.readString();
    }

    public static final Creator<TestEntity> CREATOR = new Creator<TestEntity>() {
        @Override
        public TestEntity createFromParcel(Parcel in) {

            return new TestEntity(in);
        }

        @Override
        public TestEntity[] newArray(int size) {
            return new TestEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(value);
    }
}
