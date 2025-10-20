import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGameGUI {

    /**
     * The main method to start the application.
     */
    public static void main(String[] args) {
        // Create the main window (Frame)
        JFrame frame = new JFrame("My Simple Game");

        // Set what happens when the user closes the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the custom game panel to the frame
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        // Automatically size the frame to fit the preferred size of its components (GamePanel)
        frame.pack();

        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}

// -------------------------------------------------------------




class GamePanel extends JPanel implements ActionListener {

    // Define the game resolution
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JButton startButton;
    private JButton doneButton;
    private JButton flavorButton;
    private JButton toppingsButton;
    private JButton catButton;

    private boolean startShowing = true;
    private int chooseCat = 1;

    private Image foregroundImage;

    

    

     private Image backgroundImage;
     private Image menuImage;


     private int playerX = 0; //may not be necessary, will followup later
     private int playerY = 0;


    public GamePanel() {
        setLayout(null);
        
        // Set the size of the panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // Set the background color in case the actual image doesn't load
        setBackground(Color.BLACK);

        loadBackgroundImage("assets/background.png");
        loadMenuImage("assets/menubarback.png");

        switch(chooseCat){
            case 1: loadForegroundImage("assets/sophieBig.png");
                break;
            case 2: loadForegroundImage("assets/pippiBig.png");
                break;
            case 3: loadForegroundImage("assets/sammiBig.png");
                break;
        }
        

//button images
        ImageIcon startIcon = new ImageIcon("assets/startButton.png");
        int startWidth = startIcon.getIconWidth();
        int startHeight = startIcon.getIconHeight();

        ImageIcon doneIcon = new ImageIcon("assets/doneButton.png");
        int doneWidth = doneIcon.getIconWidth();
        int doneHeight = doneIcon.getIconHeight();

        ImageIcon flavorIcon = new ImageIcon("assets/flavorButton.png");
        int flavorWidth = flavorIcon.getIconWidth();
        int flavorHeight = flavorIcon.getIconHeight();

        ImageIcon toppingsIcon = new ImageIcon("assets/toppingsButton.png");
        int toppingsWidth = toppingsIcon.getIconWidth();
        int toppingsHeight = toppingsIcon.getIconHeight();


        startButton = CustomButtonCreator.createCustomImageButton(
        "assets/startButton.png",  
        "assets/startButton.png");
        startButton.setBounds(250, 150, startWidth, startHeight);


        doneButton = CustomButtonCreator.createCustomImageButton(
            "assets/doneButton.png", 
            "assets/doneButton.png");

        doneButton.setBounds(550,528, doneWidth, doneHeight);

        doneButton.setVisible(false);


        flavorButton = CustomButtonCreator.createCustomImageButton(
            "assets/flavorButton.png", 
            "assets/flavorButton.png");

        flavorButton.setBounds(75,528, flavorWidth, flavorHeight);
        
        flavorButton.setVisible(false);
        

        toppingsButton = CustomButtonCreator.createCustomImageButton(
            "assets/toppingsButton.png", 
            "assets/toppingsButton.png");

        toppingsButton.setBounds(250,528, toppingsWidth, toppingsHeight);
        toppingsButton.setVisible(false);

        catButton = CustomButtonCreator.createCustomImageButton(
            "assets/blank.png", 
            "assets/blank.png");
        catButton.setBounds(25,400, 100, 100);
        catButton.setVisible(false);


        add(startButton);
        startButton.addActionListener(this);
        
        add(doneButton);
        add(flavorButton);
        add(toppingsButton);
        add(catButton);
        doneButton.addActionListener(this);
        flavorButton.addActionListener(this);
        toppingsButton.addActionListener(this);
        catButton.addActionListener(this);
        
    }

    
//Add foreground image, intended for in between menubar and background
    private void loadForegroundImage(String imagePath) {
        try {
            foregroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.err.println("Error loading foreground image: " + imagePath);
            foregroundImage = null;
        }
    }

    private void loadBackgroundImage(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.err.println("Error loading background image: " + imagePath);
            e.printStackTrace();
            // If the image fails to load, backgroundImage will be null
        }
    }
    private void loadMenuImage(String imagePath) {
        try {
            menuImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.err.println("Error loading foreground image: " + imagePath);
            menuImage = null;
        }
    }

    /**
     * This method is called by the Swing system to draw the contents of the panel.
     * All game graphics are drawn here.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // --- DRAW BACKGROUND IMAGE FIRST ---
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, this);
        }

        if (!startShowing && foregroundImage != null){//only draw image if the start button has been clicked
            g.drawImage(foregroundImage, 0,0,800,600, this);
        }

        if (menuImage != null) {
            g.drawImage(menuImage, 
                    playerX, 
                    playerY, 
                    menuImage.getWidth(this), 
                    menuImage.getHeight(this), 
                    this);
        }

        //Draw a rectangle as a test
        g.setColor(Color.BLUE);
        g.fillRect(50, 50, 50, 50);

        // Draw some text as a test
        g.setColor(Color.GREEN);
        g.setFont(g.getFont().deriveFont(24f));
        g.drawString("Test text", 280, 300);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which component triggered the event
        if (e.getSource() == startButton) {
            
            System.out.println("Start Game");
            
            startButton.setVisible(false);
            startShowing = false;//flag to let us know the start button is no longer visible
            doneButton.setVisible(true);
            flavorButton.setVisible(true);
            toppingsButton.setVisible(true);
            catButton.setVisible(true);
            
            repaint(); 


        } else if (e.getSource() == doneButton){
            System.out.println("Done button");//test

        } else if (e.getSource() == flavorButton){
            System.out.println("Flavor button");
        } else if (e.getSource() == toppingsButton){
            System.out.println("Toppings button");//test
        } else if (e.getSource() == catButton){
            System.out.println("cat");//test

            switch (chooseCat){//choose which cat appears on screen
                case 1: chooseCat += 1;
                    break;
                case 2: chooseCat += 1;
                    break;
                case 3: chooseCat = 1;
                    break;

            }

            switch(chooseCat){
            case 1: loadForegroundImage("assets/sophieBig.png");
                break;
            case 2: loadForegroundImage("assets/pippiBig.png");
                break;
            case 3: loadForegroundImage("assets/sammiBig.png");
                break;
            }

            repaint();
        }
    }
}
