package org.lumosframework;

import java.util.HashMap;
import java.util.Map;

public class MagicWorldContainer {
    private final Map<String, Object> magicalBeans = new HashMap<>();

    /**
     * Регистрация нового магического объекта.
     */
    public void registerBean(String name, Object bean) {
        if (magicalBeans.containsKey(name)) {
            throw new IllegalArgumentException("Magical object with name '" + name + "' already exists!");
        }
        magicalBeans.put(name, bean);
    }

    /**
     * Получение магического объекта по имени.
     */
    public <T> T getBean(String name, Class<T> type) {
        Object bean = magicalBeans.get(name);
        if (bean == null) {
            throw new IllegalArgumentException("No magical object found with name '" + name + "'!");
        }
        return type.cast(bean);
    }

    /**
     * Сканирование пакета и регистрация всех магических компонентов.
     */
    public void scanAndRegister(String basePackage) throws Exception {
        MagicScanner scanner = new MagicScanner();
        for (Class<?> clazz : scanner.scanForComponents(basePackage)) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            String beanName = clazz.getSimpleName();
            registerBean(beanName, instance);
        }
    }
}
