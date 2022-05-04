import java.io.FileReader;
import java.util.Scanner;

public class EncryptorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Encryptor encryptor = new Encryptor();
        Decryptor decryptor = new Decryptor();

        String str = "Привет, как дела?".toLowerCase();
        StringBuilder builder = new StringBuilder(str);
        int key = 2;

        System.out.println(builder);
        builder = encryptor.toEncrypt(builder, key);
        System.out.println(builder);
        builder = decryptor.toDecrypt(builder, key);
        System.out.println(builder);

//        try (Scanner scanner1 = new Scanner(System.in);
//             FileReader reader = new FileReader(scanner.nextLine())) {
//            while (reader.ready()) {
//                char ch = (char) reader.read();
//                if (ch != ' ' && ch != '.' && ch != ',') {
//                    System.out.print(ch);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Something went wrong : " + e);
//        }

    }
}
