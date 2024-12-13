package Controller;

import Model.User;
import View.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerAll {
    private DatabaseHandler dbHandling;
    private LoginView loginView;
    private ArtworkListView artworkListView;
    private AddArtworkView addArtworkView;

    private User loggedInUser;

    public ControllerAll(DatabaseHandler dbHandling) {
        this.dbHandling = dbHandling;
        loginView = new LoginView();
        loginView.setVisible(true);

        loginView.addLoginListener(e -> handleLogin());
    }

    private void handleLogin() {
        String email = loginView.getEmail();
        String password = loginView.getPassword();

        try (Connection conn = dbHandling.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                loggedInUser = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"));
                loginView.dispose();
                openArtworkListView();
            } else {
                JOptionPane.showMessageDialog(loginView, "pass / email salah.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openArtworkListView() {
        artworkListView = new ArtworkListView();
        artworkListView.setVisible(true);

        artworkListView.addAddArtworkListener(e -> openAddArtworkView());

        loadArtworks();
    }

    private void openAddArtworkView() {
        addArtworkView = new AddArtworkView();
        addArtworkView.setVisible(true);
        addArtworkView.addUploadListener(e -> handleUploadImage());
        addArtworkView.addSubmitListener(e -> handleAddArtwork());
    }

    private void handleUploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(addArtworkView);

        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            addArtworkView.setImagePath(selectedFilePath);
        }
    }

    private void handleAddArtwork() {
        String title = addArtworkView.getTitle();
        String description = addArtworkView.getDescription();
        String imagePath = addArtworkView.getImagePath();

        try (Connection conn = dbHandling.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO artworks (title, description, image_path, user_id) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, imagePath);
            stmt.setInt(4, loggedInUser.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(addArtworkView, "foto terupload.");
            addArtworkView.dispose();
            loadArtworks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadArtworks() {
        List<String> artworks = new ArrayList<>();
        try (Connection conn = dbHandling.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM artworks WHERE user_id = ?")) {
            stmt.setInt(1, loggedInUser.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                artworks.add("Title: " + rs.getString("title") + ", Description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        artworkListView.displayArtworks(artworks);
    }
}