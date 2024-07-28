package student.view;
import javax.swing.*;
import java.util.List;

import student.controller.PokedexController;
import student.model.PokeRecord;
import student.view.Components.ListItem;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;

public class PokemonTeamPanel extends JPanel {
        PokedexController controller = new PokedexController();
        List<PokeRecord> pokemonTeam;
        List<ListItem> customRectTeam = new ArrayList<>();
        JPanel teamPanel = new JPanel();

        public PokemonTeamPanel() throws IOException {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            pokemonTeam = controller.getAllPokemonInTeam();
            for (PokeRecord pokemon : pokemonTeam) {
                ListItem listItem = new ListItem(pokemon);
                customRectTeam.add(listItem);
            }

            for (ListItem item : customRectTeam) {
                teamPanel.add(item);
            }

            teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane(teamPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            this.add(scrollPane, BorderLayout.CENTER);
        }

        // Method to update the team.
        public void updatePanel(List<PokeRecord> pokeRecords) {
            // code
        }

        // Method to add mouse listener to list items
        public void addMouseListenerToListItems(MouseAdapter mouseAdapter) {
            for (ListItem listItem : customRectTeam) {
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