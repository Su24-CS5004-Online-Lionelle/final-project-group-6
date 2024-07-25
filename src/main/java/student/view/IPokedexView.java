package student.view;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.util.List;

public interface IPokedexView {

    // Set listeners for buttons
    void setListeners(ActionListener clicks);

    // Set item listeners for combo box
    void setItemListeners(ItemListener clicks);

    // Set key listeners for search bar
    void setKeyListeners(KeyListener press);

    // Set the text of the addToggleButton
    void setAddToggleButtonText(String text);

    // Set the text of the viewToggleButton
    void setViewToggleButtonText(String text);

    // Get the CheckableComboBox
    CheckableComboBox getCheckableComboBox();

    // Get the list of selected types
    List<String> getTypes();

    // Get the text from the search bar
    String getSearchbarText();
}
