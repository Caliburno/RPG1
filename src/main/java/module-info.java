module org.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens org.example.demo1 to javafx.fxml;
    exports org.example.demo1;
    opens org.example.demo1.app to javafx.fxml;
    exports org.example.demo1.app;
    exports org.example.demo1.entity;
    opens org.example.demo1.entity to javafx.fxml;
    exports org.example.demo1.entity.component;
    opens org.example.demo1.entity.component to javafx.fxml;
    exports org.example.demo1.factory;
    opens org.example.demo1.factory to javafx.fxml;
}