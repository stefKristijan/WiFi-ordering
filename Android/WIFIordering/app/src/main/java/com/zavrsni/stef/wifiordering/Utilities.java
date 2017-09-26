package com.zavrsni.stef.wifiordering;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Stef on 15.6.2016..
 */
public class Utilities {
    public static void close(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                //do nothing
            }
        }
    }

    public static void close(Statement stat) {
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                //do nothing
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                //do nothing
            }
        }
    }
}
