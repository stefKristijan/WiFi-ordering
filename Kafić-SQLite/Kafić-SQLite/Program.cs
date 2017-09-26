using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SQLite;

namespace Kafić_SQLite
{
    class Program
    {
        static void Main(string[] args)
        {
            SQLiteConnection.CreateFile("Caffe_bar_DB.sqlite");
            SQLiteConnection cb_dbConn;
            cb_dbConn = new SQLiteConnection("Data Source=Caffe_bar_DB.sqlite;Version=3;");
            cb_dbConn.Open();
            string Caffe_bar = "CREATE TABLE Caffe_bar (OIB VARCHAR(11), Naziv VARCHAR (50) NOT NULL, Adresa VARCHAR(100) NOT NULL, e_mail VARCHAR(50), br_mob VARCHAR(15), br_tel VARCHAR(15), web_str VARCHAR(50), CONSTRAINT cb_PK PRIMARY KEY(OIB))";
            SQLiteCommand cb_create = new SQLiteCommand(Caffe_bar, cb_dbConn);
            cb_create.ExecuteNonQuery();
            string insert_cb = "INSERT INTO Caffe_bar (OIB, Naziv, Adresa, e_mail, br_mob, br_tel, web_str) VALUES (\"54976321589\",\"Caffe Bar Završni\",\"Vijenac Ivana Meštrovića 159, 31000 Osijek\", \"cb_Zavrsni@gmail.com\", \"099/7845-734\", \"031/768-761\", \"cb_zavrsni.com\")";
            SQLiteCommand cb_insert = new SQLiteCommand(insert_cb, cb_dbConn);
            cb_insert.ExecuteNonQuery();
            string type = "CREATE TABLE Vrsta (sifra INT, Naziv VARCHAR(30) NOT NULL, OIB_cb VARCHAR(11), CONSTRAINT vrsta_pk PRIMARY KEY (sifra), CONSTRAINT vrsta_cb_fk FOREIGN KEY(OIB_cb) REFERENCES Caffe_bar(OIB))";
            SQLiteCommand type_create = new SQLiteCommand(type, cb_dbConn);
            type_create.ExecuteNonQuery();
            string insert_type = "INSERT INTO Vrsta SELECT 1 AS sifra, \"Topli napitci\" AS Naziv, \"54976321589\" AS OIB_cb UNION ALL SELECT 2,\"Bezalkoholna pića\",\"54976321589\" UNION ALL SELECT 3,\"Alkoholna pića\",\"54976321589\" UNION ALL SELECT 4,\"Bijela vina\",\"54976321589\" UNION ALL SELECT 5,\"Crna vina\",\"54976321589\" UNION ALL SELECT 6,\"Pivo\",\"54976321589\"";
            SQLiteCommand type_insert = new SQLiteCommand(insert_type, cb_dbConn);
            type_insert.ExecuteNonQuery();
            string drinks = "CREATE TABLE Pice (sifra_p INT, Naziv VARCHAR(30) NOT NULL, Kolicina DECIMAL (3,2) NOT NULL, Cijena DECIMAL (5,2) NOT NULL, sifra_vrste INT, CONSTRAINT p_pk PRIMARY KEY(sifra_p), CONSTRAINT p_vrsta_fk FOREIGN KEY(sifra_vrste) REFERENCES Vrsta(sifra))";
            SQLiteCommand drinks_create = new SQLiteCommand(drinks, cb_dbConn);
            drinks_create.ExecuteNonQuery();
            string insert_hdrinks = "INSERT INTO Pice SELECT 1 AS sifra_p, \"Espresso kava\" AS Naziv, 0.06 AS Kolicina, 6.00 AS Cijena, 1 AS sifra_vrste UNION ALL SELECT 2,\"Kava s mlijekom\",0.1,8.00,1 UNION ALL SELECT 3,\"Cappuccino\",0.1,8.00,1 UNION ALL SELECT 4,\"Bijela kava\",0.1,10.00,1 UNION ALL SELECT 5,\"Kava sa šlagom\",0.08,8.00,1 UNION ALL SELECT 6,\"Nescaffe\",0.1,10.00,1 UNION ALL SELECT 7,\"Kakao\",0.1,10.00,1 UNION ALL SELECT 8,\"Kakao sa šlagom\",0.1,12.00,1 UNION ALL SELECT 9,\"Topla čokolada\",0.15,10.00,1 UNION ALL SELECT 10,\"Topla čokolada sa šlagom\",0.15,12.00,1 UNION ALL SELECT 11,\"Čaj\",0.2,9.00,1";
            SQLiteCommand hdrinks_insert = new SQLiteCommand(insert_hdrinks, cb_dbConn);
            hdrinks_insert.ExecuteNonQuery();
            string insert_nadrinks = "INSERT INTO Pice SELECT 12 AS sifra_p, \"Coca-Cola\" AS Naziv, 0.25 AS Kolicina, 12.00 AS Cijena, 2 AS sifra_vrste UNION ALL SELECT 13,\"Fanta\", 0.25,12.00,2 UNION ALL SELECT 14,\"Sprite\",0.25,12.00,2 UNION ALL SELECT 15,\"Schweppes tonic/tang.\", 0.25,12.00,2 UNION ALL SELECT 16,\"Schweppes bitter lem.\", 0.25,12.00,2 UNION ALL SELECT 17,\"Cockta\", 0.25,12.00,2 UNION ALL SELECT 18,\"Orangina\", 0.25,14.00,2 UNION ALL SELECT 19,\"Red bull\", 0.25,18.00,2 UNION ALL SELECT 20,\"Ledeni čaj\", 0.25,12.00,2 UNION ALL SELECT 21,\"Prirodni sokovi\", 0.2,12.00,2 UNION ALL SELECT 22,\"Jamnica\", 0.25,6.00,2 UNION ALL SELECT 23,\"Jamnica\", 1,20.00,2 UNION ALL SELECT 24,\"Jana\", 0.33,7.00,2 UNION ALL SELECT 25,\"Limunada\", 0.2,15.00,2";
            SQLiteCommand nadrinks_insert = new SQLiteCommand(insert_nadrinks, cb_dbConn);
            nadrinks_insert.ExecuteNonQuery();
            string insert_adrinks = "INSERT INTO Pice SELECT 26 AS sifra_p, \"Jagermaister\" AS Naziv, 0.03 AS Kolicina, 12.00 AS Cijena, 3 AS sifra_vrste UNION ALL SELECT 27,\"Amaro\",0.03,8.00,3 UNION ALL SELECT 28,\"Lavov\",0.03,8.00,3 UNION ALL SELECT 29,\"Pelinkovac\",0.03,8.00,3 UNION ALL SELECT 30,\"Vigor vodka\",0.03,15.00,3 UNION ALL SELECT 31,\"Beefeater gin\",0.03,10.00,3 UNION ALL SELECT 322,\"Johnie Walker red label\",0.03,15.00,3 UNION ALL SELECT 32,\"Jack Daniel's\",0.03,15.00,3 UNION ALL SELECT 33,\"Stock\",0.03,10.00,3 UNION ALL SELECT 34,\"Vilijamovka\",0.03,15.00,3 UNION ALL SELECT 35,\"Travarica\",0.03,8.00,3 UNION ALL SELECT 36,\"Šljivovica\",0.03,8.00,3 UNION ALL SELECT 37,\"Lozovača\",0.03,8.00,3 UNION ALL SELECT 38,\"Rum\",0.03,15.00,3 UNION ALL SELECT 39,\"Brandy\",0.03,15.00,3";
            SQLiteCommand adrinks_insert = new SQLiteCommand(insert_adrinks, cb_dbConn);
            adrinks_insert.ExecuteNonQuery();
            string insert_wdrinks = "INSERT INTO Pice SELECT 40 AS sifra_p, \"Graševina Jurčević\" AS Naziv, 1 AS Kolicina, 80.00 AS Cijena, 4 AS sifra_vrste UNION ALL SELECT 41,\"Škrlet Jurčević\",1,80.00,4 UNION ALL SELECT 42,\"Pinot sivi Jurčević\",0.75,120.00,4 UNION ALL SELECT 43,\"Chardonnay Jurčević\",0.75,120.00,4";
            SQLiteCommand wdrinks_insert = new SQLiteCommand(insert_wdrinks, cb_dbConn);
            wdrinks_insert.ExecuteNonQuery();
            string insert_rdrinks = "INSERT INTO Pice SELECT 44 AS sifra_p, \"Plavac Mali Matota\" AS Naziv, 1 AS Kolicina, 80.00 AS Cijena, 5 AS sifra_vrste UNION ALL SELECT 45,\"Plavac Mali\",0.75,120.00,5 UNION ALL SELECT 46,\"Cabernet sauvignon\",0.75,120.00,5";
            SQLiteCommand rdrinks_insert = new SQLiteCommand(insert_rdrinks, cb_dbConn);
            rdrinks_insert.ExecuteNonQuery();
            string insert_bdrinks = "INSERT INTO Pice SELECT 47 AS sifra_p, \"Ožujsko\" AS Naziv, 0.5 AS Kolicina, 12.00 AS Cijena, 6 AS sifra_vrste UNION ALL SELECT 48,\"Stella artois\",0.33,13.00,6 UNION ALL SELECT 49,\"Stella artois/bezalk.\",0.33,13.00,6 UNION ALL SELECT 499,\"Beck's\",0.33,12.00,6 UNION ALL SELECT 50,\"Tomislav\",0.5,13.00,6 UNION ALL SELECT 51,\"Karlovačko\",0.5,12.00,6 UNION ALL SELECT 52,\"Pan\",0.33,12.00,6 UNION ALL SELECT 53,\"Osječko\",0.5,12.00,6 UNION ALL SELECT 54,\"Osječko crno\",0.5,13.00,6 UNION ALL SELECT 55,\"Radler\",0.5,13.00,6";
            SQLiteCommand bdrinks_insert = new SQLiteCommand(insert_bdrinks, cb_dbConn);
            bdrinks_insert.ExecuteNonQuery();
        }
    }
}
