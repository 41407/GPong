/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author 41407
 */
public class Camera {

    private double scale;

    public Camera() {
        this.scale = 1;
    }
    
    public Camera(double scale) {
        this.scale = scale;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
