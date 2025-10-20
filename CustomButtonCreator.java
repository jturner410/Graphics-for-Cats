import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class CustomButtonCreator {

    /**
     * Creates and returns a fully customized JButton using image icons.
     * @param normalImagePath Path to the image for the normal button state.
     * @param pressedImagePath Path to the image for the pressed button state.
     * @return A customized JButton instance.
     */
    public static JButton createCustomImageButton(String normalImagePath, String pressedImagePath) {
        
        // 1. Load the images as Icons
        ImageIcon normalIcon = new ImageIcon(normalImagePath);
        ImageIcon pressedIcon = new ImageIcon(pressedImagePath);
        
        // 2. Create the button
        JButton customButton = new JButton(); 
        
        // 3. Apply the Icons
        customButton.setIcon(normalIcon);
        customButton.setPressedIcon(pressedIcon); // Icon shown when the mouse button is down

        // Optional: Set a rollover (hover) icon if you have a third image
        // customButton.setRolloverIcon(new ImageIcon("path/to/button_hover.png"));

        // 4. Customize Appearance to show ONLY the image (Crucial for games)
        
        // a. Remove the background color filling
        customButton.setContentAreaFilled(false); 
        
        // b. Remove the default border drawn by the Look and Feel
        customButton.setBorderPainted(false); 
        
        // c. Remove the ugly border that appears when the button has focus
        customButton.setFocusPainted(false); 
        
        // d. Ensure no text is displayed (if your image includes text)
        customButton.setText(""); 
        
        // 5. Set the size based on the image size
        int width = normalIcon.getIconWidth();
        int height = normalIcon.getIconHeight();
        customButton.setPreferredSize(new Dimension(width, height));
        customButton.setSize(width, height);
        
        return customButton;
    }
}