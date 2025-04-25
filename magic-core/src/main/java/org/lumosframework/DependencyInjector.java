package org.lumosframework;

import org.lumosframework.annotations.Enchantment;

import java.lang.reflect.Field;

public class DependencyInjector {
    /**
     * Внедряет зависимости в объект.
     */
    public static void injectDependencies(Object bean, MagicWorldContainer container) throws IllegalAccessException {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Enchantment.class)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object dependency = container.getBean(fieldType.getSimpleName(), fieldType);
                field.set(bean, dependency);
            }
        }
    }
}
