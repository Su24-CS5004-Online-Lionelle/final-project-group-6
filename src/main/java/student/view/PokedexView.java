package student.view;
import javax.swing.*;
import java.io.File;

import java.awt.*;
import java.io.IOException;

public class PokedexView extends JFrame {
    private PokedexPanel pokedexPanel;
    private IndivPokemonPanel indivPokemonPanel;
    private PokemonListPanel pokemonListPanel;
    private PokemonTeamPanel pokemonTeamPanel;
    private Font pokemonFont;

    public PokedexView() {
        // initialize panels
        pokedexPanel = new PokedexPanel();
        indivPokemonPanel = new IndivPokemonPanel();
        pokemonListPanel = new PokemonListPanel();
        pokemonTeamPanel = new PokemonTeamPanel();
        // create layered pane to put all panels together
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 700));
        // Set background color of the layered pane
        layeredPane.setBackground(new Color(20, 20, 60));
        layeredPane.setOpaque(true);
        // set position and size of each panel
        pokedexPanel.setBounds(0, 0, 1000, 700);
        indivPokemonPanel.setBounds(53, 100, 427, 405);
        pokemonListPanel.setBounds(570, 100, 390, 440);
        pokemonTeamPanel.setBounds(570, 100, 390, 440);
        // add each panel to the layered pane
        layeredPane.add(pokedexPanel, Integer.valueOf(1)); // Lower layer
        layeredPane.add(indivPokemonPanel, Integer.valueOf(2)); // Higher layer
        layeredPane.add(pokemonListPanel, Integer.valueOf(2));
        layeredPane.add(pokemonTeamPanel, Integer.valueOf(2));

        // try to get pokemon font from resources, and set fonts in pokedexPanel
        try {
            pokemonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/gbboot.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pokemonFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            pokemonFont = new Font("Courier New", Font.BOLD, 20);
        }
        pokedexPanel.setFonts(pokemonFont);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(layeredPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
