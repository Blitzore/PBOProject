package bendageometri;

import bendageometri.benda2d.*;
import bendageometri.benda3d.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Kelas ini menyediakan antarmuka pengguna grafis (GUI) untuk Kalkulator
 * Geometri. GUI telah dipisah untuk menangani kalkulator 2D dan 3D secara
 * terpisah. Menggunakan Swing untuk komponen visual dan SwingWorker untuk tugas
 * di latar belakang.
 */
public class MainGui extends JFrame {

    // Panel utama yang dapat diganti-ganti menggunakan CardLayout
    private CardLayout mainCardLayout;
    private JPanel mainContentPanel;

    // Map untuk menyimpan panel input untuk setiap bentuk geometri (repositori pusat)
    private final Map<String, JPanel> inputPanels = new LinkedHashMap<>();
    // Map untuk menyimpan referensi ke semua text field di setiap panel input
    private final Map<String, List<JTextField>> inputFields = new LinkedHashMap<>();

    // Panel dan komponen untuk demo
    private JTextArea multithreadingTextArea;
    private JTextArea polymorphismTextArea;

    // Daftar nama bentuk untuk memisahkan 2D dan 3D
    private final List<String> shapes2D = Arrays.asList(
            "Persegi", "Persegi Panjang", "Lingkaran", "Segitiga", "Trapesium",
            "Jajaran Genjang", "Belah Ketupat", "Layang-Layang", "Juring Lingkaran", "Tembereng Lingkaran"
    );
    private final List<String> shapes3D = Arrays.asList(
            "Kubus", "Balok", "Bola", "Tabung", "Kerucut", "Kerucut Terpancung",
            "Cincin Bola", "Juring Bola", "Tembereng Bola", "Prisma Segitiga", "Prisma Trapesium",
            "Prisma Jajaran Genjang", "Prisma Belah Ketupat", "Prisma Layang-Layang",
            "Limas Persegi", "Limas Persegi Panjang", "Limas Segitiga", "Limas Trapesium",
            "Limas Jajaran Genjang", "Limas Belah Ketupat", "Limas Layang-Layang"
    );

    public MainGui() {
        // --- Pengaturan Frame Utama ---
        setTitle("Kalkulator Geometri GUI");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Daftarkan semua panel input ke dalam map terlebih dahulu
        registerAllShapes();

        // --- Panel Navigasi (Kiri) ---
        JPanel navigationPanel = new JPanel(new GridLayout(5, 1, 10, 10)); // Diubah menjadi 5 baris
        navigationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Tombol navigasi baru
        JButton calc2DButton = new JButton("Kalkulator 2D");
        JButton calc3DButton = new JButton("Kalkulator 3D");
        JButton multiThreadButton = new JButton("Demo Multithreading");
        JButton polyButton = new JButton("Demo Polimorfisme");
        JButton exitButton = new JButton("Keluar");

        navigationPanel.add(calc2DButton);
        navigationPanel.add(calc3DButton);
        navigationPanel.add(multiThreadButton);
        navigationPanel.add(polyButton);
        navigationPanel.add(exitButton);

        // --- Panel Konten Utama (Tengah/Kanan) dengan CardLayout ---
        mainCardLayout = new CardLayout();
        mainContentPanel = new JPanel(mainCardLayout);
        mainContentPanel.setBorder(new EmptyBorder(10, 0, 10, 10));

        // Membuat dan menambahkan panel-panel utama
        mainContentPanel.add(createGenericCalculatorPanel("2D"), "KALKULATOR_2D");
        mainContentPanel.add(createGenericCalculatorPanel("3D"), "KALKULATOR_3D");
        mainContentPanel.add(createDemoPanel("Multithreading"), "MULTITHREADING");
        mainContentPanel.add(createDemoPanel("Polimorfisme"), "POLIMORFISME");

        add(navigationPanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);

        // --- Menambahkan Action Listeners untuk Navigasi ---
        calc2DButton.addActionListener(e -> mainCardLayout.show(mainContentPanel, "KALKULATOR_2D"));
        calc3DButton.addActionListener(e -> mainCardLayout.show(mainContentPanel, "KALKULATOR_3D"));
        multiThreadButton.addActionListener(e -> mainCardLayout.show(mainContentPanel, "MULTITHREADING"));
        polyButton.addActionListener(e -> mainCardLayout.show(mainContentPanel, "POLIMORFISME"));
        exitButton.addActionListener(e -> System.exit(0));

        // Tampilkan panel kalkulator 2D secara default
        mainCardLayout.show(mainContentPanel, "KALKULATOR_2D");
    }

    /**
     * Membuat panel kalkulator generik untuk 2D atau 3D.
     *
     * @param type Tipe kalkulator ("2D" atau "3D").
     * @return JPanel yang berisi fungsionalitas kalkulator.
     */
    private JPanel createGenericCalculatorPanel(String type) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        List<String> shapeList = type.equals("2D") ? shapes2D : shapes3D;

        // --- Panel Pilihan Bentuk (Atas) ---
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> localShapeComboBox = new JComboBox<>();
        selectionPanel.add(new JLabel("Pilih Bentuk " + type + ":"));
        selectionPanel.add(localShapeComboBox);

        // --- Panel Input Dinamis (Tengah) ---
        CardLayout localInputCardLayout = new CardLayout();
        JPanel localInputCardsPanel = new JPanel(localInputCardLayout);

        // Tambahkan panel input yang sesuai ke CardLayout dan item ke ComboBox
        for (String shapeName : shapeList) {
            if (inputPanels.containsKey(shapeName)) {
                localInputCardsPanel.add(inputPanels.get(shapeName), shapeName);
                localShapeComboBox.addItem(shapeName);
            }
        }

        // --- Panel Kontrol dan Hasil (Bawah) ---
        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        JButton calculateButton = new JButton("Hitung");
        JLabel localResultLabel = new JLabel("Hasil akan ditampilkan di sini.", SwingConstants.CENTER);
        localResultLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        controlPanel.add(calculateButton, BorderLayout.NORTH);
        controlPanel.add(localResultLabel, BorderLayout.CENTER);

        // Ganti panel input saat item ComboBox berubah
        localShapeComboBox.addActionListener(e -> {
            String selectedShape = (String) localShapeComboBox.getSelectedItem();
            localInputCardLayout.show(localInputCardsPanel, selectedShape);
            localResultLabel.setText("Hasil akan ditampilkan di sini.");
        });

        // Menambahkan Listener untuk Tombol Hitung, menggunakan komponen lokal
        calculateButton.addActionListener(e -> performCalculation(localShapeComboBox, localResultLabel));

        panel.add(selectionPanel, BorderLayout.NORTH);
        panel.add(localInputCardsPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Membuat panel untuk demo, baik Multithreading maupun Polimorfisme.
     */
    private JPanel createDemoPanel(String demoType) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JButton runButton = new JButton("Jalankan Demo " + demoType);
        JTextArea textArea = new JTextArea("Output demo akan ditampilkan di sini...");
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);

        if (demoType.equals("Multithreading")) {
            multithreadingTextArea = textArea;
        } else {
            polymorphismTextArea = textArea;
        }

        runButton.addActionListener(e -> {
            if (demoType.equals("Multithreading")) {
                runMultithreadingDemo();
            } else {
                runPolymorphismDemo();
            }
        });

        panel.add(runButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Helper untuk membuat panel input dengan label dan text field yang sesuai.
     */
    private void createInputPanel(String shapeName, String... labels) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        List<JTextField> currentFields = new ArrayList<>();

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            JTextField textField = new JTextField(10);
            panel.add(textField, gbc);
            currentFields.add(textField);
        }

        inputPanels.put(shapeName, panel);
        inputFields.put(shapeName, currentFields);
    }

    /**
     * Mendaftarkan semua panel input untuk semua bentuk geometri.
     */
    private void registerAllShapes() {
        // --- 2D Shapes ---
        createInputPanel("Persegi", "Sisi:");
        createInputPanel("Persegi Panjang", "Panjang:", "Lebar:");
        createInputPanel("Lingkaran", "Jari-jari:");
        createInputPanel("Segitiga", "Alas:", "Tinggi:", "Sisi B:", "Sisi C:");
        createInputPanel("Trapesium", "Sisi Atas:", "Sisi Bawah:", "Tinggi:", "Sisi Miring 1:", "Sisi Miring 2:");
        createInputPanel("Jajaran Genjang", "Alas:", "Tinggi:", "Sisi Miring:");
        createInputPanel("Belah Ketupat", "Diagonal 1:", "Diagonal 2:");
        createInputPanel("Layang-Layang", "Diagonal 1:", "Diagonal 2:", "Sisi A (pendek):", "Sisi B (panjang):");
        createInputPanel("Juring Lingkaran", "Jari-jari:", "Sudut Pusat (derajat):");
        createInputPanel("Tembereng Lingkaran", "Jari-jari:", "Sudut Pusat (derajat):");

        // --- 3D Shapes ---
        createInputPanel("Kubus", "Sisi:");
        createInputPanel("Balok", "Panjang:", "Lebar:", "Tinggi:");
        createInputPanel("Bola", "Jari-jari:");
        createInputPanel("Tabung", "Jari-jari:", "Tinggi:");
        createInputPanel("Kerucut", "Jari-jari:", "Tinggi:");
        createInputPanel("Kerucut Terpancung", "Jari-jari Alas:", "Jari-jari Atas:", "Tinggi:");
        createInputPanel("Cincin Bola", "Jari-jari Bola Induk:", "Tinggi Cincin:", "Jari-jari Atas:", "Jari-jari Bawah:");
        createInputPanel("Juring Bola", "Jari-jari Bola:", "Tinggi Tutup Juring:");
        createInputPanel("Tembereng Bola", "Jari-jari Bola:", "Tinggi Tembereng:");
        createInputPanel("Prisma Segitiga", "Alas Segitiga:", "Tinggi Segitiga:", "Sisi B:", "Sisi C:", "Tinggi Prisma:");
        createInputPanel("Prisma Trapesium", "Sisi Atas:", "Sisi Bawah:", "Tinggi Alas:", "Sisi Miring 1:", "Sisi Miring 2:", "Tinggi Prisma:");
        createInputPanel("Prisma Jajaran Genjang", "Alas:", "Tinggi Alas:", "Sisi Miring:", "Tinggi Prisma:");
        createInputPanel("Prisma Belah Ketupat", "Diagonal 1:", "Diagonal 2:", "Tinggi Prisma:");
        createInputPanel("Prisma Layang-Layang", "Diagonal 1:", "Diagonal 2:", "Sisi A:", "Sisi B:", "Tinggi Prisma:");
        createInputPanel("Limas Persegi", "Sisi Alas:", "Tinggi Limas:");
        createInputPanel("Limas Persegi Panjang", "Panjang Alas:", "Lebar Alas:", "Tinggi Limas:");
        createInputPanel("Limas Segitiga", "Alas Segitiga:", "Tinggi Alas:", "Sisi B:", "Sisi C:", "Tinggi Limas:");
        createInputPanel("Limas Trapesium", "Sisi Atas:", "Sisi Bawah:", "Tinggi Alas:", "Sisi Miring 1:", "Sisi Miring 2:", "Tinggi Limas:");
        createInputPanel("Limas Jajaran Genjang", "Alas:", "Tinggi Alas:", "Sisi Miring:", "Tinggi Limas:");
        createInputPanel("Limas Belah Ketupat", "Diagonal 1:", "Diagonal 2:", "Tinggi Limas:");
        createInputPanel("Limas Layang-Layang", "Diagonal 1:", "Diagonal 2:", "Sisi A:", "Sisi B:", "Tinggi Limas:");
    }

    /**
     * Logika utama yang dijalankan saat tombol "Hitung" ditekan.
     *
     * @param activeComboBox ComboBox yang sedang aktif (baik 2D maupun 3D).
     * @param activeResultLabel Label hasil yang sesuai dengan panel yang aktif.
     */
    private void performCalculation(JComboBox<String> activeComboBox, JLabel activeResultLabel) {
        String selectedShape = (String) activeComboBox.getSelectedItem();
        if (selectedShape == null) {
            return;
        }

        List<JTextField> fields = inputFields.get(selectedShape);
        List<Double> values = new ArrayList<>();

        try {
            // Ambil dan parse semua nilai dari text field
            for (JTextField field : fields) {
                if (field.getText().trim().isEmpty()) {
                    throw new NumberFormatException("Input tidak boleh kosong.");
                }
                values.add(Double.parseDouble(field.getText().trim()));
            }

            String resultText = "";
            // Gunakan switch-case untuk membuat objek dan menghitung
            switch (selectedShape) {
                // --- 2D Shapes ---
                case "Persegi":
                    Persegi p = new Persegi(values.get(0));
                    resultText = String.format("Luas: %.2f, Keliling: %.2f", p.luas, p.keliling);
                    break;
                case "Persegi Panjang":
                    PersegiPanjang pp = new PersegiPanjang(values.get(0), values.get(1));
                    resultText = String.format("Luas: %.2f, Keliling: %.2f", pp.luas, pp.keliling);
                    break;
                case "Lingkaran":
                    Lingkaran l = new Lingkaran(values.get(0));
                    resultText = String.format("Luas: %.2f, Keliling: %.2f", l.luas, l.keliling);
                    break;
                case "Segitiga":
                    Segitiga s = new Segitiga(values.get(0), values.get(1), values.get(2), values.get(3));
                    resultText = String.format("Luas: %.2f, Keliling: %.2f", s.luas, s.keliling);
                    break;
                case "Trapesium":
                    Trapesium t = new Trapesium(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                    resultText = String.format("Luas: %.2f, Keliling: %.2f", t.luas, t.keliling);
                    break;
                case "Jajaran Genjang":
                    JajaranGenjang jg = new JajaranGenjang(values.get(0), values.get(1), values.get(2));
                    resultText = String.format("Luas: %.2f, Keliling: %.2f", jg.luas, jg.keliling);
                    break;
                case "Belah Ketupat":
                    BelahKetupat bk = new BelahKetupat(values.get(0), values.get(1));
                    resultText = String.format("Luas: %.2f, Keliling: %.2f", bk.luas, bk.keliling);
                    break;
                case "Layang-Layang":
                    LayangLayang ll = new LayangLayang(values.get(0), values.get(1), values.get(2), values.get(3));
                    resultText = String.format("Luas: %.2f, Keliling: %.2f", ll.luas, ll.keliling);
                    break;
                case "Juring Lingkaran":
                    JuringLingkaran jl = new JuringLingkaran(values.get(0), values.get(1));
                    resultText = String.format("Luas: %.2f, Keliling (busur+2r): %.2f", jl.getLuasJuring(), jl.getKelilingJuring());
                    break;
                case "Tembereng Lingkaran":
                    TemberengLingkaran tl = new TemberengLingkaran(values.get(0), values.get(1));
                    resultText = String.format("Luas: %.2f, Keliling (busur+tali): %.2f", tl.getLuasTembereng(), tl.getKelilingTembereng());
                    break;

                // --- 3D Shapes ---
                case "Kubus":
                    PrismaPersegi kubus = new PrismaPersegi(values.get(0));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", kubus.getVolume(), kubus.getLuasPermukaan());
                    break;
                case "Balok":
                    PrismaPersegiPanjang balok = new PrismaPersegiPanjang(values.get(0), values.get(1), values.get(2));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", balok.getVolume(), balok.getLuasPermukaan());
                    break;
                case "Bola":
                    Bola bola = new Bola(values.get(0));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", bola.getVolume(), bola.getLuasPermukaan());
                    break;
                case "Tabung":
                    Tabung tabung = new Tabung(values.get(0), values.get(1));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", tabung.getVolume(), tabung.getLuasPermukaan());
                    break;
                case "Kerucut":
                    Kerucut kerucut = new Kerucut(values.get(0), values.get(1));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", kerucut.getVolume(), kerucut.getLuasPermukaan());
                    break;
                case "Kerucut Terpancung":
                    KerucutTerpancung kt = new KerucutTerpancung(values.get(0), values.get(1), values.get(2));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", kt.getVolume(), kt.getLuasPermukaan());
                    break;
                case "Cincin Bola":
                    CincinBola cb = new CincinBola(values.get(0), values.get(1), values.get(2), values.get(3));
                    resultText = String.format("Volume: %.2f, Luas Permukaan (Lengkung): %.2f", cb.getVolume(), cb.getLuasPermukaan());
                    break;
                case "Juring Bola":
                    JuringBola jb = new JuringBola(values.get(0), values.get(1));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", jb.getVolume(), jb.getLuasPermukaan());
                    break;
                case "Tembereng Bola":
                    TemberengBola tb = new TemberengBola(values.get(0), values.get(1));
                    resultText = String.format("Volume: %.2f, Luas Permukaan (Lengkung): %.2f", tb.getVolume(), tb.getLuasPermukaan());
                    break;
                case "Prisma Segitiga":
                    PrismaSegitiga ps = new PrismaSegitiga(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", ps.getVolume(), ps.getLuasPermukaan());
                    break;
                case "Prisma Trapesium":
                    PrismaTrapesium pt = new PrismaTrapesium(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", pt.getVolume(), pt.getLuasPermukaan());
                    break;
                case "Prisma Jajaran Genjang":
                    PrismaJajaranGenjang pjg = new PrismaJajaranGenjang(values.get(0), values.get(1), values.get(2), values.get(3));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", pjg.getVolume(), pjg.getLuasPermukaan());
                    break;
                case "Prisma Belah Ketupat":
                    PrismaBelahKetupat pbk = new PrismaBelahKetupat(values.get(0), values.get(1), values.get(2));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", pbk.getVolume(), pbk.getLuasPermukaan());
                    break;
                case "Prisma Layang-Layang":
                    PrismaLayangLayang pll = new PrismaLayangLayang(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", pll.getVolume(), pll.getLuasPermukaan());
                    break;
                case "Limas Persegi":
                    LimasPersegi lp = new LimasPersegi(values.get(0), values.get(1));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", lp.getVolume(), lp.getLuasPermukaan());
                    break;
                case "Limas Persegi Panjang":
                    LimasPersegiPanjang lpp = new LimasPersegiPanjang(values.get(0), values.get(1), values.get(2));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", lpp.getVolume(), lpp.getLuasPermukaan());
                    break;
                case "Limas Segitiga":
                    LimasSegitiga ls = new LimasSegitiga(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", ls.getVolume(), ls.getLuasPermukaan());
                    break;
                case "Limas Trapesium":
                    LimasTrapesium lt = new LimasTrapesium(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", lt.getVolume(), lt.getLuasPermukaan());
                    break;
                case "Limas Jajaran Genjang":
                    LimasJajaranGenjang ljg = new LimasJajaranGenjang(values.get(0), values.get(1), values.get(2), values.get(3));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", ljg.getVolume(), ljg.getLuasPermukaan());
                    break;
                case "Limas Belah Ketupat":
                    LimasBelahKetupat lbk = new LimasBelahKetupat(values.get(0), values.get(1), values.get(2));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", lbk.getVolume(), lbk.getLuasPermukaan());
                    break;
                case "Limas Layang-Layang":
                    LimasLayangLayang lll = new LimasLayangLayang(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
                    resultText = String.format("Volume: %.2f, Luas Permukaan: %.2f", lll.getVolume(), lll.getLuasPermukaan());
                    break;

                default:
                    resultText = "Kalkulasi untuk '" + selectedShape + "' belum diimplementasikan.";
            }
            activeResultLabel.setText(resultText);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage() + "\nHarap masukkan angka yang valid.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan kalkulasi: " + ex.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Menjalankan demo multithreading menggunakan SwingWorker agar GUI tetap
     * responsif.
     */
    private void runMultithreadingDemo() {
        multithreadingTextArea.setText("Memulai demo multithreading... Harap tunggu...\n\n");
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                PrintStream old = System.out;
                System.setOut(ps);
                // Panggil metode demo asli dari kelas MainGeometri
                MainGeometri.jalankanMultithreading();
                System.out.flush();
                System.setOut(old);
                return baos.toString();
            }

            @Override
            protected void done() {
                try {
                    multithreadingTextArea.setText(get());
                } catch (Exception e) {
                    multithreadingTextArea.setText("Gagal menjalankan demo:\n" + e.getMessage());
                }
            }
        };
        worker.execute();
    }

    /**
     * Menjalankan demo polimorfisme menggunakan SwingWorker.
     */
    private void runPolymorphismDemo() {
        polymorphismTextArea.setText("Memulai demo polimorfisme...\n\n");
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                PrintStream old = System.out;
                System.setOut(ps);
                // Panggil metode demo asli dari kelas MainGeometri
                MainGeometri.demonstrasiPolimorfisme();
                System.out.flush();
                System.setOut(old);
                return baos.toString();
            }

            @Override
            protected void done() {
                try {
                    polymorphismTextArea.setText(get());
                } catch (Exception e) {
                    polymorphismTextArea.setText("Gagal menjalankan demo:\n" + e.getMessage());
                }
            }
        };
        worker.execute();
    }

    /**
     * Metode main untuk meluncurkan aplikasi GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainGui frame = new MainGui();
            frame.setVisible(true);
        });
    }
}
