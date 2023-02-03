package com.cargo.model.entity;

import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * The Cargo class represents the cargo that is being transported.
 * Delivery Status can be TRANSIT, DELIVERED, RECEIVED, DECLINED.
 * Invoice Status can be PAYED, PENDING.
 */
public class Cargo extends Model implements Serializable {
    private int id;
    private String type;
    private User user;
    private String receiverFullname;
    private Branch departureBranch;
    private Branch destinationBranch;
    private int price;
    private int weight;
    private int length;
    private int height;
    private int width;
    private Timestamp creationDate;
    private Timestamp deliveryDate;
    private DeliveryStatus deliveryStatus;
    private InvoiceStatus invoiceStatus;

    public Cargo(int id, String type, User user, String receiverFullname, Branch departureBranch, Branch destinationBranch, int price, int weight, int length, int height, int width, Timestamp creationDate, Timestamp deliveryDate, DeliveryStatus deliveryStatus, InvoiceStatus invoiceStatus) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.receiverFullname = receiverFullname;
        this.departureBranch = departureBranch;
        this.destinationBranch = destinationBranch;
        this.price = price;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.width = width;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
        this.invoiceStatus = invoiceStatus;
    }

    public Cargo() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiverFullname() {
        return receiverFullname;
    }

    public void setReceiverFullname(String receiverFullname) {
        this.receiverFullname = receiverFullname;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Branch getDepartureBranch() {
        return departureBranch;
    }

    public void setDepartureBranch(Branch departureBranch) {
        this.departureBranch = departureBranch;
    }

    public Branch getDestinationBranch() {
        return destinationBranch;
    }

    public void setDestinationBranch(Branch destinationBranch) {
        this.destinationBranch = destinationBranch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return id == cargo.id && price == cargo.price && weight == cargo.weight && length == cargo.length && height == cargo.height && width == cargo.width && Objects.equals(type, cargo.type) && Objects.equals(user, cargo.user) && Objects.equals(receiverFullname, cargo.receiverFullname) && Objects.equals(departureBranch, cargo.departureBranch) && Objects.equals(destinationBranch, cargo.destinationBranch) && Objects.equals(creationDate, cargo.creationDate) && Objects.equals(deliveryDate, cargo.deliveryDate) && deliveryStatus == cargo.deliveryStatus && invoiceStatus == cargo.invoiceStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, user, receiverFullname, departureBranch, destinationBranch, price, weight, length, height, width, creationDate, deliveryDate, deliveryStatus, invoiceStatus);
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", user=" + user +
                ", receiverFullname='" + receiverFullname + '\'' +
                ", departureBranch=" + departureBranch +
                ", destinationBranch=" + destinationBranch +
                ", price=" + price +
                ", weight=" + weight +
                ", length=" + length +
                ", height=" + height +
                ", width=" + width +
                ", creationDate=" + creationDate +
                ", deliveryDate=" + deliveryDate +
                ", deliveryStatus=" + deliveryStatus +
                ", invoiceStatus=" + invoiceStatus +
                '}';
    }
}
