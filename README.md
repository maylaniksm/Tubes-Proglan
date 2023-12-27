**Gambaran Aplikasi:**
- Aplikasi ini adalah alat berbasis JavaFX untuk mengelola reservasi lapangan futsal.
- Pengguna dapat memasukkan nama mereka, memilih tanggal, dan memilih waktu mulai dan selesai untuk memesan lapangan futsal.
- Aplikasi menyediakan antarmuka yang ramah pengguna dengan bidang input, tombol, dan tabel yang menampilkan reservasi.

**User Interface:**
1. **Bagian Input:**
    - **Kolom Nama:** Memungkinkan pengguna memasukkan nama mereka.
    - **Pemilih Tanggal:** Memungkinkan pengguna memilih tanggal untuk reservasi.
    - **ComboBox Waktu Mulai dan Waktu Selesai:** Dropdown untuk memilih waktu mulai dan selesai.

2. **Tombol Aksi:**
    - **Tombol "Sewa Lapangan":** Memulai proses reservasi, memvalidasi input, dan memperbarui daftar reservasi.
    - **Tombol "Update":** Memungkinkan pengguna memperbarui reservasi yang sudah ada dengan informasi baru.
    - **Tombol "Hapus":** Memungkinkan pengguna menghapus reservasi yang sudah ada.

3. **Tabel Reservasi:**
    - Menampilkan tabel dengan kolom untuk nama penyewa, tanggal, waktu mulai, dan waktu selesai.
    - Pengguna dapat melihat reservasi yang sudah ada dalam format tabel.

**Logika Reservasi:**
- Reservasi ditambahkan ke sistem dengan kombinasi unik dari tanggal, waktu mulai, dan waktu selesai.
- Validasi waktu memastikan bahwa reservasi memiliki waktu mulai dan waktu selesai yang valid dalam format HH:00.
- Konflik diperiksa untuk mencegah adanya reservasi yang tumpang tindih untuk tanggal dan waktu yang sama.

**Manajemen Data:**
- ObservableList digunakan untuk menyimpan reservasi, memungkinkan pembaruan UI secara otomatis ketika data berubah.
- Reservasi adalah instansi dari kelas `SewaLapangan`, mengemas informasi tentang setiap reservasi.

**Error Handling:**
- Aplikasi menyediakan dialog kesalahan untuk berbagai skenario, seperti bidang yang tidak lengkap, format waktu yang tidak valid, atau reservasi yang bertabrakan.

**Fungsionalitas:**
- Pengguna dapat menambahkan, memperbarui, dan menghapus reservasi.
- Aplikasi mencegah reservasi dengan slot waktu yang tidak valid atau tumpang tindih.
- Antarmuka pengguna mencerminkan perubahan secara real-time, memberikan pengalaman pengguna yang dinamis dan responsif.
