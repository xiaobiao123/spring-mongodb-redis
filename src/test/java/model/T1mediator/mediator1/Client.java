package model.T1mediator.mediator1;

//测试类  http://haolloyin.blog.51cto.com/1177454/333810/
public class Client {
    public static void main(String[] args) {

        //创建一个中介者  
        AbstractMediator mediator = new Mediator();

        //创建两个同事  
        ColleagueA colleagueA = new ColleagueA(mediator);
        ColleagueB colleagueB = new ColleagueB(mediator);

        //中介者分别与每个同事建立联系  
        mediator.addColleague("ColleagueA", colleagueA);
        mediator.addColleague("ColleagueB", colleagueB);

        //同事们开始工作  
        colleagueA.self();
        colleagueA.out();
        System.out.println("======================合作愉快，任务完成！\n");

        colleagueB.self();
        colleagueB.out();
        System.out.println("======================合作愉快，任务完成！");
    }
} 