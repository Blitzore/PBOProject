/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;
import bendageometri.benda2d.Segitiga;
/**
 *
 * @author nbnrc
 */
public class PrismaSegitiga extends Segitiga { // Mewarisi Segitiga untuk alasnya
    private double tinggiPrisma; // Bisa mutable dengan setter

    // Konstruktor utama
    public PrismaSegitiga(double alasSegitiga, double tinggiAlasSegitiga, 
                          double sisiBAlas, double sisiCAlas, 
                          double tinggiPrisma) {
        // Memanggil konstruktor Segitiga: public Segitiga(double alas, double tinggi, double sisiB, double sisiC)
        // Di sini, 'alasSegitiga' dari input akan menjadi 'alas' di kelas Segitiga
        super(alasSegitiga, tinggiAlasSegitiga, sisiBAlas, sisiCAlas); 
        // Dimensi alas menjadi immutable
        // Validasi alasSegitiga, tinggiAlasSegitiga, sisiBAlas, sisiCAlas ditangani oleh Segitiga
        setTinggiPrisma(tinggiPrisma); // Menggunakan setter untuk validasi tinggiPrisma
    }
    

    // Getter untuk tinggiPrisma
    public double getTinggiPrisma() {
        return tinggiPrisma;
    }

    // Setter untuk tinggiPrisma dengan validasi
    public void setTinggiPrisma(double tinggiPrisma) {
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma harus bernilai positif.");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    // Getter untuk properti alas (getAlas(), getTinggiAlas(), getSisiBAlas(), getSisiCAlas())
    // diwarisi dari Segitiga (immutable)

    @Override
    public double hitungVolume() {
        // Volume Prisma = Luas Alas (dari Segitiga) * tinggiPrisma
        return super.hitungLuas() * this.tinggiPrisma;
    }

    @Override
    public double hitungLuasPermukaan() {
        // Luas Permukaan Prisma = 2 * Luas Alas + Luas Selimut
        // Luas Selimut = Keliling Alas * tinggiPrisma
        double luasAlas = super.hitungLuas();
        double kelilingAlas = super.hitungKeliling();
        return (2 * luasAlas) + (kelilingAlas * this.tinggiPrisma);
    }
}
