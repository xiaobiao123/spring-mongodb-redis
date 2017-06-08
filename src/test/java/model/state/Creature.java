package model.state;

/**
 * State状态设计模式类似于Switch多路分支功能的开关
 *
 * State状态设计模式中，状态自动切换并传播，不需要再改动标识，代码显得非常优雅。
 */
public class Creature {
    //状态接口  
    private interface State {
        String response();
    }

    private class Forg implements State {
        public String response() {
            return "Ribbet!";
        }
    }

    private class Prince implements State {
        public String response() {
            return "Darling!";
        }
    }

    private State state = new Forg();

    public void greet() {
        System.out.println(state.response());
    }

    public void kiss() {
        state = new Prince();
    }

    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.greet();
        creature.kiss();
        creature.greet();
    }
}  