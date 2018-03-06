package tw.com.wd.utils;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Row;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLoader {
    public Class<?> loadClassFromJar(String className) throws Exception {
        File jarDir = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "libs");

        System.out.printf("isDirectory %s\n", jarDir.isDirectory());
        System.out.printf("exists %s\n", jarDir.exists());
        System.out.printf("AbsolutePath %s\n", jarDir.getAbsolutePath());
        System.out.printf("SystemClassLoader Location: %s\n", ClassLoader.getSystemClassLoader().getResource("."));

        URLClassLoader classLoader = null;

        File[] jarFiles = jarDir.listFiles();
        for (File jarFile : jarFiles) {
            System.out.printf("Filename: %s\n", jarFile.getName());
            System.out.printf("Filepath: %s\n", jarFile.getPath());
            System.out.printf("FileAbsolutePath: %s\n", jarFile.getAbsolutePath());
            System.out.printf("URL: %s\n", jarFile.toURI().toURL());

            classLoader = URLClassLoader.newInstance(new URL[]{jarFile.toURI().toURL()}, Thread.currentThread().getContextClassLoader());

            JarFile j = new JarFile(jarFile);

            Enumeration<JarEntry> jarEntryEnumeration = j.entries();

            while (jarEntryEnumeration.hasMoreElements()) {
                JarEntry jarEntry = jarEntryEnumeration.nextElement();

                if (jarEntry.getName().endsWith("class")) {
                    String classname = jarEntry.getName().substring(0, jarEntry.getName().length() - 6).replace('/', '.');

                    System.out.printf("Load class: %s\n", classname);
                    classLoader.loadClass(classname);
                }
            }
            j.close();
        }





        return classLoader.loadClass(className);
    }

    public static void main(String[] args) throws Exception {
        //final Class<?> hbufferClazz       = new JarLoader().loadClassFromJar("tw.com.wd.hbase.util.hbasebuffer.impl.HBaseBuffer");
        final Class<?> hbufferClazz       = Class.forName("tw.com.wd.hbase.util.hbasebuffer.impl.HBaseBuffer");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Method getInstanceMethod = hbufferClazz.getDeclaredMethod("getInstance");
                    Object hbufferInstance = getInstanceMethod.invoke(null);
                    Method putMethod = hbufferClazz.getDeclaredMethod("put", Row.class, TableName.class);

                    putMethod.invoke(hbufferInstance, null);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }).start();
    }
}
