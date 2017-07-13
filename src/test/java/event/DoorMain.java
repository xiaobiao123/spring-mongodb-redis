package event;

/**
 * http://www.cnblogs.com/atyou/archive/2013/01/07/2850321.html
 */
public class DoorMain {
    public static void main(String[] args) {
        DoorManager manager = new DoorManager();
        manager.addDoorListener(new DoorListener1());// 给门1增加监听器
        manager.addDoorListener(new DoorListener2());// 给门2增加监听器
        // 开门
        manager.fireWorkspaceOpened();
        System.out.println("我已经进来了");
        // 关门
        manager.fireWorkspaceClosed();
    }
}