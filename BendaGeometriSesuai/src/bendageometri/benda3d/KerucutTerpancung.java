/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;

import bendageometri.benda2d.Lingkaran;

/**
 *
 * @author nbnrc
 */
public class KerucutTerpancung extends Lingkaran implements Runnable {
    private double jariJariAtas;
    private double tinggi;
    private double garisPelukis;
    private double volume;
    private double luasPermukaan;

    // Konstruktor utama
    public KerucutTerpancung(double jariJariAlas, double jariJariAtas, double tinggi) throws PerhitunganLingkaranException, IllegalArgumentException {
        super(jariJariAlas);
        
        if (jariJariAtas <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Dimensi kerucut terpancung harus bernilai positif.");
        }
        if (jariJariAtas == jariJariAlas){
            throw new IllegalArgumentException("Jari-jari atas dan alas tidak boleh sama (itu akan menjadi Tabung).");
        }
        this.jariJariAtas = jariJariAtas;
        this.tinggi = tinggi;
        
        this.garisPelukis = hitungGarisPelukis();
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Getter
    public double getJariJariAtas() { return this.jariJariAtas; }
    public double getTinggi() { return this.tinggi; }
    public double getGarisPelukis() { return this.garisPelukis; }
    public double getVolume() { return this.volume; }
    public double getLuasPermukaan() { return this.luasPermukaan; }
    
    private double hitungGarisPelukis() {
        return Math.sqrt(Math.pow(this.tinggi, 2) + Math.pow(super.jariJari - this.jariJariAtas, 2));
    }

    // Metode ini baru untuk kelas ini
    public double hitungVolume() {
        // DIUBAH: Menerapkan ide Anda untuk memanfaatkan super.luas
        // Rumus: V = 1/3 * t * (luasAlas + pi*R*r + pi*r^2)
        
        // Bagian tengah dari rumus (pi * R * r)
        double bagianTengah = Math.PI * super.jariJari * this.jariJariAtas;
        // Bagian atas dari rumus (pi * r^2)
        double luasAtap = Math.PI * Math.pow(this.jariJariAtas, 2);

        this.volume = (1.0/3.0) * this.tinggi * (super.luas + bagianTengah + luasAtap);
        return this.volume;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double jariJariAlas, double jariJariAtas, double tinggi) throws PerhitunganLingkaranException, IllegalArgumentException {
        if (jariJariAlas <= 0 || jariJariAtas <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Dimensi harus positif.");
        }
        // DIUBAH: Menerapkan ide Anda untuk memanfaatkan super.hitungLuas()
        double luasAlas = super.hitungLuas(jariJariAlas);
        double luasAtap = super.hitungLuas(jariJariAtas);
        double bagianTengah = Math.PI * jariJariAlas * jariJariAtas;
        
        return (1.0/3.0) * tinggi * (luasAlas + bagianTengah + luasAtap);
    }
    
    // Metode ini baru untuk kelas ini
    public double hitungLuasPermukaan() {
        // Luas Permukaan = Luas Alas + Luas Atap + Luas Selimut
        double luasAlas = super.luas;
        double luasAtap = Math.PI * Math.pow(this.jariJariAtas, 2);
        double luasSelimut = Math.PI * (super.jariJari + this.jariJariAtas) * this.garisPelukis;
        
        this.luasPermukaan = luasAlas + luasAtap + luasSelimut;
        return this.luasPermukaan;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double jariJariAlas, double jariJariAtas, double tinggi) throws PerhitunganLingkaranException, IllegalArgumentException {
        if (jariJariAlas <= 0 || jariJariAtas <= 0 || tinggi <= 0) {
            throw new IllegalArgumentException("Dimensi harus positif.");
        }
        // Memanfaatkan metode superclass untuk menghitung luas alas dan atap
        double luasAlas = super.hitungLuas(jariJariAlas);
        double luasAtap = super.hitungLuas(jariJariAtas);
        
        double garisPelukis = Math.sqrt(Math.pow(tinggi, 2) + Math.pow(jariJariAlas - jariJariAtas, 2));
        double luasSelimut = Math.PI * (jariJariAlas + jariJariAtas) * garisPelukis;
        
        return luasAlas + luasAtap + luasSelimut;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Kerucut Terpancung (R=" + this.jariJari + ", r=" + getJariJariAtas() + ", t=" + getTinggi() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Kerucut Terpancung (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}