package org.lumosframework.core.example;

import org.lumosframework.MagicWorld;

public class Main {
    public static void main(String[] args) throws Exception {
        MagicWorld container = new MagicWorld();
        container.scanAndRegister("org.lumosframework");
        container.performBindingRitual();

        Wizard harryPotter = container.getMagicalEntity("Wizard", Wizard.class);
        harryPotter.castSpell();
    }
}