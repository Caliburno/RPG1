package org.example.demo1.entity.component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

public class PlayerComponent extends Component {
    private Entity player;
    private double targetX, targetY;
    private boolean moving = false;
    private final double SPEED = 150;

    @Override
    public void onAdded() {
        this.entity = getEntity();
        this.targetX = entity.getX();
        this.targetY = entity.getY();
    }

    public void moveTo (double x, double y) {
        this.targetX = x;
        this.targetY = y;
        this.moving = true;
    }

    @Override
    public void onUpdate(double tpf) {
        if (moving) {
            double dx = targetX - entity.getX();
            double dy = targetY - entity.getY();
            double distance = Math.hypot(dx, dy);

            if (distance < 5) {
                moving = false;
                entity.setPosition(targetX, targetY);
            } else {
                double dirX = dx / distance;
                double dirY = dy / distance;

                entity.translate(dirX * SPEED * tpf, dirY * SPEED * tpf);
            }
        }
    }
}
