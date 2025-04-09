module tp2_poo2.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;

    opens tp2_poo2.demo to javafx.fxml;
    exports tp2_poo2.demo;
}