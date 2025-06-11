/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda2d;

/**
 *
 * @author nbnrc
 */
public class JajarGenjang extends Benda2D implements Runnable {
    public double alas;
    public double sisiMiring;
    public double tinggi;
    public double luas;
    public double keliling;
    
    public JajarGenjang(double alas, double sisiMiring, double tinggi) {
        if (alas <= 0 || sisiMiring <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Alas, sisi miring, dan tinggi harus bernilai positif.");
        }
        this.alas = alas;
        this.sisiMiring = sisiMiring;
        this.tinggi = tinggi;
        this.luas = hitungLuas();
        this.keliling = hitungKeliling();
    }
    
    @Override
    public double hitungLuas() {
        this.luas = this.alas * this.tinggi;
        return this.luas;
    }
    
    @Override
    public double hitungKeliling() {
        this.keliling = 2 * (this.alas + this.sisiMiring);
        return this.keliling;
    }
    
    @Override
    public synchronized void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[JajarGenjang] Luas: " + hitungLuas());
        System.out.println("[JajarGenjang] Keliling: " + hitungKeliling());
        notifyAll();
    }
}
