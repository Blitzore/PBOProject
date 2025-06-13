package bendageometri.benda3d;
import bendageometri.benda2d.Lingkaran;

public class TemberengBola extends Lingkaran implements Runnable{
    // Atribut spesifik kelas ini dibuat private karena tidak ada turunan lagi
    private double tinggiTembereng;
    private double volume;
    private double luasPermukaan; // Luas permukaan melengkung dari tembereng (tutup bola)

    // Exception kustom dari file asli
    public static class PerhitunganTemberengBolaException extends Exception {
        public PerhitunganTemberengBolaException(String message) {
            super(message);
        }
    }

    // Konstruktor utama
    public TemberengBola(double jariJariBola, double tinggiTembereng) throws PerhitunganLingkaranException, PerhitunganTemberengBolaException {
        // Memanggil konstruktor Lingkaran untuk merepresentasikan "lingkaran besar" bola
        super(jariJariBola);
        
        if (tinggiTembereng <= 0 || tinggiTembereng > (2 * jariJariBola)) {
            throw new PerhitunganTemberengBolaException("Tinggi tembereng harus lebih dari 0 dan tidak melebihi diameter bola.");
        }
        this.tinggiTembereng = tinggiTembereng;
        
        // Hitung dan simpan nilai saat objek dibuat
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter untuk mengakses atribut private
    public double getTinggiTembereng() { return this.tinggiTembereng; }
    public double getVolume() { return this.volume; }
    public double getLuasPermukaan() { return this.luasPermukaan; }

    // Metode ini baru untuk kelas TemberengBola
    public double hitungVolume() {
        // Rumus volume tembereng bola (segmen) = (1/3) * PI * h^2 * (3R - h)
        // di mana R adalah jari-jari bola dan h adalah tinggi tembereng
        this.volume = (1.0/3.0) * Math.PI * Math.pow(this.tinggiTembereng, 2) * (3 * super.jariJari - this.tinggiTembereng);
        return this.volume;
    }
    
    // Overloading untuk hitungVolume
    public double hitungVolume(double jariJariBola, double tinggiTembereng) throws PerhitunganTemberengBolaException {
        if (jariJariBola <= 0 || tinggiTembereng <= 0 || tinggiTembereng > (2 * jariJariBola)) {
            throw new PerhitunganTemberengBolaException("Dimensi tembereng tidak valid.");
        }
        // Menghitung langsung dengan parameter
        return (1.0/3.0) * Math.PI * Math.pow(tinggiTembereng, 2) * (3 * jariJariBola - tinggiTembereng);
    }

    // Metode ini baru untuk kelas TemberengBola
    public double hitungLuasPermukaan() {
        // Menghitung luas permukaan melengkung dari tembereng (tutup bola/spherical cap)
        // Rumus luas tutup bola = 2 * PI * R * h
        // Kita bisa gunakan super.keliling yang merupakan 2 * PI * R
        this.luasPermukaan = super.keliling * this.tinggiTembereng;
        return this.luasPermukaan;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double jariJariBola, double tinggiTembereng) throws PerhitunganLingkaranException, PerhitunganTemberengBolaException {
        if (jariJariBola <= 0 || tinggiTembereng <= 0) {
            throw new PerhitunganTemberengBolaException("Dimensi untuk perhitungan luas permukaan tidak valid.");
        }
        // Memanfaatkan super.hitungKeliling untuk efisiensi
        double kelilingLingkaranBesar = super.hitungKeliling(jariJariBola);
        return kelilingLingkaranBesar * tinggiTembereng;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Tembereng Bola (R=" + this.jariJari + ", h=" + getTinggiTembereng() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Tembereng Bola (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan (Lengkung): %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
