package org.example.demo1;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

class EnemyComponent extends Component {

    private final Entity objetivo;
    private final double speed = 100;
    private double attackTimer = 0;
    private final double attackCooldown = 1.5;
    private final double attackRange = 50;


    public EnemyComponent(Entity objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public void onUpdate(double tpf) {
        if (!objetivo.isActive())
            return;

        Point2D dir = objetivo.getCenter()
                .subtract(entity.getCenter())
                .normalize();
        entity.translate(dir.multiply(speed * tpf));

        double distance = entity.getCenter().distance(objetivo.getCenter());
        attackTimer += tpf;
        if (distance < attackRange && attackTimer >= attackCooldown) {
            attackTimer = 0;

            HealthComponent hp = objetivo.getComponent(HealthComponent.class);
            if (hp  != null) {
                hp.damage(3);
                System.out.println("+10 damage");
                System.out.println("Current health: " + hp.getHp());
            }
        }
    }
}
