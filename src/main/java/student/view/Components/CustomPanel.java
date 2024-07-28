package student.view.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CustomPanel extends JPanel {
    private static List<CustomPanel> panels = new ArrayList<>();
    private boolean isHighlighted = false;
    private String text;

    public CustomPanel(String text) {
        this.text = text;
        setPreferredSize(new Dimension(400, 100)); // Adjust the size as needed
        setOpaque(false); // Ensure transparency

        // Add this panel to the list of panels
        panels.add(this);

        // Add mouse listener for highlighting
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                highlightPanel();
            }
        });
    }

    /**
     * Method to get the name of pokemon.
     * @return
     */
    public String getPokemonName() {
        return text;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100); // Set the preferred width and height
    }

    /**
     * Custom painting code for the component. This method overrides the default
     * painting behavior to draw a rounded rectangle with a black border, an inner
     * white rounded rectangle, and two red stripes on the left and right sides.
     *
     * @param g the object used to paint the component
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        /** Enable anti-aliasing for smooth edges. */
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int arcSize = 30;
        /** Draw the rounded rectangle with black border. */
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(0, 0, width, height, arcSize, arcSize);
        /** Draw the inner white rounded rectangle. */
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, width - 10, height - 10, arcSize, arcSize);
        /** Draw the left red stripe. */
        g2d.setColor(Color.RED);
        g2d.fillRoundRect(5, 5, 20, height - 10, arcSize, arcSize);
        /** Draw the right red stripe. */
        g2d.fillRoundRect(width - 25, 5, 20, height - 10, arcSize, arcSize);

        // Draw the text in the center
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int textX = (width - textWidth) / 2;
        int textY = (height + textHeight) / 2 - fm.getDescent();
        g2d.drawString(text, textX, textY);

        // Highlight the panel if it is selected
        if (isHighlighted) {
            g2d.setColor(new Color(0, 0, 0, 50)); // Semi-transparent black
            g2d.fillRoundRect(1, 1, width - 2, height - 2, arcSize, arcSize);
        }
    }

    private void highlightPanel() {
        // Unhighlight all panels
        for (CustomPanel panel : panels) {
            panel.isHighlighted = false;
            panel.repaint();
        }

        // Highlight this panel
        isHighlighted = true;
        repaint();
    }
}
