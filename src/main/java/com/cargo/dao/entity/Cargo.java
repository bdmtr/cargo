package com.cargo.dao.entity;

import com.cargo.dao.enums.DeliveryStatus;
import com.cargo.dao.enums.InvoiceStatus;

import java.sql.Timestamp;
import java.util.Objects;

public class Cargo extends Model {
    private int id;
    private String type;
    private int userId;
    private String receiverFullname;
    private int departureBranchId;
    private int destinationBranchId;
    private int price;
    private int weight;
    private int length;
    private int height;
    private int width;
    private Timestamp creationDate;
    private Timestamp deliveryDate;
    private DeliveryStatus deliveryStatus;
    private InvoiceStatus invoiceStatus;


    public Cargo(int id, String type, int userId, String receiverFullname, int departureBranchId,
                 int destinationBranchId, int price, int weight, int length, int height, int width,
                 Timestamp creationDate, Timestamp deliveryDate, DeliveryStatus deliveryStatus, InvoiceStatus invoiceStatus) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.receiverFullname = receiverFullname;
        this.departureBranchId = departureBranchId;
        this.destinationBranchId = destinationBranchId;
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

    public Cargo(String type, int userId, String receiverFullname, int departureBranchId, int destinationBranchId,
                 int price, int weight, int length, int height, int width, Timestamp creationDate,
                 Timestamp deliveryDate, DeliveryStatus deliveryStatus, InvoiceStatus invoiceStatus) {
        this.type = type;
        this.userId = userId;
        this.receiverFullname = receiverFullname;
        this.departureBranchId = departureBranchId;
        this.destinationBranchId = destinationBranchId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReceiverFullname() {
        return receiverFullname;
    }

    public void setReceiverFullname(String receiverFullname) {
        this.receiverFullname = receiverFullname;
    }

    public int getDepartureBranchId() {
        return departureBranchId;
    }

    public void setDepartureBranchId(int departureBranchId) {
        this.departureBranchId = departureBranchId;
    }

    public int getDestinationBranchId() {
        return destinationBranchId;
    }

    public void setDestinationBranchId(int destinationBranchId) {
        this.destinationBranchId = destinationBranchId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return id == cargo.id && userId == cargo.userId && departureBranchId == cargo.departureBranchId && destinationBranchId == cargo.destinationBranchId && weight == cargo.weight && length == cargo.length && height == cargo.height && width == cargo.width && type.equals(cargo.type) && receiverFullname.equals(cargo.receiverFullname) && price == (cargo.price) && creationDate.equals(cargo.creationDate) && deliveryDate.equals(cargo.deliveryDate) && deliveryStatus == cargo.deliveryStatus && invoiceStatus == cargo.invoiceStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, userId, receiverFullname, departureBranchId, destinationBranchId, price, weight, length, height, width, creationDate, deliveryDate, deliveryStatus, invoiceStatus);
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                ", receiverFullname='" + receiverFullname + '\'' +
                ", departureId=" + departureBranchId +
                ", destinationId=" + destinationBranchId +
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
