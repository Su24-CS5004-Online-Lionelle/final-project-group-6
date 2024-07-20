package student.view.Components;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.Dimension;

public class InputTextField extends JTextField {
    private Shape shape;
    private int radius;

    public InputTextField(int size, int radius) {
        super(size);
        this.radius = radius;
        setOpaque(false); // As we paint our own background
        setBorder(new EmptyBorder(0, 10, 0, 10)); // Adjust for text padding
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        }
        return shape.contains(x, y);
    }

    @Override
    public Dimension getPreferredSize() {
    // Assuming you want to keep the original width and just change the height
        Dimension size = super.getPreferredSize();
        return new Dimension(size.width, 30);
}
}
