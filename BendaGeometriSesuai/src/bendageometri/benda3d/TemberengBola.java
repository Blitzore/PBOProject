/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;

/**
 *
 * @author nbnrc
 */
public class TemberengBola extends Bola { 
    private double tinggiTemberengBola; // h (tinggi tembereng/calotte)

    // Konstruktor utama
    public TemberengBola(double jariJariBolaInduk, double tinggiTembereng) {
        super(jariJariBolaInduk); // Menginisialisasi jariJariBolaInduk (R) via Bola
                                  // Validasi jariJariBolaInduk (>0) oleh Lingkaran -> Bola
        setTinggiTemberengBola(tinggiTembereng);
    }

    // Getter untuk tinggiTemberengBola
    public double getTinggiTemberengBola() {
        return tinggiTemberengBola;
    }

    // Setter untuk tinggiTemberengBola dengan validasi
    public void setTinggiTemberengBola(double tinggiTembereng) {
        double R_induk = getJariJari(); // Ini adalah jariJariBolaInduk
        if (tinggiTembereng <= 0 || tinggiTembereng > 2 * R_induk) {
            throw new IllegalArgumentException(
                String.format("Tinggi tembereng bola (h=%.2f) harus lebih besar dari 0 dan tidak melebihi diameter bola induk (D=%.2f).",
                tinggiTembereng, 2 * R_induk)
            );
        }
        this.tinggiTemberengBola = tinggiTembereng;
    }

    @Override
    public double hitungVolume() {
        // Volume Tembereng Bola (spherical cap)
        // V = (1/3) * PI * h^2 * (3R - h)
        double R = getJariJari(); // jariJariBolaInduk
        double h = this.tinggiTemberengBola;
        return (1.0 / 3.0) * Math.PI * Math.pow(h, 2) * (3 * R - h);
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Total Tembereng Bola = Luas Permukaan Lengkung (Topi) + Luas Alas Lingkaran
        double R = getJariJari();
        double h = this.tinggiTemberengBola;

        // Luas Permukaan Lengkung Tembereng Bola (calotte/topi) = 2 * PI * R * h
        double luasTopi = 2 * Math.PI * R * h; //

        // Hitung jari-jari alas tembereng (r_alas)
        // r_alas^2 = h * (2R - h)
        double rAlasKuadrat = h * (2 * R - h);
        // Karena h > 0 dan h <= 2R, maka 2R-h >= 0, sehingga rAlasKuadrat akan >= 0.
        // Jika h = 2R (seluruh bola dianggap tembereng), rAlasKuadrat = 0.
        if (rAlasKuadrat < 0) rAlasKuadrat = 0; // Antisipasi floating point issue, walau secara matematis tidak boleh <0
        
        // Luas Alas Lingkaran = PI * r_alas^2
        double luasAlas = Math.PI * rAlasKuadrat; //
        
        return luasTopi + luasAlas;
    }
}
