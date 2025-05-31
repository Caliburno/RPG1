package org.example.demo1.factory;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.demo1.app.MainGame;
import org.example.demo1.entity.component.EnemyComponent;
import org.example.demo1.entity.Type;
import org.example.demo1.entity.component.HealthComponent;
import org.example.demo1.entity.component.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder(data)
                .viewWithBBox(new Rectangle(40, 40, Color.BLUE))
                .type(Type.ENEMY)
                .collidable()
                .with(new EnemyComponent(FXGL.<MainGame>getAppCast().getPlayer()))
                .build();
    }

    @Spawns("player")
    public Entity newPlayer (SpawnData data) {
        return entityBuilder(data)
                .type(Type.PLAYER)
                .viewWithBBox(new Rectangle(40, 40, Color.RED))
                .collidable()
                .with(new HealthComponent(100, 100))
                .with(new PlayerComponent())
                .build();

    }

}

