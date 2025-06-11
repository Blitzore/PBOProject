/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class PersegiPanjang extends Benda2D implements Runnable {
    public double panjang;
    public double lebar;
    public double luas;
    public double keliling;
    
    public PersegiPanjang(double panjang, double lebar) {
        if (panjang <= 0 || lebar <= 0) {
            throw new IllegalArgumentException("Panjang dan lebar harus bernilai positif.");
        }
        this.panjang = panjang;
        this.lebar = lebar;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    @Override
    public double hitungLuas() {
        this.luas = this.panjang * this.lebar;
        return this.luas;
    }
    
    @Override
    public double hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
        return this.keliling;
    }
    
    @Override
    public synchronized void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[PersegiPanjang] Luas: " + hitungLuas());
        System.out.println("[PersegiPanjang] Keliling: " + hitungKeliling());
        notifyAll();
    }
}
