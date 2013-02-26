/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author lassi
 */
public class Ball {

    private double x;
    private double xSpeed;
    private double y;
    private double ySpeed;
    private double size;

    public Ball(double x, double y, double xSpeed, double ySpeed, double size) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.size = size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public double update(Stage s) {
        if (this.y < 0 || this.y > s.getHeight()) {
            yCollide();
        }
        this.x += this.xSpeed;
        this.y += this.ySpeed;

        if(xSpeed > 60) {
            xSpeed--;
        }
        if(ySpeed > 20) {
            ySpeed--;
        }
        
        return x;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void yCollide() {
        this.ySpeed *= -1;
    }

    /**
     *
     * @param p
     * @return true if ball hits the paddle
     */
    public boolean xCollide(Paddle p) {
        /**
         * Temporary variable for the direction of the ball
         */
        int d = -1;
        if (xSpeed > 0) {
            d = 1;
        }

        if (this.y > p.getY() && this.y < p.getY() + p.getHeight()) {
            if (this.x < p.getX() + 1) {
                this.xSpeed *= -1;
                this.x = p.getX() - 10*d;
                return true;
            }
        }
        return false;
    }
}