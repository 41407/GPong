/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import logic.Ball;
import logic.Paddle;

/**
 *
 * @author 41407
 */
public class Drawer {

    private Camera c;
    private double scale;
    
    public Drawer(Camera c) {
        this.c = c;
        this.scale = c.getScale();
    }

    public void drawPaddle(Paddle p, Graphics g) {
        double scale = c.getScale();

        int visualX = (int) ((int) p.getX() / scale);
        int visualY = (int) ((int) p.getY() / scale);
        int visualWidth = (int) ((int) p.getWidth() / scale);
        int visualHeight = (int) ((int) p.getHeight() / scale);

//        visualX -= visualWidth / 2;

        g.fillRect(visualX, visualY, visualWidth, visualHeight);
    }

    public void drawBalls(HashSet<Ball> balls, Graphics g) {

        try {
            for (Ball b : balls) {
                drawBall(b, g);
            }
        } catch (ConcurrentModificationException e) {
        }
    }

    public void drawBall(Ball b, Graphics g) {
  
        g.setColor(b.getColor());
        int visualX = (int) ((int) b.getX() / scale);
        int visualY = (int) ((int) b.getY() / scale);
        int visualSize = (int) ((int) b.getSize() / scale);

        visualX -= visualSize / 2;
        visualY -= visualSize / 2;

        g.fillRect(visualX, visualY, visualSize, visualSize);
    }

    public void drawScore(int scoreA, int scoreB, Graphics g) {
        g.drawString(scoreA + " - " + scoreB, 100, 100);
    }
}