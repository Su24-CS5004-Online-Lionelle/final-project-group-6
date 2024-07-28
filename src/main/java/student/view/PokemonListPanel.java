package student.view;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List; // Add this import statement
import java.awt.event.MouseAdapter;

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
                ListItem listItem = new ListItem(pokemon);
                customRectList.add(listItem);
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

        // Method to update the list.
        public void updatePanel(List<PokeRecord> pokeRecords) {
            // code
        }

        // Method to add mouse listener to list items
        public void addMouseListenerToListItems(MouseAdapter mouseAdapter) {
            for (ListItem listItem : customRectList) {
                listItem.addMouseListener(mouseAdapter);
            }
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
