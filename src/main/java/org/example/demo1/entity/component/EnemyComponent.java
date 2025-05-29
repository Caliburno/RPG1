package org.example.demo1.entity.component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class EnemyComponent extends Component {

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
            if (hp != null) {
                hp.damage(10);
                System.out.println("+10 damage");
                System.out.println("Current health: " + hp.getHp());
            }
        }
    }
}
