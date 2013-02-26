/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import logic.Gameplay;

/**
 *
 * @author lassi
 */
public class PongPanel extends JPanel {
    
    private Gameplay game;
    private Drawer drawer;
    
    public PongPanel(Gameplay game) {
        this.game = game;
        drawer = new Drawer(new Camera(1));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        drawer.drawBalls(game.getBalls(), g);
        g.setColor(Color.getHSBColor((float) (System.currentTimeMillis() % 4000) / 4000,
                1, (float)1.0));
        drawer.drawPaddle(game.getLeftPaddle(), g);
        drawer.drawPaddle(game.getRightPaddle(), g);
        drawer.drawScore(game.getPlayer1score(), game.getPlayer2score(), g);
    }
}
