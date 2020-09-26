
import java.io.*;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static HashMap initQuick() {
        HashMap<String, String> BankFind = new HashMap<String, String>();
        FileReader fr = null;
        try {
            fr = new FileReader("/Users/wonmo/Desktop/quickpcagit/支行索引.txt");
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
        HashMap BankFind = initQuick();
        if(BankFind == null || BankFind.isEmpty())
        {
            System.out.println("初始化索引失败");
        }else{
            List list = Find.Find("未来路", BankFind);
            System.out.println(list);
        }
    }
}
