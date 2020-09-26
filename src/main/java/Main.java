
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

    static int index_max = 5;

    static List readTextLine(File sourceFile, Integer[] index) throws IOException {
        Arrays.sort(index);
        List<String> res = new ArrayList<String>();
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = null;
        Integer i = 0;
        String red="";
        int line = 0;
            while ((red = reader.readLine() ) != null) {
                line++;
                if(i == index.length) break;
                int index_ = Arrays.binarySearch(index,line);
                if(index_ > -1)
                {
                    i++;
                    res.add(red);
                }

            }
        reader.close();
        in.close();
        return res;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, String> BankFind = new HashMap<String, String>();
        FileReader fr=new FileReader("/Users/wonmo/Desktop/quickpcagit/支行索引.txt");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        while ((line=br.readLine())!=null) {
            String[] kv = line.split("-");
            BankFind.put(kv[0],kv[1]);
        }
        br.close();
        fr.close();
        long startTime = System.currentTimeMillis();
        StringReader input = new StringReader("农业常熟".trim());
        IKSegmenter ikSeg = new IKSegmenter(input, true);   // true 用智能分词 ，false细粒度
        String t = "";
        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
            String tmp= lexeme.getLexemeText();
            String t_ = (String)BankFind.get(tmp);
            if(t_ != null && t_.length() > 0){
                t = t + "," + t_;
            }
        }

        if(t=="")
        {

        }else{
            String[] _t =   t.split(",");
            File f = new File("/Users/wonmo/Desktop/quickpcagit/支行列表.txt");
            int n = 0;
            int index_num=index_max;
            if(_t.length < index_max)  index_num = _t.length;
            Integer[] intlist = new Integer[index_max]; //new Integer[_t.length];
            int i_ = 0;
            for(String str:_t){
                if(i_ == index_max) break;
                    if(!str.equals("") && Integer.parseInt(str) > 0){
                        intlist[i_]=Integer.parseInt(str);//Exception in this line
                        i_++;
                }
            }
            List __t = readTextLine(f,intlist);
            long endTime = System.currentTimeMillis();
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
            System.out.println(__t);
        }




    }
}
