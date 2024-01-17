import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String path = getPath();

        String fileContent = readFile(path);

        analyseFile(fileContent);
    }

    public static String getPath(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Provide the file path : ");

        return scanner.next();
    }

    public static String readFile(String path){

        StringBuilder stringBuilder = new StringBuilder();

        try{
            FileReader fileReader = new FileReader(path);

            int character;

            while((character = fileReader.read()) != -1){
                stringBuilder.append((char)character);
            }

            fileReader.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }

    public static void analyseFile(String fileContent){

        boolean beforeQuestionMark = true;

        try{
            FileWriter questionsWriter = new FileWriter("Questions.txt");
            FileWriter answersWriter = new FileWriter("Answers.txt");

            int counter = 1;
            questionsWriter.write(counter + ". ");
            answersWriter.write(counter++ + ". ");

            for (int i = 0; i < fileContent.length(); i++) {

                if (fileContent.charAt(i) == '?') {
                    beforeQuestionMark = false;
                    i++;
                }
                else if (fileContent.charAt(i) == '\n') {
                    beforeQuestionMark = true;
                    questionsWriter.write("\n" + counter + ". ");
                    answersWriter.write("\n" + counter++ + ". ");
                    i++;
                }

                if (beforeQuestionMark)
                    questionsWriter.write(fileContent.charAt(i));
                else
                    answersWriter.write(fileContent.charAt(i));
            }

            questionsWriter.close();
            answersWriter.close();

        } catch(IOException e){
            throw new RuntimeException(e);
        }

    }

}