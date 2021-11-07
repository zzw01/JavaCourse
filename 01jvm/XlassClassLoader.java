import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 第一周jvm作业：第二题-必做
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 */
public class XlassClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        String className = "Hello";
        Class<?> xlassClass = new XlassClassLoader().findClass(className);
        // 看看里面有些什么方法
        for (Method m : xlassClass.getDeclaredMethods()) {
            System.out.println(xlassClass.getSimpleName() + "." + m.getName());
        }
        // 创建对象
        Object instance = null;
        instance = xlassClass.getDeclaredConstructor().newInstance();
        // 调用实例方法
        String methodName = "hello";
        Method method = null;
        method = xlassClass.getMethod(methodName);
        if (instance != null && method != null) {
            method.invoke(instance);
        }else {
            System.out.println("创建实例对象or获取方法 为空");
        }
    }

    /**
     * 根据路径查找class
     * @param className
     * @return
     */
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        // 文件后缀
        String suffix = ".xlass";
        //从当前类所在包下加载指定名称的文件
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(className + suffix);
        try {
            // 读取数据
            int length = resourceAsStream.available();
            byte[] byteArray = new byte[length];
            resourceAsStream.read(byteArray);
            // 转换
            byte[] classBytes = decode(byteArray);
            // 通知底层定义这个类
            return defineClass(className, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(className, e);
        } finally {
            close(resourceAsStream);
        }
    }

    // 解码
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

    // 关闭
    private static void close(Closeable res) {
        if (null != res) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
