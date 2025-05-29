package org.example.demo1;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.text.Text;


public class MainGame extends GameApplication {

    private Entity player;
    private double targetX, targetY;
    private boolean moving = false;
    private final double SPEED = 150;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Mi Primer Juego FXGL");
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {

        player = FXGL.entityBuilder()
                .at(300, 300)
                .viewWithBBox(new Rectangle(40, 40, Color.RED))
                .type(Tipo.PLAYER)
                .collidable()
                .with(new HealthComponent(100, 100))
                .buildAndAttach();

        FXGL.getGameWorld().addEntityFactory(new EnemyFactory());
        FXGL.spawn("enemy", 800, 300);
        FXGL.spawn("enemy", 900, 200);
        FXGL.spawn("enemy", 1000, 400);

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

    public Entity getPlayer(){
        return player;
    }

    @Override
    protected void  initInput() {
        FXGL.onBtnDown(MouseButton.PRIMARY, () -> {
            Point2D click = FXGL.getInput().getMousePositionWorld();
            targetX = click.getX();
            targetY = click.getY();
            moving = true;
        });
    }

    @Override
    protected void onUpdate(double tpf) {
        if (moving) {
            Point2D dir = new Point2D(targetX, targetY)
                    .subtract(player.getCenter())
                    .normalize();

            player.translate(dir.multiply((SPEED * tpf)));

            if (player.getCenter().distance(targetX, targetY) < 4) {
                moving = false;
            }
        }
    }

    private void onDeath() {
        FXGL.getDialogService().showMessageBox("Game Over", () -> {
            FXGL.getGameController().startNewGame();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
