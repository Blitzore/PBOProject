package bendageometri;

import bendageometri.benda2d.*;
import bendageometri.benda3d.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainGeometri {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n===== Kalkulator Benda Geometri =====");
            System.out.println("1. Hitung Benda 2D");
            System.out.println("2. Hitung Benda 3D");
            System.out.println("3. Jalankan Semua Kalkulasi (Multithreading)");
            System.out.println("4. Jalankan Demo Polimorfisme (Lingkaran)");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");

            try {
                int pilihan = scanner.nextInt();
                switch (pilihan) {
                    case 1:
                        menuBenda2D(scanner);
                        break;
                    case 2:
                        menuBenda3D(scanner);
                        break;
                    case 3:
                        jalankanMultithreading();
                        break;
                    case 4:
                        demonstrasiPolimorfisme();
                        break;
                    case 0:
                        berjalan = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Input tidak valid. Harap masukkan angka.");
                scanner.next(); // Membersihkan input yang salah
            }
        }

        System.out.println("\nTerima kasih telah menggunakan program ini!");
        scanner.close();
    }
    
    private static void menuBenda2D(Scanner scanner) {
        System.out.println("\n--- Pilih Bentuk 2D ---");
        System.out.println("1. Persegi");
        System.out.println("2. Persegi Panjang");
        System.out.println("3. Lingkaran");
        System.out.println("4. Segitiga");
        System.out.println("5. Trapesium");
        System.out.println("6. Jajaran Genjang");
        System.out.println("7. Belah Ketupat");
        System.out.println("8. Layang-Layang");
        System.out.println("9. Juring Lingkaran");
        System.out.println("10. Tembereng Lingkaran");
        System.out.println("0. Kembali");
        System.out.print("Pilihan Anda: ");
        
        try {
            int pilihan = scanner.nextInt();
            if (pilihan == 0) return;
            
            switch (pilihan) {
                case 1: // Persegi
                    try {
                        System.out.print("Masukkan sisi persegi: ");
                        double sisi = scanner.nextDouble();
                        Persegi persegi = new Persegi(sisi);
                        System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", persegi.luas, persegi.keliling);
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                    
                case 2: // Persegi Panjang
                    try {
                        System.out.print("Masukkan panjang: "); double panjang = scanner.nextDouble();
                        System.out.print("Masukkan lebar: "); double lebar = scanner.nextDouble();
                        PersegiPanjang pp = new PersegiPanjang(panjang, lebar);
                        System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", pp.luas, pp.keliling);
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                    
                case 3: // Lingkaran
                    try {
                        System.out.print("Masukkan jari-jari lingkaran: ");
                        double jariJari = scanner.nextDouble();
                        Lingkaran lingkaran = new Lingkaran(jariJari);
                        System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", lingkaran.luas, lingkaran.keliling);
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                    
                case 4: // Segitiga
                    try {
                        System.out.print("Masukkan alas segitiga (sisi A): "); double alas = scanner.nextDouble();
                        System.out.print("Masukkan tinggi segitiga: "); double tinggi = scanner.nextDouble();
                        System.out.print("Masukkan sisi B: "); double sisiB = scanner.nextDouble();
                        System.out.print("Masukkan sisi C: "); double sisiC = scanner.nextDouble();
                        Segitiga segitiga = new Segitiga(alas, tinggi, sisiB, sisiC);
                        System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", segitiga.luas, segitiga.keliling);
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                    
                case 5: // Trapesium
                    try {
                        System.out.print("Masukkan sisi atas: "); double sisiAtas = scanner.nextDouble();
                        System.out.print("Masukkan sisi bawah: "); double sisiBawah = scanner.nextDouble();
                        System.out.print("Masukkan tinggi: "); double tinggiTrap = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring 1: "); double sisiMiring1 = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring 2: "); double sisiMiring2 = scanner.nextDouble();
                        Trapesium trapesium = new Trapesium(sisiAtas, sisiBawah, tinggiTrap, sisiMiring1, sisiMiring2);
                        System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", trapesium.luas, trapesium.keliling);
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                    
                case 6: // Jajaran Genjang
                    try {
                        System.out.print("Masukkan alas: "); double alasJG = scanner.nextDouble();
                        System.out.print("Masukkan tinggi: "); double tinggiJG = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring: "); double sisiMiringJG = scanner.nextDouble();
                        JajaranGenjang jg = new JajaranGenjang(alasJG, tinggiJG, sisiMiringJG);
                        System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", jg.luas, jg.keliling);
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                    
                case 7: // Belah Ketupat
                    try {
                        System.out.print("Masukkan diagonal 1: "); double d1 = scanner.nextDouble();
                        System.out.print("Masukkan diagonal 2: "); double d2 = scanner.nextDouble();
                        BelahKetupat bk = new BelahKetupat(d1, d2);
                        System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", bk.luas, bk.keliling);
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                    
                case 8: // Layang-Layang
                    try {
                        System.out.print("Masukkan diagonal 1: "); double d1LL = scanner.nextDouble();
                        System.out.print("Masukkan diagonal 2: "); double d2LL = scanner.nextDouble();
                        System.out.print("Masukkan sisi A (pendek): "); double sisiA = scanner.nextDouble();
                        System.out.print("Masukkan sisi B (panjang): "); double sisiBLL = scanner.nextDouble();
                        LayangLayang ll = new LayangLayang(d1LL, d2LL, sisiA, sisiBLL);
                        System.out.printf("   > Luas: %.2f, Keliling: %.2f\n", ll.luas, ll.keliling);
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                    
                case 9: // Juring Lingkaran
                try {
                    System.out.print("Masukkan jari-jari: ");
                    double jariJuring = scanner.nextDouble();
                    System.out.print("Masukkan sudut pusat (derajat): ");
                    double sudut = scanner.nextDouble();
                    
                    // Membuat objek dengan desain baru yang aman
                    JuringLingkaran juring = new JuringLingkaran(jariJuring, sudut);
                    
                    // DIUBAH: Menggunakan getter untuk mendapatkan nilai spesifik juring
                    // juring.luas -> juring.getLuasJuring()
                    // juring.keliling -> juring.getKelilingJuring()
                    System.out.printf("   > Luas: %.2f, Keliling (busur + 2r): %.2f\n", 
                                      juring.getLuasJuring(), juring.getKelilingJuring());

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                    scanner.nextLine(); // Membersihkan buffer scanner setelah error
                }
                break;

                case 10: // Tembereng Lingkaran
                    try {
                        System.out.print("Masukkan jari-jari: ");
                        double jariTembereng = scanner.nextDouble();
                        System.out.print("Masukkan sudut pusat (derajat): ");
                        double sudutTembereng = scanner.nextDouble();

                        // Membuat objek dengan desain baru yang aman
                        TemberengLingkaran tembereng = new TemberengLingkaran(jariTembereng, sudutTembereng);

                        // DIUBAH: Menggunakan getter untuk mendapatkan nilai spesifik tembereng
                        // tembereng.luas -> tembereng.getLuasTembereng()
                        // tembereng.keliling -> tembereng.getKelilingTembereng()
                        System.out.printf("   > Luas: %.2f, Keliling (busur + tali busur): %.2f\n", 
                                          tembereng.getLuasTembereng(), tembereng.getKelilingTembereng());

                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                        scanner.nextLine(); // Membersihkan buffer scanner setelah error
                    }
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (InputMismatchException e) {
            System.err.println("Input tidak valid. Harap masukkan sebuah angka.");
            scanner.next();
        }
    }
    
    private static void menuBenda3D(Scanner scanner) {
        System.out.println("\n--- Pilih Bentuk 3D ---");
        System.out.println("1. Kubus (Prisma Persegi)");
        System.out.println("2. Balok (Prisma Persegi Panjang)");
        System.out.println("3. Bola");
        System.out.println("4. Tabung");
        System.out.println("5. Kerucut");
        System.out.println("6. Kerucut Terpancung");
        System.out.println("7. Cincin Bola");
        System.out.println("8. Juring Bola");
        System.out.println("9. Tembereng Bola");
        System.out.println("10. Prisma Segitiga");
        System.out.println("11. Prisma Trapesium");
        System.out.println("12. Prisma Jajaran Genjang");
        System.out.println("13. Prisma Belah Ketupat");
        System.out.println("14. Prisma Layang-Layang");
        System.out.println("15. Limas Persegi");
        System.out.println("16. Limas Persegi Panjang");
        System.out.println("17. Limas Segitiga");
        System.out.println("18. Limas Trapesium");
        System.out.println("19. Limas Jajaran Genjang");
        System.out.println("20. Limas Belah Ketupat");
        System.out.println("21. Limas Layang-Layang");
        System.out.println("0. Kembali");
        System.out.print("Pilihan Anda: ");
        
        try {
            int pilihan = scanner.nextInt();
            if (pilihan == 0) return;

            switch (pilihan) {
                case 1: // Kubus
                    try {
                        System.out.print("Masukkan sisi kubus: ");
                        double sisi = scanner.nextDouble();
                        PrismaPersegi kubus = new PrismaPersegi(sisi);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", kubus.getVolume(), kubus.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 2: // Balok
                    try {
                        System.out.print("Masukkan panjang: "); double p = scanner.nextDouble();
                        System.out.print("Masukkan lebar: "); double l = scanner.nextDouble();
                        System.out.print("Masukkan tinggi: "); double t = scanner.nextDouble();
                        PrismaPersegiPanjang balok = new PrismaPersegiPanjang(p, l, t);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", balok.getVolume(), balok.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 3: // Bola
                    try {
                        System.out.print("Masukkan jari-jari bola: "); double r = scanner.nextDouble();
                        Bola bola = new Bola(r);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", bola.getVolume(), bola.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 4: // Tabung
                    try {
                        System.out.print("Masukkan jari-jari: "); double r = scanner.nextDouble();
                        System.out.print("Masukkan tinggi: "); double t = scanner.nextDouble();
                        Tabung tabung = new Tabung(r, t);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", tabung.getVolume(), tabung.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 5: // Kerucut
                    try {
                        System.out.print("Masukkan jari-jari: "); double r = scanner.nextDouble();
                        System.out.print("Masukkan tinggi: "); double t = scanner.nextDouble();
                        Kerucut kerucut = new Kerucut(r, t);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", kerucut.getVolume(), kerucut.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 6: // Kerucut Terpancung
                    try {
                        System.out.print("Masukkan jari-jari alas: "); double rAlas = scanner.nextDouble();
                        System.out.print("Masukkan jari-jari atas: "); double rAtas = scanner.nextDouble();
                        System.out.print("Masukkan tinggi: "); double t = scanner.nextDouble();
                        KerucutTerpancung kt = new KerucutTerpancung(rAlas, rAtas, t);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", kt.getVolume(), kt.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 7: // Cincin Bola
                    try {
                        System.out.print("Masukkan jari-jari bola induk: "); double R = scanner.nextDouble();
                        System.out.print("Masukkan tinggi cincin: "); double t = scanner.nextDouble();
                        System.out.print("Masukkan jari-jari atas cincin: "); double rAtas = scanner.nextDouble();
                        System.out.print("Masukkan jari-jari bawah cincin: "); double rBawah = scanner.nextDouble();
                        CincinBola cincin = new CincinBola(R, t, rAtas, rBawah);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan (Lengkung): %.2f\n", cincin.getVolume(), cincin.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 8: // Juring Bola
                    try {
                        System.out.print("Masukkan jari-jari bola: "); double r = scanner.nextDouble();
                        System.out.print("Masukkan tinggi tutup juring: "); double t = scanner.nextDouble();
                        JuringBola juring = new JuringBola(r, t);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", juring.getVolume(), juring.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 9: // Tembereng Bola
                    try {
                        System.out.print("Masukkan jari-jari bola: "); double r = scanner.nextDouble();
                        System.out.print("Masukkan tinggi tembereng: "); double t = scanner.nextDouble();
                        TemberengBola tembereng = new TemberengBola(r, t);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan (Lengkung): %.2f\n", tembereng.getVolume(), tembereng.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 10: // Prisma Segitiga
                    try {
                        System.out.print("Masukkan alas segitiga: "); double alas = scanner.nextDouble();
                        System.out.print("Masukkan tinggi segitiga: "); double tAlas = scanner.nextDouble();
                        System.out.print("Masukkan sisi B: "); double sB = scanner.nextDouble();
                        System.out.print("Masukkan sisi C: "); double sC = scanner.nextDouble();
                        System.out.print("Masukkan tinggi prisma: "); double tPrisma = scanner.nextDouble();
                        PrismaSegitiga prisma = new PrismaSegitiga(alas, tAlas, sB, sC, tPrisma);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", prisma.getVolume(), prisma.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 11: // Prisma Trapesium
                    try {
                        System.out.print("Masukkan sisi atas trapesium: "); double sAtas = scanner.nextDouble();
                        System.out.print("Masukkan sisi bawah trapesium: "); double sBawah = scanner.nextDouble();
                        System.out.print("Masukkan tinggi trapesium: "); double tAlas = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring 1: "); double sM1 = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring 2: "); double sM2 = scanner.nextDouble();
                        System.out.print("Masukkan tinggi prisma: "); double tPrisma = scanner.nextDouble();
                        PrismaTrapesium prisma = new PrismaTrapesium(sAtas, sBawah, tAlas, sM1, sM2, tPrisma);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", prisma.getVolume(), prisma.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 12: // Prisma Jajaran Genjang
                    try {
                        System.out.print("Masukkan alas jajaran genjang: "); double alas = scanner.nextDouble();
                        System.out.print("Masukkan tinggi jajaran genjang: "); double tAlas = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring: "); double sMiring = scanner.nextDouble();
                        System.out.print("Masukkan tinggi prisma: "); double tPrisma = scanner.nextDouble();
                        PrismaJajaranGenjang prisma = new PrismaJajaranGenjang(alas, tAlas, sMiring, tPrisma);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", prisma.getVolume(), prisma.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 13: // Prisma Belah Ketupat
                    try {
                        System.out.print("Masukkan diagonal 1: "); double d1 = scanner.nextDouble();
                        System.out.print("Masukkan diagonal 2: "); double d2 = scanner.nextDouble();
                        System.out.print("Masukkan tinggi prisma: "); double tPrisma = scanner.nextDouble();
                        PrismaBelahKetupat prisma = new PrismaBelahKetupat(d1, d2, tPrisma);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", prisma.getVolume(), prisma.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 14: // Prisma Layang-Layang
                    try {
                        System.out.print("Masukkan diagonal 1: "); double d1 = scanner.nextDouble();
                        System.out.print("Masukkan diagonal 2: "); double d2 = scanner.nextDouble();
                        System.out.print("Masukkan sisi A (pendek): "); double sA = scanner.nextDouble();
                        System.out.print("Masukkan sisi B (panjang): "); double sB = scanner.nextDouble();
                        System.out.print("Masukkan tinggi prisma: "); double tPrisma = scanner.nextDouble();
                        PrismaLayangLayang prisma = new PrismaLayangLayang(d1, d2, sA, sB, tPrisma);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", prisma.getVolume(), prisma.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 15: // Limas Persegi
                    try {
                        System.out.print("Masukkan sisi alas: "); double sisi = scanner.nextDouble();
                        System.out.print("Masukkan tinggi limas: "); double tLimas = scanner.nextDouble();
                        LimasPersegi limas = new LimasPersegi(sisi, tLimas);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", limas.getVolume(), limas.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 16: // Limas Persegi Panjang
                    try {
                        System.out.print("Masukkan panjang alas: "); double p = scanner.nextDouble();
                        System.out.print("Masukkan lebar alas: "); double l = scanner.nextDouble();
                        System.out.print("Masukkan tinggi limas: "); double tLimas = scanner.nextDouble();
                        LimasPersegiPanjang limas = new LimasPersegiPanjang(p, l, tLimas);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", limas.getVolume(), limas.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 17: // Limas Segitiga
                    try {
                        System.out.print("Masukkan alas segitiga: "); double alas = scanner.nextDouble();
                        System.out.print("Masukkan tinggi segitiga: "); double tAlas = scanner.nextDouble();
                        System.out.print("Masukkan sisi B: "); double sB = scanner.nextDouble();
                        System.out.print("Masukkan sisi C: "); double sC = scanner.nextDouble();
                        System.out.print("Masukkan tinggi limas: "); double tLimas = scanner.nextDouble();
                        LimasSegitiga limas = new LimasSegitiga(alas, tAlas, sB, sC, tLimas);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", limas.getVolume(), limas.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 18: // Limas Trapesium
                    try {
                        System.out.print("Masukkan sisi atas trapesium: "); double sAtas = scanner.nextDouble();
                        System.out.print("Masukkan sisi bawah trapesium: "); double sBawah = scanner.nextDouble();
                        System.out.print("Masukkan tinggi trapesium: "); double tAlas = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring 1: "); double sM1 = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring 2: "); double sM2 = scanner.nextDouble();
                        System.out.print("Masukkan tinggi limas: "); double tLimas = scanner.nextDouble();
                        LimasTrapesium limas = new LimasTrapesium(sAtas, sBawah, tAlas, sM1, sM2, tLimas);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", limas.getVolume(), limas.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 19: // Limas Jajaran Genjang
                    try {
                        System.out.print("Masukkan alas jajaran genjang: "); double alas = scanner.nextDouble();
                        System.out.print("Masukkan tinggi jajaran genjang: "); double tAlas = scanner.nextDouble();
                        System.out.print("Masukkan sisi miring: "); double sMiring = scanner.nextDouble();
                        System.out.print("Masukkan tinggi limas: "); double tLimas = scanner.nextDouble();
                        LimasJajaranGenjang limas = new LimasJajaranGenjang(alas, tAlas, sMiring, tLimas);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", limas.getVolume(), limas.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 20: // Limas Belah Ketupat
                    try {
                        System.out.print("Masukkan diagonal 1: "); double d1 = scanner.nextDouble();
                        System.out.print("Masukkan diagonal 2: "); double d2 = scanner.nextDouble();
                        System.out.print("Masukkan tinggi limas: "); double tLimas = scanner.nextDouble();
                        LimasBelahKetupat limas = new LimasBelahKetupat(d1, d2, tLimas);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", limas.getVolume(), limas.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                case 21: // Limas Layang-Layang
                    try {
                        System.out.print("Masukkan diagonal 1: "); double d1 = scanner.nextDouble();
                        System.out.print("Masukkan diagonal 2: "); double d2 = scanner.nextDouble();
                        System.out.print("Masukkan sisi A (pendek): "); double sA = scanner.nextDouble();
                        System.out.print("Masukkan sisi B (panjang): "); double sB = scanner.nextDouble();
                        System.out.print("Masukkan tinggi limas: "); double tLimas = scanner.nextDouble();
                        LimasLayangLayang limas = new LimasLayangLayang(d1, d2, sA, sB, tLimas);
                        System.out.printf("   > Volume: %.2f, Luas Permukaan: %.2f\n", limas.getVolume(), limas.getLuasPermukaan());
                    } catch (Exception e) { System.err.println("Error: " + e.getMessage()); scanner.next(); }
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (InputMismatchException e) {
            System.err.println("Input tidak valid. Harap masukkan sebuah angka.");
            scanner.next();
        }
    }
    
    public static void jalankanMultithreading() {
        System.out.println("\n===== Demo Multithreading Klasik Dimulai =====");
        
        // 1. Buat daftar untuk menampung semua thread
        List<Thread> daftarThread = new ArrayList<>();
        
        try {
            System.out.println("Mempersiapkan semua 31 objek geometri untuk dijalankan di thread terpisah...");
            
            // --- Menambahkan Semua Objek Geometri ke Daftar ---
            // Setiap objek Runnable dibungkus dalam objek Thread baru.
            
            // 2D Shapes
            daftarThread.add(new Thread(new Persegi(5), "Persegi"));
            daftarThread.add(new Thread(new PersegiPanjang(10, 6), "PersegiPanjang"));
            daftarThread.add(new Thread(new Segitiga(6, 4, 5, 5), "Segitiga"));
            daftarThread.add(new Thread(new Lingkaran(7), "Lingkaran"));
            daftarThread.add(new Thread(new BelahKetupat(12, 16), "BelahKetupat"));
            daftarThread.add(new Thread(new JajaranGenjang(10, 5, 6), "JajaranGenjang"));
            daftarThread.add(new Thread(new Trapesium(10, 16, 4, 5, 5), "Trapesium"));
            daftarThread.add(new Thread(new LayangLayang(21, 16, 10, 17), "LayangLayang"));
            daftarThread.add(new Thread(new JuringLingkaran(14, 60), "JuringLingkaran"));
            daftarThread.add(new Thread(new TemberengLingkaran(14, 90), "TemberengLingkaran"));

            // 3D Polygon-based Shapes
            daftarThread.add(new Thread(new PrismaPersegi(8), "Kubus"));
            daftarThread.add(new Thread(new LimasPersegi(6, 4), "LimasPersegi"));
            daftarThread.add(new Thread(new PrismaPersegiPanjang(8, 6, 10), "Balok"));
            daftarThread.add(new Thread(new LimasPersegiPanjang(8, 6, 10), "LimasPersegiPanjang"));
            daftarThread.add(new Thread(new PrismaSegitiga(6, 4, 5, 5, 10), "PrismaSegitiga"));
            daftarThread.add(new Thread(new LimasSegitiga(6, 4, 5, 5, 10), "LimasSegitiga"));
            daftarThread.add(new Thread(new PrismaBelahKetupat(12, 16, 10), "PrismaBelahKetupat"));
            daftarThread.add(new Thread(new LimasBelahKetupat(12, 16, 10), "LimasBelahKetupat"));
            daftarThread.add(new Thread(new PrismaJajaranGenjang(10, 5, 6, 12), "PrismaJajaranGenjang"));
            daftarThread.add(new Thread(new LimasJajaranGenjang(10, 5, 6, 12), "LimasJajaranGenjang"));
            daftarThread.add(new Thread(new PrismaTrapesium(10, 16, 4, 5, 5, 12), "PrismaTrapesium"));
            daftarThread.add(new Thread(new LimasTrapesium(10, 16, 4, 5, 5, 12), "LimasTrapesium"));
            daftarThread.add(new Thread(new PrismaLayangLayang(21, 16, 10, 17, 10), "PrismaLayangLayang"));
            daftarThread.add(new Thread(new LimasLayangLayang(21, 16, 10, 17, 10), "LimasLayangLayang"));
            
            // 3D Circle-based Shapes
            daftarThread.add(new Thread(new Bola(14), "Bola"));
            daftarThread.add(new Thread(new Tabung(7, 10), "Tabung"));
            daftarThread.add(new Thread(new Kerucut(7, 24), "Kerucut"));
            daftarThread.add(new Thread(new KerucutTerpancung(14, 7, 24), "KerucutTerpancung"));
            daftarThread.add(new Thread(new JuringBola(10, 4), "JuringBola"));
            daftarThread.add(new Thread(new CincinBola(10, 8, 6, 6), "CincinBola"));
            daftarThread.add(new Thread(new TemberengBola(10, 2), "TemberengBola"));

            // 3. Fase Peluncuran: Mulai semua thread agar berjalan secara paralel
            System.out.println("\nMeluncurkan semua " + daftarThread.size() + " thread...");
            for (Thread t : daftarThread) {
                t.start();
            }

            // 4. Fase Penantian: Thread 'main' akan menunggu semua thread di list ini selesai
            System.out.println("\nThread utama menunggu semua perhitungan selesai. Amati urutan 'selesai' yang acak...");
            for (Thread t : daftarThread) {
                t.join(); // Menunggu thread 't' untuk selesai sebelum melanjutkan loop
            }

        } catch (Exception e) {
            System.err.println("Terjadi error pada thread utama: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n===== Semua Perhitungan Paralel Telah Selesai =====");
    }
    
    // File: BendaGeometriSesuai/src/bendageometri/MainGeometri.java

    public static void demonstrasiPolimorfisme() {
        System.out.println("\n===== Demo Polimorfisme Lengkap Dimulai =====");
        System.out.println("Membuat semua objek turunan Lingkaran dengan referensi ke kelas induk...");

        try {
            // Deklarasi Polimorfik: Semua variabel referensi bertipe Lingkaran
            Lingkaran objLingkaran = new Lingkaran(10);
            Lingkaran objJuring = new JuringLingkaran(10, 90);
            Lingkaran objTembereng = new TemberengLingkaran(10, 90);
            Lingkaran objBola = new Bola(10);
            Lingkaran objTabung = new Tabung(10, 20);
            Lingkaran objKerucut = new Kerucut(10, 15);
            Lingkaran objKerucutTerpancung = new KerucutTerpancung(10, 5, 12);
            Lingkaran objJuringBola = new JuringBola(10, 4);
            Lingkaran objCincinBola = new CincinBola(10, 8, 6, 6);
            Lingkaran objTemberengBola = new TemberengBola(10, 2);

            System.out.println("\nMemanggil satu metode 'displayInfo(Lingkaran benda)' untuk semua objek...");
            
            displayInfo(objLingkaran);
            displayInfo(objJuring);
            displayInfo(objTembereng);
            displayInfo(objBola);
            displayInfo(objTabung);
            displayInfo(objKerucut);
            displayInfo(objKerucutTerpancung);
            displayInfo(objJuringBola);
            displayInfo(objCincinBola);
            displayInfo(objTemberengBola);

        } catch (Exception e) {
            System.err.println("Terjadi galat (error) saat demo polimorfisme: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n===== Demo Polimorfisme Selesai =====");
    }

    /**
     * Metode pembantu yang akan memeriksa tipe asli objek dan menampilkan informasi yang sesuai.
     * **[DIUBAH]** Metode ini sekarang juga menampilkan properti yang diwarisi dari Lingkaran.
     */
    public static void displayInfo(Lingkaran benda) {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Tipe Objek Aktual: " + benda.getClass().getSimpleName());

        // Menggunakan 'instanceof' untuk memeriksa "bentuk asli" dari objek
        // dan memanggil getter yang sesuai setelah casting.

        if (benda instanceof JuringLingkaran) {
            JuringLingkaran juring = (JuringLingkaran) benda;
            System.out.printf("  -> Luas Lingkaran Induk      : %.2f\n", juring.luas);
            System.out.printf("  -> Keliling Lingkaran Induk  : %.2f\n", juring.keliling);
            System.out.printf("  -> Luas Juring               : %.2f\n", juring.getLuasJuring());
            System.out.printf("  -> Keliling Juring           : %.2f\n", juring.getKelilingJuring());

        } else if (benda instanceof TemberengLingkaran) {
            TemberengLingkaran tembereng = (TemberengLingkaran) benda;
            System.out.printf("  -> Luas Lingkaran Induk      : %.2f\n", tembereng.luas);
            System.out.printf("  -> Keliling Lingkaran Induk  : %.2f\n", tembereng.keliling);
            System.out.printf("  -> Luas Tembereng            : %.2f\n", tembereng.getLuasTembereng());
            System.out.printf("  -> Keliling Tembereng        : %.2f\n", tembereng.getKelilingTembereng());

        } else if (benda instanceof Tabung) {
            Tabung t = (Tabung) benda;
            System.out.printf("  -> Luas Alas                 : %.2f\n", t.luas);
            System.out.printf("  -> Keliling Alas             : %.2f\n", t.keliling);
            System.out.printf("  -> Volume                    : %.2f\n", t.getVolume());
            System.out.printf("  -> Luas Permukaan            : %.2f\n", t.getLuasPermukaan());
        } else if (benda instanceof Bola) {
            Bola b = (Bola) benda;
            System.out.printf("  -> Luas Lingkaran Besar      : %.2f\n", b.luas);
            System.out.printf("  -> Keliling Lingkaran Besar  : %.2f\n", b.keliling);
            System.out.printf("  -> Volume                    : %.2f\n", b.getVolume());
            System.out.printf("  -> Luas Permukaan            : %.2f\n", b.getLuasPermukaan());
        } else if (benda instanceof Kerucut) {
            Kerucut k = (Kerucut) benda;
            System.out.printf("  -> Luas Alas                 : %.2f\n", k.luas);
            System.out.printf("  -> Keliling Alas             : %.2f\n", k.keliling);
            System.out.printf("  -> Volume                    : %.2f\n", k.getVolume());
            System.out.printf("  -> Luas Permukaan            : %.2f\n", k.getLuasPermukaan());
        } else if (benda instanceof KerucutTerpancung) {
            KerucutTerpancung kt = (KerucutTerpancung) benda;
            System.out.printf("  -> Luas Alas Bawah           : %.2f\n", kt.luas);
            System.out.printf("  -> Keliling Alas Bawah       : %.2f\n", kt.keliling);
            System.out.printf("  -> Volume                    : %.2f\n", kt.getVolume());
            System.out.printf("  -> Luas Permukaan            : %.2f\n", kt.getLuasPermukaan());
        } else if (benda instanceof JuringBola) {
            JuringBola jb = (JuringBola) benda;
            System.out.printf("  -> Luas Lingkaran Besar      : %.2f\n", jb.luas);
            System.out.printf("  -> Keliling Lingkaran Besar  : %.2f\n", jb.keliling);
            System.out.printf("  -> Volume                    : %.2f\n", jb.getVolume());
            System.out.printf("  -> Luas Permukaan            : %.2f\n", jb.getLuasPermukaan());
        } else if (benda instanceof CincinBola) {
            CincinBola cb = (CincinBola) benda;
            System.out.printf("  -> Luas Lingkaran Besar      : %.2f\n", cb.luas);
            System.out.printf("  -> Keliling Lingkaran Besar  : %.2f\n", cb.keliling);
            System.out.printf("  -> Volume                    : %.2f\n", cb.getVolume());
            System.out.printf("  -> Luas Permukaan            : %.2f\n", cb.getLuasPermukaan());
        } else if (benda instanceof TemberengBola) {
            TemberengBola tb = (TemberengBola) benda;
            System.out.printf("  -> Luas Lingkaran Besar      : %.2f\n", tb.luas);
            System.out.printf("  -> Keliling Lingkaran Besar  : %.2f\n", tb.keliling);
            System.out.printf("  -> Volume                    : %.2f\n", tb.getVolume());
            System.out.printf("  -> Luas Permukaan            : %.2f\n", tb.getLuasPermukaan());
        } else if (benda instanceof Lingkaran) {
            // Ini adalah kasus dasar jika objeknya adalah Lingkaran murni.
            System.out.printf("  -> Luas Lingkaran            : %.2f\n", benda.luas);
            System.out.printf("  -> Keliling Lingkaran        : %.2f\n", benda.keliling);
        }
    }
}