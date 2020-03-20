package parser;

import java.io.*;

public class ToFile {

    private String path = "";
    private String tempFile = "D:\\proyects\\cuboLED\\src\\temp\\temp.java";

    public ToFile(String path){
        this.path=path;
    }

    public void changeFile(String name) {
        path = name;
    }

    public boolean toFile(byte data[], File destination){
        try(FileOutputStream fos= new FileOutputStream(destination)){
            fos.write(data);
            fos.close();
            return  true;
        }catch (Exception e){
            System.out.println("there was an error");
            return false;
        }
    }
    public boolean add_line(int line, String text) throws IOException {
        return add_lineAux(line, text);
    }
    private boolean add_lineAux(int line, String text) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String result = "";
        int i = 0;
        while ((st = br.readLine()) != null) {
            result += st;
            if (i == line) {
                result += text;
            }result += "\n";
            i++;
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write(result);
        writer.close();
        return true;
    }

}