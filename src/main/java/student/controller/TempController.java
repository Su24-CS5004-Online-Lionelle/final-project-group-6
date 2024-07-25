package student.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import student.view.PokedexView;
import java.io.OutputStream;

public class TempController implements ActionListener, ItemListener, KeyListener {

    private PokedexView pokedexView;

    public TempController(PokedexView view) {
        this.pokedexView = view;
        view.setListeners(this);
        view.setItemListeners(this);
        view.setKeyListeners(this);
    }

    // Here it is listening for the buttons. As you can see, there are two cases per
    // toggle button, because as it changes its text, the case changes with it.
    // I was trying to handle the text switching in view, but was having issues.
    // Maybe this is actually easier for you too.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save Team":
                System.out.println("Save Team Button Pressed!");
                break;
            case "Add to Team":
                pokedexView.setAddToggleButtonText("Remove from Team");
                break;
            case "Remove from Team":
                pokedexView.setAddToggleButtonText("Add to Team");
                break;
            case "Team View":
                pokedexView.setViewToggleButtonText("List View");
                break;
            case "List View":
                pokedexView.setViewToggleButtonText("Team View");
                break;
        }
    }
    
    // Here I was seeing if the combobox was working properly, but it doesn't
    // seem to want to print out the list WITH the item that was just selected,
    // so we'll have to figure that out.
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == pokedexView.getCheckableComboBox() && e.getStateChange() == ItemEvent.SELECTED) {
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
        System.out.println(pokedexView.getSearchbarText());
    }


}
