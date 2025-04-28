package org.lumosframework;

import org.lumosframework.annotations.Artifact;
import org.reflections.Reflections;

import java.util.Set;

public class SnitchSeeker {

    /**
     * Scanning package and found all classes with annotation @ArcaneArtifact.
     */

    public Set<Class<?>> seekForArtifacts(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        return reflections.getTypesAnnotatedWith(Artifact.class);
    }
}
