package infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InHandler implements InvocationHandler {
    private Object bean;

    public InHandler(Object bean) {
        this.bean = bean;
    }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            Method beanMethod = bean.getClass().getMethod(method.getName(),
                    method.getParameterTypes());

            Benchmark bm = beanMethod.getAnnotation(Benchmark.class);

            if ( bm != null && bm.on()) {
                long start = System.nanoTime();
                result = beanMethod.invoke(bean, args);
                long end = System.nanoTime();
                System.out.println(end - start);
            }
            else {
                 result = beanMethod.invoke(bean, args);
            }
            return result;

        }
}
