package model.nullObject009;

/**
 * Created by Administrator on 2017/6/16.
 * http://blog.csdn.net/qiumengchen12/article/details/44923139
 */
public class Client {
    public static void main(String[] args) {
        BookFactory bookFactory = new BookFactory();
        Book book = bookFactory.getBook(-1);
        if (book.isNull()) {
            //这里由客户端定制提醒代码
            System.out.println("兄弟，你输入的ID不符合规范吧。");
        } else {
            book.show();
        }
    }
}
