module edu.iipw.pap {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.sql;
    exports edu.iipw.pap;
    opens edu.iipw.pap;
}
