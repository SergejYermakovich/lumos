package org.lumosframework.core.example;

import org.lumosframework.MagicWorld;

public class Main {
    public static void main(String[] args) throws Exception {
        MagicWorld magicWorld = new MagicWorld();
        magicWorld.scanAndRegister("org.lumosframework");
        magicWorld.loadSpellBook("magic.properties");
        magicWorld.performBindingRitual();

        Wizard harryPotter = magicWorld.getMagicalEntity("Wizard", Wizard.class);
        harryPotter.castSpell();
    }
}