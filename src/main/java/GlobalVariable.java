
import java.util.HashMap;

public class GlobalVariable {
    static HashMap hashMap = null;

    static public  void set(HashMap h) {
        hashMap = h;
    }

    static public HashMap get(){
        return  hashMap;
    }

}
