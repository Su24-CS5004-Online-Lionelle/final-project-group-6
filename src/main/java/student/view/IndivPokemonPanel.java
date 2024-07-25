package student.view;
import javax.swing.*;
import java.awt.*;

public class IndivPokemonPanel extends JPanel {
        // placeholder to make visualizing panel easier
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            g.fillRect(0, 0, 427, 405);
        }
}
