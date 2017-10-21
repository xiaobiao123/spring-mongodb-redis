package testxxx;

public class InitializerExamples1 {
    static int count; 
    int i; 

    //静态初始化
    static{ 
        //This is a static initializers. Run only when Class is first loaded. 
        //Only static variables can be accessed 
        System.out.println("Static Initializer"); 
        //i = 6;//COMPILER ERROR
        System.out.println("Count when Static Initializer is run is " + count); 
    } 
 
    public static void main(String[] args) { 
        InitializerExamples1 example = new InitializerExamples1();
        InitializerExamples1 example2 = new InitializerExamples1();
        InitializerExamples1 example3 = new InitializerExamples1();
    } 
} 