package 算法.load.balancing;

import java.util.*;

/**
 * 加权轮询（Weight Round Robin）法
 * <p>
 * 不同的服务器可能机器配置和当前系统的负载并不相同，因此它们的抗压能力也不尽相 同，给配置高、
 * 负载低的机器配置更高的权重，让其处理更多的请求，而低配置、高负载的机器，则给其分配较低的权重，
 * 降低其系统负载。加权轮询法可以很好地
 * 处理这一问题，并将请求顺序按照权重分配到后端。加权轮询法的代码实现大致如下：
 */
public class WeightRoundRobin {
    private static Integer pos=0;

    public static String getServer() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<String>();
        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++)
                serverList.add(server);
        }

        String server = null;
        synchronized (pos) {
            if (pos > serverList.size())
                pos = 0;
            server = serverList.get(pos);
            pos++;
        }

        return server;
    }
}