package student.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import student.GUIUtil;
import student.model.PokeRecord;
import student.model.PokemonModel; // need to change to controller
import student.model.PokemonMoves.PokemonMove;
import student.model.PokemonTypes.PokemonType;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class IndivPokemonPanel extends JPanel {

    private PokemonModel model = PokemonModel.getInstance(); // need to change to controller
    private PokeRecord record;

    public IndivPokemonPanel(String name) {

        try {
            this.record = model.getPokemonByName(name);
        } catch (IOException e) {
            GUIUtil.showMessage("Error: " + e.getMessage(), "IOException");
        }

        /** Set layout manager for the main panel. */
        this.setLayout(new BorderLayout());
        /** Create the name panel. */
        JPanel namePanel = createNamePanel();
        /** Create the image panel. */
        JPanel imagePanel = createImagePanel();
        /** Create the info panel. */
        JPanel infoPanel = createInfoPanel();

        JPanel scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new BorderLayout());
        scrollablePanel.add(namePanel, BorderLayout.NORTH);
        scrollablePanel.add(imagePanel, BorderLayout.CENTER);
        scrollablePanel.add(infoPanel, BorderLayout.SOUTH);

        // Wrap the scrollable panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove default border

        this.add(scrollPane, BorderLayout.CENTER);
    }

    private Border createCustomBorder() {
        // Create a border with red sides and white center
        Border outerBorder = new LineBorder(Color.RED, 10);
        Border innerBorder = new EmptyBorder(5, 5, 5, 5); // Padding inside the border
        Border compoundBorder = new CompoundBorder(outerBorder, innerBorder);
        return compoundBorder;
    }

    private JPanel createNamePanel() {
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(this.record.name(), SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        Font labelFont = label.getFont();
        Font newFont = new Font(labelFont.getFontName(), Font.ITALIC, 35);
        label.setFont(newFont);
        label.setBorder(createCustomBorder());
        namePanel.add(label);
        return namePanel;
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel();
        try {
            URL imageURL = new URL(this.record.sprites().getFrontDefault());
            Image image = ImageIO.read(imageURL);
            int width = 200;
            int height = 200;
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(imageIcon);
            imagePanel.add(imageLabel);
            return imagePanel;
        } catch (MalformedURLException e) {
            GUIUtil.showMessage("Error: Unvalid image link.", "MalformedURLException");
        } catch (IOException e) {
            GUIUtil.showMessage("Error: " + e.getMessage(), "IOException");
        }
        return null;
    }
    
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Stack info vertically
        JTextArea info = new JTextArea
        (
            "ID: " + String.valueOf(this.record.id()) + "\n" + "\n" +
            "Weight: " + String.valueOf(this.record.weight()) + "\n" + "\n" +
            "Height: " + String.valueOf(this.record.height()) + "\n" + "\n" +
            "Types: " + getTypeFromList() + "\n" + "\n" +
            "Moves: " + getMoveFromList()
        );
        Font font = new Font("Dialog", Font.ITALIC, 25);
        info.setFont(font);
        info.setForeground(Color.BLACK); // Set text color
        info.setEditable(false); // Make text area non-editable
        info.setLineWrap(true); // Wrap text lines
        info.setWrapStyleWord(true);
        infoPanel.add(info);
        return infoPanel;
    }

    private String getTypeFromList() {
        List<PokemonType> typeList = this.record.types();
        String typeString = typeList.toString();
        String newTypeString = typeString.substring(1, typeString.length() - 1);
        return newTypeString;
    }

    private String getMoveFromList() {
        List<PokemonMove> movesList = this.record.moves();
        StringBuilder moveName = new StringBuilder();
        for (int i = 0; i < Math.min(10, movesList.size()); i++) {
            String moveDetails = movesList.get(i).toString();
            String[] moveParts = moveDetails.split(":");
            String moveString = moveParts[0].substring(1, moveParts[0].length() - 1);
            moveName.append(moveString + ", ");
        }
        String newMoveString = moveName.toString().substring(0, moveName.toString().length() - 2);
        return newMoveString;
    }

    // placeholder to make visualizing panel easier
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, 427, 405);
    }
}
