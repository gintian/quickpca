
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.util.*;

public class Main {


    static String readTextLine(File sourceFile, int lineNumber) throws IOException {
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = null;
        int line = 1;
            reader.setLineNumber(lineNumber);
            long i = reader.getLineNumber();
            while (reader.readLine() != null) {
                line++;
                if (i == line) {
                    s = reader.readLine();
                    break;
                }
            }
        reader.close();
        in.close();
        return s;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, String> BankFind = new HashMap<String, String>();
        FileReader fr=new FileReader("/Users/wonmo/Desktop/quickpca/支行索引.txt");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        while ((line=br.readLine())!=null) {
            String[] kv = line.split("-");
            BankFind.put(kv[0],kv[1]);
        }
        br.close();
        fr.close();
        long startTime = System.currentTimeMillis();
        String t = (String)BankFind.get("郑州");
        String[] _t = t.split(",");
        ArrayList<String> res = new ArrayList<String>();
        File f = new File("/Users/wonmo/Desktop/quickpca/支行列表.txt");
        int n = 0;
        for (String item : _t){
            n++;
            if (n>5) break;
            Integer i = Integer.valueOf(item);
            String __t = readTextLine(f,i);
            if(__t != null)
                res.add(__t);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        System.out.println(res);
    }
}
