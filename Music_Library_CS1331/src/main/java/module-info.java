module com.example.music_library_cs1331 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.music_library_cs1331 to javafx.fxml;
    exports com.example.music_library_cs1331;
}