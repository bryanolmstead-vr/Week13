import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Component;
import javax.swing.border.Border;

// Class to get images from the resources directory
class GetImage {
    public JLabel getImage(String filename) {
        return new JLabel(new ImageIcon(getClass().getResource("/resources/"+filename)));
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Week13");

        // Create a JPanel using BoxLayout and set its size
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(300, 450));
        panel.setMaximumSize(new Dimension(300, 450));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set panel to a background color based on a RGB hashcode
        String bushnellBlueHashCode = "#011E40";
        Color bushnellBlue = Color.decode(bushnellBlueHashCode);
        panel.setBackground(bushnellBlue);

        // Place an image on the panel as a JLabel
        JLabel logo = new GetImage().getImage("BushnellLogo.png");
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(logo);
        panel.setVisible(true);  

        // Create a 7x4 grid of JLabels numbered 0-27
        // Create an outline around each label and center the text
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(7, 4));
        gridPanel.setMaximumSize(new Dimension(250, 250));
        gridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gridPanel.setBorder(border);
        for (int i=0; i<28; i++) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setFont(new Font("Sans-Serif", Font.BOLD, 20));
            label.setPreferredSize(new Dimension(50,50));
            label.setHorizontalAlignment(SwingConstants.CENTER); 
            label.setBorder(border);
            gridPanel.add(label);
        }
        panel.add(gridPanel);

        // create a field where phone number can be validated and displayed properly
        JTextField phoneNumber = new JTextField(15);
        phoneNumber.setPreferredSize(new Dimension(100,30));
        phoneNumber.setFont(new Font("Sans-Serif", Font.BOLD, 26));

        phoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get raw text, eliminate everything but digits, add leading zeros to 10 digits, format to "(###) ###-####"
                String rawText = phoneNumber.getText();
                String digitsText = rawText.replaceAll("[^0-9]","");
                long   digitsVal;
                if (digitsText.length() == 0) {
                    digitsVal = 0;
                } else {
                    digitsVal  = Long.parseLong(digitsText);
                }
                if (digitsVal < 10000000L) {
                    digitsVal += 5410000000L;
                }
                String zeroPad = String.format("%010d", digitsVal);
                String phoneFormatted = "(" + zeroPad.substring(0,3) + ") " + zeroPad.substring(3,6) + "-" + zeroPad.substring(6,10);
                phoneNumber.setText(phoneFormatted);
                }
            });
        panel.add(phoneNumber);

        // Create an array of strings to display as JLabels
        String[] topic = new String[4];
        topic[0] = "anger";
        topic[1] = "stress";
        topic[2] = "relationship";
        topic[3] = "depression";
        for (int i=0; i<topic.length; i++) {
            JLabel topicLabel = new JLabel(topic[i]);
            topicLabel.setFont(new Font("Sans-Serif", Font.BOLD, 18));
            topicLabel.setForeground(Color.WHITE);
            topicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(topicLabel);
        }

        frame.add(panel); 
        frame.pack(); 
        frame.setVisible(true);
    }
}
