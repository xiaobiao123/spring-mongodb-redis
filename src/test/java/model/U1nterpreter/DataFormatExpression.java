package model.U1nterpreter;

import java.util.Calendar;
import java.util.Date;

//具体的日期格式化表达式
class DataFormatExpression implements AbstractExpression {
    private static final String pattern1 = "yyyy-MM-dd";
    private static final String pattern2 = "yyyy/MM/dd";
    private static final Calendar calendar = Calendar.getInstance();
    //默认日期不分隔，如：20120606  
    private String separator = "";

    public DataFormatExpression() {
    }

    public DataFormatExpression(String pattern) {
        if (pattern1.equals(pattern)) {
            separator = "-";
        } else if (pattern2.equals(pattern)) {
            separator = "/";
        }
    }

    public String getYear(Calendar cal) {
        return cal.get(Calendar.YEAR) + "";
    }

    public String getMonth(Calendar cal) {
        int month = cal.get(Calendar.MONTH) + 1;
        return month < 10 ? "0" + month : "" + month;
    }

    public String getDay(Calendar cal) {
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day < 10 ? "0" + day : "" + day;
    }

    public String format(Date date) {
        calendar.setTime(date);
        return getYear(calendar) + separator + getMonth(calendar) + separator + getDay(calendar);
    }
}  