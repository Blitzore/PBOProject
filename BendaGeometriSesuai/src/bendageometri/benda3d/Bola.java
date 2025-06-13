package bendageometri.benda3d;

import bendageometri.benda2d.*;

public class Bola extends Lingkaran implements Runnable {
    private double volume;
    private double luasPermukaan;

    public static class PerhitunganBolaException extends Exception  {
        public PerhitunganBolaException(String message) {
            super(message);
        }
    }
    
    // Konstruktor utama
    public Bola(double jariJari) throws PerhitunganLingkaranException {
        // Memanggil konstruktor Lingkaran, yang otomatis menghitung super.luas dan super.keliling
        super(jariJari);
        
        // Hitung dan simpan volume serta luas permukaan saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }
    
    // Getter untuk mengakses atribut private
    public double getVolume() {
        return this.volume;
    }
    
    public double getLuasPermukaan() {
        return this.luasPermukaan;
    }

    public double hitungVolume() {
        // DIUBAH: Rumus volume bola menggunakan super.luas untuk efisiensi
        // V = 4/3 * pi * r^3  = (4/3) * (pi * r^2) * r = (4/3) * super.luas * this.jariJari
        this.volume = (4.0/3.0) * super.luas * super.jariJari;
        return this.volume;
    }
    
    // Overloading untuk hitungVolume dengan parameter
    public double hitungVolume(double jariJari) throws PerhitunganLingkaranException, PerhitunganBolaException {
        if (jariJari <= 0) {
            throw new PerhitunganBolaException("Jari-jari untuk perhitungan volume harus positif.");
        }
        // Menghitung luas alas terlebih dahulu menggunakan metode superclass
        double luasAlas = super.hitungLuas(jariJari);
        return (4.0/3.0) * luasAlas * jariJari;
    }
    
    public double hitungLuasPermukaan() {
        // DIUBAH: Rumus luas permukaan bola menggunakan super.luas
        // LP = 4 * pi * r^2 = 4 * (luas lingkaran)
        this.luasPermukaan = 4 * super.luas;
        return this.luasPermukaan;
    }
    
    // Overloading untuk hitungLuasPermukaan dengan parameter
    public double hitungLuasPermukaan(double jariJari) throws PerhitunganLingkaranException, PerhitunganBolaException {
        if (jariJari <= 0) {
            throw new PerhitunganBolaException("Jari-jari untuk perhitungan luas permukaan harus positif.");
        }
        // Menghitung luas lingkaran terlebih dahulu menggunakan metode superclass
        return 4 * super.hitungLuas(jariJari);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Bola r=" + this.jariJari);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Bola (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
