/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;

/**
 *
 * @author nbnrc
 */
public class JuringBola extends Bola { // Mewarisi Bola untuk jari-jari bola induk (r)
    private double tinggiTopiJuring; // h (tinggi topi bola yang membentuk juring)

    // Konstruktor utama
    public JuringBola(double jariJariBolaInduk, double tinggiTopiJuring) {
        super(jariJariBolaInduk); // Menginisialisasi jariJariBolaInduk (r) via Bola
                                  // Validasi jariJariBolaInduk (>0) oleh Lingkaran -> Bola
        setTinggiTopiJuring(tinggiTopiJuring);
    }

    // Getter untuk tinggiTopiJuring
    public double getTinggiTopiJuring() {
        return tinggiTopiJuring;
    }

    // Setter untuk tinggiTopiJuring dengan validasi
    public void setTinggiTopiJuring(double tinggiTopiJuring) {
        double rInduk = getJariJari(); // jariJariBolaInduk
        // Tinggi topi (h) harus antara 0 (eksklusif) dan diameter bola (inklusif, jika topi adalah seluruh bola)
        // Juring yang valid dengan kerucut di dalamnya, h <= r.
        // Namun, definisi umum sektor biasanya memiliki h <= r.
        // Batasan h pada umumnya untuk topi bola yaitu 0 < h <= 2r.
        if (tinggiTopiJuring <= 0 || tinggiTopiJuring > (2 * rInduk)) {
            throw new IllegalArgumentException(
                String.format("Tinggi topi juring (h=%.2f) harus lebih besar dari 0 dan tidak melebihi diameter bola induk (D=%.2f).",
                tinggiTopiJuring, 2 * rInduk)
            );
        }
        this.tinggiTopiJuring = tinggiTopiJuring;
    }


    @Override
    public double hitungVolume() {
        // Volume Juring Bola = (2/3) * PI * r^2 * h
        // r adalah jariJariBolaInduk, h adalah tinggiTopiJuring
        double r = getJariJari();
        double h = this.tinggiTopiJuring;
        return (2.0 / 3.0) * Math.PI * Math.pow(r, 2) * h; //
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Juring Bola = Luas Topi Bola + Luas Selimut Kerucut
        double r = getJariJari();
        double h = this.tinggiTopiJuring;

        // Luas Topi Bola (L_topi) = 2 * PI * r * h
        double luasTopi = 2 * Math.PI * r * h; //

        // Luas Selimut Kerucut (L_kerucut)
        // Jari-jari alas kerucut (a) = sqrt(h * (2r - h)) atau sqrt(r^2 - (r-h)^2)
        // garis pelukis kerucutnya adalah r (jari-jari bola).
        double aKuadrat = h * (2 * r - h); // a^2 = 2rh - h^2
        if (aKuadrat < 0) aKuadrat = 0; // Menghindari error domain jika h sangat kecil atau h=2r
        double a = Math.sqrt(aKuadrat); 
        
        double luasSelimutKerucut = Math.PI * a * r;

        return luasTopi + luasSelimutKerucut; //
    }
}
