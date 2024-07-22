package student.view;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class PokedexPanel extends JPanel {
    private JTextArea leftOutputTextArea;
    private JTextArea rightOutputTextArea;
    private JScrollPane leftScrollPane;
    private JScrollPane rightScrollPane;

    public PokedexPanel() {
        this.setPreferredSize(new Dimension(1000, 700));
        this.setBackground(new Color(20, 20, 60));
        this.setLayout(null); // Use null layout for absolute positioning

        // Initialize left text area
        leftOutputTextArea = new JTextArea();
        leftOutputTextArea.setEditable(false); // Make it read-only
        leftOutputTextArea.setLineWrap(true);
        leftOutputTextArea.setWrapStyleWord(true);
        leftOutputTextArea.setBackground(new Color(240, 240, 248)); // Light gray background
        leftOutputTextArea.setForeground(new Color(0, 0, 0));
        leftOutputTextArea.setFont(new Font("Courier New", Font.BOLD, 15));

        // Initialize left scroll pane
        leftScrollPane = new JScrollPane(leftOutputTextArea);
        leftScrollPane.setBounds(53, 100, 427, 405); // Set position and size
        leftScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        leftScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add left scroll pane to panel
        this.add(leftScrollPane);

        // You can set initial text here if needed
        setLeftOutputText("List of Pokemon go here...");

        // Initialize left text area
        rightOutputTextArea = new JTextArea();
        rightOutputTextArea.setEditable(false); // Make it read-only
        rightOutputTextArea.setLineWrap(true);
        rightOutputTextArea.setWrapStyleWord(true);
        rightOutputTextArea.setBackground(new Color(20, 20, 20)); // Light gray background
        rightOutputTextArea.setForeground(new Color(240, 240, 240));
        rightOutputTextArea.setFont(new Font("Courier New", Font.BOLD, 15));

        // Initialize left scroll pane
        rightScrollPane = new JScrollPane(rightOutputTextArea);
        rightScrollPane.setBounds(570, 100, 390, 440); // Set position and size
        rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add left scroll pane to panel
        this.add(rightScrollPane);

        // You can set initial text here if needed
        setRightOutputText("Team is created here...");
    }

    // Method to set text
    public void setLeftOutputText(String text) {
        leftOutputTextArea.setText(text);
    }

    // Method to set text
    public void setRightOutputText(String text) {
        rightOutputTextArea.setText(text);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        // create left pokedex shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(25, 40, 488, 650);

        // Create right pokedex shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(542, 40, 450, 650);

        // create right pokedex
        g2D.setColor(new Color(230, 40, 50));
        g2D.fillRect(25, 25, 488, 650);

        // create left pokedex
        g2D.setColor(new Color(230, 40, 50));
        g2D.fillRect(542, 25, 450, 650);

        // create the shadow of the top part of left pokedex
        GeneralPath topPokedexShadow = new GeneralPath();
        topPokedexShadow.moveTo(25, 25);
        topPokedexShadow.lineTo(25, 80);
        topPokedexShadow.lineTo(220, 80);
        topPokedexShadow.lineTo(280, 40);
        topPokedexShadow.lineTo(525, 40);
        topPokedexShadow.lineTo(525, 25);
        topPokedexShadow.lineTo(25, 25);
        topPokedexShadow.lineTo(100, 240);
        topPokedexShadow.closePath();

        g2D.setColor(new Color(160, 30, 40));  // Darker red for shadow effect
        g2D.fill(topPokedexShadow);  // Fill the shadow shape

        // Create top part of pokedex
        GeneralPath topPokedex = (GeneralPath) topPokedexShadow.clone();
        AffineTransform translateTransform = new AffineTransform();
        translateTransform.translate(0, -10);
        topPokedex.transform(translateTransform);
        g2D.setColor(new Color(255, 100, 100));
        g2D.fill(topPokedex);

        // create top carved out part of left pokedex
        AffineTransform flipTransform = new AffineTransform();
        flipTransform.translate(1050, 0);
        flipTransform.scale(-1, 1);
        GeneralPath mirroredPokedex = (GeneralPath) topPokedex.clone();
        mirroredPokedex.transform(flipTransform);
        g2D.setColor(new Color(20, 20, 60));
        g2D.fill(mirroredPokedex);
        
        // create outline shape for left pokedex
        GeneralPath leftPokedexOutline = new GeneralPath();
        leftPokedexOutline.moveTo(35, 80);
        leftPokedexOutline.lineTo(35, 665);
        leftPokedexOutline.lineTo(500, 665);
        leftPokedexOutline.lineTo(500, 40);
        leftPokedexOutline.lineTo(280, 40);
        leftPokedexOutline.lineTo(220, 80);
        leftPokedexOutline.lineTo(35, 80);
        leftPokedexOutline.closePath();

        g2D.setColor(new Color(160, 30, 40));
        g2D.setStroke(new BasicStroke(5));
        g2D.draw(leftPokedexOutline);

        // create right pokedex outline
        GeneralPath rightPokedexOutline = new GeneralPath();
        rightPokedexOutline.moveTo(553, 40);
        rightPokedexOutline.lineTo(553, 665);
        rightPokedexOutline.lineTo(980, 665);
        rightPokedexOutline.lineTo(980, 80);
        rightPokedexOutline.lineTo(825, 80);
        rightPokedexOutline.lineTo(765, 40);
        rightPokedexOutline.lineTo(553, 40);
        rightPokedexOutline.closePath();

        g2D.draw(rightPokedexOutline);

        // create hinge
        g2D.setColor(new Color(255, 100, 100));
        g2D.fillRect(512, 15, 30, 650);

        // top of hinge
        g2D.setColor(new Color(255, 100, 100));
        g2D.fillOval(512, 8, 30, 15);

        // bottom hinge shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillOval(512, 657, 30, 15);
        
        // hinge shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(531, 11, 8, 650);

        // hinge highlight
        g2D.setColor(new Color(255, 150, 150));
        g2D.fillRect(518, 10, 4, 646);

        // Create white sphere
        g2D.setColor(new Color(240, 240, 240));
        g2D.fill(new Ellipse2D.Double(35, 20, 45, 45));
        // Create blue sphere
        g2D.setColor(new Color(0, 0, 205));
        g2D.fill(new Ellipse2D.Double(39, 23, 37, 37));
        // Create blue highlight
        g2D.setColor(new Color(135, 206, 250, 128));
        g2D.fill(new Ellipse2D.Double(42, 25, 29, 29));
        // Create white highlight
        g2D.setColor(new Color(255, 255, 255));
        g2D.fill(new Ellipse2D.Double(53, 33, 5, 5));

        // Create small red top button
        g2D.setColor(new Color(0, 0, 0));
        g2D.fill(new Ellipse2D.Double(109, 22.5, 22, 22));
        g2D.setColor(new Color(255, 0, 0));
        g2D.fill(new Ellipse2D.Double(111.5, 24, 17, 17));
        g2D.setColor(new Color(240, 240, 240));
        g2D.fill(new Ellipse2D.Double(117, 27, 4, 4));

        // Create small yellow top button
        g2D.setColor(new Color(0, 0, 0));
        g2D.fill(new Ellipse2D.Double(149, 22.5, 22, 22));
        g2D.setColor(new Color(240, 240, 0));
        g2D.fill(new Ellipse2D.Double(151.5, 24, 17, 17));
        g2D.setColor(new Color(255, 255, 255));
        g2D.fill(new Ellipse2D.Double(157, 27, 4, 4));

        // Create small green top button
        g2D.setColor(new Color(0, 0, 0));
        g2D.fill(new Ellipse2D.Double(189, 22.5, 22, 22));
        g2D.setColor(new Color(0,240, 0));
        g2D.fill(new Ellipse2D.Double(191.5, 24, 17, 17));
        g2D.setColor(new Color(255, 255, 255));
        g2D.fill(new Ellipse2D.Double(197, 27, 4, 4));

        // Create left screen background shadow
        GeneralPath leftScreenShadow = new GeneralPath();
        leftScreenShadow.moveTo(48, 105);
        leftScreenShadow.lineTo(48, 518);
        leftScreenShadow.lineTo(70, 545);
        leftScreenShadow.lineTo(485, 545);
        leftScreenShadow.lineTo(485, 105);
        leftScreenShadow.lineTo(48, 105);
        leftScreenShadow.closePath();

        g2D.setColor(new Color(160, 30, 40));
        g2D.fill(leftScreenShadow);
        // Create left screen background
        GeneralPath leftScreen = new GeneralPath();
        leftScreen.moveTo(48, 95);
        leftScreen.lineTo(48, 508);
        leftScreen.lineTo(70, 535);
        leftScreen.lineTo(485, 535);
        leftScreen.lineTo(485, 95);
        leftScreen.lineTo(48, 95);
        leftScreen.closePath();

        g2D.setColor(new Color(240, 240, 240));
        g2D.fill(leftScreen);

        // Create left screen outline
        GeneralPath leftScreenOutline = new GeneralPath();
        leftScreenOutline.moveTo(53, 100);
        leftScreenOutline.lineTo(53, 505);
        leftScreenOutline.lineTo(480, 505);
        leftScreenOutline.lineTo(480, 100);
        leftScreenOutline.lineTo(53, 100);
        leftScreenOutline.closePath();

        g2D.setColor(new Color(0, 0, 50));
        g2D.fill(leftScreenOutline);

        // create red button bottom of screen
        g2D.setColor(new Color(0, 0, 0));
        g2D.fill(new Ellipse2D.Double(98, 508.5, 22, 22));
        g2D.setColor(new Color(255, 0, 0));
        g2D.fill(new Ellipse2D.Double(100.8, 510, 17, 17));
        g2D.setColor(new Color(240, 240, 240));
        g2D.fill(new Ellipse2D.Double(106.5, 513, 4, 4));

        // create screen vent
        g2D.setColor(new Color(0, 0, 0));
        g2D.fillRect(370, 510, 80, 3);
        g2D.fillRect(370, 518, 80, 3);
        g2D.fillRect(370, 526, 80, 3);

        // create arrow buttons shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(435, 575, 20, 70);
        g2D.fillRect(410, 600, 70, 20);
        // create arrow buttons
        g2D.setColor(new Color(20, 20, 20));
        g2D.fillRect(435, 570, 20, 70);
        g2D.fillRect(410, 595, 70, 20);

        // create right screen Shadow
        g2D.setColor(new Color(160, 30, 40));
        g2D.fillRect(565, 90, 400, 450);
        // create right screen
        g2D.setColor(new Color(40, 40, 40));
        g2D.fillRect(565, 95, 400, 450);

        // create bottom two rectangle on right pokedex
        g2D.setColor(new Color(40, 40, 40));
        g2D.fillRect(600, 622, 150, 22);
        g2D.fillRect(780, 622, 150, 22);
    }
}
