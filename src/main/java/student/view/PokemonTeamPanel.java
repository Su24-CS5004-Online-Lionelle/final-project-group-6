package student.view;
import javax.swing.*;
import java.awt.*;

public class PokemonTeamPanel extends JPanel {
        // placeholder to make visualizing panel easier
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, 390, 440);
            // set visibility to false while list is shown
            this.setVisible(false);
        }
}