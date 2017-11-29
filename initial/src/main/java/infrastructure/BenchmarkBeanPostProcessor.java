package infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor {

    private Class<?>[] getAllInterfaces(Object bean) {
        if (bean != null) {
            List<Class<?>> interfaces = new ArrayList<>();
            Class<?> curClass = bean.getClass();
            while (curClass.getSuperclass() != null) {
                interfaces.addAll(Arrays.asList(curClass.getInterfaces()));
                curClass = curClass.getSuperclass();
            }
            return interfaces.toArray(new Class<?>[interfaces.size()]);
        }
        else {
            return null;
        }
    }


    public Object createBeanProxy(Object bean) {
        Object proxy = bean;

        Method[] methods = bean.getClass().getMethods();
        for (Method met : methods) {
            if (met.getAnnotation(Benchmark.class) != null) {
                Class<?>[] interfaces = getAllInterfaces(bean);
                InvocationHandler ih = new InHandler(bean);
                proxy = Proxy.newProxyInstance
                        (bean.getClass().getClassLoader(), interfaces, ih);
                break;
            }
        }
        return proxy;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Created " + beanName);
//        return createBeanProxy(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object proxy = createBeanProxy(bean);
        System.out.println("Initialized " + beanName + " " + ObjectUtils
                .identityToString(proxy));
        return proxy;
//        System.out.println("Initialized " + beanName + " " + ObjectUtils
//                .identityToString(bean));
//        return bean;
    }
}
