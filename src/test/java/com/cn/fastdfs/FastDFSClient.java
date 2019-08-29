//package com.cn.fastdfs;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import com.google.common.base.Joiner;
//import com.google.common.base.Splitter;
//import com.google.common.base.Strings;
//import org.csource.common.MyException;
//import org.csource.common.NameValuePair;
//import org.csource.com.cn.fastdfs.*;
//
//public class FastDFSClient {
//
//    private TrackerClient trackerClient = null;
//    private TrackerServer trackerServer = null;
//    private StorageServer storageServer = null;
//    private StorageClient1 storageClient = null;
//
//    public FastDFSClient(String conf) throws Exception {
//        if (conf.contains("classpath:")) {
//            conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
//        }
//        ClientGlobal.init(conf);
//        trackerClient = new TrackerClient();
//        trackerServer = trackerClient.getConnection();
//        storageServer = null;
//        storageClient = new StorageClient1(trackerServer, storageServer);
//    }
//
//    public TrackerClient getTrackerClient() {
//        return trackerClient;
//    }
//
//    public void setTrackerClient(TrackerClient trackerClient) {
//        this.trackerClient = trackerClient;
//    }
//
//    public TrackerServer getTrackerServer() {
//        return trackerServer;
//    }
//
//    public void setTrackerServer(TrackerServer trackerServer) {
//        this.trackerServer = trackerServer;
//    }
//
//    public StorageServer getStorageServer() {
//        return storageServer;
//    }
//
//    public void setStorageServer(StorageServer storageServer) {
//        this.storageServer = storageServer;
//    }
//
//    public StorageClient1 getStorageClient() {
//        return storageClient;
//    }
//
//    public void setStorageClient(StorageClient1 storageClient) {
//        this.storageClient = storageClient;
//    }
//
//    /**
//     * 上传文件方法
//     * <p>Title: uploadFile</p>
//     * <p>Description: </p>
//     *
//     * @param fileName 文件全路径
//     * @param extName  文件扩展名，不包含（.）
//     * @param metas    文件扩展信息
//     * @return
//     * @throws Exception
//     */
//    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
//        String result = storageClient.upload_file1(fileName, extName, metas);
//        return result;
//    }
//
//
//    public String uploadFile(String fileName) throws Exception {
//        return uploadFile(fileName, null, null);
//    }
//
//    public FileInfo get_file_info(String group, String fileName) {
//        FileInfo fileUrl = null;
//        try {
//            fileUrl = storageClient.get_file_info(group, fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//        return fileUrl;
//    }
//
//    public String uploadFile(String fileName, String extName) throws Exception {
//        return uploadFile(fileName, extName, null);
//    }
//
//    /**
//     * 上传文件方法
//     * <p>Title: uploadFile</p>
//     * <p>Description: </p>
//     *
//     * @param fileContent 文件的内容，字节数组
//     * @param extName     文件扩展名
//     * @param metas       文件扩展信息
//     * @return
//     * @throws Exception
//     */
//    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
//        String result = storageClient.upload_file1(fileContent, extName, metas);
//        return result;
//    }
//
//    public String uploadFile(byte[] fileContent) throws Exception {
//        return uploadFile(fileContent, null, null);
//    }
//
//    public String uploadFile(byte[] fileContent, String extName) throws Exception {
//        return uploadFile(fileContent, extName, null);
//    }
//
////    public static boolean validateDFSFilePath(String originFileName) {
////        if (Strings.isNullOrEmpty(originFileName)) {
////            return false;
////        }
////        Pattern p = Pattern.compile("[a-zA-Z_0-9\\-]+\\/[a-zA-Z_0-9\\-]+\\/[a-zA-Z_0-9\\-]+\\/[a-zA-Z_0-9\\-]+\\/.+");
////        Matcher m = p.matcher(originFileName);
////        return m.matches();
////    }
////
////    public String[] doParseRelativeFileName(String originFileName) {
//////        String originFileName="group1/M00/00/00/rB4VXFkVHROAeM0KAAAthHV31sI505.jpg";
////        if (!validateDFSFilePath(originFileName)) {
////            //throw new DFSValidateException("dfs文件路径不合法: " + originFileName);
////        }
////        // valid dfs file id
////        String[] res = new String[2];
////        int index = originFileName.indexOf("/");
////        res[0] = originFileName.substring(0, index);
////        res[1] = originFileName.substring(index + 1, originFileName.length());
////        return res;
////    }
////
////    /**
////     * 生成主图的缩略图附图的接口
////     *
////     * @param originFileName 主图文件系统路径
////     * @param scaleSize      缩略尺寸设置
////     * @return 附图的dfs路径
////     * @throws Exception 异常
////     */
////    public String scale(String originFileName, ScaleSize scaleSize) throws Exception {
////        String[] res = this.doParseRelativeFileName(originFileName);
////        String groupName = res[0];
////        String remoteFileName = res[1];
////        FastDFSClient client2 = new FastDFSClient("D:\\IdeaProjects_1603\\springmvc\\src\\main\\resources\\conf\\fastClient.conf");
////
////        StorageClient client = storageClient;
////        // 检查是否已经存在此尺寸的缩略图
////        String scaleFileName = buildSlaveFileName(originFileName, buildScaleSuffix(scaleSize));
////        if (doesFileExist(client, scaleFileName)) {
////            // 图片存在
////            return scaleFileName;
////        }
////        // 下载主图
////        byte[] src = client.download_file(groupName, remoteFileName);
////        if (src == null) {
////           // throw new DFSException("水印处理失败: 无法获取主文件");
////        }
////        // 生成缩略图片
////        //返回水印图片路径
////        return doScale(client, src, groupName, remoteFileName, scaleSize);
////    }
////
////    /**
////     * 缩略图处理逻辑
////     *
////     * @param client         dfs storage client
////     * @param src            file content, original file content
////     * @param groupName      dfs group name
////     * @param remoteFileName dfs remote file name
////     * @param sSize          scale file size with width/height
////     * @return handler result list, 有可能为null
////     * @throws IOException          异常
////     * @throws SimpleImageException 异常
////     * @throws MyException          异常
////     */
////    private String doScale(StorageClient client, byte[] src, String groupName, String remoteFileName, ScaleSize sSize)
////            throws IOException, SimpleImageException, MyException {
////        String ext = getFileExtension(remoteFileName);
////        byte[] scaleSrc = ImageUtil.scaleImage(src, ext, sSize.getSwidth(), sSize.getSheight());
////        String scaleRes[] = client.upload_file(groupName, remoteFileName, buildScaleSuffix(sSize), scaleSrc, ext, null);
////        if (scaleRes == null) {
////            log.debug("缩略文件可能已经存在,无需重新生成");
////            return null;
////        }
////        return groupName + SEPARATOR + scaleRes[1];
////    }
////
////    /**
////     * 用来构建dfs系统slave文件的文件名
////     * slave文件包括缩略图, 文字水印图, 签章, 灰度文件等
////     *
////     * @param originFileName
////     * @param slaveFileSuffix
////     * @return
////     */
////    public static String buildSlaveFileName(String originFileName, String slaveFileSuffix) {
////        List<String> tokens = Splitter.on(".").splitToList(originFileName);
////        StringBuilder stringBuilder = new StringBuilder();
////        String pre = Joiner.on(".").join(tokens.subList(0, tokens.size() - 1));
////        stringBuilder.append(pre).append(slaveFileSuffix).append(".").append(tokens.get(tokens.size() - 1));
////        return stringBuilder.toString();
////    }
////
////    /**
////     * 获得缩略图的后缀
////     *
////     * @param scaleSize
////     * @return
////     */
////    public static String buildScaleSuffix(ScaleSize scaleSize) {
////        return "_" + scaleSize.getSwidth() + "x" + scaleSize.getSheight();
////    }
////
////    private boolean doesFileExist(StorageClient client, String originFileName) throws Exception {
////        String[] res = this.doParseRelativeFileName(originFileName);
////        String groupName = res[0];
////        String remoteFileName = res[1];
////        FileInfo fileInfo = client.get_file_info(groupName, remoteFileName);
////        return fileInfo != null;
////    }
//}