package org.lumosframework.core.example;

import org.lumosframework.annotations.ArcaneArtifact;
import org.lumosframework.annotations.Enchantment;

@ArcaneArtifact
public class Wizard {

    @Enchantment
    private Wand wand;

    public void castSpell() {
        System.out.println("Casting spell with " + wand.getName());
    }
}
