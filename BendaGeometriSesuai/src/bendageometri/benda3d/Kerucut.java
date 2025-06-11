package bendageometri.benda3d;

import bendageometri.benda2d.Lingkaran;

/**
 * Representasi objek Kerucut yang merupakan bangun ruang 3D berbasis alas
 * lingkaran.
 */
public class Kerucut extends Lingkaran {

    // ✅ Custom exception untuk error perhitungan
    public static class PerhitunganKerucutException extends RuntimeException {
        public PerhitunganKerucutException(String message) {
            super(message);
        }
    }

    public final double tinggiKerucut;
    public final double garisPelukis; // Dihitung sekali saat konstruktor

    /**
     * Konstruktor Kerucut
     * 
     * @param jariJariAlas  Jari-jari alas kerucut (harus > 0)
     * @param tinggiKerucut Tinggi kerucut (harus > 0)
     */
    public Kerucut(double jariJari, double tinggiKerucut) {
        super(jariJari); // Validasi jari-jari dilakukan di Lingkaran

        if (tinggiKerucut <= 0) {
            throw new IllegalArgumentException("Tinggi kerucut harus lebih dari 0.");
        }

        this.tinggiKerucut = tinggiKerucut;

        // Hitung garis pelukis (s = sqrt(r^2 + t^2))
        double r = super.jariJari;
        this.garisPelukis = Math.sqrt(r * r + tinggiKerucut * tinggiKerucut);

        if (Double.isNaN(this.garisPelukis) || this.garisPelukis <= 0) {
            throw new PerhitunganKerucutException("Perhitungan garis pelukis tidak valid.");
        }
    }

    public double getTinggiKerucut() {
        return tinggiKerucut;
    }

    public double getGarisPelukis() {
        return garisPelukis;
    }

    @Override
    public double hitungVolume() {
        try {
            return (1.0 / 3.0) * super.luas * tinggiKerucut;
        } catch (Exception e) {
            throw new PerhitunganKerucutException("Gagal menghitung volume kerucut: " + e.getMessage());
        }
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasAlas = super.luas;
            double luasSelimut = Math.PI * super.jariJari * garisPelukis;
            return luasAlas + luasSelimut;
        } catch (Exception e) {
            throw new PerhitunganKerucutException("Gagal menghitung luas permukaan kerucut: " + e.getMessage());
        }
    }

    public double hitungVolume(double jariBaru) {
        try {
            return (1.0 / 3.0) * super.hitungLuas(jariBaru) * tinggiKerucut;
        } catch (Exception e) {
            throw new PerhitunganKerucutException("Gagal menghitung volume kerucut: " + e.getMessage());
        }
    }

    public double hitungLuasPermukaan(double jariBaru) {
        try {
            double luasAlas = super.hitungLuas(jariBaru);
            double luasSelimut = Math.PI * jariBaru * garisPelukis;
            return luasAlas + luasSelimut;
        } catch (Exception e) {
            throw new PerhitunganKerucutException("Gagal menghitung luas permukaan kerucut: " + e.getMessage());
        }
    }
}
