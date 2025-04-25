package org.lumosframework;

import java.util.HashMap;
import java.util.Map;

public class MagicWorld {
    private final Map<String, Object> magicalEntities = new HashMap<>();

    /**
     * Registry of a new magical entity.
     */
    public void registerMagicalEntity(String name, Object bean) {
        if (magicalEntities.containsKey(name)) {
            throw new IllegalArgumentException("Magical object with name '" + name + "' already exists!");
        }
        magicalEntities.put(name, bean);
    }

    /**
     * Getting magical entity by name.
     */
    public <T> T getMagicalEntity(String name, Class<T> type) {
        Object magicalEntity = magicalEntities.get(name);
        if (magicalEntity == null) {
            throw new IllegalArgumentException("No magical object found with name '" + name + "'!");
        }
        return type.cast(magicalEntity);
    }

    /**
     * Scanning of package and registration of all magical components.
     */
    public void scanAndRegister(String basePackage) throws Exception {
        SnitchSeeker snitchSeeker = new SnitchSeeker();
        for (Class<?> clazz : snitchSeeker.seekForArtifacts(basePackage)) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            String beanName = clazz.getSimpleName();
            registerMagicalEntity(beanName, instance);
        }
    }

    public void performBindingRitual() throws IllegalAccessException {
        for (Object entity : magicalEntities.values()) {
            Enchanter.performBinding(entity, this);
        }
    }
}
