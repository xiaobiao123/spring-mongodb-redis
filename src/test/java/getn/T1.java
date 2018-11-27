package getn;

import java.lang.reflect.ParameterizedType;

public class T1<T> {
    private Class classt;

    public T1() {

        //通过getGenericSuperclass方法可以获取当前对象的直接超类的 Type
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.classt = (Class) type.getActualTypeArguments()[0];
        System.out.println(this.classt);


        ////参数化类型
        //ParameterizedType parameterizedType= (ParameterizedType) genericSuperclass;
        ////返回表示此类型实际类型参数的 Type 对象的数组
        //Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        //this.clazz= (Class<T>)actualTypeArguments[0];
    }

    public <T> void test001(T[] array) {
        for (T element : array) {
            System.out.printf("%s ", element);
        }
    }

    /**
     * 有界泛型
     *
     * @param array
     * @param <T>
     */
    public <T extends Number> void test002(T[] array) {
        for (T element : array) {
            System.out.printf("%s ", element);
        }
    }

    //public T1() {
    //  ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
    //  this.classt = (Class) type.getActualTypeArguments()[0];
    //  System.out.println(this.classt);

}