package com.zavrsni.stef.wifiordering;

import android.app.ActionBar;
import android.content.Intent;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        orderedDrinks = (List<Drinks>) getIntent().getSerializableExtra("ordered");
        setListView(orderedDrinks);
        getPrice(orderedDrinks);
    }

    private static List<Drinks> orderedDrinks = new ArrayList<>();
    private List<OrderRow> OR = new ArrayList<OrderRow>();

    public void onClickOrder(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                createOrder();
                makeOrder();
            }
        }).start();
    }

   /* public void onClickDelete(View view){
        orderedDrinks.clear();
        OR.clear();
        ListView lv = (ListView) findViewById(R.id.lvOrdered);
        setListView(orderedDrinks);
        Button del = (Button) findViewById(R.id.btnDeleteAll);
        if (del != null) {
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (getApplicationContext(),CartActivity.class);
                    intent.putExtra("ordered", (Serializable) OR);
                    startActivity(intent);
                }
            });
        }
    }*/

    private void createOrder() {
        for (int i = 0; i < orderedDrinks.size(); i++) {
            OrderRow or1 = new OrderRow(0, 0, 1);
            boolean exists = false;
            if (OR.isEmpty()){
                EditText tb = (EditText) findViewById(R.id.tb_stol);
                or1.setId_table(Integer.parseInt((tb.getText().toString())));
            }else {
                for (int j = 0; j < OR.size(); j++) {
                    if (OR.get(j).getId_drink() == orderedDrinks.get(i).getId()) {
                        exists = true;
                        OR.get(j).setAmount(OR.get(j).getAmount() + 1);
                    }
                }
            }
            if (exists == false) {
                EditText tb = (EditText) findViewById(R.id.tb_stol);
                or1.setId_table(Integer.parseInt((tb.getText().toString())));
                or1.setId_drink(orderedDrinks.get(i).getId());
                OR.add(or1);
            }
        }
    }

    private void makeOrder() {
        try {
            Connection c = getMySQLConnection();
            PreparedStatement stat = c.prepareStatement("insert into narudzba values (?,?,?)");
            for (int i = 0; i < OR.size(); i++) {
                stat.setInt(1, OR.get(i).getId_drink());
                stat.setInt(2, OR.get(i).getId_table());
                stat.setInt(3, OR.get(i).getAmount());
                stat.execute();
            }
            stat.close();
            c.close();
            OR.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getMySQLConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://192.168.1.106:3306/caffebardb", "guest", "guest");
    }


    private void getPrice(List<Drinks> drinks) {
        double price = 0;
        for (int i = 0; i < drinks.size(); i++) {
            price = price + drinks.get(i).getPrice();
        }
        TextView tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvPrice.setText(tvPrice.getText() + " " + price + " kn ");
    }

    private void setListView(List<Drinks> drinks) {
        ArrayAdapter<Drinks> adapter = new ArrayAdapter<Drinks>(this, R.layout.drinks_tv, drinks);
        ListView lv_drinks = (ListView) findViewById(R.id.lvOrdered);
        lv_drinks.setAdapter(adapter);
    }

}