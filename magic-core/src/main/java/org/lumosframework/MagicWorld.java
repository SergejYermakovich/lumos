package org.lumosframework;

import org.lumosframework.annotations.Artifact;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MagicWorld {
    private final Map<String, Object> magicalEntities = new HashMap<>();
    private final Properties spellBook = new Properties();

    /**
     * Load the book of spells from a .properties file.
     */
    public void loadSpellBook(String filePath) throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new IllegalArgumentException("Spell book not found: " + filePath);
            }
            spellBook.load(input);
        }
    }

    /**
     * Get a value from the spell book.
     */
    public String getSpellValue(String key) {
        return spellBook.getProperty(key);
    }

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
            if (clazz.isAnnotationPresent(Artifact.class)) {
                Artifact artifact = clazz.getAnnotation(Artifact.class);
                String beanName = artifact.value().isEmpty() ? clazz.getSimpleName() : artifact.value();
                Object instance = clazz.getDeclaredConstructor().newInstance();
                registerMagicalEntity(beanName, instance);
            }

        }
    }

    public void performBindingRitual() throws IllegalAccessException {
        for (Object entity : magicalEntities.values()) {
            Enchanter.performBinding(entity, this);
        }
    }
}
