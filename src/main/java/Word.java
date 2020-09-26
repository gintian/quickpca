/**
 * 分词
 */

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;

public class Word {

    public static void main(String[] args) throws IOException {
        FileReader fr=new FileReader("/Users/wonmo/Desktop/quickpca/支行列表.txt");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("/Users/wonmo/Desktop/quickpca/支行分词.txt"));
            while ((line=br.readLine())!=null) {
                StringReader input = new StringReader(line.trim());
                IKSegmenter ikSeg = new IKSegmenter(input, true);   // true 用智能分词 ，false细粒度
                String t = "";
                for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
                    String tmp= lexeme.getLexemeText();
                    if(tmp.indexOf("银行")>-1 || tmp.indexOf("中国")>-1 || tmp.indexOf("支行")>-1 || tmp.indexOf("公司")>-1 || tmp.indexOf("股份有限公司")>-1 || tmp.indexOf("农村")>-1 || tmp.indexOf("信用")>-1 || tmp.equals("路") || tmp.equals("区") || tmp.equals("县")) continue;
                    t = t + tmp +"-";
                }
                input.close();
                out.write(t+"\r\n");
            }
            br.close();
            fr.close();
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
        }

    }
}
