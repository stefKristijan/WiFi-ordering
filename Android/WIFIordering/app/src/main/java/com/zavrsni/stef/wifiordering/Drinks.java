package com.zavrsni.stef.wifiordering;

/**
 * Created by Stef on 15.6.2016..
 */
public class Drinks implements java.io.Serializable {
    private int id;
    private String name;
    private double amount;
    private double price;
    private int id_type;

    public Drinks(int i,String n, double a, double p, int it){
        id=i;
        name=n; price=p; id_type=it;
    }
    public Drinks(){
        super();
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    @Override
    public String toString(){
        return this.name+"\t"+this.amount+"l\t"+this.price+"kn";
    }
}
