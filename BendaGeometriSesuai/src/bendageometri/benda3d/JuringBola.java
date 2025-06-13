package bendageometri.benda3d;
import bendageometri.benda2d.Lingkaran;

public class JuringBola extends Lingkaran implements Runnable {
    private double tinggiTutup;
    private double volume;
    private double luasPermukaan;

    public static class PerhitunganJuringBolaException extends Exception {
        public PerhitunganJuringBolaException(String message) {
            super(message);
        }
    }

    // Konstruktor utama
    public JuringBola(double jariJariBola, double tinggiTutup) throws PerhitunganLingkaranException, PerhitunganJuringBolaException {
        super(jariJariBola);
        
        if (tinggiTutup <= 0 || tinggiTutup > (2 * jariJariBola)) {
            throw new PerhitunganJuringBolaException("Tinggi tutup juring harus lebih dari 0 dan tidak melebihi diameter bola.");
        }
        this.tinggiTutup = tinggiTutup;
        
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter
    public double getTinggiTutup() { return this.tinggiTutup; }
    public double getVolume() { return this.volume; }
    public double getLuasPermukaan() { return this.luasPermukaan; }

    // Metode ini baru untuk kelas JuringBola
    public double hitungVolume() {
        // Rumus volume juring bola = (2/3) * PI * R^2 * h
        // Menggunakan super.luas yang merupakan PI * R^2
        this.volume = (2.0/3.0) * super.luas * this.tinggiTutup;
        return this.volume;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double jariJariBola, double tinggiTutup) throws PerhitunganLingkaranException, PerhitunganJuringBolaException {
        if (jariJariBola <= 0 || tinggiTutup <= 0 || tinggiTutup > (2 * jariJariBola)) {
            throw new PerhitunganJuringBolaException("Dimensi juring tidak valid.");
        }
        double luasLingkaranBesar = super.hitungLuas(jariJariBola);
        return (2.0/3.0) * luasLingkaranBesar * tinggiTutup;
    }

    // Metode ini baru untuk kelas JuringBola
    public double hitungLuasPermukaan() {
        // DIUBAH: Menerapkan ide Anda untuk menggunakan super.keliling
        // Luas Permukaan = Luas Tutup Bola + Luas Selimut Kerucut
        // L = (2*pi*R*h) + (pi*r*R)
        // L = (super.keliling * h) + (0.5 * super.keliling * r)
        
        // Jari-jari alas kerucut bagian dalam: r = sqrt(h * (2R - h))
        double jariJariAlasJuring = Math.sqrt(this.tinggiTutup * (2 * super.jariJari - this.tinggiTutup));
        
        // Menghitung luas tutup dan luas selimut menggunakan super.keliling
        double luasTutup = super.keliling * this.tinggiTutup;
        double luasSelimutKerucut = 0.5 * super.keliling * jariJariAlasJuring;
        
        this.luasPermukaan = luasTutup + luasSelimutKerucut;
        return this.luasPermukaan;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double jariJariBola, double tinggiTutup) throws PerhitunganLingkaranException, PerhitunganJuringBolaException {
        if (jariJariBola <= 0 || tinggiTutup <= 0 || tinggiTutup > (2 * jariJariBola)) {
            throw new PerhitunganJuringBolaException("Dimensi juring tidak valid.");
        }
        
        // Memanfaatkan super.hitungKeliling untuk efisiensi
        double kelilingLingkaranBesar = super.hitungKeliling(jariJariBola);
        
        // Logika sama dengan metode di atas, tapi dengan parameter
        double jariJariAlasJuring = Math.sqrt(tinggiTutup * (2 * jariJariBola - tinggiTutup));
        
        double luasTutup = kelilingLingkaranBesar * tinggiTutup;
        double luasSelimutKerucut = 0.5 * kelilingLingkaranBesar * jariJariAlasJuring;
        
        return luasTutup + luasSelimutKerucut;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Juring Bola (R=" + this.jariJari + ", h=" + getTinggiTutup() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Juring Bola (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
