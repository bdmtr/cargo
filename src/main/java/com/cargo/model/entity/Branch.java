package com.cargo.model.entity;

import com.cargo.model.enums.City;

import java.io.Serializable;
import java.util.Objects;

public class Branch extends  Model implements Serializable {
    private int id;
    private City city;
    private String address;

    public Branch(int id, City city, String address) {
        this.id = id;
        this.city = city;
        this.address = address;
    }

    public Branch(City city, String address) {
        this.city = city;
        this.address = address;
    }

    public Branch() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return id == branch.id && city == branch.city && address.equals(branch.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, address);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", city=" + city +
                ", address='" + address + '\'' +
                '}';
    }
}
