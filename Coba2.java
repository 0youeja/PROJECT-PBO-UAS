import java.util.Scanner;

public class Coba2 {

    static String kategori(int gaji) {
        String kat = "";
        if (gaji < 2000000) {
            kat = "A";
        } else if (gaji >= 2000000 && gaji <= 10000000) {
            kat = "B";
        } else if (gaji > 10000000) {
            kat = "C";
        }
        return kat;
    }

    static int bayar(String jalur, String gol, int jmlBln) {
        int dsp = 0;
        int spp = 0;
        int total = 0;
        if (jalur != null) switch (jalur) {
            case "SBMPTN":
                if (gol != null) switch (gol) {
                    case "A":
                        dsp = 5000000;
                        spp = 500000;
                        break;
                    case "B":
                        dsp = 15000000;
                        spp = 1000000;
                        break;
                    case "C":
                        dsp = 30000000;
                        spp = 2000000;
                        break;
                    default:
                        break;
                }
                break;
            case "SNMPTN":
                if (gol != null) switch (gol) {
                    case "A":
                        dsp = 7000000;
                        spp = 500000;
                        break;
                    case "B":
                        dsp = 17000000;
                        spp = 1000000;
                        break;
                    case "C":
                        dsp = 35000000;
                        spp = 2000000;
                        break;
                    default:
                        break;
                }
                break;
            case "Mandiri":
                if (gol != null) switch (gol) {
                    case "A":
                        dsp = 10000000;
                        spp = 1000000;
                        break;
                    case "B":
                        dsp = 25000000;
                        spp = 2000000;
                        break;
                    case "C":
                        dsp = 50000000;
                        spp = 3000000;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        total = dsp + (jmlBln * spp);
        return total;
    }

    public static void main(String[] args) {
        int IDM[] = {1, 2, 3, 4};
        String nama[] = {"Mira", "Nina", "Oemar", "Pena"};
        String jalur[] = {"SBMPTN", "SNMPTN", "Mandiri", "SBMPTN"};
        String alamat[] = {"Sawojajar", "Kedungkandang", "Ijen", "Dinoyo"};

        Scanner input = new Scanner(System.in);

        System.out.println("Inputkan ID Mahasiswa :");
        int idIn = input.nextInt();

        System.out.println("Inputkan Pendapatan Orang Tua :");
        int gajiIn = input.nextInt();

        System.out.println("Inputkan Jumlah Bulan SPP yg akan dibayar :");
        int jumlahIn = input.nextInt();

        input.close();

        System.out.println("ID Mahasiswa : " + IDM[idIn - 1]);
        System.out.println("Nama Mahasiswa : " + nama[idIn - 1]);
        System.out.println("Jalur Masuk : " + jalur[idIn - 1]);
        System.out.println("Kategori Pendapatan : " + kategori(gajiIn));
        System.out.println("Total biaya : " + bayar(jalur[idIn - 1], kategori(gajiIn), jumlahIn));
        System.out.println("Alamat : " + alamat[idIn - 1]);
    }
}
