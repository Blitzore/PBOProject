package bendageometri;

import bendageometri.benda2d.*;
import bendageometri.benda3d.*;
import java.util.Scanner;

public class MainGeometri {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Kalkulator Benda Geometri (Versi Input) ---");
        
        while (true) {
            System.out.println("\nPilih kategori:");
            System.out.println("1. Benda 2D");
            System.out.println("2. Benda 3D");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            int kategori = scanner.nextInt();
            
            if (kategori == 0) break;
            
            switch (kategori) {
                case 1:
                    menuBenda2D(scanner);
                    break;
                case 2:
                    menuBenda3D(scanner);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        
        System.out.println("\nTerima kasih telah menggunakan program ini!");
        scanner.close();
    }
    
    private static void menuBenda2D(Scanner scanner) {
        System.out.println("\nPilih bentuk 2D:");
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
        int pilihan = scanner.nextInt();
        
        if (pilihan == 0) return;
        
        try {
            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan sisi persegi: ");
                    double sisi = scanner.nextDouble();
                    Persegi persegi = new Persegi(sisi);
                    System.out.printf("Luas: %.2f\n", persegi.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", persegi.hitungKeliling());
                    break;
                    
                case 2:
                    System.out.print("Masukkan panjang: ");
                    double panjang = scanner.nextDouble();
                    System.out.print("Masukkan lebar: ");
                    double lebar = scanner.nextDouble();
                    PersegiPanjang pp = new PersegiPanjang(panjang, lebar);
                    System.out.printf("Luas: %.2f\n", pp.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", pp.hitungKeliling());
                    break;
                    
                case 3:
                    System.out.print("Masukkan jari-jari lingkaran: ");
                    double jariJari = scanner.nextDouble();
                    Lingkaran lingkaran = new Lingkaran(jariJari);
                    System.out.printf("Luas: %.2f\n", lingkaran.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", lingkaran.hitungKeliling());
                    break;
                    
                case 4:
                    System.out.print("Masukkan alas segitiga: ");
                    double alas = scanner.nextDouble();
                    System.out.print("Masukkan tinggi segitiga: ");
                    double tinggi = scanner.nextDouble();
                    System.out.print("Masukkan sisi B: ");
                    double sisiB = scanner.nextDouble();
                    System.out.print("Masukkan sisi C: ");
                    double sisiC = scanner.nextDouble();
                    Segitiga segitiga = new Segitiga(alas, tinggi, sisiB, sisiC);
                    System.out.printf("Luas: %.2f\n", segitiga.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", segitiga.hitungKeliling());
                    break;
                    
                case 5:
                    System.out.print("Masukkan sisi atas trapesium: ");
                    double sisiAtas = scanner.nextDouble();
                    System.out.print("Masukkan sisi bawah trapesium: ");
                    double sisiBawah = scanner.nextDouble();
                    System.out.print("Masukkan tinggi trapesium: ");
                    double tinggiTrap = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring 1: ");
                    double sisiMiring1 = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring 2: ");
                    double sisiMiring2 = scanner.nextDouble();
                    Trapesium trapesium = new Trapesium(sisiAtas, sisiBawah, tinggiTrap, sisiMiring1, sisiMiring2);
                    System.out.printf("Luas: %.2f\n", trapesium.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", trapesium.hitungKeliling());
                    break;
                    
                case 6:
                    System.out.print("Masukkan alas jajaran genjang: ");
                    double alasJG = scanner.nextDouble();
                    System.out.print("Masukkan tinggi jajaran genjang: ");
                    double tinggiJG = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring: ");
                    double sisiMiringJG = scanner.nextDouble();
                    JajaranGenjang jg = new JajaranGenjang(alasJG, tinggiJG, sisiMiringJG);
                    System.out.printf("Luas: %.2f\n", jg.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", jg.hitungKeliling());
                    break;
                    
                case 7:
                    System.out.print("Masukkan diagonal 1 belah ketupat: ");
                    double d1 = scanner.nextDouble();
                    System.out.print("Masukkan diagonal 2 belah ketupat: ");
                    double d2 = scanner.nextDouble();
                    BelahKetupat bk = new BelahKetupat(d1, d2);
                    System.out.printf("Luas: %.2f\n", bk.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", bk.hitungKeliling());
                    break;
                    
                case 8:
                    System.out.print("Masukkan diagonal 1 layang-layang: ");
                    double d1LL = scanner.nextDouble();
                    System.out.print("Masukkan diagonal 2 layang-layang: ");
                    double d2LL = scanner.nextDouble();
                    System.out.print("Masukkan sisi A: ");
                    double sisiA = scanner.nextDouble();
                    System.out.print("Masukkan sisi B: ");
                    double sisiBLL = scanner.nextDouble();
                    LayangLayang ll = new LayangLayang(d1LL, d2LL, sisiA, sisiBLL);
                    System.out.printf("Luas: %.2f\n", ll.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", ll.hitungKeliling());
                    break;
                    
                case 9:
                    System.out.print("Masukkan jari-jari juring: ");
                    double jariJuring = scanner.nextDouble();
                    System.out.print("Masukkan sudut pusat (derajat): ");
                    double sudut = scanner.nextDouble();
                    JuringLingkaran juring = new JuringLingkaran(jariJuring, sudut);
                    System.out.printf("Luas: %.2f\n", juring.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", juring.hitungKeliling());
                    break;
                    
                case 10:
                    System.out.print("Masukkan jari-jari tembereng: ");
                    double jariTembereng = scanner.nextDouble();
                    System.out.print("Masukkan sudut pusat (derajat): ");
                    double sudutTembereng = scanner.nextDouble();
                    TemberengLingkaran tembereng = new TemberengLingkaran(jariTembereng, sudutTembereng);
                    System.out.printf("Luas: %.2f\n", tembereng.hitungLuas());
                    System.out.printf("Keliling: %.2f\n", tembereng.hitungKeliling());
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void menuBenda3D(Scanner scanner) {
        System.out.println("\nPilih bentuk 3D:");
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
        int pilihan = scanner.nextInt();
        
        if (pilihan == 0) return;
        
        try {
            switch (pilihan) {
                // Bentuk yang sudah ada sebelumnya
                case 1: // Kubus
                    System.out.print("Masukkan sisi kubus: ");
                    double sisiKubus = scanner.nextDouble();
                    PrismaPersegi kubus = new PrismaPersegi(sisiKubus); // Konstruktor baru
                    System.out.printf("Volume: %.2f\n", kubus.getVolume()); // Gunakan getter
                    System.out.printf("Luas Permukaan: %.2f\n", kubus.getLuasPermukaan()); // Gunakan getter
                    break;
                    
                case 2: // Balok
                    System.out.print("Masukkan panjang balok: ");
                    double panjangBalok = scanner.nextDouble();
                    System.out.print("Masukkan lebar balok: ");
                    double lebarBalok = scanner.nextDouble();
                    System.out.print("Masukkan tinggi balok: ");
                    double tinggiBalok = scanner.nextDouble();
                    PrismaPersegiPanjang balok = new PrismaPersegiPanjang(panjangBalok, lebarBalok, tinggiBalok);
                    System.out.printf("Volume: %.2f\n", balok.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", balok.hitungLuasPermukaan());
                    break;
                    
                case 3: // Bola
                    System.out.print("Masukkan jari-jari bola: ");
                    double jariBola = scanner.nextDouble();
                    Bola bola = new Bola(jariBola);
                    System.out.printf("Volume: %.2f\n", bola.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", bola.hitungLuasPermukaan());
                    break;
                    
                case 4: // Tabung
                    System.out.print("Masukkan jari-jari tabung: ");
                    double jariTabung = scanner.nextDouble();
                    System.out.print("Masukkan tinggi tabung: ");
                    double tinggiTabung = scanner.nextDouble();
                    Tabung tabung = new Tabung(jariTabung, tinggiTabung);
                    System.out.printf("Volume: %.2f\n", tabung.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", tabung.hitungLuasPermukaan());
                    break;
                    
                case 5: // Kerucut
                    System.out.print("Masukkan jari-jari kerucut: ");
                    double jariKerucut = scanner.nextDouble();
                    System.out.print("Masukkan tinggi kerucut: ");
                    double tinggiKerucut = scanner.nextDouble();
                    Kerucut kerucut = new Kerucut(jariKerucut, tinggiKerucut);
                    System.out.printf("Volume: %.2f\n", kerucut.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", kerucut.hitungLuasPermukaan());
                    break;
                    
                case 6: // Kerucut Terpancung
                    System.out.print("Masukkan jari-jari bawah: ");
                    double jariBawah = scanner.nextDouble();
                    System.out.print("Masukkan jari-jari atas: ");
                    double jariAtas = scanner.nextDouble();
                    System.out.print("Masukkan tinggi: ");
                    double tinggiKT = scanner.nextDouble();
                    KerucutTerpancung kt = new KerucutTerpancung(jariBawah, jariAtas, tinggiKT);
                    System.out.printf("Volume: %.2f\n", kt.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", kt.hitungLuasPermukaan());
                    break;
                    
                case 7: // Cincin Bola
                    System.out.print("Masukkan jari-jari bola induk: ");
                    double jariInduk = scanner.nextDouble();
                    System.out.print("Masukkan jari-jari alas atas: ");
                    double jariAtasCincin = scanner.nextDouble();
                    System.out.print("Masukkan jari-jari alas bawah: ");
                    double jariBawahCincin = scanner.nextDouble();
                    System.out.print("Masukkan tinggi cincin: ");
                    double tinggiCincin = scanner.nextDouble();
                    CincinBola cincin = new CincinBola(jariInduk, jariAtasCincin, jariBawahCincin, tinggiCincin);
                    System.out.printf("Volume: %.2f\n", cincin.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", cincin.hitungLuasPermukaan());
                    break;
                    
                case 8: // Juring Bola
                    System.out.print("Masukkan jari-jari bola: ");
                    double jariJuring = scanner.nextDouble();
                    System.out.print("Masukkan tinggi topi juring: ");
                    double tinggiJuring = scanner.nextDouble();
                    JuringBola juring = new JuringBola(jariJuring, tinggiJuring);
                    System.out.printf("Volume: %.2f\n", juring.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", juring.hitungLuasPermukaan());
                    break;
                    
                case 9: // Tembereng Bola
                    System.out.print("Masukkan jari-jari bola: ");
                    double jariTembereng = scanner.nextDouble();
                    System.out.print("Masukkan tinggi tembereng: ");
                    double tinggiTembereng = scanner.nextDouble();
                    TemberengBola tembereng = new TemberengBola(jariTembereng, tinggiTembereng);
                    System.out.printf("Volume: %.2f\n", tembereng.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", tembereng.hitungLuasPermukaan());
                    break;
                    
                case 10: // Prisma Segitiga
                    System.out.print("Masukkan alas segitiga: ");
                    double alasPrisma = scanner.nextDouble();
                    System.out.print("Masukkan tinggi segitiga: ");
                    double tinggiAlasPrisma = scanner.nextDouble();
                    System.out.print("Masukkan sisi B: ");
                    double sisiBPrisma = scanner.nextDouble();
                    System.out.print("Masukkan sisi C: ");
                    double sisiCPrisma = scanner.nextDouble();
                    System.out.print("Masukkan tinggi prisma: ");
                    double tinggiPrismaSegitiga = scanner.nextDouble();
                    PrismaSegitiga prismaSegitiga = new PrismaSegitiga(alasPrisma, tinggiAlasPrisma, sisiBPrisma, sisiCPrisma, tinggiPrismaSegitiga);
                    System.out.printf("Volume: %.2f\n", prismaSegitiga.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", prismaSegitiga.hitungLuasPermukaan());
                    break;
                    
                case 11: // Prisma Trapesium
                    System.out.print("Masukkan sisi atas trapesium: ");
                    double sisiAtasTrap = scanner.nextDouble();
                    System.out.print("Masukkan sisi bawah trapesium: ");
                    double sisiBawahTrap = scanner.nextDouble();
                    System.out.print("Masukkan tinggi trapesium: ");
                    double tinggiTrap = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring 1: ");
                    double sisiMiring1 = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring 2: ");
                    double sisiMiring2 = scanner.nextDouble();
                    System.out.print("Masukkan tinggi prisma: ");
                    double tinggiPrismaTrap = scanner.nextDouble();
                    PrismaTrapesium prismaTrapesium = new PrismaTrapesium(sisiAtasTrap, sisiBawahTrap, tinggiTrap, sisiMiring1, sisiMiring2, tinggiPrismaTrap);
                    System.out.printf("Volume: %.2f\n", prismaTrapesium.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", prismaTrapesium.hitungLuasPermukaan());
                    break;
                    
                case 12: // Prisma Jajaran Genjang
                    System.out.print("Masukkan alas jajaran genjang: ");
                    double alasJG = scanner.nextDouble();
                    System.out.print("Masukkan tinggi jajaran genjang: ");
                    double tinggiJG = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring: ");
                    double sisiMiringJG = scanner.nextDouble();
                    System.out.print("Masukkan tinggi prisma: ");
                    double tinggiPrismaJG = scanner.nextDouble();
                    PrismaJajaranGenjang prismaJG = new PrismaJajaranGenjang(alasJG, tinggiJG, sisiMiringJG, tinggiPrismaJG);
                    System.out.printf("Volume: %.2f\n", prismaJG.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", prismaJG.hitungLuasPermukaan());
                    break;
                    
                case 13: // Prisma Belah Ketupat
                    System.out.print("Masukkan diagonal 1: ");
                    double d1BK = scanner.nextDouble();
                    System.out.print("Masukkan diagonal 2: ");
                    double d2BK = scanner.nextDouble();
                    System.out.print("Masukkan tinggi prisma: ");
                    double tinggiPrismaBK = scanner.nextDouble();
                    PrismaBelahKetupat prismaBK = new PrismaBelahKetupat(d1BK, d2BK, tinggiPrismaBK);
                    System.out.printf("Volume: %.2f\n", prismaBK.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", prismaBK.hitungLuasPermukaan());
                    break;
                    
                case 14: // Prisma Layang-Layang
                    System.out.print("Masukkan diagonal 1: ");
                    double d1LL = scanner.nextDouble();
                    System.out.print("Masukkan diagonal 2: ");
                    double d2LL = scanner.nextDouble();
                    System.out.print("Masukkan sisi A: ");
                    double sisiALL = scanner.nextDouble();
                    System.out.print("Masukkan sisi B: ");
                    double sisiBLL = scanner.nextDouble();
                    System.out.print("Masukkan tinggi prisma: ");
                    double tinggiPrismaLL = scanner.nextDouble();
                    PrismaLayangLayang prismaLL = new PrismaLayangLayang(d1LL, d2LL, sisiALL, sisiBLL, tinggiPrismaLL);
                    System.out.printf("Volume: %.2f\n", prismaLL.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", prismaLL.hitungLuasPermukaan());
                    break;
                    
                case 15: // Limas Persegi
                    System.out.print("Masukkan sisi alas: ");
                    double sisiLimas = scanner.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiLimasPersegi = scanner.nextDouble();
                    LimasPersegi limasPersegi = new LimasPersegi(sisiLimas, tinggiLimasPersegi);
                    System.out.printf("Volume: %.2f\n", limasPersegi.hitungVolume());
                    System.out.printf("Luas Permukaan: %.2f\n", limasPersegi.hitungLuasPermukaan());
                    break;
                    
                case 16: // Limas Persegi Panjang
                    System.out.print("Masukkan panjang alas: ");
                    double panjangLimas = scanner.nextDouble();
                    System.out.print("Masukkan lebar alas: ");
                    double lebarLimas = scanner.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiLimasPP = scanner.nextDouble();
                    LimasPersegiPanjang limasPP = new LimasPersegiPanjang(panjangLimas, lebarLimas, tinggiLimasPP);
                    System.out.printf("Volume: %.2f\n", limasPP.hitungVolume());
                    System.out.printf("Luas Permukaan (perkiraan): %.2f\n", limasPP.hitungLuasPermukaan());
                    break;
                    
                case 17: // Limas Segitiga
                    System.out.print("Masukkan alas segitiga: ");
                    double alasLimas = scanner.nextDouble();
                    System.out.print("Masukkan tinggi segitiga: ");
                    double tinggiAlasLimas = scanner.nextDouble();
                    System.out.print("Masukkan sisi B: ");
                    double sisiBLimas = scanner.nextDouble();
                    System.out.print("Masukkan sisi C: ");
                    double sisiCLimas = scanner.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiLimasSegitiga = scanner.nextDouble();
                    LimasSegitiga limasSegitiga = new LimasSegitiga(alasLimas, tinggiAlasLimas, sisiBLimas, sisiCLimas, tinggiLimasSegitiga);
                    System.out.printf("Volume: %.2f\n", limasSegitiga.hitungVolume());
                    System.out.printf("Luas Permukaan (perkiraan): %.2f\n", limasSegitiga.hitungLuasPermukaan());
                    break;
                    
                case 18: // Limas Trapesium
                    System.out.print("Masukkan sisi atas: ");
                    double sisiAtasLimas = scanner.nextDouble();
                    System.out.print("Masukkan sisi bawah: ");
                    double sisiBawahLimas = scanner.nextDouble();
                    System.out.print("Masukkan tinggi trapesium: ");
                    double tinggiTrapLimas = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring 1: ");
                    double sisiMiring1Limas = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring 2: ");
                    double sisiMiring2Limas = scanner.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiLimasTrap = scanner.nextDouble();
                    LimasTrapesium limasTrapesium = new LimasTrapesium(sisiAtasLimas, sisiBawahLimas, tinggiTrapLimas, sisiMiring1Limas, sisiMiring2Limas, tinggiLimasTrap);
                    System.out.printf("Volume: %.2f\n", limasTrapesium.hitungVolume());
                    System.out.printf("Luas Permukaan (perkiraan): %.2f\n", limasTrapesium.hitungLuasPermukaan());
                    break;
                    
                case 19: // Limas Jajaran Genjang
                    System.out.print("Masukkan alas: ");
                    double alasLimasJG = scanner.nextDouble();
                    System.out.print("Masukkan tinggi: ");
                    double tinggiLimasJG = scanner.nextDouble();
                    System.out.print("Masukkan sisi miring: ");
                    double sisiMiringLimasJG = scanner.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiLimasJGTotal = scanner.nextDouble();
                    LimasJajaranGenjang limasJG = new LimasJajaranGenjang(alasLimasJG, tinggiLimasJG, sisiMiringLimasJG, tinggiLimasJGTotal);
                    System.out.printf("Volume: %.2f\n", limasJG.hitungVolume());
                    System.out.printf("Luas Permukaan (perkiraan): %.2f\n", limasJG.hitungLuasPermukaan());
                    break;
                    
                case 20: // Limas Belah Ketupat
                    System.out.print("Masukkan diagonal 1: ");
                    double d1LimasBK = scanner.nextDouble();
                    System.out.print("Masukkan diagonal 2: ");
                    double d2LimasBK = scanner.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiLimasBK = scanner.nextDouble();
                    LimasBelahKetupat limasBK = new LimasBelahKetupat(d1LimasBK, d2LimasBK, tinggiLimasBK);
                    System.out.printf("Volume: %.2f\n", limasBK.hitungVolume());
                    System.out.printf("Luas Permukaan (perkiraan): %.2f\n", limasBK.hitungLuasPermukaan());
                    break;
                    
                case 21: // Limas Layang-Layang
                    System.out.print("Masukkan diagonal 1: ");
                    double d1LimasLL = scanner.nextDouble();
                    System.out.print("Masukkan diagonal 2: ");
                    double d2LimasLL = scanner.nextDouble();
                    System.out.print("Masukkan sisi A: ");
                    double sisiALimasLL = scanner.nextDouble();
                    System.out.print("Masukkan sisi B: ");
                    double sisiBLimasLL = scanner.nextDouble();
                    System.out.print("Masukkan tinggi limas: ");
                    double tinggiLimasLL = scanner.nextDouble();
                    LimasLayangLayang limasLL = new LimasLayangLayang(d1LimasLL, d2LimasLL, sisiALimasLL, sisiBLimasLL, tinggiLimasLL);
                    System.out.printf("Volume: %.2f\n", limasLL.hitungVolume());
                    System.out.printf("Luas Permukaan (perkiraan): %.2f\n", limasLL.hitungLuasPermukaan());
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}