module com.example.examenrecujorge {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires java.naming;
    requires java.sql;
    requires java.base;

    requires mysql.connector.java;
    requires jasperreports;

    opens com.example.examenrecujorge to javafx.fxml;
    exports com.example.examenrecujorge;
    opens com.example.examenrecujorge.model to javafx.fxml, org.hibernate.orm.core;
    exports com.example.examenrecujorge.model;
    opens com.example.examenrecujorge.controller to javafx.fxml;
    exports com.example.examenrecujorge.controller;
}

