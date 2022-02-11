package finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Services {

    private static final String SRC_COM_ADVANCED_FINAL_PRACTICE_WORD_TXT_RANDOM = "src/finalproject/random.txt";
    private static final String SRC_COM_ADVANCED_FINAL_PRACTICE_WORD_TXT_HEWAN = "src/finalproject/hewan.txt";
    private static final String SRC_COM_ADVANCED_FINAL_PRACTICE_WORD_TXT_BUAH = "src/finalproject/buah.txt";
    private static final String SRC_COM_ADVANCED_FINAL_PRACTICE_WORD_TXT_NEGARA = "src/finalproject/negara.txt";

    public void menu() {
        int pilihan = 0;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("------------------------------------");
            System.out.println("Silahkan pilih kategori kata");
            System.out.println("1. Nama Hewan");
            System.out.println("2. Nama Buah");
            System.out.println("3. Nama Negara");
            System.out.println("9. Keluar Permainan");
            System.out.print("Masukkan pilihan Anda: ");
            try {
                pilihan = Integer.parseInt(input.next());
            } catch (NumberFormatException nfe) {
                System.out.println("Hanya boleh memasukkan nomor ...");
            }

            switch (pilihan) {
                case 1:
                    System.out.println("Kamu memilih kategori NAMA HEWAN");
                    try {
                        start(1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Kamu memilih kategori NAMA BUAH");
                    try {
                        start(2);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Kamu memilih kategori NAMA NEGARA");
                    try {
                        start(3);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    System.out.println("Keluar aplikasi ...");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak ada dalam menu.");
            }
        } while (pilihan != 9);
    }

    public void start(int kategori) throws FileNotFoundException {
        Scanner inputScanner = new Scanner(System.in);
        String randomWord = getRandomWord(kategori);
        char[] randomWordArray = randomWord.toCharArray();
        char[] guessingArray = new char[randomWord.length()];
        Arrays.fill(guessingArray,'_');
        int numberOfChances = randomWord.length();

        System.out.println("Panjang Karakter dan Jumlah Kesempatan Kamu untuk Menebak: " + numberOfChances);
        System.out.println("Tebak Kata: " + new String(guessingArray));

        while (numberOfChances > 0) {
            System.out.println("-------------------------------------");
            System.out.print("Ketikkan 1 Karakter (Huruf): ");
            String inputLine = inputScanner.nextLine().toLowerCase();

            while (inputLine.equals("")) {
                System.out.println("Karakter tidak boleh kosong!");
                System.out.print("Ketikkan 1 Karakter (Huruf): ");
                inputLine = inputScanner.nextLine().toLowerCase();
            }

            char letter = inputLine.charAt(0);

            boolean isGuessingCorrect = false;
            for (int i = 0; i < randomWord.length(); i++) {
                if (letter == randomWordArray[i]) {
                    guessingArray[i] = letter;
                    isGuessingCorrect = true;
                }
            }

            if (isGuessingCorrect) {
                System.out.println("Tebakkan yang baik!");
                if (isGameFinished(guessingArray)) {
                    break;
                }
            } else {
                numberOfChances--;
                if (numberOfChances == 0) {
                    System.out.println("Maaf kesempatanmu telah habis.");
                    System.out.println("Tebak Kata adalah: " + randomWord);
                    break;
                }
                System.out.println("Kesempatan menebak menjadi: " + numberOfChances);
                System.out.println("Coba kata lain!");
            }
            System.out.println("Tebak Kata: " + new String(guessingArray));

        }

    }

    private boolean isGameFinished(char[] guessingArray) {
        for (int i = 0; i < guessingArray.length; i++) {
            if (guessingArray[i] == '_') {
                return false;
            }
        }

        System.out.println("Selamat, kamu berhasil menebak kata!");
        System.out.println("Kata tersebut adalah " + new String((guessingArray)));
        return true;
    }

    private String getRandomWord(int kategori) throws FileNotFoundException {
        List<String> words = getTheWords(kategori);
        Random randomNumberGenerator = new Random();
        int randomIndex = randomNumberGenerator.nextInt(words.size());

        return words.get(randomIndex);
    }

    private List<String> getTheWords(int kategori) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        File wordsFile;

        if (kategori == 1) {
            wordsFile = new File(SRC_COM_ADVANCED_FINAL_PRACTICE_WORD_TXT_HEWAN);
        } else if (kategori == 2) {
            wordsFile = new File(SRC_COM_ADVANCED_FINAL_PRACTICE_WORD_TXT_BUAH);
        } else if (kategori == 3) {
            wordsFile = new File(SRC_COM_ADVANCED_FINAL_PRACTICE_WORD_TXT_NEGARA);
        } else {
            wordsFile = new File(SRC_COM_ADVANCED_FINAL_PRACTICE_WORD_TXT_RANDOM);
        }

        Scanner wordScanner = new Scanner(wordsFile);
        while (wordScanner.hasNextLine()) {
            words.add(wordScanner.nextLine());
        }
        return words;
    }
}
