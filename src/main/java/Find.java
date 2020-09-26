import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.util.*;

public class Find {


    private static int index_max = 5; //查找返回的最大数

    private static String[] arraySort(String[] arr) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String str : arr) {
            if (arr.equals("") || str.length() == 0) continue;
            Integer count = map.get(str);
            if (null != count) {
                map.put(str, count + 1);
            } else {
                map.put(str, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> arg0, Map.Entry<String, Integer> arg1) {
                return arg1.getValue().compareTo(arg0.getValue());
            }
        });

        String[] res = new String[list.size()];
        int n = 0;
        for (Map.Entry<String, Integer> entry : list) {
            res[n] = entry.getKey();
            n++;
        }

        return res;
    }


    private static List readTextLine(File sourceFile, Integer[] index) throws IOException {
        Arrays.sort(index);
        List<String> res = new ArrayList<String>();
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = null;
        Integer i = 0;
        String red = "";
        int line = 0;
        while ((red = reader.readLine()) != null) {
            line++;
            if (i == index.length) break;
            int index_ = Arrays.binarySearch(index, line);
            if (index_ > -1) {
                i++;
                res.add(red);
            }

        }
        reader.close();
        in.close();
        return res;
    }

    public static List Find(String bank,HashMap BankFind) throws IOException {
        StringReader input = new StringReader(bank.trim());
        IKSegmenter ikSeg = new IKSegmenter(input, true);   // true 用智能分词 ，false细粒度
        String t = "";
        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
            String tmp = lexeme.getLexemeText();
            String t_ = (String) BankFind.get(tmp);
            if (t_ != null && t_.length() > 0) {
                t = t + "," + t_;
            }
        }

        if (t == "")
            return null;
        else {
            String[] _tx = t.split(",");
            String[] _t = arraySort(_tx);
            File f = new File("/Users/wonmo/Desktop/quickpcagit/支行列表.txt");
            int n = 0;
            int index_num = index_max;
            if (_t.length < index_max) index_num = _t.length;
            Integer[] intlist = new Integer[index_max]; //new Integer[_t.length];
            int i_ = 0;
            for (String str : _t) {
                if (i_ == index_max) break;
                if (!str.equals("") && Integer.parseInt(str) > 0) {
                    intlist[i_] = Integer.parseInt(str);//Exception in this line
                    i_++;
                }
            }
            try {
                List __t = readTextLine(f, intlist);
                return __t;
            } catch (Exception e) {
                return null;
            }
        }

    }
}
