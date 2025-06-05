/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri;
import bendageometri.benda2d.Lingkaran;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nbnrc
 */
public class MultiThreadingDemo {
    public static void main(String[] args) {
        System.out.println("--- Demo Multithreading dengan Lingkaran ---");

        // 1. Buat data Lingkaran
        List<Lingkaran> daftarLingkaran = new ArrayList<>();
        daftarLingkaran.add(new Lingkaran(5.0));
        daftarLingkaran.add(new Lingkaran(7.0));
        daftarLingkaran.add(new Lingkaran(3.5));
        daftarLingkaran.add(new Lingkaran(10.2));
        daftarLingkaran.add(new Lingkaran(1.0));
        // Tambahkan lebih banyak lingkaran jika ingin melihat efeknya lebih jelas
        for (int i = 1; i <= 20; i++) {
             daftarLingkaran.add(new Lingkaran(2.0 * i));
        }


        // 2. Buat list untuk menyimpan Runnable tasks dan Threads
        List<LingkaranCalculator> kalkulatorTasks = new ArrayList<>();
        List<Thread> daftarThread = new ArrayList<>();

        System.out.println("\nMembuat dan memulai thread...");
        for (Lingkaran l : daftarLingkaran) {
            try {
                LingkaranCalculator task = new LingkaranCalculator(l);
                kalkulatorTasks.add(task);
                
                Thread thread = new Thread(task);
                daftarThread.add(thread);
                thread.start(); // Memulai eksekusi thread
            } catch (IllegalArgumentException e) {
                System.err.println("Gagal membuat Lingkaran atau Task: " + e.getMessage());
            }
        }

        // 3. Tunggu semua thread selesai (join)
        System.out.println("\nMenunggu semua thread selesai...");
        for (Thread t : daftarThread) {
            try {
                t.join(); // Thread utama akan menunggu thread 't' ini selesai
            } catch (InterruptedException e) {
                System.err.println("Thread terinterupsi: " + e.getMessage());
                Thread.currentThread().interrupt(); // Set status interrupt
            }
        }

        // 4. Kumpulkan dan tampilkan hasil
        System.out.println("\n--- Hasil Perhitungan Paralel ---");
        for (LingkaranCalculator task : kalkulatorTasks) {
            if (task.getHasil() != null) {
                System.out.println(task.getHasil());
            } else {
                // Ini bisa terjadi jika ada error saat pembuatan Lingkaran atau task tidak sempat berjalan karena error awal
                System.out.println("Sebuah task tidak menghasilkan hasil (mungkin karena error sebelumnya).");
            }
        }

        System.out.println("\n--- Semua proses selesai ---");
    }
}

