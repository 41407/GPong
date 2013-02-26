/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import ui.PongFrame;

/**
 *
 * @author 41407
 */
public class Gameplay {

    private HashSet<Ball> balls;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Stage stage;
    private int player1score = 0;
    private int player2score = 0;
    private PongFrame frame;
    private HashSet<Ball> ballsToAdd;

    public Gameplay(int x, int y) {
        this.balls = new HashSet();
        this.ballsToAdd = new HashSet();
        this.stage = new Stage(x, y);
        this.leftPaddle = new Paddle(50, (int) y / 2, 32, 150);
        this.rightPaddle = new Paddle(x - 50, (int) y / 2, 32, 150);
        System.out.println(x + " y: " + y);
    }

    public void loop() {
        Random r = new Random();
        while (true) {
            player1score = balls.size();
            if (balls.isEmpty()) {
                double xSpeed = 5 * Math.pow(-1, r.nextInt(2));
                double ySpeed = 2 * Math.pow(-1, r.nextInt(2));
                addABall(stage.getHeight() / 2, stage.getHeight() / 2, xSpeed, ySpeed, false);
            }
            addBalls();
            handleCollisions();
            rightPaddle.update(stage);
            leftPaddle.update(stage);
            frame.updatePanel();
            try {
                Thread.currentThread().sleep(16);
            } catch (InterruptedException ex) {
                System.out.println("Perse!");
            }
        }
    }

    private void handleCollisions() {
        for (Iterator<Ball> it = balls.iterator(); it.hasNext();) {
            Ball b = it.next();
            double x = b.update(stage);
            if (x < leftPaddle.getX() + leftPaddle.getWidth()) {
                if (b.xCollide(leftPaddle)) {
                    addABall(leftPaddle.getX() + leftPaddle.getWidth() + 10,
                            b.getY(),
                            Math.abs(b.getxSpeed()),
                            b.getySpeed(),
                            true);
                }
            }
            if (x < 0) {
                it.remove();
            } else if (x > rightPaddle.getX()) {
                if (b.xCollide(rightPaddle)) {
                    addABall(rightPaddle.getX() - 10,
                            b.getY(),
                            -Math.abs(b.getxSpeed()),
                            b.getySpeed(),
                            true);
                }
            }
            if (x > stage.getWidth()) {
                it.remove();
            }
        }
    }

    public HashSet<Ball> getBalls() {
        return balls;
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }

    public Stage getStage() {
        return stage;
    }

    public int getPlayer1score() {
        return player1score;
    }

    public int getPlayer2score() {
        return player2score;
    }

    public void setFrame(PongFrame frame) {
        this.frame = frame;
    }

    public PongFrame getFrame() {
        return frame;
    }

    private void addABall(double x, double y, double xSpeed, double ySpeed, boolean random) {
        Random r = new Random();
        if (random) {
            ySpeed += (r.nextDouble() - 0.5)*1.4;
            xSpeed += r.nextDouble() - 0.5;
        }
        Ball b = new Ball(x, y, xSpeed, ySpeed, calculateSize());
        ballsToAdd.add(b);
    }

    private void addBalls() {
        if (!ballsToAdd.isEmpty()) {
            for (Ball b : ballsToAdd) {
                balls.add(b);
            }
        }
        ballsToAdd.clear();
    }

    private double calculateSize() {
        return Math.max(1, 20 - (balls.size() / 500));
    }
}
