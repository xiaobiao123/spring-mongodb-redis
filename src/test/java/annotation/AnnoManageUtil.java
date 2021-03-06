//package annotation;
//
//import cn.com.asman.right.annotation.RightDefinition;
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.collect.Maps;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//import org.mockito.internal.util.collections.Sets;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.nio.file.DirectoryStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.*;
//
//public final class AnnoManageUtil {
//
//
//    @Test
//    public void testPacaageAnnotation() throws Exception {
//
//        List<Class<?>> packageController = AnnoManageUtil.getPackageController("cn.com.asman.right.controller", RestController.class);
//
//        Map<String, Map<String, Set<String>>> urlMap = Maps.newHashMap();
//        for (Class<?> cl : packageController) {
//            RequestMapping mapping = cl.getAnnotation(RequestMapping.class);
//            String classUrl = "";
//            if (mapping.value().length > 0) {
//                classUrl = mapping.value()[0];
//                System.out.println(mapping.value()[0]);
//            }
//
//            Method[] methods = cl.getMethods();
//            for (Method method : methods) {
//                Annotation[] methodNnnotations = method.getAnnotations();
//                String methodUrl = "";
//                Map<String, Set<String>> rightSet = Maps.newHashMap();
//                for (Annotation annotation : methodNnnotations) {
//                    if (annotation instanceof RequestMapping) {
//                        methodUrl = ((RequestMapping) annotation).value()[0];
//                    } else if (annotation instanceof GetMapping) {
//                        methodUrl = ((GetMapping) annotation).value()[0];
//                    } else if (annotation instanceof PutMapping) {
//                        methodUrl = ((PutMapping) annotation).value()[0];
//                    } else if (annotation instanceof DeleteMapping) {
//                        methodUrl = ((DeleteMapping) annotation).value()[0];
//                    } else if (annotation instanceof PostMapping) {
//                        methodUrl = ((PostMapping) annotation).value()[0];
//                    } else if (annotation instanceof RightDefinition) {
//                        //获取访问所需要的功能权限
//                        String[] fun = ((RightDefinition) annotation).functionTokens();
//                        if (fun != null && fun.length > 0) {
//                            Set<String> funSet = Sets.newSet();
//                            for (int i = 0; i < fun.length; i++) {
//                                funSet.add(fun[i]);
//                            }
//                            rightSet.put("funToken", funSet);
//                        }
//                        //获取访问所需要的菜单权限
//                        String[] menu = ((RightDefinition) annotation).menuTokens();
//                        if (fun != null && menu.length > 0) {
//                            Set<String> menuSet = Sets.newSet();
//                            for (int i = 0; i < menu.length; i++) {
//                                menuSet.add(menu[i]);
//                            }
//                            rightSet.put("menuSet", menuSet);
//                        }
//                    } else {
//                        continue;
//                    }
//                }
//                /**
//                 * url和访问权限转换成
//                 * {
//                 *     "/admin/right/update_role":{
//                 *         "funToken":[
//                 *             ""
//                 *         ],
//                 *         "menuSet":[
//                 *             ""
//                 *         ]
//                 *     },
//                 *     "/admin/right/find_right":{
//                 *         "funToken":[
//                 *             "2020",
//                 *             "2021"
//                 *         ],
//                 *         "menuSet":[
//                 *             "1010",
//                 *             "1011"
//                 *         ]
//                 *     }
//                 * }
//                 */
//                if (!StringUtils.isEmpty(methodUrl)) {
//                    if (!StringUtils.isEmpty(classUrl)) {
//                        String url = classUrl + methodUrl;
//                        if (urlMap.get(url) != null) {
//                            throw new Exception("url为" + url + "已存在");
//                        } else {
//                            urlMap.put(url, rightSet);
//                        }
//                    }
//                }
//
//            }
//        }
//
//        System.out.println(JSONObject.toJSONString(urlMap));
//        System.out.println(JSONObject.toJSONString(packageController));
//        Map<String, ExecutorBean> mapp = Maps.newHashMap();
////        AnnoManageUtil.getRequestMappingMethod(packageController,mapp);
//    }
//
//
//    /**
//     * 获取当前包路径下指定的Controller注解类型的文件
//     *
//     * @param packageName 包名
//     * @param annotation  注解类型
//     * @return 文件
//     */
//    public static List<Class<?>> getPackageController(String packageName, Class<? extends Annotation> annotation) {
//        List<Class<?>> classList = new ArrayList<Class<?>>();
//
//        String packageDirName = packageName.replace('.', '/');
//
//        Enumeration<URL> dirs = null;
//
//        //获取当前目录下面的所有的子目录的url
//        try {
//            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        while (dirs.hasMoreElements()) {
//            URL url = dirs.nextElement();
//
//            //得到但钱url的类型
//            String protocol = url.getProtocol();
//
//            //如果当前类型是文件类型
//            if ("file".equals(protocol)) {
//                //获取包的物理路径
//                String filePath = null;
//                try {
//                    filePath = URLDecoder.decode(url.getFile(), "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//
//                filePath = filePath.substring(1);
//                getFilePathClasses(packageName, filePath, classList, annotation);
//            }
//        }
//
//
//        return classList;
//    }
//
//    /**
//     * 从指定的包下面找到文件名
//     *
//     * @param packageName
//     * @param filePath
//     * @param classList
//     * @param annotation  注解类型
//     */
//    private static void getFilePathClasses(String packageName, String filePath, List<Class<?>> classList,
//                                           Class<? extends Annotation> annotation) {
//        Path dir = Paths.get(filePath);
//
//        DirectoryStream<Path> stream = null;
//        try {
//            //获得当前目录下的文件的stream流
//            stream = Files.newDirectoryStream(dir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (Path path : stream) {
//            String fileName = String.valueOf(path.getFileName());
//
//            String className = fileName.substring(0, fileName.length() - 6);
//
//            Class<?> classes = null;
//            try {
//                classes = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            //判断该注解类型是不是所需要的类型
//            if (null != classes && null != classes.getAnnotation(annotation)) {
//                //把这个文件加入classlist中
//                classList.add(classes);
//            }
//        }
//    }
//
//    /**
//     * 获取classList下面的RequestMapping方法保存在mapp中
//     *
//     * @param classList 保存加了Controller的类
//     * @param mapp      存放url和ExecutorBean的对应关系
//     */
//    public static void getRequestMappingMethod(List<Class<?>> classList, Map<String, ExecutorBean> mapp) {
//        for (Class classes : classList) {
//            //得到该类下面的所有方法
//            Method[] methods = classes.getDeclaredMethods();
//
//            for (Method method : methods) {
//                //得到该类下面的RequestMapping注解
//                GetMapping requestMapping = method.getAnnotation(GetMapping.class);
//                if (null != requestMapping) {
//                    ExecutorBean executorBean = new ExecutorBean();
//                    try {
//                        executorBean.setObject(classes.newInstance());
//                    } catch (InstantiationException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
//
//                    executorBean.setMethod(method);
//
//                    mapp.put(requestMapping.name(), executorBean);
//
//                }
//            }
//        }
//    }
//
//
//}
