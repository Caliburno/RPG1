package org.example.demo1.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.demo1.app.MainGame;
import org.example.demo1.entity.component.EnemyComponent;
import org.example.demo1.entity.Tipo;

public class EnemyFactory implements EntityFactory {

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder(data)
                .viewWithBBox(new Rectangle(40, 40, Color.BLUE))
                .type(Tipo.ENEMY)
                .collidable()
                .with(new EnemyComponent(FXGL.<MainGame>getAppCast().getPlayer()))
                .build();
    }
}

