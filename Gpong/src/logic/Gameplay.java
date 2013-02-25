/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author 41407
 */
public class Gameplay {

    private HashSet<Ball> balls;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Stage s;

    public Gameplay(int x, int y) {
        this.balls = new HashSet();
        this.s = new Stage(x, y);
        this.leftPaddle = new Paddle((int) x / 10, (int) y / 2, 10, 200);
        this.rightPaddle = new Paddle((int) x - x / 10, (int) y / 2, 10, 200);
    }

    private void loop() {
        if (balls.isEmpty()) {
            int xSpeed = 10;
            int ySpeed = 5;
            addABall(s.getHeight() / 2, s.getHeight() / 2, xSpeed, ySpeed);
        }

        for (Iterator<Ball> it = balls.iterator(); it.hasNext();) {
            Ball b = it.next();
            int x = b.update(s);
            if (x < leftPaddle.getX()) {
                if (x < 0) {
                    it.remove();
                }
                if (b.xCollide(leftPaddle)) {
                    addABall(b.getX(), b.getY(), b.x, x);
                }
            } else if (x > rightPaddle.getX()) {
                if (x > s.getWidth()) {
                    it.remove();
                }
                b.xCollide(rightPaddle);
            }
        }

        rightPaddle.update(s);
        leftPaddle.update(s);
    }

    private void addABall(int x, int y, int xSpeed, int ySpeed) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
