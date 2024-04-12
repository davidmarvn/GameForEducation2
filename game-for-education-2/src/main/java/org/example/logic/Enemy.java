package org.example.logic;

import javax.swing.*;
import java.awt.*;

public class Enemy extends Entity {
    private int damage;
    private int enemySteps;

    public Enemy(int x, int y, String url, int damage) {
        super(x, y, url);
        this.damage = damage;
    }

    public void turnRight(Direction direction){
        switch (direction){
            case RIGHT -> move(enemySteps, Direction.DOWN);
            case DOWN -> move(enemySteps, Direction.LEFT);
            case LEFT -> move(enemySteps, Direction.UP);
            case UP -> move(enemySteps, Direction.RIGHT);
        }
    }
}
