package com.zavrsni.stef.wifiordering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final int REQUEST_CODE_100 = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first();
        //Prije stvaranja intent-a potrebno je u AndroidManifest dodati novu aktivnost (u ovom slučaju CartActivity)
        Button cart = (Button) findViewById(R.id.btnCart);
        if (cart != null) {
            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (getApplicationContext(),CartActivity.class);
                    intent.putExtra("ordered", (Serializable) ordered);
                    startActivityForResult(intent,REQUEST_CODE_100);
                }
            });
        }
    }
    private List<Drinks> drinks = new ArrayList<Drinks>();
    private List<Drinks> ordered = new ArrayList<Drinks>();

    private void first(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection c = getMySQLConnection();
                    ResultSet rs = getDrinks(c);
                    getList(rs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_100){
            if(resultCode==RESULT_OK){
                ordered.clear();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View view){
        setListView();
        Button btn = (Button) findViewById(R.id.button);
        btn.setEnabled(false);
    }


    private void getList(ResultSet rs) throws Exception{
        while(rs.next()) {
            Drinks d = new Drinks();
            d.setId(rs.getInt("id"));
            d.setName(rs.getString("naziv"));
            d.setAmount(rs.getDouble("kolicina"));
            d.setPrice(rs.getDouble("cijena"));
            d.setId_type(rs.getInt("id_vrsta"));
            drinks.add(d);
        }
    }

    private static Connection getMySQLConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://192.168.1.106:3306/caffebardb","guest","guest");
    }

    private static ResultSet getResultSet(Connection c, String sql) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Statement stm = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        return stm.executeQuery(sql);
    }
    private static ResultSet getDrinks(Connection c) throws Exception{
        return getResultSet(c, "select * from pice");
    }



    private void setListView() {
        ArrayAdapter<Drinks> adapter = new ArrayAdapter<Drinks>(this, R.layout.drinks_tv, drinks);
        ListView lv_drinks = (ListView) findViewById(R.id.lv_Drinks);
        lv_drinks.setAdapter(adapter);
        lv_drinks.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView) view;
        ordered.add(drinks.get(position));
        String message = "Dodao si " + drinks.get(position).getName() + " u košaricu!";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Button Cart = (Button) findViewById(R.id.btnCart);
        Cart.setEnabled(true);
    }

 /*public static void insertCB(Connection c)throws Exception{
        PreparedStatement st = c.prepareStatement("insert into caffebar values(?,?,?,?,?,?,?)");
        st.setString(1,"0245742533");
        st.setString(2,"CaffeBar Index");
        st.setString(3,"Hrvatskih branitelja 4");
        st.setString(4,"index@gmail.com");
        st.setString(5,"");
        st.setString(6,"");
        st.setString(7,"");
        st.execute();
        st.close();
    }
*/

/*  if (lv_drinks != null) {
            lv_drinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tv = (TextView) view;
                    Order o = new Order();
                    o.addItem(drinks.get(position));
                    String message = "Dodao si " + ((TextView) view).getText() + " u košaricu!";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            });
        }*/
/*
    public void getDrinks(){
        String query = "SELECT * FROM caffebardb.pice;";
        String url ="jdbc:mysql://192.168.1.106:3306";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(url,"guest","guest");
         Statement stat = c.createStatement();
            ResultSet rs=stat.executeQuery(query);
            while (rs.next()) {
                Drinks d = new Drinks();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("naziv"));
                d.setAmount(rs.getDouble("kolicina"));
                d.setPrice(rs.getDouble("cijena"));
                d.setId_type(rs.getInt("id_vrsta"));
                drinks.add(d);
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage("This is an alert with no consequence");
                dlgAlert.setTitle(d.toString());
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
            Utilities.close(c);
            Utilities.close(stat);
            Utilities.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e)
        {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }





    public void onClick(View view){
        Log.d("tag","mag");
              getDrinks();
setListView();
    }


    protected void insert(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/caffebardb";
            Connection c = DriverManager.getConnection(url,"guest","guest");
            PreparedStatement st = c.prepareStatement("insert into  values(?,?,?,?,?,?,?)");
            st.setString(1,"0245742533");
            st.setString(2,"CaffeBar Index");
            st.setString(3,"Hrvatskih branitelja 4");
            st.setString(4,"index@gmail.com");
            st.setString(5,"");
            st.setString(6,"");
            st.setString(7,"");
            st.execute();
            st.close();
            c.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
