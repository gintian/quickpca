
import java.io.*;
import java.util.HashMap;

public class Main {

    private static HashMap initQuick() {
        HashMap<String, String> BankFind = new HashMap<String, String>();
        FileReader fr = null;
        try {
            fr = new FileReader("bankindex.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] kv = line.split("-");
                BankFind.put(kv[0], kv[1]);
            }
            br.close();
            fr.close();
            return BankFind;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("=============================================");
        System.out.println("");
        System.out.println("          欢迎使用全国支行快速查询 v0.1");
        System.out.println("");
        System.out.println("    作者：Y = A * x + B       日期：2020-09-27");
        System.out.println("");
        System.out.println("GitHub：https://github.com/iwonmo/quickpca.git");
        System.out.println("");
        System.out.println("=============================================");
        HashMap BankFind = initQuick();
        if(BankFind == null || BankFind.isEmpty())
        {
            System.out.println("初始化索引失败 ...");
        }else{
            try {
                System.out.println("初始化支行索引 ... ok");
                GlobalVariable.set(BankFind);
                new Server().bind(3001);
            } catch (Exception e) {
                System.out.println("启动失败 ...");
                e.printStackTrace();
            }
        }
    }
}
