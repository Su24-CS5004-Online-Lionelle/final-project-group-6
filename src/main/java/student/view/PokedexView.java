package student.view;
import javax.swing.*;
import java.awt.*;

public class PokedexView extends JFrame {
    public PokedexView() {
        PokedexPanel pokedexPanel = new PokedexPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(pokedexPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
