package org.lumosframework;

import org.lumosframework.annotations.Charmed;
import org.lumosframework.annotations.MagicValue;

import java.lang.reflect.Field;

/**
 * Dependency injector
 */
public class Enchanter {

    /**
     * Injecting dependencies into object
     */
    public static void performBinding(Object entity, MagicWorld magicWorld) throws IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Charmed.class)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object dependency = magicWorld.getMagicalEntity(fieldType.getSimpleName(), fieldType);
                field.set(entity, dependency);
            } else if (field.isAnnotationPresent(MagicValue.class)) {
                field.setAccessible(true);
                MagicValue magicValueAnnotation = field.getAnnotation(MagicValue.class);
                String key = magicValueAnnotation.key();
                String value = magicWorld.getSpellValue(key);

                if (value != null) {
                    Object convertedValue = convertValue(value, field.getType());
                    field.set(entity, convertedValue);
                } else {
                    throw new RuntimeException("Magic value not found for key: " + key);
                }
            }
        }
    }


    private static Object convertValue(String value, Class<?> targetType) {
        if (targetType == String.class) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            throw new IllegalArgumentException("Unsupported magic type: " + targetType);
        }
    }
}
