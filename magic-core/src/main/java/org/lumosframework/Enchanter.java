package org.lumosframework;

import org.lumosframework.annotations.Enchantment;

import java.lang.reflect.Field;

/**
 * Dependency injector
 */
public class Enchanter {

    /**
     * Injecting dependencies into object
     */
    public static void performBinding(Object magicalEntity, MagicWorld magicWorld) throws IllegalAccessException {
        for (Field field : magicalEntity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Enchantment.class)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object dependency = magicWorld.getMagicalEntity(fieldType.getSimpleName(), fieldType);
                field.set(magicalEntity, dependency);
            }
        }
    }
}
