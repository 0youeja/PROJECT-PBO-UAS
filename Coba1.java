import java.util.Scanner;

public class Coba1 {

    static int hitung(String cottage, String jenisHari, int jumlahHari) {
        int tarif = 0;
        int total = 0;
        switch (cottage) {
            case "Duku":
            case "Jeruk":
                switch (jenisHari) {
                    case "Weekday":
                        tarif = 915000;
                        break;
                    case "Weekend":
                        tarif = 1025000;
                        break;
                    case "Holiday":
                        tarif = 1225000;
                        break;
                    default:
                        break;
                }
                break;
            case "Alpukat":
            case "Jambu Air":
                switch (jenisHari) {
                    case "Weekday":
                        tarif = 575000;
                        break;
                    case "Weekend":
                        tarif = 695000;
                        break;
                    case "Holiday":
                        tarif = 895000;
                        break;
                    default:
                        break;
                }
                break;
            case "Durian":
            case "Melon":
                switch (jenisHari) {
                    case "Weekday":
                        tarif = 595000;
                        break;
                    case "Weekend":
                        tarif = 715000;
                        break;
                    case "Holiday":
                        tarif = 915000;
                        break;
                    default:
                        break;
                }
                break;
            case "Belimbing":
            case "Mangga":
            case "Kedondong":
                switch (jenisHari) {
                    case "Weekday":
                        tarif = 495000;
                        break;
                    case "Weekend":
                        tarif = 575000;
                        break;
                    case "Holiday":
                        tarif = 755000;
                        break;
                    default:
                        break;
                }
                break;
            default:
                tarif = 0;
                break;
        }
        total = jumlahHari * tarif;
        return total;
    }

    public static void main(String[] args) {
        int[] IDPelanggan = {1, 2, 3, 4, 5};
        String[] nama = {"Ali", "Budi", "Dani", "Edi", "Umar"};

        Scanner input = new Scanner(System.in);

        System.out.println("Inputkan ID Pelanggan : ");
        int idIn = input.nextInt();
        input.nextLine();  // Clear the newline

        System.out.println("Inputkan Jenis Cottage : ");
        String cottage = input.nextLine();

        System.out.println("Inputkan Golongan Hari : ");
        String jenisHari = input.nextLine();

        System.out.println("Inputkan Lama Hari Menginap : ");
        int jumlahHari = input.nextInt();

        System.out.println("ID Pelanggan : " + idIn);
        System.out.println("Nama Pelanggan : " + nama[idIn - 1]);
        System.out.println("Tipe Cottage : " + cottage);
        System.out.println("Golongan Hari : " + jenisHari);
        System.out.println("Jumlah Hari : " + jumlahHari);
        int total = hitung(cottage, jenisHari, jumlahHari);
        System.out.println("Total Biaya : " + total);
        
        input.close();
    }
}
