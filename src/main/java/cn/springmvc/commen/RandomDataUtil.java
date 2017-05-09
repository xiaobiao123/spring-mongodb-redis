package cn.springmvc.commen;

import java.util.Random;


public class RandomDataUtil {
	 public RandomDataUtil() {
	    }
	
	 /**
	  *  
	  *  生成六位水机数字
	  *  
	  */
    public static int SixNumRadom (){
    	Random random = new Random();
    	int result = random.nextInt(899999);
    	result  = 100000+result;
    	return result;
    }
  
    /**
     * 
     * 随机生成六位字母
     * @param args
     * 
     */
    public static String SixRadom() {
        String result="";
        for(int i=0;i<6;i++){  
            int intVal=(int)(Math.random()*26+97);
            result=result+(char)intVal;  
        }  
        return result;
    }  
    
    /**
     * 
     * 生成随机字符串
     * @param length 生成的字符串长度
     * 
     */
    public static String getStringRandom(int length){
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数  
        for(int i = 0; i < 6; i++) {  
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));
            }  
        }  
        return val;   
        }
}
