package in.arpaul.sampleexprecycler.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aritrapal on 18/07/17.
 */

public class ParcelDO extends BaseDO implements Parcelable {

    public static final String SORTED = "SORTED";
    public static final String UNSORTED = "UNSORTED";

    private String _id = "";
    private String parcel_id = "";
    private String cust_name = "";
    private String custNumber = "";

    public ParcelDO() {
    }

    public ParcelDO(String _id, String parcel_id, String cust_name, String custNumber) {
        this._id = _id;
        this.parcel_id = parcel_id;
        this.cust_name = cust_name;
        this.custNumber = custNumber;
    }

    public void setID(String _id) {
        this._id = _id;
    }

    public String getID() {
        return _id;
    }

    public void setParcelID(String parcel_id) {
        this.parcel_id = parcel_id;
    }

    public String getParcelID() {
        return parcel_id;
    }

    public void setCustName(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCustName() {
        return cust_name;
    }

    public void setCustNumber(String custNumber) {
        this.custNumber = custNumber;
    }

    public String getCustNumber() {
        return custNumber;
    }

    public ParcelDO(Parcel parcel) {
        _id = parcel.readString();
        parcel_id = parcel.readString();
        cust_name = parcel.readString();
        custNumber = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(_id);
        parcel.writeString(parcel_id);
        parcel.writeString(cust_name);
        parcel.writeString(custNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelDO> CREATOR = new Creator<ParcelDO>() {
        @Override
        public ParcelDO createFromParcel(Parcel in) {
            return new ParcelDO(in);
        }

        @Override
        public ParcelDO[] newArray(int size) {
            return new ParcelDO[size];
        }
    };
}
