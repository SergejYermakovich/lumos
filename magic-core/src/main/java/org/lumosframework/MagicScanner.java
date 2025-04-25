package org.lumosframework;

import org.lumosframework.annotations.MagicalComponent;
import org.reflections.Reflections;

import java.util.Set;

public class MagicScanner {

    /**
     * Сканирует пакет и находит все классы с аннотацией @MagicalComponent.
     */
    public Set<Class<?>> scanForComponents(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        return reflections.getTypesAnnotatedWith(MagicalComponent.class);
    }
}
