import java.io.BufferedWriter;
import java.io.FileWriter;

public class AppFileWriter {

    public void toWriteFile(StringBuilder builder, String src){
        try (FileWriter fileWriter = new FileWriter(src);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            writer.write(builder.toString());
            writer.flush();

        } catch (Exception e) {
            System.out.println("Something went wrong : " + e);
        }
    }
}
