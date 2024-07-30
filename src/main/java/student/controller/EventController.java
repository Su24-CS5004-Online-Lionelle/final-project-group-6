package student.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import student.model.PokeRecord;
import student.view.PokedexPanel;
import student.view.PokedexView;
import student.view.PokemonListPanel;
import student.view.PokemonTeamPanel;
import student.view.Components.ListItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class EventController implements ActionListener, ItemListener, KeyListener {

    private PokedexView pokedexView;
    private PokedexController controller = new PokedexController();

    public EventController(PokedexView view) {
        this.pokedexView = view;
        view.setListeners(this);
        view.setItemListeners(this);
        view.setKeyListeners(this);
        addMouseListeners();
    }

    // Listening for list/team item clicks
    public void addMouseListeners() {
        this.pokedexView.addMouseListenerToListItems(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ListItem listItem = (ListItem) e.getSource();
                try {
                    handlePokemonClick(listItem.getCurrPokemon()); // Here you see how you can get the clicked pokemon name
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    // Method that runs when pokemon in list is clicked. Says which pokemon was clicked.
    public void handlePokemonClick(PokeRecord pokemonName) throws IOException {
        System.out.println("Clicked on: " + pokemonName.name());
    }

    // Listening for button clicks.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save Team":
                System.out.println("Save Team Button Pressed!");
                break;
            case "Add to Team":
                System.out.println("Add to team clicked!");
                PokeRecord recordToAdd = PokemonListPanel.getInstance().getIsHighlited();
                controller.addPokemonToTeam(recordToAdd);
                PokemonListPanel.getInstance().refreshPanel(null);
                PokedexPanel.getInstance().refreshAddToggleButton();
                try {
                    PokemonTeamPanel.getInstance().refreshPanel();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Remove from Team":
                System.out.println("Removed from team clicked!");
                PokeRecord recordToRemove = PokemonListPanel.getInstance().getIsHighlited();
                controller.removePokemonFromTeam(recordToRemove);
                PokemonListPanel.getInstance().refreshPanel(null);
                PokedexPanel.getInstance().refreshAddToggleButton();
                try {
                    PokemonTeamPanel.getInstance().refreshPanel();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    // Here I was seeing if the combobox was working properly, but it doesn't
    // seem to want to print out the list WITH the item that was just selected,
    // so we'll have to figure that out.
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println(pokedexView.getTypes());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }
    // this is the one that seems to work for the search bar.
    // The other two aren't getting the last letter typed for some reason.
    @Override
    public void keyReleased(KeyEvent e) {
        String input = pokedexView.getSearchbarText();
        PokemonListPanel listPanel = PokemonListPanel.getInstance();
        try {
            List<PokeRecord> records = controller.filterByContains(input);
            listPanel.refreshPanel(records);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(input);
    }
}
