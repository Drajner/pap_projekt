module edu.iipw.pap {
    requires transitive javafx.controls;
    requires transitive java.sql;
    requires javafx.fxml;
    exports edu.iipw.pap;
    opens edu.iipw.pap to javafx.fxml;
}
