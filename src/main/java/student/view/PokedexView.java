package student.view;
import javax.swing.*;

import student.view.Components.InputTextField;
import student.view.Components.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;


public class PokedexView extends JFrame implements ActionListener {
    private static PokedexView instance;
    private JButton searchButton;
    private JPanel topPanel;
    private JPanel centerPanel;
    private InputTextField inputField;

       // Constructor
       public PokedexView() {
        screenSetup();
        this.setVisible(true);
    }

    /**
     * Returns the singleton instance.
     *
     * @return singelton instance of the DNInfoFrame
     */
    public static PokedexView getInstance() {
        if (instance == null) {
            instance = new PokedexView();
        }
        return instance;
    }
    // Handle button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            System.out.println("clicked");
        }
    }

     private void setDefaultFont(Font font) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof Font) {
                UIManager.put(key, font);
            }
        }
    }

    private void screenSetup() {
        try {
            // Load the pixelated font
            InputStream is = getClass().getResourceAsStream("/Font/gbboot.ttf");
            Font pixelatedFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f);
            setDefaultFont(pixelatedFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Fallback to a default font in case of error
            setDefaultFont(new Font("Monospaced", Font.PLAIN, 14));
        }
        setTitle("Pokedex");
        setSize(400, 600);
        setBackground(new Color(180, 0, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top section
        topPanel = new JPanel();
        topPanel.setBackground(new Color(180, 0, 0));
        topPanel.setPreferredSize(new Dimension(600, 40));
        topPanel.setLayout(new FlowLayout());

        // Center section
        centerPanel = new GridPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        // Add components to center panel as needed

        // Example button
        searchButton = new JButton("Search Pokemon");
        searchButton.addActionListener(this); // Register listener
        centerPanel.add(searchButton);

        // Input field
        inputField = new InputTextField(12, 7);
        topPanel.add(inputField);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
