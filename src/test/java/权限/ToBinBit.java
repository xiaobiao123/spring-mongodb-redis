package 权限;

import java.util.Arrays;
//把十进制转换为二进制的位
public class ToBinBit
{
    public static void main(String[] args)
    {
        //1.假设现在有一个int为20，需要转换为二进制输出
        int number = 1;
        //2.需要一个长度为32的int数组来存储结果二进制
        int[] bit = new int[32];
        //3.循环，把原始数除以2取得余数，这个余数就是二进制数，原始的数等于商。
        //商如果不能再除以二，结束循环。

        for(int i = 0; number > 1; i++)
        {
            //取得除以2的余数
            int b = number % 2;
            //数字赋值为除以2的商
            number = number / 2;

            bit[i] = b;

            if( number < 2 )
            {
                //已经不能再把数除以2，就把上直接放到数组的下一位
                bit[i + 1] = number;
            }
        }

        //4.翻转数组
        for(int i = 0; i < bit.length / 2;i++)
        {
            int temp = bit[i];
            //第一个数的值设置为最后一个数的值
            //第二次的时候，i是1，把第二个数的值，赋值为倒数第二个
            bit[i] = bit[ bit.length - 1 - i ];
            bit[ bit.length - 1 - i ] = temp;
        }

        System.out.println( Arrays.toString(bit) );

    }
}