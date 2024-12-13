package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ArtworkListView extends JFrame {
    private JPanel artworkPanel;
    private JButton addArtworkButton;

    public ArtworkListView() {
        setTitle("Artwork List");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        artworkPanel = new JPanel();
        artworkPanel.setLayout(new BoxLayout(artworkPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(artworkPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        addArtworkButton = new JButton("Add Artwork");
        add(addArtworkButton, BorderLayout.SOUTH);
    }

    public void displayArtworks(List<String> artworks) {
        artworkPanel.removeAll();
        for (String artwork : artworks) {
            artworkPanel.add(new JLabel(artwork));
        }
        artworkPanel.revalidate();
        artworkPanel.repaint();
    }

    public void addAddArtworkListener(ActionListener listener) {
        addArtworkButton.addActionListener(listener);
    }
}
