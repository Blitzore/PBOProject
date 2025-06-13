package bendageometri.benda2d;

public class TemberengLingkaran extends Lingkaran {
    // Atribut spesifik untuk Tembereng, dipisahkan dari Lingkaran.
    private final double sudut;
    private double luasTembereng;
    private double kelilingTembereng;

    // Exception kustom yang spesifik untuk Tembereng.
    public static class PerhitunganTemberengException extends Exception {
        public PerhitunganTemberengException(String message) {
            super(message);
        }
    }

    // Konstruktor utama.
    public TemberengLingkaran(double jariJari, double sudut) throws PerhitunganLingkaranException, PerhitunganTemberengException {
        // 1. Panggil konstruktor Lingkaran. Ini akan otomatis menghitung
        //    dan menyimpan `super.luas` dan `super.keliling` untuk lingkaran penuh.
        super(jariJari);

        // 2. Validasi input spesifik untuk Tembereng.
        if (sudut <= 0 || sudut >= 360) {
            throw new PerhitunganTemberengException("Sudut tembereng harus lebih dari 0 dan kurang dari 360 derajat.");
        }
        this.sudut = sudut;

        // 3. Hitung dan simpan nilai spesifik Tembereng saat objek dibuat.
        this.luasTembereng = hitungLuasTembereng();
        this.kelilingTembereng = hitungKelilingTembereng();
    }

    // --- GETTERS ---
    // Menyediakan akses baca ke atribut private.
    public double getSudut() {
        return this.sudut;
    }

    public double getLuasTembereng() {
        return this.luasTembereng;
    }

    public double getKelilingTembereng() {
        return this.kelilingTembereng;
    }

    // --- METODE KALKULASI INSTANCE ---
    // Metode ini menghitung nilai berdasarkan state internal objek (`this`).

    public double hitungLuasTembereng() {
        // Luas Tembereng = Luas Juring - Luas Segitiga
        double sudutRad = Math.toRadians(this.sudut);
        
        // Luas juring dihitung efisien menggunakan `super.luas`
        double luasJuring = (this.sudut / 360.0) * super.luas;
        
        // Luas segitiga dihitung menggunakan rumus trigonometri
        double luasSegitiga = 0.5 * Math.pow(this.jariJari, 2) * Math.sin(sudutRad);
        
        this.luasTembereng = luasJuring - luasSegitiga;
        return this.luasTembereng;
    }

    public double hitungKelilingTembereng() {
        // Keliling Tembereng = Panjang Busur + Panjang Tali Busur
        double sudutRad = Math.toRadians(this.sudut);

        // Panjang busur dihitung efisien menggunakan `super.keliling`
        double panjangBusur = (this.sudut / 360.0) * super.keliling;

        // Panjang tali busur dihitung menggunakan rumus 2 * r * sin(sudut/2)
        double panjangTaliBusur = 2 * this.jariJari * Math.sin(sudutRad / 2);

        this.kelilingTembereng = panjangBusur + panjangTaliBusur;
        return this.kelilingTembereng;
    }

    // --- METODE OVERLOADING (KALKULATOR PUBLIK) ---
    // Metode ini menghitung nilai dari nol berdasarkan parameter yang diberikan.

    public double hitungLuasTembereng(double jariJari, double sudut) throws PerhitunganLingkaranException, PerhitunganTemberengException {
        if (jariJari <= 0 || sudut <= 0 || sudut >= 360) {
            throw new PerhitunganTemberengException("Jari-jari dan sudut tidak valid.");
        }
        double sudutRad = Math.toRadians(sudut);
        double luasLingkaranPenuh = super.hitungLuas(jariJari);
        double luasJuring = (sudut / 360.0) * luasLingkaranPenuh;
        double luasSegitiga = 0.5 * Math.pow(jariJari, 2) * Math.sin(sudutRad);
        return luasJuring - luasSegitiga;
    }

    public double hitungKelilingTembereng(double jariJari, double sudut) throws PerhitunganLingkaranException, PerhitunganTemberengException {
        if (jariJari <= 0 || sudut <= 0 || sudut >= 360) {
            throw new PerhitunganTemberengException("Jari-jari dan sudut tidak valid.");
        }
        double sudutRad = Math.toRadians(sudut);
        double kelilingLingkaranPenuh = super.hitungKeliling(jariJari);
        double panjangBusur = (sudut / 360.0) * kelilingLingkaranPenuh;
        double panjangTaliBusur = 2 * jariJari * Math.sin(sudutRad / 2);
        return panjangBusur + panjangTaliBusur;
    }

    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Tembereng Lingkaran r=" + this.jariJari + ", sudut=" + getSudut());
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Tembereng Lingkaran (setelah " + jeda + " ms)");
            System.out.printf("   > Luas Tembereng: %.2f, Keliling Tembereng: %.2f\n", getLuasTembereng(), getKelilingTembereng());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread untuk Tembereng Lingkaran diinterupsi.");
        }
    }
}
