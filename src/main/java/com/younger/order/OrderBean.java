package com.younger.order;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

public class OrderBean implements WritableComparable<OrderBean>, Serializable {
    public String orderId;
    private String userId;;
    private String pdtName;
    private float price;
    private int number;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPdtName() {
        return pdtName;
    }

    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getAmountFee() {
        return amountFee;
    }

    public void setAmountFee(float amountFee) {
        this.amountFee = amountFee;
    }

    private float amountFee;

    public void set (String orderId, String userId, String pdtName, float price, int number) {
        this.orderId = orderId;
        this.userId = userId;
        this.pdtName = pdtName;
        this.price = price;
        this.number = number;
        this.amountFee = price*number;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", pdtName='" + pdtName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", amountFee=" + amountFee +
                '}';
    }

    @Override
    public int compareTo(OrderBean o) {
        return Float.compare(o.getAmountFee(), this.getAmountFee())==0?this.pdtName.compareTo(o.getPdtName()):Float.compare(o.getAmountFee(), this.getAmountFee());
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.orderId);
        out.writeUTF(this.userId);
        out.writeUTF(this.pdtName);
        out.writeFloat(this.price);
        out.writeInt(this.number);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readUTF();
        this.userId = in.readUTF();
        this.pdtName = in.readUTF();
        this.price = in.readFloat();
        this.number = in.readInt();
        this.amountFee = this.price * this.number;
    }
}
