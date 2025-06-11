/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class Trapesium extends Benda2D implements Runnable {
    public double sisiAtas;
    public double sisiBawah;
    public double tinggi;
    public double sisiMiring1;
    public double sisiMiring2;
    public double luas;
    public double keliling;
    
    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiMiring1, double sisiMiring2) {
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0 || sisiMiring1 <= 0 || sisiMiring2 <= 0) {
            throw new IllegalArgumentException("Semua parameter harus bernilai positif.");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
        this.sisiMiring1 = sisiMiring1;
        this.sisiMiring2 = sisiMiring2;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    @Override
    public double hitungLuas() {
        this.luas = 0.5 * (this.sisiAtas + this.sisiBawah) * this.tinggi;
        return this.luas;
    }
    
    @Override
    public double hitungKeliling() {
        this.keliling = this.sisiAtas + this.sisiBawah + this.sisiMiring1 + this.sisiMiring2;
        return this.keliling;
    }
    
    @Override
    public synchronized void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[Trapesium] Luas: " + hitungLuas());
        System.out.println("[Trapesium] Keliling: " + hitungKeliling());
        notifyAll();
    }
}
