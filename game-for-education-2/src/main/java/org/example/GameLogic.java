package org.example;

import org.example.logic.*;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    private Ball ball;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;
    public final int ENEMY_STEPS = 5;
    private final int BALL_STEPS = 20;


    public GameLogic() {
        this.ball = null;
        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
    }

    public void initialize() {

        ball = new Ball(20, 20, "bomb_green.jpg");

        Enemy enemy1 = new Enemy(350,350, "bomb.jpg",100);
        Enemy enemy2 = new Enemy(150,250, "bomb.jpg",100);
        enemies.add(enemy1);
        enemies.add(enemy2);

        Wall wall1 = new Wall(250, 30, 250, 130, Color.BLACK);
        Wall wall2 = new Wall(100, 50, 150, 50, Color.BLACK);
        walls.add(wall1);
        walls.add(wall2);
    }

    public void update() {
        //ball.move(2, Direction.RIGHT);
        for (Enemy enemy: enemies) {
            int differenceX = Math.abs(ball.getCoord().x - enemy.getCoord().x);
            int differenceY = Math.abs(ball.getCoord().y - enemy.getCoord().y);

            if (differenceX > differenceY) {
                // Direction LEFT, RIGHT
                if (ball.getCoord().x - enemy.getCoord().x > 0) {
                    // Direction RIGHT

                } else {
                    if (!predictEnemyCollision(Direction.LEFT, enemy)){
                        enemy.move(ENEMY_STEPS, Direction.LEFT);
                    }else{
                        enemy.turnRight(Direction.LEFT);
                    }
                    // Direction LEFT
                }
            } else {
                // Direction UP, DOWN
                if (ball.getCoord().y - enemy.getCoord().y > 0) {
                    // Direction DOWN
                    enemy.move(ENEMY_STEPS, Direction.DOWN);
                } else {
                    // Direction UP
                    enemy.move(ENEMY_STEPS, Direction.UP);
                }
            }
        }
        for (Wall wall: walls) {
            if (ball.isCollided(wall.getRectangle())){
                wall.inactivate();
            }
        }
    }

    public boolean predictCollision(Direction direction) {
        Rectangle moveRectangle = new Rectangle();
        switch (direction) {
            case RIGHT -> {
                moveRectangle = new Rectangle(ball.getX() + BALL_STEPS, ball.getY(), ball.getWidth(), ball.getHeight());
            }
            case LEFT -> {
                moveRectangle = new Rectangle(ball.getX() - BALL_STEPS, ball.getY(), ball.getWidth(), ball.getHeight());
            }
            case UP -> {
                moveRectangle = new Rectangle(ball.getX(), ball.getY() - BALL_STEPS, ball.getWidth(), ball.getHeight());
            }
            case DOWN -> {
                moveRectangle = new Rectangle(ball.getX(), ball.getY() + BALL_STEPS, ball.getWidth(), ball.getHeight());
            }

        }
        for (Wall wall:walls) {
            if (moveRectangle.intersects(wall.getRectangle())){
                return true;
            }
        }
        return false;


    }
    public boolean predictEnemyCollision(Direction direction, Enemy enemy) {
        Rectangle moveRectangle = new Rectangle();
            switch (direction) {
                case RIGHT -> {
                    moveRectangle = new Rectangle(enemy.getX() + ENEMY_STEPS, enemy.getY(), enemy.getWidth(), enemy.getHeight());
                }
                case LEFT -> {
                    moveRectangle = new Rectangle(enemy.getX() - ENEMY_STEPS, enemy.getY(), enemy.getWidth(), enemy.getHeight());
                }
                case UP -> {
                    moveRectangle = new Rectangle(enemy.getX(), enemy.getY() - ENEMY_STEPS, enemy.getWidth(), enemy.getHeight());
                }
                case DOWN -> {
                    moveRectangle = new Rectangle(enemy.getX(), enemy.getY() + ENEMY_STEPS, enemy.getWidth(), enemy.getHeight());
                }

            }
            for (Wall wall : walls) {
                if (moveRectangle.intersects(wall.getRectangle())) {
                    return true;
                }

        }
        return false;


    }

    public ArrayList<Enemy> getEnemy() {
        return enemies;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void movePlayer(Direction direction) {
        ball.move(BALL_STEPS, direction);

    }
}
