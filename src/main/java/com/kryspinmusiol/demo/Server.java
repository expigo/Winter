package com.kryspinmusiol.demo;

import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {

        try {
            org.h2.tools.Server.main();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
