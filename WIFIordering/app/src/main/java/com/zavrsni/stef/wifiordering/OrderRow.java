package com.zavrsni.stef.wifiordering;

/**
 * Created by Stef on 16.6.2016..
 */
public class OrderRow implements java.io.Serializable{

    private int id_drink;
    private int id_table;
    private int amount;

    public OrderRow(int id,int it, int a){
        id_table=it;
        id_drink=id;
        amount=a;
    }
    public int getId_drink() {
        return id_drink;
    }

    public void setId_drink(int id_drink) {
        this.id_drink = id_drink;
    }

    public int getId_table() {
        return id_table;
    }

    public void setId_table(int id_table) {
        this.id_table = id_table;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return this.amount+"x\t"+this.id_drink+"l\t"+this.id_table+"kn";
    }
}
