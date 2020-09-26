/**
 * 创建索引
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Index {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        HashMap<String, String> BankList = new HashMap<String, String>();
        FileReader fr=new FileReader("/Users/wonmo/Desktop/quickpca/支行分词.txt");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        String[] arrs=null;
        int n = 0;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("/Users/wonmo/Desktop/quickpca/支行索引.txt"));
            while ((line=br.readLine())!=null) {
                n++;
                arrs = line.split("-");
                for(String item : arrs){
                    if(item.length() > 0){
                        String t = "";
                        t = (String) BankList.get(item);
                        if (t != null && t.length() != 0 ){
                            String[] _t = t.split(",");
                            ArrayList<String> con =new ArrayList<>();
                            con.addAll(Arrays.asList(_t));
                            if(!con.contains(String.valueOf(n)))
                                BankList.put(item,t+","+String.valueOf(n));
                        }
                        else
                            BankList.put(item,String.valueOf(n));
                    }
                }
            }
            br.close();
            fr.close();
            for (Map.Entry<String, String> entry : BankList.entrySet())
                out.write(entry.getKey()+"-"+entry.getValue()+"\r\n");
            out.flush();
            out.close();
            long endTime = System.currentTimeMillis();
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
            System.out.println("文件创建成功！");
            System.out.println("总数："+String.valueOf(BankList.size()));
            System.out.println("抽取样式："+(String)BankList.get("平凉"));
        } catch (IOException e) {
        }

    }
}
