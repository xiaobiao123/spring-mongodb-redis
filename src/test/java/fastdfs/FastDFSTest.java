//package fastdfs;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.base.Strings;
//import org.csource.common.MyException;
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.*;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by Administrator on 2017/5/12.
// */
//public class FastDFSTest {
//
//    private static FastDFSClient fastDFSClient;
//
//    private static StorageClient1 storageClient;
//    String groupName = "group1";
//    String remoteFileName = "M00/00/00/rB4VXFkVJLuAIsoDAAAthHV31sI554.jpg";
//
//
//    static {
//        try {
//            fastDFSClient = new FastDFSClient("D:\\IdeaProjects_1603\\springmvc\\src\\main\\resources\\conf\\fastClient.conf");
//            storageClient = fastDFSClient.getStorageClient();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 用java基础代码操作的流程
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testUpload() throws Exception {
//        // 1、把FastDFS提供的jar包添加到工程中
//        // 2、初始化全局配置。加载一个配置文件。
//        ClientGlobal.init("D:\\IdeaProjects_1603\\springmvc\\src\\main\\resources\\conf\\fastClient.conf");
//        // 3、创建一个TrackerClient对象。
//        TrackerClient trackerClient = new TrackerClient();
//        // 4、创建一个TrackerServer对象。
//        TrackerServer trackerServer = trackerClient.getConnection();
//        // 5、声明一个StorageServer对象，null。
//        StorageServer storageServer = null;
//        // 6、获得StorageClient对象。
//        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//        // 7、直接调用StorageClient对象方法上传文件即可。
//        String[] strings = storageClient.upload_file("C:\\dowan.jpg", "jpg", null);
//        for (String string : strings) {
//            System.out.println(string);
//        }
//    }
//
//
//    @Test
//    public void testFastDfsClient() throws Exception {
//        FastDFSClient client = new FastDFSClient("D:\\IdeaProjects_1603\\springmvc\\src\\main\\resources\\conf\\fastClient.conf");
//        NameValuePair[] meta_list = new NameValuePair[4];
//        meta_list[0] = new NameValuePair("width", "50");
//        meta_list[1] = new NameValuePair("heigth", "50");
//        meta_list[2] = new NameValuePair("bgcolor", "#000000");
//        meta_list[3] = new NameValuePair("title", "Untitle");
//        String uploadFile = client.uploadFile("C:\\dowan.jpg", "jpg", meta_list);
//        System.out.println(uploadFile);//返回的是文件的
//    }
//
//    /**
//     * 判断文件是否已存在
//     *
//     * @throws Exception
//     */
//    @Test
//    public void doesFileExist() throws Exception {
//        FastDFSClient client = new FastDFSClient("D:\\IdeaProjects_1603\\springmvc\\src\\main\\resources\\conf\\fastClient.conf");
//        FileInfo fileInfo = client.get_file_info("group1", "M00/00/00/rB4VXFkVJLuAIsoDAAAthHV31sI554.jpg");
//        System.out.println(fileInfo != null);
//    }
//
//
//    /**
//     * 添加或者修改dfs系统中文件的元信息
//     *
//     * @param client         dfs client
//     * @param groupName      主文件所在组
//     * @param remoteFileName 主文件id
//     * @param key            meta key
//     * @param value          meta value
//     * @throws IOException 异常
//     * @throws MyException 异常
//     */
//    @Test
//    public void upsertMetaData() throws IOException, MyException {
//        String groupName = "group1";
//        String remoteFileName = "M00/00/00/rB4VXFkVJLuAIsoDAAAthHV31sI554.jpg";
//        String key = "key";
//        String value = "value";
//        NameValuePair[] pairs = new NameValuePair[1];
//        pairs[0] = new NameValuePair(key, value);
//        // TODO 设置dfs系统中文件的元信息
//         storageClient.set_metadata(groupName, remoteFileName, pairs, (byte) 'M');
//        // TODO 获取dfs系统中文件的元信息
//        System.out.println(JSON.toJSONString(storageClient.get_metadata(groupName, remoteFileName)));
//    }
//
//    /**
//     * get file abs path in dfs
//     *
//     * @param groupName      文件所在dfs的分组
//     * @param remoteFileName 文件所在dfs远端文件名
//     * @return abs path
//     * @throws Exception 异常
//     */
//    @Test
//    public void doAbsolutePath() throws Exception {
//        FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
//        String url = "http://" + fileInfo.getSourceIpAddr() + ":"
//                + "8888" + "/" + groupName + "/" + remoteFileName;
//        System.out.println(url);
//    }
//
//    /**
//     * 下载文件
//     * @throws Exception
//     */
//    @Test
//    public void doDownload() throws Exception {
//        storageClient.download_file(groupName, remoteFileName);
//    }
//
//}
