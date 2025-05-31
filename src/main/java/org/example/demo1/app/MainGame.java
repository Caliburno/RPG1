package org.example.demo1.app;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.text.Text;
import org.example.demo1.entity.component.PlayerComponent;
import org.example.demo1.factory.EntityFactory;
import org.example.demo1.entity.component.HealthComponent;
import org.example.demo1.entity.Type;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class MainGame extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Mi Primer Juego FXGL");
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new EntityFactory());

        player = FXGL.spawn("player", 300, 300);

        FXGL.spawn("enemy", 800, 300);
        FXGL.spawn("enemy", 900, 200);
        FXGL.spawn("enemy", 1000, 400);
    }

    public Entity getPlayer(){
        return player;
    }

    @Override
    protected void  initInput() {

        getInput().addAction(new UserAction("Click Move") {
            @Override
            protected void onActionBegin() {
                Point2D worldPoint = getInput().getMousePositionWorld();
                player.getComponent(PlayerComponent.class).moveTo(worldPoint.getX(), worldPoint.getY());
            }
        }, MouseButton.PRIMARY);
    }

    @Override
    protected void initUI() {
        Rectangle bgBar = new Rectangle(104, 24, Color.DARKGRAY);
        Rectangle hpBar = new Rectangle(100, 20, Color.LIMEGREEN);
        Text hpTxt = new Text();

        hpBar.setTranslateX(12);
        hpBar.setTranslateY(12);
        bgBar.setTranslateY(10);
        bgBar.setTranslateX(10);
        hpTxt.setTranslateX(30);
        hpTxt.setTranslateY(26);
        hpTxt.setFill(Color.BLACK);
        hpTxt.setFont(Font.font(14));

        FXGL.getGameScene().addUINode(bgBar);
        FXGL.getGameScene().addUINode(hpBar);
        FXGL.getGameScene().addUINode(hpTxt);


        HealthComponent hp = player.getComponent(HealthComponent.class);

        FXGL.getGameTimer().runAtInterval(() -> {
            double ratio = hp.getHp() / (double) hp.getMaxHp();
            hpBar.setWidth(100 * ratio);
            hpTxt.setText(hp.getHp() + " / " + hp.getMaxHp());
        }, Duration.millis(100));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
