package org.lumosframework.core.example;

import org.lumosframework.annotations.Artifact;
import org.lumosframework.annotations.Charmed;
import org.lumosframework.annotations.MagicValue;

@Artifact
public class Wizard {

    @Charmed
    private Wand wand;

    @MagicValue(key = "wizzard.name")
    private String name;

    public void castSpell() {
        System.out.println("Casting spell with " + wand.getName() + " by " + name);
    }
}
