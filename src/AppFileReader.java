import java.io.BufferedReader;
import java.io.FileReader;

public class AppFileReader {

    public StringBuilder toReadFile(String src){
        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(src);
             BufferedReader reader = new BufferedReader(fileReader)) {

            while (reader.ready()) {
                builder.append(reader.readLine().toLowerCase());
            }

        } catch (Exception e) {
            System.out.println("Something went wrong : " + e);
        }
        return builder;
    }
}
