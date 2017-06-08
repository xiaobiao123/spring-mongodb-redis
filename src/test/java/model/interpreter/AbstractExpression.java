package model.interpreter;

import java.util.Date;

//抽象表达式
interface AbstractExpression {
    public String format(Date date);
}  