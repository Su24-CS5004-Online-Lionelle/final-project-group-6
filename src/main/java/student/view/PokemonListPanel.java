package student.view;
import javax.swing.*;
import java.awt.*;

public class PokemonListPanel extends JPanel {
        // placeholder to make visualizing panel easier
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            g.fillRect(0, 0, 390, 440);
            // visibility true when team view if off
            this.setVisible(true);
        }
}