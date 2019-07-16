package secert;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class CipherTest {


    private static Key key;

    @Test
    public void testCipher() {

        try {
            Cipher cipher = Cipher.getInstance("DES");
            //1.算法名称 2.算法/模式/填充
            //1.getInstance("str");通过传入算法名称,生成对应的cipher对象

            //2.doFinal();结束多部分加密或解密操作
            //3.doFinal(多参数);有几个重载方法,结束单部分或(多部分)加解密操作
            //4.init(); 初始化cipher对象,有多个重载方法,根据传入参数不同进行重载
            //5.getAlgorithm();返回加密算法名称

            //6.getBlockSize();返回加密块的大小

            //7.update();继续多部分加密解密操作,根据传入方法不同

            //8.wrap();包装秘钥

            //9.unwrap();解包秘钥

            //10.getProvider();返回cipher对象的提供者

            //11.getParameter();返回cipher对象的参数

            //12.getIV();返回新缓冲区初始化变量

            //13.getExemptionMechanism();返回豁免机制对象,如果不使用返回null

            //14.getOutputSize();根据给定的输入长度 inputLen（以字节为单位），返回保存下一个 update 或 doFinal 操作结果所需的输出缓冲区长度（以字节为单位）。

            //15.getMaxAllowedKeyLength();JCE 仲裁策略文件，返回指定转换的最大密钥长度;一般最大只能秘钥长度只能到128位,例如:AES加密如果要实现256位,
            // 则要安装了 JCE 无限制强度仲裁策略文件，则返回 Integer.MAX_VALUE

            //16.getMaxAllowedParameterSpec();返回仲裁策略文件，返回包含最大 Cipher 参数值的 AlgorithmParameterSpec
            // 对象。如果安装了 JCE 无限制强度仲裁策略文件，或者策略文件中对用于指定转换的参数没有最大限制，则返回 null。
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void getInstance() {
        //https://blog.csdn.net/lz710117239/article/details/71119032

//        https://www.cnblogs.com/langtianya/archive/2013/01/31/2883867.html
        try {
            Cipher c = Cipher.getInstance("AES");

            // 实例化KeyGenerator对象，并指定DES算法
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // 生成SecretKey对象
            SecretKey secretKey = keyGenerator.generateKey();

            c.init(Cipher.WRAP_MODE, secretKey);

            byte[] wrap = c.wrap(secretKey);

            c.init(Cipher.UNWRAP_MODE, secretKey);

//        上述实例化操作是一种最为简单的实现，并没有考虑DES分组算法的工作模式和填充模式，可通过以下方式对其设定：
            Cipher c2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
