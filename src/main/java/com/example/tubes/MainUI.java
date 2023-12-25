// MainUI.java
package com.example.tubes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class MainUI {
    private TextField namaField;
    private DatePicker tanggalPicker;
    private ComboBox<String> jamMulaiComboBox;
    private ComboBox<String> jamSelesaiComboBox;
    private TableView<SewaLapangan> tableView;
    private ObservableList<SewaLapangan> data = FXCollections.observableArrayList();

    private GridPane grid;

    public MainUI() {
        createUI();
    }

    private void createUI() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setAlignment(Pos.CENTER_LEFT);

        // Nama Penyewa
        grid.add(new Label("Nama Penyewa:"), 0, 0);
        namaField = new TextField();
        grid.add(namaField, 1, 0);

        // Tanggal
        grid.add(new Label("Tanggal:"), 0, 1);
        tanggalPicker = new DatePicker();
        grid.add(tanggalPicker, 1, 1);

        // Jam Mulai
        grid.add(new Label("Jam Mulai:"), 0, 2);
        jamMulaiComboBox = new ComboBox<>();
        fillJamComboBox(jamMulaiComboBox);
        grid.add(jamMulaiComboBox, 1, 2);

        // Jam Selesai
        grid.add(new Label("Jam Selesai:"), 0, 3);
        jamSelesaiComboBox = new ComboBox<>();
        fillJamComboBox(jamSelesaiComboBox);
        grid.add(jamSelesaiComboBox, 1, 3);

        // Tombol untuk menyewa lapangan
        Button sewaButton = new Button("Sewa Lapangan");
        sewaButton.setOnAction(e -> sewaLapangan());
        grid.add(sewaButton, 0, 4);

        // Tombol untuk mengupdate data
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateSewaLapangan());
        grid.add(updateButton, 1, 4);

        // Tombol untuk menghapus data
        Button deleteButton = new Button("Hapus");
        deleteButton.setOnAction(e -> hapusSewaLapangan());
        grid.add(deleteButton, 2, 4);

        // Tabel
        tableView = new TableView<>();
        tableView.setEditable(false);

        TableColumn<SewaLapangan, String> namaCol = new TableColumn<>("Nama Penyewa");
        namaCol.setCellValueFactory(new PropertyValueFactory<>("namaPenyewa"));
        namaCol.setMinWidth(165);

        TableColumn<SewaLapangan, String> tanggalCol = new TableColumn<>("Tanggal");
        tanggalCol.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        TableColumn<SewaLapangan, String> jamMulaiCol = new TableColumn<>("Jam Mulai");
        jamMulaiCol.setCellValueFactory(new PropertyValueFactory<>("jamMulai"));

        TableColumn<SewaLapangan, String> jamSelesaiCol = new TableColumn<>("Jam Selesai");
        jamSelesaiCol.setCellValueFactory(new PropertyValueFactory<>("jamSelesai"));

        tableView.getColumns().addAll(namaCol, tanggalCol, jamMulaiCol, jamSelesaiCol);
        grid.add(tableView, 0, 5, 3, 1);
        tableView.setMinWidth(400);

    }
    public GridPane getGrid() {
        return grid;
    }

    private void fillJamComboBox(ComboBox<String> comboBox) {
        for (int i = 8; i <= 23; i++) {
            comboBox.getItems().add(String.format("%02d:00", i));
        }
    }
    private void sewaLapangan() {
        String namaPenyewa = namaField.getText().trim();
        String tanggal = String.valueOf(tanggalPicker.getValue());
        String jamMulai = jamMulaiComboBox.getValue();
        String jamSelesai = jamSelesaiComboBox.getValue();

        if (namaPenyewa.isEmpty() || tanggal == null || jamMulai == null || jamSelesai == null) {
            showErrorDialog("Mohon lengkapi semua field!");
        } else if (!isValidTime(jamMulai) || !isValidTime(jamSelesai)) {
            showErrorDialog("Format jam tidak valid. Gunakan format HH:00.");
        } else if (jamMulai.compareTo(jamSelesai) >= 0) {
            showErrorDialog("Jam mulai harus sebelum jam selesai.");
        } else if (cekKonflik(tanggal, jamMulai, jamSelesai)) {
            showErrorDialog("Jadwal pada tanggal dan jam tersebut sudah terisi.");
        } else {
            data.add(new SewaLapangan(namaPenyewa, tanggal.toString(), jamMulai, jamSelesai));

            namaField.clear();
            tanggalPicker.setValue(null);
            jamMulaiComboBox.setValue(null);
            jamSelesaiComboBox.setValue(null);

            tableView.setItems(data);
        }
    }

    private void updateSewaLapangan() {
        SewaLapangan selectedSewa = tableView.getSelectionModel().getSelectedItem();
        if (selectedSewa != null) {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

            String namaPenyewa = namaField.getText().trim();
            String tanggal = String.valueOf(tanggalPicker.getValue());
            String jamMulai = jamMulaiComboBox.getValue();
            String jamSelesai = jamSelesaiComboBox.getValue();

            if (namaPenyewa.isEmpty() || tanggal == null || jamMulai == null || jamSelesai == null) {
                showErrorDialog("Mohon lengkapi semua field!");
            } else if (!isValidTime(jamMulai) || !isValidTime(jamSelesai)) {
                showErrorDialog("Format jam tidak valid. Gunakan format HH:00.");
            } else if (jamMulai.compareTo(jamSelesai) >= 0) {
                showErrorDialog("Jam mulai harus sebelum jam selesai.");
            } else if (cekKonflik(tanggal, jamMulai, jamSelesai)) {
                showErrorDialog("Jadwal pada tanggal dan jam tersebut sudah terisi.");
            } else {
                data.set(selectedIndex, new SewaLapangan(namaPenyewa, tanggal, jamMulai, jamSelesai));

                namaField.clear();
                tanggalPicker.setValue(null);
                jamMulaiComboBox.setValue(null);
                jamSelesaiComboBox.setValue(null);

                tableView.setItems(data);
            }
        } else {
            showErrorDialog("Pilih data yang akan diupdate.");
        }
    }
    private void hapusSewaLapangan() {
        SewaLapangan selectedSewa = tableView.getSelectionModel().getSelectedItem();
        if (selectedSewa != null) {
            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

            data.remove(selectedIndex);

            namaField.clear();
            tanggalPicker.setValue(null);
            jamMulaiComboBox.setValue(null);
            jamSelesaiComboBox.setValue(null);

            tableView.setItems(data);
        } else {
            showErrorDialog("Pilih data yang akan dihapus.");
        }
    }
    private boolean cekKonflik(String tanggal, String jamMulai, String jamSelesai) {
        for (SewaLapangan sewa : data) {
            if (sewa.getTanggal().equals(tanggal) && sewa.getJamMulai().equals(jamMulai) && sewa.getJamSelesai().equals(jamSelesai)) {
                return true;
            }
            if (sewa.getTanggal().equals(tanggal) &&
                    ((jamMulai.compareTo(sewa.getJamSelesai()) < 0 && jamSelesai.compareTo(sewa.getJamMulai()) > 0) ||
                            (sewa.getJamMulai().compareTo(jamSelesai) < 0 && sewa.getJamSelesai().compareTo(jamMulai) > 0))) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidTime(String time) {
        return time.matches("([01]?[0-9]|2[0-3]):00");
    }
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
