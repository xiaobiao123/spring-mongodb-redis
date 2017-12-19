/**
 * 
 */
package javastudy.trycatch;

/**
 * try {}里有一个return语句，那么紧跟在这个try后的finally {}里的code会不会被执行，什么时候被执行，还是在return之后执行？
 * @author Hongten
 * @date 2013-12-10
 *
 * 在try语句中，在执行return语句时，要返回的结果已经准备好了，就在此时，程序转到finally执行了。
 * 在转去之前，try中先把要返回的结果存放到不同于a的局部变量中去，执行完finally之后，在从中取出返回接口
 * 即使在finally中对变量a进行了改变，但是不影响返回接口，他应该使用栈保存返回值。
 *
 * 运行时，在try中，要返回的结果已经准备好了，就在这个时候，程序跳到了finally，这个时候结果已经放到了x的局部表量中，
 * 执行完finally后，再取出结果，finally对x进行了改变，但不会影响返回的结果。
 *
 */
public class TestC {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        System.out.println("结果： " + new TestC().test());
    }
    
    static int test(){
        int i = 1;
        try {
            System.out.println("try里面的i : " + i);
            return i;
        }finally{
            System.out.println("进入finally...");
            ++i;
            System.out.println("fianlly里面的i : " + i);

        }
    }
}