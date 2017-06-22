package model.interpreter;

import org.junit.Test;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;

/**
 * •java.util.Pattern
 * •java.text.Normalizer
 * •java.text.DateFormat
 * •java.text.MessageFormat
 * •java.text.NumberFormat
 * <p>
 *     给定一个语言，定义其文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子
 * <p>
 * Interpreter解释器设计模式的应用场景：
 * 在软件构建过程中，若果某一特定领域的问题比较复杂，类似的模式不断重复出现，
 * 如果使用普通的编程方式来实现将面临非常频繁的变化。在这种情况下，将特定领
 * 域的问题表达为某种语法规则下的句子，再构建一个解释器来解释这样的句子，从而达到解决问题的目的。
 */
public class InterpreterDemo {
    public static void main(String[] args) {
        Date date = new Date();
        AbstractExpression expression1 = new DataFormatExpression();
        System.out.println(expression1.format(date));

        AbstractExpression expression2 = new DataFormatExpression("yyyy-MM-dd");
        System.out.println(expression2.format(date));

        AbstractExpression expression3 = new DataFormatExpression("yyyy/MM/dd");
        System.out.println(expression3.format(date));


    }

    @Test
    public void NumberFormat() {
        NumberFormat instance = NumberFormat.getNumberInstance();
        instance.setMinimumFractionDigits(2);
        instance.setGroupingUsed(true);
        System.out.println(instance.format("13323"));
    }
}  