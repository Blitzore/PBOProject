package bendageometri.benda3d;

import bendageometri.benda2d.Lingkaran;

/**
 * Representasi objek Kerucut yang merupakan bangun ruang 3D berbasis alas
 * lingkaran.
 */
public class Kerucut extends Lingkaran implements Runnable {
    // Atribut spesifik kelas ini dibuat private karena tidak ada turunan lagi
    private double tinggi;
    private double garisPelukis;
    private double volume;
    private double luasPermukaan;

    // Exception kustom dari file asli
    public static class PerhitunganKerucutException extends Exception {
        public PerhitunganKerucutException(String message) {
            super(message);
        }
    }
    
    // Konstruktor utama
    public Kerucut(double jariJari, double tinggi) throws PerhitunganLingkaranException, PerhitunganKerucutException {
        // Memanggil konstruktor kelas induk (Lingkaran)
        super(jariJari);
        
        if (tinggi <= 0) {
            throw new PerhitunganKerucutException("Tinggi kerucut harus bernilai positif.");
        }
        this.tinggi = tinggi;
        
        // Hitung dan simpan nilai-nilai saat objek dibuat
        this.garisPelukis = hitungGarisPelukis();
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Getter untuk mengakses atribut private
    public double getTinggi() { return this.tinggi; }
    public double getGarisPelukis() { return this.garisPelukis; }
    public double getVolume() { return this.volume; }
    public double getLuasPermukaan() { return this.luasPermukaan; }
    
    // Metode untuk menghitung garis pelukis (s)
    private double hitungGarisPelukis() {
        // Rumus pythagoras: s = sqrt(r^2 + t^2)
        return Math.sqrt(Math.pow(this.jariJari, 2) + Math.pow(this.tinggi, 2));
    }

    // Metode ini baru untuk kelas Kerucut
    public double hitungVolume() {
        // Rumus volume kerucut = (1/3) * Luas Alas * tinggi
        // Memanfaatkan super.luas dari Lingkaran untuk efisiensi
        this.volume = (1.0/3.0) * super.luas * this.tinggi;
        return this.volume;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double jariJari, double tinggi) throws PerhitunganLingkaranException, PerhitunganKerucutException {
        if (jariJari <= 0 || tinggi <= 0) {
            throw new PerhitunganKerucutException("Jari-jari dan tinggi harus positif.");
        }
        // Memanfaatkan super.hitungLuas untuk efisiensi
        double luasAlas = super.hitungLuas(jariJari);
        return (1.0/3.0) * luasAlas * tinggi;
    }
    
    // Metode ini baru untuk kelas Kerucut
    public double hitungLuasPermukaan() {
        // Luas Permukaan = Luas Alas + Luas Selimut
        // Luas Selimut = PI * r * s (s = garis pelukis)
        double luasSelimut = Math.PI * this.jariJari * this.garisPelukis;
        this.luasPermukaan = super.luas + luasSelimut;
        return this.luasPermukaan;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double jariJari, double tinggi) throws PerhitunganLingkaranException, PerhitunganKerucutException {
        if (jariJari <= 0 || tinggi <= 0) {
            throw new PerhitunganKerucutException("Jari-jari dan tinggi harus positif.");
        }
        // Memanfaatkan super.hitungLuas untuk luas alas
        double luasAlas = super.hitungLuas(jariJari);
        
        // Menghitung garis pelukis dari parameter
        double garisPelukis = Math.sqrt(Math.pow(jariJari, 2) + Math.pow(tinggi, 2));
        double luasSelimut = Math.PI * jariJari * garisPelukis;
        
        return luasAlas + luasSelimut;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Kerucut r=" + this.jariJari + ", t=" + this.tinggi);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Kerucut (setelah " + jeda + " ms)");
            System.out.println("   > Volume: " + this.volume + ", Luas Permukaan: " + this.luasPermukaan);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
