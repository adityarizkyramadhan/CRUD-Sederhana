import java.io.*;
import java.util.Scanner;

public class CRUD {
    public static void main(String[] args) {
        menuUtama();
    }
    private static void menuUtama(){
        while (true){
            Scanner s = new Scanner(System.in);
            System.out.println("""
                Selamat Datang di CRUD Nama Sederhana
                Pilihan Menu :
                1. Lihat Nama 
                2. Hapus Nama
                3. Tambah Nama
                4. Cari Nama
                5. Exit
                """);
            System.out.print("Masukkan pilihan menu (pilih 1-5): ");
            int pil = s.nextInt();
            switch (pil) {
                case 1 -> cetakNama(cekNama());
                case 2 -> deleteNama();
                case 3 -> tambahNama();
                case 4 -> cariNama();
                case 5 -> updateNama();
                case 6 -> System.exit(0);
            }
        }

    }

    //Add Nama
    private static void tambahNama(){
        String fileRead = cekNama();
        Scanner s = new Scanner(System.in);
        System.out.print("Masukkan nama yang akan ditambahkan : ");
        String nama = s.nextLine();
        String [] split = fileRead.split("\n");
        String [] addFile = new String[split.length+1];
        for (int i = 0; i< addFile.length; i++){
            if (i< addFile.length-1){
                addFile[i]=split[i];
            }else {
                addFile[i]=nama;
            }
        }
        String out = "";
        for (String each : addFile){
            out = out + each + "\n";
        }
        tulisFile(out);




    }



    //Print Nama
    private static void cetakNama (String fileRead){
        String [] split = fileRead.split("\n");
        System.out.println("===================== Nama Karyawan =====================");
        for (int i=0; i< split.length; i++){
            System.out.printf("%d. %-30s\n",(i+1),split[i]);
        }
        System.out.println("======================== Selesai ========================");

    }

    private static String cekNama (){
        FileReader fr;
        BufferedReader br;
        String temp = "";
        try {
            fr = new FileReader("Kumpulan nama.csv");
            br = new BufferedReader(fr);
            String save = br.readLine();
            while (save!=null) {
                temp = temp + save + "\n";
                save = br.readLine();
            }
        }catch (Exception e){
            temp = "Error";
        }
        return temp;
    }

    // Cari Nama
    private static void cariNama (){
        Scanner s = new Scanner(System.in);
        FileReader fr;
        BufferedReader br;
        String ret = "";
        System.out.print("Masukkan nama yang akan dicari : ");
        String nama = s.nextLine();
        try {
            fr = new FileReader("Kumpulan nama.csv");
            br = new BufferedReader(fr);
            String save = br.readLine();
            String temp = "";
            int jum = 0;
            while (save!=null) {
                String [] cek = save.split(" ");
                for (String potong : cek){
                    if (potong.equalsIgnoreCase(nama)){
                        jum++;
                        temp = temp + jum + ". " +  save + "\n";
                        break;
                    }else if (save.equalsIgnoreCase(nama)){
                        jum++;
                        temp = temp + jum + ". " +  save + "\n";
                        break;
                    }
                }
                save = br.readLine();
            }
            ret = "Data Ditemukan Sebanyak : " + jum + "\n" + temp;
        }catch (Exception e){
            ret = "Error";
        }
        System.out.println(ret);
    }

    // Update Nama
    private static void updateNama (){
        String file = cekNama();
        String [] cek = file.split("\n");
        String toWrite = "";
        Scanner s = new Scanner(System.in);
        System.out.println("Siapa yang ingin diupdate nama : ");
        String nama = s.nextLine();
        System.out.print("Update nama : ");
        String update = s.nextLine();
        for (int i = 0; i<cek.length; i++){
            if (nama.equalsIgnoreCase(cek[i])){
                cek[i] = update;
            }
            toWrite= toWrite+cek[i]+ "\n";
        }
        tulisFile(toWrite);



    }



    private static void deleteNama (){
        cetakNama(cekNama());
        Scanner s = new Scanner(System.in);
        System.out.print("Masukkan Target Hapus (Wajib nama lengkap sesuai data) : ");
        String delete = s.nextLine();
        String [] split = cekNama().split("\n");
        for (int i=0; i< split.length;i++){
            if (delete.equalsIgnoreCase(split[i])){
                split[i] = null;
            }
        }

        String [] clean = new String[split.length-1];
        for (int i=0, x=0; i< split.length;i++){
            if (split[i]==null){
                continue;
            }else {
                clean[x]=split[i];
                x++;
            }
        }

        String out = "";
        for (int i=0; i< clean.length; i++){
            out = out + clean[i] + "\n";
        }
        tulisFile(out);
    }


    private static void tulisFile(String write){
        FileWriter fw;
        BufferedWriter bw;
        try {
            fw = new FileWriter("Kumpulan nama.csv");
            fw.write(write);
            fw.flush();
        }catch (IOException e){
            System.out.println("Error");
        }
    }


}
