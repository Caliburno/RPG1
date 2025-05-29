package org.example.demo1.ui;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.demo1.entity.component.HealthComponent;

public class HealthBar extends HBox {

    public static final double WIDTH = 200;
    public static final double HEIGHT =20;

    public HealthBar(HealthComponent hp, Entity player) {
        Rectangle background = new Rectangle(WIDTH,HEIGHT, Color.DARKGRAY);
        Rectangle fill = new Rectangle(WIDTH, HEIGHT, Color.LIMEGREEN);

       //hp = player.getComponent(hp);
    }

}
