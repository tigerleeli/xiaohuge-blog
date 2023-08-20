package org.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeGame extends Application {

    private static final int WIDTH = 20; // 游戏界面的宽度
    private static final int HEIGHT = 20; // 游戏界面的高度
    private static final int SIZE = 20; // 每个格子的大小
    private static final int SPEED = 5; // 蛇的速度

    private Deque<Point> snake = new ArrayDeque<>(); // 蛇的身体
    private Direction direction = Direction.RIGHT; // 蛇的初始方向

    private Point food; // 食物的位置

    private boolean gameOver = false; // 游戏是否结束
    private boolean gamePaused = false; // 游戏是否暂停

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(WIDTH * SIZE, HEIGHT * SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case UP:
                    if (direction != Direction.DOWN) {
                        direction = Direction.UP;
                    }
                    break;
                case DOWN:
                    if (direction != Direction.UP) {
                        direction = Direction.DOWN;
                    }
                    break;
                case LEFT:
                    if (direction != Direction.RIGHT) {
                        direction = Direction.LEFT;
                    }
                    break;
                case RIGHT:
                    if (direction != Direction.LEFT) {
                        direction = Direction.RIGHT;
                    }
                    break;
                case P:
                    gamePaused = !gamePaused;
                    break;
                case R:
                    initGame();
                    break;
                default:
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("贪吃蛇游戏");
        primaryStage.setResizable(false);
        primaryStage.show();

        initGame();

        new AnimationTimer() {
            private long lastUpdateTime;

            @Override
            public void handle(long now) {
                if (now - lastUpdateTime >= 1_000_000_000 / SPEED) { // 调整蛇的速度
                    lastUpdateTime = now;
                    if (!gameOver && !gamePaused) {
                        move();
                        checkCollision();
                        paint(gc);
                    }
                }
            }
        }.start();
    }

    // 初始化游戏
    private void initGame() {
        snake.clear();
        snake.add(new Point(WIDTH / 2, HEIGHT / 2));
        generateFood();
        gameOver = false;
        gamePaused = false;
    }

    // 蛇的移动
    private void move() {
        Point head = snake.getFirst();
        Point newHead = null;
        switch (direction) {
            case UP:
                newHead = new Point(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                newHead = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                newHead = new Point(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                newHead = new Point(head.getX() + 1, head.getY());
                break;
            default:
                break;
        }
        // 判断是否撞到自己的身体
        if (snake.contains(newHead)) {
            gameOver = true;
            showGameOverDialog();
            return;
        }
        // 判断是否撞到墙壁
        if (newHead.getX() < 0 || newHead.getX() >= WIDTH ||
                newHead.getY() < 0 || newHead.getY() >= HEIGHT) {
            gameOver = true;
            showGameOverDialog();
            return;
        }
        snake.addFirst(newHead);
        if (newHead.equals(food)) {
            generateFood();
        } else {
            snake.removeLast();
        }
    }

    // 检测碰撞
    private void checkCollision() {
        Point head = snake.getFirst();
        for (Point point : snake) {
            if (point != head && point.equals(head)) {
                gameOver = true;
                showGameOverDialog();
                break;
            }
        }
    }

    // 生成食物
    private void generateFood() {
        boolean validPosition;
        int x, y;
        do {
            validPosition = true;
            x = (int) (Math.random() * WIDTH);
            y = (int) (Math.random() * HEIGHT);
            for (Point point : snake) {
                if (point.getX() == x && point.getY() == y) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);
        food = new Point(x, y);
    }

    // 绘制游戏画面
    private void paint(GraphicsContext gc) {
        // 清空画布
        gc.clearRect(0, 0, WIDTH * SIZE, HEIGHT * SIZE);

        // 绘制蛇身
        gc.setFill(javafx.scene.paint.Color.GREEN);
        for (Point point : snake) {
            gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
        }

        // 绘制头部
        gc.setFill(javafx.scene.paint.Color.DARKGREEN);
        Point head = snake.getFirst();
        gc.fillRect(head.getX() * SIZE, head.getY() * SIZE, SIZE, SIZE);

        // 绘制食物
        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillRect(food.getX() * SIZE, food.getY() * SIZE, SIZE, SIZE);
    }

    // 显示游戏结束对话框
    private void showGameOverDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("游戏结束");
        alert.setHeaderText(null);
        alert.setContentText("游戏结束，您的得分是：" + (snake.size() - 1));
        alert.show();

        alert.setOnHidden(event -> {
            initGame(); // 游戏结束后重新开始游戏
        });
    }

    // 方向枚举类
    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    // 坐标点类
    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}