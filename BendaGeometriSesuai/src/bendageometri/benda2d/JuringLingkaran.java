package bendageometri.benda2d;

public class JuringLingkaran extends Lingkaran {
    // Atribut spesifik untuk Juring, dipisahkan dari Lingkaran.
    private final double sudut;
    private double luasJuring;
    private double kelilingJuring;

    // Exception kustom yang spesifik untuk Juring.
    public static class PerhitunganJuringException extends Exception {
        public PerhitunganJuringException(String message) {
            super(message);
        }
    }
    
    // Konstruktor utama.
    public JuringLingkaran(double jariJari, double sudut) throws PerhitunganLingkaranException, PerhitunganJuringException {
        // 1. Panggil konstruktor Lingkaran. Ini akan otomatis menghitung
        //    dan menyimpan `super.luas` dan `super.keliling` untuk lingkaran penuh.
        super(jariJari);
        
        // 2. Validasi input spesifik untuk Juring.
        if (sudut <= 0 || sudut > 360) {
            throw new PerhitunganJuringException("Sudut juring harus antara 0 dan 360 derajat.");
        }
        this.sudut = sudut;
        
        // 3. Hitung dan simpan nilai spesifik Juring saat objek dibuat.
        this.luasJuring = hitungLuasJuring();
        this.kelilingJuring = hitungKelilingJuring();
    }
    
    // --- GETTERS ---
    // Menyediakan akses baca ke atribut private.
    public double getSudut() {
        return this.sudut;
    }
    
    public double getLuasJuring() {
        return this.luasJuring;
    }
    
    public double getKelilingJuring() {
        return this.kelilingJuring;
    }

    // --- METODE KALKULASI INSTANCE ---
    // Metode ini menghitung nilai berdasarkan state internal objek (`this`).

    public double hitungLuasJuring() {
        // Efisien: Menggunakan `super.luas` (luas lingkaran penuh) yang sudah dihitung di superclass.
        // Rumus: Luas Juring = (sudut / 360) * Luas Lingkaran Penuh
        this.luasJuring = (this.sudut / 360.0) * super.luas;
        return this.luasJuring;
    }
    
    public double hitungKelilingJuring() {
        // Efisien: Menggunakan `super.keliling` yang sudah dihitung.
        // Rumus: Keliling Juring = (Panjang Busur) + 2 * jari-jari
        double panjangBusur = (this.sudut / 360.0) * super.keliling;
        this.kelilingJuring = panjangBusur + (2 * this.jariJari);
        return this.kelilingJuring;
    }
    
    // --- METODE OVERLOADING (KALKULATOR PUBLIK) ---
    // Metode ini menghitung nilai dari nol berdasarkan parameter yang diberikan.

    public double hitungLuasJuring(double jariJari, double sudut) throws PerhitunganLingkaranException, PerhitunganJuringException {
        if (jariJari <= 0 || sudut <= 0 || sudut > 360) {
            throw new PerhitunganJuringException("Jari-jari dan sudut harus positif (sudut <= 360).");
        }
        // Menghitung luas lingkaran penuh terlebih dahulu menggunakan metode superclass.
        double luasLingkaranPenuh = super.hitungLuas(jariJari);
        return (sudut / 360.0) * luasLingkaranPenuh;
    }
    
    public double hitungKelilingJuring(double jariJari, double sudut) throws PerhitunganLingkaranException, PerhitunganJuringException {
        if (jariJari <= 0 || sudut <= 0 || sudut > 360) {
            throw new PerhitunganJuringException("Jari-jari dan sudut harus positif (sudut <= 360).");
        }
        // Menghitung keliling lingkaran penuh terlebih dahulu.
        double kelilingLingkaranPenuh = super.hitungKeliling(jariJari);
        double panjangBusur = (sudut / 360.0) * kelilingLingkaranPenuh;
        return panjangBusur + (2 * jariJari);
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Juring Lingkaran r=" + this.jariJari + ", sudut=" + this.sudut);
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Juring Lingkaran (setelah " + jeda + " ms)");
            System.out.printf("   > Luas Juring: %.2f, Keliling Juring: %.2f\n", getLuasJuring(), getKelilingJuring());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Juring Lingkaran diinterupsi.");
        }
    }
}
