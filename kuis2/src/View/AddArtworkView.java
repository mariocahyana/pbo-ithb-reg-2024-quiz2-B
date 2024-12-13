package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddArtworkView extends JFrame {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JButton uploadButton;
    private JButton submitButton;
    private JLabel imagePathLabel;
    private String imagePath;

    public AddArtworkView() {
        setTitle("Add Artwork");
        setSize(488, 378);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(12, 20, 80, 23);
        panel.add(titleLabel);

        titleField = new JTextField(20);
        titleField.setBounds(100, 20, 165, 23);
        panel.add(titleField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(12, 60, 80, 23);
        panel.add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(100, 60, 165, 70);
        panel.add(descriptionArea);

        uploadButton = new JButton("Upload Image");
        uploadButton.setBounds(12, 140, 120, 23);
        panel.add(uploadButton);

        imagePathLabel = new JLabel("No file selected");
        imagePathLabel.setBounds(155, 140, 200, 23);
        panel.add(imagePathLabel);

        submitButton = new JButton("Submit");
        submitButton.setBounds(12, 195, 80, 23);
        panel.add(submitButton);

        add(panel);
    }

    public String getTitle() {
        return titleField.getText();
    }

    public String getDescription() {
        return descriptionArea.getText();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String path) {
        this.imagePath = path;
        imagePathLabel.setText(path);
    }

    public void addUploadListener(ActionListener listener) {
        uploadButton.addActionListener(listener);
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}
