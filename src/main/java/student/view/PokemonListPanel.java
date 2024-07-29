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
        private static PokemonListPanel instance;
        PokedexController controller = new PokedexController();
        List<ListItem> customRectList = new ArrayList<>();
        JPanel listPanel = new JPanel();

        // Private constructor to prevent instantiation
        private PokemonListPanel() {
            // Initialization code here
        }

          // Public method to provide access to the instance
        public static synchronized PokemonListPanel getInstance() {
            if (instance == null) {
                instance = new PokemonListPanel();
            }
            return instance;
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

        /**
     * Refreshed the panel, removes current content and calls initialize again.
     */
    public void refreshPanel(List<PokeRecord> records) {
        // Remove all existing components
        this.removeAll();
        this.customRectList.clear();
        this.listPanel.removeAll();

        // Reinitialize components (assuming the constructor logic is in a method called initializeComponents)
        initializePanel(records);

        // Revalidate and repaint to update the UI
        this.revalidate();
        this.repaint();
    }

    /**
     * Initialize pokemon list based on input.
     */
    public void initializePanel(List<PokeRecord> records) {
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        System.out.println(records.size());
        for (PokeRecord pokemon : records) {
            ListItem listItem = new ListItem(pokemon);
            customRectList.add(listItem);
        }

        for (ListItem item : customRectList) {
            listPanel.add(item);
        }

        // Calculate the total height needed for the listPanel
        int totalHeight = 0;
        for (ListItem item : customRectList) {
            totalHeight += item.getPreferredSize().height;
        }
        listPanel.setPreferredSize(new Dimension(listPanel.getWidth(), totalHeight));

        // Validate and repaint to ensure layout is updated
        listPanel.revalidate();
        listPanel.repaint();

        JScrollPane scrollPane = new JScrollPane(listPanel);
        this.add(scrollPane, BorderLayout.CENTER);

        // Revalidate and repaint the main panel to update the UI
        scrollPane.revalidate();
        scrollPane.repaint();
        this.revalidate();
        this.repaint();
    }

}
