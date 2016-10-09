package sample.model;



import java.io.*;

/**
 * Created by Lektor on 25.09.2016.
 */
public class IO {
    public static void write(String fileName, String str) throws IOException {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try(FileWriter fw = new FileWriter(file.getAbsoluteFile())) {
                fw.write(str);
            }
        }catch (IOException e){
                throw new RuntimeException(e);
        }
    }


    public static String read(String path) throws FileNotFoundException{
        StringBuilder sb = new StringBuilder();
        exists(path);
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String s;
            while ((s = br.readLine()) != null){
                sb.append(s);
                sb.append(" ");
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists())
            throw new FileNotFoundException(file.getName());
    }

    public static void updateFile(String fileName, String e) throws IOException {
        exists(fileName);
        StringBuilder sb = new StringBuilder();
        String line = read(fileName);
        sb.append(line);
        sb.append(e);
        write(fileName, sb.toString());
    }

    public static void delete(String path) throws FileNotFoundException{
        exists(path);
        new File(path).delete();
    }
}
