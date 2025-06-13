package bendageometri.benda3d;
import bendageometri.benda2d.*;

public class CincinBola extends Lingkaran implements Runnable {
    private double tinggiCincin;
    private double jariJariAtas;
    private double jariJariBawah;
    private double volume;
    private double luasPermukaan;

    public static class PerhitunganCincinBolaException extends Exception {
        public PerhitunganCincinBolaException(String message) {
            super(message);
        }
    }

    // Konstruktor utama
    public CincinBola(double jariJariBola, double tinggiCincin, double jariJariAtas, double jariJariBawah) throws PerhitunganLingkaranException, PerhitunganCincinBolaException {
        super(jariJariBola);
        
        if (tinggiCincin <= 0 || jariJariAtas < 0 || jariJariBawah < 0) {
            throw new PerhitunganCincinBolaException("Dimensi cincin bola harus bernilai positif.");
        }
        this.tinggiCincin = tinggiCincin;
        this.jariJariAtas = jariJariAtas;
        this.jariJariBawah = jariJariBawah;
        
        this.volume = hitungVolume();
        this.luasPermukaan = hitungLuasPermukaan();
    }

    // Getter
    public double getTinggiCincin() { return this.tinggiCincin; }
    public double getJariJariAtas() { return this.jariJariAtas; }
    public double getJariJariBawah() { return this.jariJariBawah; }
    public double getVolume() { return this.volume; }
    public double getLuasPermukaan() { return this.luasPermukaan; }

    // Metode override ini tetap menggunakan rumus standar untuk kejelasan
    public double hitungVolume() {
        double r1_kuadrat = Math.pow(this.jariJariAtas, 2);
        double r2_kuadrat = Math.pow(this.jariJariBawah, 2);
        double h_kuadrat = Math.pow(this.tinggiCincin, 2);
        
        this.volume = (1.0/6.0) * Math.PI * this.tinggiCincin * (3 * r1_kuadrat + 3 * r2_kuadrat + h_kuadrat);
        return this.volume;
    }
    
    // Overloading sekarang menggunakan pendekatan modular sesuai ide Anda
    public double hitungVolume(double tinggiCincin, double jariJariAtas, double jariJariBawah) throws PerhitunganCincinBolaException, PerhitunganLingkaranException {
        if (tinggiCincin <= 0 || jariJariAtas < 0 || jariJariBawah < 0) {
            throw new PerhitunganCincinBolaException("Dimensi cincin bola tidak valid.");
        }
        // Menghitung luas lingkaran atas dan bawah menggunakan metode superclass
        double luasAtap = super.hitungLuas(jariJariAtas);
        double luasBawah = super.hitungLuas(jariJariBawah);
        
        // Volume dihitung sebagai jumlah volume silinder + sisa volume
        // Rumus: V = (1/2 * L_atap * h) + (1/2 * L_bawah * h) + (1/6 * PI * h^3)
        double volBagianAtap = 0.5 * luasAtap * tinggiCincin;
        double volBagianBawah = 0.5 * luasBawah * tinggiCincin;
        double volBagianTengah = (1.0/6.0) * Math.PI * Math.pow(tinggiCincin, 3);
        
        return volBagianAtap + volBagianBawah + volBagianTengah;
    }

    public double hitungLuasPermukaan() {
        // Rumus luas zona bola = 2 * PI * R * h
        this.luasPermukaan = super.keliling * this.tinggiCincin;
        return this.luasPermukaan;
    }
    
    // Overloading untuk hitungLuasPermukaan
    public double hitungLuasPermukaan(double jariJariBola, double tinggiCincin) throws PerhitunganLingkaranException, PerhitunganCincinBolaException {
        if (jariJariBola <= 0 || tinggiCincin <= 0) {
            throw new PerhitunganCincinBolaException("Dimensi untuk perhitungan luas permukaan tidak valid.");
        }
        double kelilingLingkaranBesar = super.hitungKeliling(jariJariBola);
        return kelilingLingkaranBesar * tinggiCincin;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("-> [Mulai] Thread untuk Cincin Bola (R=" + this.jariJari + ", h=" + getTinggiCincin() + ")");
            long jeda = (long) (Math.random() * 2000 + 1000);
            Thread.sleep(jeda);
            System.out.println("<- [Selesai] Cincin Bola (setelah " + jeda + " ms)");
            System.out.printf("   > Volume: %.2f, Luas Permukaan (Lengkung): %.2f\n", getVolume(), getLuasPermukaan());
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}