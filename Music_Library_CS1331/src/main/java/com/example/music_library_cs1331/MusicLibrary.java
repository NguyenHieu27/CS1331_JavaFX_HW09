package com.example.music_library_cs1331;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.Random;

/**
 * This JavaFX class represents a music library.
 * @author Pnguyen337
 * @version 1.0
 */
public class MusicLibrary extends Application {
    private static final int MAX_SONGS = 6;
    private TilePane libraryPane;
    private TextField titleInput;
    private TextField albumInput;
    private ComboBox<Genres> genreBox;
    private Label emptyLabels;
    private Label addLabel;
    private TextField songNameField;
    private TextField albumNameField;
    private String[] songTitles = new String[MAX_SONGS];
    private String[] albumNames = new String[MAX_SONGS];
    private String[] songGenres = new String[MAX_SONGS];
    private int i = 0;
    private int j = 0;
    private GridPane tileBox;

    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("file:///C:/Users/nguye/IdeaProjects/Music_Library_CS1331/src/main/java/com/example/music_library_cs1331/musicImage.jpg");
        ImageView imageView = new ImageView(image);
        Pane pane = new Pane();
        pane.getChildren().add(imageView);
        Label libraryLabel = new Label("My Music Library");
        libraryLabel.setTextFill(Color.WHITESMOKE);
        Font font = Font.font("Gotham Medium", FontWeight.BOLD, 40);
        libraryLabel.setFont(font);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(libraryLabel);
        VBox.setMargin(libraryLabel, new Insets(6, 0, 0, 16));
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(10));
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        tilePane.getChildren().addAll(imageView);
        tileBox = new GridPane();
        tileBox.setPadding(new Insets(10));
        tileBox.setHgap(10);
        tileBox.setVgap(10);
        tileBox.setAlignment(Pos.CENTER_LEFT);
        tileBox.setMargin(tileBox, new Insets(0, 0, 0, 10));
        for (int k = 0; k < MAX_SONGS; k++) {
            songTitles[k] = "";
            albumNames[k] = "";
            songGenres[k] = "";
        }
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                if (index < MAX_SONGS) {
                    emptyLabels = new Label("Empty");
                    emptyLabels.setFont(Font.font("Nexa Book", FontPosture.ITALIC, 17));
                    emptyLabels.setAlignment(Pos.CENTER);
                    emptyLabels.setTextFill(Color.WHITESMOKE);
                    emptyLabels.setPrefSize(145, 145);
                    emptyLabels.setWrapText(true);
                    BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(68, 68, 68),
                            new CornerRadii(5), Insets.EMPTY);
                    Background background = new Background(backgroundFill);
                    emptyLabels.setBackground(background);
                    tileBox.add(emptyLabels, col, row);

                    emptyLabels.setOnMouseEntered(event -> {
                        Label empty = (Label) (event.getSource());
                        System.out.println("Entered");
                        if (songTitles[index].isEmpty()
                                && empty.getText().equals("Empty")) {
                            empty.setText("Add (+)");
                            empty.setFont(Font.font("Nexa", FontWeight.BOLD,
                                    FontPosture.REGULAR, 18));
                            empty.setTextFill(Color.WHITE);
                            BackgroundFill backgroundFill2 = new BackgroundFill(Color.rgb(76, 76, 76),
                                    new CornerRadii(5), Insets.EMPTY);
                            Background background2 = new Background(backgroundFill2);
                            empty.setBackground(background2);
                        }
                    });
                    emptyLabels.setOnMouseClicked(event -> {
                        Label empty = (Label) (event.getSource());
                        System.out.println("Clicked");
                        songTitles[index] = "";
                        albumNames[index] = "";
                        songGenres[index] = "";
                        empty.setText("Empty");
                        empty.setFont(Font.font("Nexa Book", FontPosture.ITALIC, 17));
                        empty.setTextFill(Color.WHITESMOKE);
                        empty.setBackground(background);
                    });
                    emptyLabels.setOnMouseExited(event -> {
                        System.out.println("Exit");
                        Label empty = (Label) (event.getSource());
                        if (songTitles[index].isEmpty() && empty.getText().equals("Add (+)")) {
                            empty.setText("Empty");
                            empty.setFont(Font.font("Nexa Book", FontPosture.ITALIC, 17));
                            empty.setTextFill(Color.WHITESMOKE);
                            empty.setBackground(background);
                        }
                    });
                }
            }
        }
        songNameField = new TextField();
        albumNameField = new TextField();
        songNameField.setOnAction(event -> {
            Label empty = (Label) (event.getSource());
            if (empty.getText().equals("Empty")) {
                empty.setText(songNameField.getText());
            }
            songNameField.clear();
        });

        HBox inputBox = new HBox();
        inputBox.setSpacing(10);
        inputBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        inputBox.setAlignment((Pos.BOTTOM_LEFT));
        Label inputLabel = new Label("Add a new song:");
        inputLabel.setTextFill(Color.WHITESMOKE);
        Font fontInput = Font.font("Spotify Circular Book", 17);
        inputLabel.setFont(fontInput);
        titleInput = new TextField();
        titleInput.setPromptText("Song Title");
        albumInput = new TextField();
        albumInput.setPromptText("Album Name");
        genreBox = new ComboBox<>();
        genreBox.getItems().addAll(Arrays.asList(Genres.values()));
        genreBox.getSelectionModel().select(Genres.POP);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);
        Button addButton = new Button("Add Song");
        Font fontAdd = Font.font("Gotham Medium");
        addButton.setFont(fontAdd);
        addButton.setOnAction(event -> addSong());

        buttonBox.getChildren().add(addButton);
        inputBox.getChildren().addAll(inputLabel, titleInput, albumInput, genreBox, buttonBox);
        HBox.setMargin(inputLabel, new Insets(0, 0, 15, 20));
        HBox.setMargin(titleInput, new Insets(0, 0, 15, 5));
        HBox.setMargin(albumInput, new Insets(0, 0, 15, 5));
        HBox.setMargin(genreBox, new Insets(0, 0, 15, 5));
        HBox.setMargin(addButton, new Insets(0, 0, 15, 5));

        StackPane root = new StackPane();
        root.getChildren().addAll(tilePane, vbox, tileBox);
        StackPane.setAlignment(inputBox, Pos.BOTTOM_LEFT);
        root.setMargin(tileBox, new Insets(0, 0, 0, 18));
        root.getChildren().add(inputBox);
        Scene scene = new Scene(root, image.getWidth(), image.getHeight());
        imageView.setFitWidth(image.getWidth());
        imageView.setFitHeight(image.getHeight());

        pane.setMaxSize(image.getWidth(), image.getHeight());
        primaryStage.setMaxWidth(image.getWidth());
        primaryStage.setMaxHeight(image.getHeight());
        pane.setMinSize(550, 800);
        primaryStage.setMinWidth(730);
        primaryStage.setMinHeight(500);
        primaryStage.setTitle("My Music Library");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Adding songs into the library upon clicking the button.
     * Set up song titles, albums, and genre.
     */
    private void addSong() {
        boolean songAdded = false;
        String title = "";
        String album = "";
        Genres genre;

        // Find empty label
        Label emptyLabel = null;
        for (j = 0; j < i; j++) {
            Label checkEmpty = (Label) tileBox.getChildren().get(j);
            if (checkEmpty.getText().equals("Empty")) {
                emptyLabel = checkEmpty;
                break;
            }
        }

        // If there is an empty label, add song to it
        if (emptyLabel != null) {
            title = titleInput.getText().trim().isEmpty() ? "Untitled"
                    : titleInput.getText().trim();
            album = albumInput.getText().trim().isEmpty() ? "Unknown"
                    : albumInput.getText().trim();
            genre = genreBox.getSelectionModel().getSelectedItem();
            emptyLabel.setText(title + "\n" + album + "\n" + genre);
            emptyLabel.setTextFill(Color.WHITE);
            emptyLabel.setAlignment(Pos.CENTER);
            emptyLabel.setTextAlignment(TextAlignment.CENTER);
            emptyLabel.setFont(Font.font("Nexa", FontWeight.BOLD,
                    FontPosture.REGULAR, 18));
            Color random = generateColor();
            BackgroundFill backgroundFill = new BackgroundFill(random,
                    new CornerRadii(5), Insets.EMPTY);
            Background background = new Background(backgroundFill);
            emptyLabel.setBackground(background);
        } else if (i < MAX_SONGS) {
            title = titleInput.getText().trim().isEmpty() ? "Untitled"
                    : titleInput.getText().trim();
            album = albumInput.getText().trim().isEmpty() ? "Unknown"
                    : albumInput.getText().trim();
            genre = genreBox.getSelectionModel().getSelectedItem();
            addLabel = (Label) tileBox.getChildren().get(i);
            addLabel.setText(title + "\n" + album + "\n" + genre);
            addLabel.setTextFill(Color.WHITE);
            addLabel.setAlignment(Pos.CENTER);
            addLabel.setTextAlignment(TextAlignment.CENTER);
            addLabel.setFont(Font.font("Nexa", FontWeight.BOLD,
                    FontPosture.REGULAR, 18));
            Color random = generateColor();
            BackgroundFill backgroundFill = new BackgroundFill(random,
                    new CornerRadii(5), Insets.EMPTY);
            Background background = new Background(backgroundFill);
            addLabel.setBackground(background);
            i++;
        } else {
            songAdded = true;
        }

        if (songAdded) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Music Library Full");
            alert.setHeaderText("Your music library is full!");
            alert.setContentText("Please delete a song before adding a new one.");
            alert.showAndWait();
        }

        // Clear the input fields
        titleInput.clear();
        albumInput.clear();
        genreBox.getSelectionModel().select(Genres.POP);
    }

    /**
     * A method helper that generates
     * random color for Label background.
     * @return randomized color.
     */
    public static Color generateColor() {
        Random random = new Random();
        float h = random.nextFloat() * 360;
        float s = random.nextFloat() * 0.3f + 0.3f;
        float b = random.nextFloat() * 0.4f + 0.5f;
        return Color.hsb(h, s, b);
    }

    /**
     * Main method to run the code.
     * @param args arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
