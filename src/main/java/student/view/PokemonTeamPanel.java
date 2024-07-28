package student.view;
import javax.swing.*;

import student.controller.PokedexController;
import student.model.PokeRecord;
import student.view.Components.CustomPanel;
import java.util.List;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;

public class PokemonTeamPanel extends JPanel {
        PokedexController controller = new PokedexController();
        List<PokeRecord> pokemonTeam;
        List<CustomPanel> customRectTeam = new ArrayList<>();
        JPanel teamPanel = new JPanel();

        public PokemonTeamPanel() throws IOException {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            pokemonTeam = controller.getAllPokemonInTeam();
            for (PokeRecord pokemon : pokemonTeam) {
                customRectTeam.add(new CustomPanel(pokemon.name()));
            }

            for (CustomPanel item : customRectTeam) {
                teamPanel.add(item);
            }

            teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane(teamPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            this.add(scrollPane, BorderLayout.CENTER);

            this.setVisible(false);
        }

        // Expose method to add mouse listener to CustomPanel items
        public void addMouseListenerToCustomPanel(MouseAdapter mouseAdapter) {
            for (CustomPanel customPanel : customRectTeam) {
                customPanel.addMouseListener(mouseAdapter);
            }
        }

        /**
         * Method updates the panel with a new list of pokeRecords.
         * @param pokeRecords
         */
        public void updatePanel(List<PokeRecord> pokeRecords) {
            // add code
        }
}
