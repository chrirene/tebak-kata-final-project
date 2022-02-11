package finalproject;

import java.io.FileNotFoundException;

public class TebakKata {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Game Tebak Kata Berdasarkan Kategori");
        System.out.println("------------------------------------");
        System.out.println("------------Sudah  Siap?------------");
        System.out.println("-----------Ayo Kita Mulai-----------");
        Services services = new Services();
        services.menu();
    }
}
