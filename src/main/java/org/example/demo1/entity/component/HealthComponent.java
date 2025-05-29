package org.example.demo1.entity.component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class HealthComponent extends Component {

    private final int maxHp;
    private int hp;

    public HealthComponent(int maxHp, int hp) {
        this.maxHp = maxHp;
        this.hp = hp;
    }

    public void damage (int amount) {
        hp -= amount;
        if (hp <= 0) {
            hp = 0;
            onDeath();
        }
    }

    public void heal(int amount) {
        hp += Math.min(maxHp, hp + amount);
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    private void onDeath() {
        FXGL.getDialogService().showMessageBox("Game Over", () -> {
            FXGL.getGameController().startNewGame();
        });
        // Notar: el entity.removeFromWorld() no va acá porque se pierde antes del cartel
    }
}
