/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bendageometri.benda3d;

import bendageometri.benda2d.LayangLayang;

/**
 *
 * @author nbnrc
 */
public class PrismaLayangLayang extends LayangLayang { // Mewarisi LayangLayang untuk alasnya

    public static class InvalidPrismaException extends IllegalArgumentException {
        public InvalidPrismaException(String message) {
            super(message);
        }
    }

    private double tinggiPrisma; // Bisa mutable dengan setter

    // Konstruktor utama
    public PrismaLayangLayang(double d1Alas, double d2Alas,
            double sisiAAlas, double sisiBAlas,
            double tinggiPrisma) {
        super(d1Alas, d2Alas, sisiAAlas, sisiBAlas);
        try {
            setTinggiPrisma(tinggiPrisma);
        } catch (IllegalArgumentException e) {
            throw new InvalidPrismaException("Tinggi prisma tidak valid: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Gagal membuat prisma layang-layang", e);
        }
    }

    // Overloaded constructor: jika alas LayangLayang didefinisikan dengan dua
    // pasang sisi
    // dan diagonal penghubung sisi berbeda (d2). Diagonal utama (d1) akan dihitung
    // oleh LayangLayang.
    public PrismaLayangLayang(double sisiPendekAlas, double sisiPanjangAlas,
            double diagonalPenghubungAlas, double tinggiPrisma) {
        super(sisiPendekAlas, sisiPanjangAlas, diagonalPenghubungAlas);
        try {
            setTinggiPrisma(tinggiPrisma);
        } catch (IllegalArgumentException e) {
            throw new InvalidPrismaException("Tinggi prisma tidak valid: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Gagal membuat prisma layang-layang", e);
        }
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

    // Getter untuk properti alas diwarisi dari LayangLayang (immutable)

    @Override
    public double hitungVolume() {
        try {
            return super.hitungLuas() * this.tinggiPrisma;
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghitung volume prisma layang-layang", e);
        }
    }

    @Override
    public double hitungLuasPermukaan() {
        try {
            double luasAlas = super.hitungLuas();
            double kelilingAlas = super.hitungKeliling();
            return (2 * luasAlas) + (kelilingAlas * this.tinggiPrisma);
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghitung luas permukaan prisma layang-layang", e);
        }
    }
}
