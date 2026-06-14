package com.mycompany.manajemenkursus_tubespbo;

import java.sql.*;
import java.util.*;

public class DataMateri {

    public static List<String[]> bacaSemua() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT `Nama Materi`, `Nama Pengajar`, `Isi` FROM materi";
        try (Connection con = Koneksi.getConnection();
             Statement  st  = con.createStatement();
             ResultSet  rs  = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("Nama Materi"),
                    rs.getString("Nama Pengajar") != null ? rs.getString("Nama Pengajar") : "",
                    rs.getString("Isi")           != null ? rs.getString("Isi")           : ""
                });
            }
        } catch (SQLException e) {
            showError("Gagal membaca data", e);
        }
        return list;
    }

    public static boolean tambah(String namaMateri, String namaPengajar, String isi) {
        String sql = "INSERT INTO materi (`Nama Materi`, `Nama Pengajar`, `Isi`) VALUES (?, ?, ?)";
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, namaMateri);
            ps.setString(2, namaPengajar);
            ps.setString(3, isi);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            showError("Gagal menambah data", e);
            return false;
        }
    }
    public static boolean edit(String judulLama, String judulBaru, String pengajarBaru, String isiBaru) {
        String sql = "UPDATE materi SET `Nama Materi`=?, `Nama Pengajar`=?, `Isi`=? " +
                     "WHERE `Nama Materi`=?";
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, judulBaru);
            ps.setString(2, pengajarBaru);
            ps.setString(3, isiBaru);
            ps.setString(4, judulLama);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            showError("Gagal mengedit data", e);
            return false;
        }
    }

    public static boolean hapus(String namaMateri) {
        String sql = "DELETE FROM materi WHERE `Nama Materi`=?";
        try (Connection con = Koneksi.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, namaMateri);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            showError("Gagal menghapus data", e);
            return false;
        }
    }

    public static int jumlah() {
        return bacaSemua().size();
    }

    private static void showError(String msg, SQLException e) {
        System.err.println(msg + ": " + e.getMessage());
        javax.swing.JOptionPane.showMessageDialog(null,
            msg + ":\n" + e.getMessage(), "Error DB",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}