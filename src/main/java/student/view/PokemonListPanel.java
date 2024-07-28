package student.view;
import javax.swing.*;

import org.checkerframework.checker.units.qual.s;

import java.util.ArrayList;
import java.util.List; // Add this import statement

import student.controller.PokedexController;
import student.model.PokeRecord;
import student.view.Components.ListItem;

import java.awt.*;

public class PokemonListPanel extends JPanel {
        PokedexController controller = new PokedexController();
        List<PokeRecord> pokemonList;
        List<ListItem> customRectList = new ArrayList<>();
        JPanel listPanel = new JPanel();

        public PokemonListPanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            pokemonList = controller.getAllPokemon();
            for (PokeRecord pokemon : pokemonList) {
                String nameCapitalized = pokemon.name().substring(0, 1).toUpperCase() + pokemon.name().substring(1).toLowerCase();
                customRectList.add(new ListItem(nameCapitalized));
            }

            for (ListItem item : customRectList) {
                listPanel.add(item);
            }

            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane(listPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            this.add(scrollPane, BorderLayout.CENTER);
        }

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
