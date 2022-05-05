import java.util.Scanner;

public class Cryptoanalyzer {

    private static String srcInput;
    private static String srcOutput;
    private static Scanner scanner = new Scanner(System.in);
    private static AppFileReader fileReader = new AppFileReader();
    private static AppFileWriter fileWriter = new AppFileWriter();
    private static Encryptor encryptor = new Encryptor();
    private static Decryptor decryptor = new Decryptor();
    private static StringBuilder builder = new StringBuilder();
    private static int key;

    private static final String ENTER_THE_PATH_TO_READ = "Введите путь к файлу для чтения: ";
    private static final String ENTER_THE_KEY = "Введите ключ шифрования: ";
    private static final String ENTER_THE_PATH_TO_WRITE = "Введите путь к файлу для записи: ";
    private static final String COMPLETED = "Файл записан.";


    public static void main(String[] args) {

        int select;
        boolean isTrue = true;


        while (isTrue) {
            System.out.println("Приветствую! Это криптоанализатор выполняет шифрование текста шифром Цезаря..\n" +
                    "Введите номер необходимого действия:\n" +
                    "1 - Зашифровать файл\n" +
                    "2 - Расшифровать файл\n" +
                    "3 - \"Взломать\" файл (подбор ключа методом Брутфорс)\n" +
                    "0 - Выход из приложения.");
            select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1: {
                    encryptFileToNewFile();
                    break;
                }
                case 2: {
                    decryptFileToNewFile();
                    break;
                }
                case 3: {
                    bruteForce();
                    break;
                }
                case 0:
                    isTrue = false;
            }

        }
    }

    private static void encryptFileToNewFile() {
        System.out.print(ENTER_THE_PATH_TO_READ);
        srcInput = scanner.nextLine();
        System.out.print(ENTER_THE_KEY);
        key = scanner.nextInt();
        scanner.nextLine();

        builder = encryptor.toEncrypt(fileReader.toReadFile(srcInput), key);

        System.out.print(ENTER_THE_PATH_TO_WRITE);
        srcOutput = scanner.nextLine();

        fileWriter.toWriteFile(builder, srcOutput);
        System.out.println(COMPLETED);
    }

    private static void decryptFileToNewFile() {
        System.out.print(ENTER_THE_PATH_TO_READ);
        srcInput = scanner.nextLine();
        System.out.print(ENTER_THE_KEY);
        key = scanner.nextInt();
        scanner.nextLine();

        builder = decryptor.toDecrypt(fileReader.toReadFile(srcInput), key);

        System.out.print(ENTER_THE_PATH_TO_WRITE);
        srcOutput = scanner.nextLine();

        fileWriter.toWriteFile(builder, srcOutput);
        System.out.println(COMPLETED);
    }

    private static void bruteForce() {
        Alphabet alphabet = new Alphabet();
        int size = 128;
        boolean isReading = false;
        boolean isEnd = false;

        System.out.print(ENTER_THE_PATH_TO_READ);
        srcInput = scanner.nextLine();

        for (int i = 1; i < alphabet.getAlphabet().length(); i++) {
            builder = decryptor.toDecrypt(fileReader.toReadFile(srcInput), i);
            System.out.println("Показан отрывок текста или весь текст, если он меньше " + size + " символов: ");
            System.out.println(builder.length() < size ? builder : builder.substring(0, size));
            System.out.print("Данный текст читаемый? (Y\\N)");

            while (true) {
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    isReading = true;
                    break;
                }else if(answer.equalsIgnoreCase("n")) {
                    break;
                }else{
                    System.out.print("Нужно ввести либо \"Y\"(Да), \"N\"(Нет):");
                }
            }
            if (isReading) {break;}
            if (alphabet.getAlphabet().length()-1 == i){isEnd = true;}
        }

        if (isEnd){
            System.out.println("К сожалению, подобрать ключ не удалось. Попробуйте снова.");
        }else {
            System.out.print(ENTER_THE_PATH_TO_WRITE);
            srcOutput = scanner.nextLine();

            fileWriter.toWriteFile(builder, srcOutput);
            System.out.println(COMPLETED);
        }
    }
}

//        D:/JavaRushTasks/FirstApp/src/ReadFile.txt
//        D:/JavaRushTasks/FirstApp/src/WriteFile.txt