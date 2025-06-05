/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;

/**
 *
 * @author nbnrc
 */
public class CincinBola extends Bola {
    private final double jariJariAlasAtas;      // r1
    private final double jariJariAlasBawah;     // r2
    private final double tinggiCincin;          // h (tinggi zona/frustum)

    // Konstruktor utama
    public CincinBola(double jariJari, double r1AlasAtas, double r2AlasBawah, double hTinggiCincin) {
        super(jariJari); // Menginisialisasi jariJariBolaInduk (R) via Bola
                         // Validasi jariJariBolaInduk (>0) oleh Lingkaran -> Bola

        if (r1AlasAtas < 0) { // Jari-jari alas bisa 0 jika salah satu ujungnya adalah puncak calotte, tapi tidak negatif
            throw new IllegalArgumentException("Jari-jari alas atas (r1) tidak boleh negatif.");
        }
        if (r2AlasBawah < 0) { // Sama untuk r2
            throw new IllegalArgumentException("Jari-jari alas bawah (r2) tidak boleh negatif.");
        }
        if (hTinggiCincin <= 0) {
            throw new IllegalArgumentException("Tinggi cincin/zona (h) harus bernilai positif.");
        }

        double R_induk = getJariJari(); // Ini adalah jariJariBolaInduk dari superclass Bola

        // Validasi geometris dasar
        if (r1AlasAtas > R_induk) {
            throw new IllegalArgumentException(
                String.format("Jari-jari alas atas (r1=%.2f) tidak boleh melebihi jari-jari bola induk (R=%.2f).", r1AlasAtas, R_induk)
            );
        }
        if (r2AlasBawah > R_induk) {
            throw new IllegalArgumentException(
                String.format("Jari-jari alas bawah (r2=%.2f) tidak boleh melebihi jari-jari bola induk (R=%.2f).", r2AlasBawah, R_induk)
            );
        }
        if (hTinggiCincin > 2 * R_induk) {
            throw new IllegalArgumentException(
                String.format("Tinggi cincin/zona (h=%.2f) tidak boleh melebihi diameter bola induk (D=%.2f).", hTinggiCincin, 2 * R_induk)
            );
        }


        this.jariJariAlasAtas = r1AlasAtas;
        this.jariJariAlasBawah = r2AlasBawah;
        this.tinggiCincin = hTinggiCincin;
    }

    // Getter
    public double getJariJariBolaInduk() {
        return getJariJari(); // Diwarisi dari Bola -> Lingkaran
    }

    public double getJariJariAlasAtas() {
        return jariJariAlasAtas;
    }

    public double getJariJariAlasBawah() {
        return jariJariAlasBawah;
    }

    public double getTinggiCincin() {
        return tinggiCincin;
    }

    @Override
    public double hitungVolume() {
        // Volume zona bola dengan dua alas (frustum of a sphere)
        // V = (1/6) * PI * h * (3*r1^2 + 3*r2^2 + h^2)
        // Di sini, r1 = jariJariAlasAtas, r2 = jariJariAlasBawah, h = tinggiCincin
        return (1.0 / 6.0) * Math.PI * this.tinggiCincin * (3 * Math.pow(this.jariJariAlasAtas, 2) + 
                3 * Math.pow(this.jariJariAlasBawah, 2) + 
                Math.pow(this.tinggiCincin, 2));
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas permukaan zona bola (selimut lengkung) = 2 * PI * R_bolaInduk * h_cincin
        // Ditambah luas dua alas lingkaran.
        double luasSelimutZone = 2 * Math.PI * getJariJariBolaInduk() * this.tinggiCincin;
        double luasAlasAtas = Math.PI * Math.pow(this.jariJariAlasAtas, 2);
        double luasAlasBawah = Math.PI * Math.pow(this.jariJariAlasBawah, 2);
        return luasSelimutZone + luasAlasAtas + luasAlasBawah;
    }
}