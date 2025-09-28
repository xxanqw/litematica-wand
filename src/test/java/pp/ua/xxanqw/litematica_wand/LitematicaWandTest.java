package pp.ua.xxanqw.litematica_wand;

import java.io.InputStream;

/**
 * Main test class for LitematicaWand mod functionality
 * Validates mod structure, resources, and basic functionality
 */
public class LitematicaWandTest {

    /**
     * Validates that the mod ID is correctly defined
     */
    public static void testModId() {
        if (LitematicaWand.MOD_ID == null || LitematicaWand.MOD_ID.isEmpty()) {
            throw new AssertionError("MOD_ID should not be null or empty");
        }
        if (!"litematica_wand".equals(LitematicaWand.MOD_ID)) {
            throw new AssertionError("MOD_ID should be 'litematica_wand'");
        }
        System.out.println("✓ Mod ID validation passed");
    }

    /**
     * Validates that the mod logger is properly initialized
     */
    public static void testLogger() {
        if (LitematicaWand.LOGGER == null) {
            throw new AssertionError("Logger should not be null");
        }
        System.out.println("✓ Logger validation passed");
    }

    /**
     * Validates that all required resource files exist
     */
    public static void testResourceFiles() {
        // Test fabric.mod.json
        if (LitematicaWandTest.class.getClassLoader().getResource("fabric.mod.json") == null) {
            throw new AssertionError("fabric.mod.json should exist");
        }

        // Test wand texture
        if (getResourceAsStream("assets/litematica_wand/textures/item/wand.png") == null) {
            throw new AssertionError("Wand texture should exist");
        }

        // Test wand model
        if (getResourceAsStream("assets/litematica_wand/models/item/wand.json") == null) {
            throw new AssertionError("Wand model should exist");
        }

        // Test recipe
        if (getResourceAsStream("data/litematica_wand/recipe/wand_recipe.json") == null) {
            throw new AssertionError("Wand recipe should exist");
        }

        // Test language files
        if (getResourceAsStream("assets/litematica_wand/lang/en_us.json") == null) {
            throw new AssertionError("English language file should exist");
        }
        if (getResourceAsStream("assets/litematica_wand/lang/ru_ru.json") == null) {
            throw new AssertionError("Russian language file should exist");
        }
        if (getResourceAsStream("assets/litematica_wand/lang/uk_ua.json") == null) {
            throw new AssertionError("Ukrainian language file should exist");
        }

        // Test mixin configuration
        if (getResourceAsStream("litematica_wand.mixins.json") == null) {
            throw new AssertionError("Mixin configuration should exist");
        }

        // Test mod icon
        if (getResourceAsStream("assets/litematica_wand/logo_mod.png") == null) {
            throw new AssertionError("Mod icon should exist");
        }

        System.out.println("✓ Resource files validation passed");
    }

    /**
     * Validates that client entrypoint class exists
     */
    public static void testClientEntrypoint() {
        try {
            Class.forName("pp.ua.xxanqw.litematica_wand.client.LitematicaWandClient");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("Client entrypoint class should exist: " + e.getMessage());
        }
        System.out.println("✓ Client entrypoint validation passed");
    }

    /**
     * Validates that item class files exist
     */
    public static void testItemClassFiles() {
        // Test that item class files exist by checking if they're in the classpath
        if (getResourceAsStream("pp/ua/xxanqw/litematica_wand/item/WandItem.class") == null) {
            throw new AssertionError("WandItem.class should exist");
        }
        if (getResourceAsStream("pp/ua/xxanqw/litematica_wand/item/ModItems.class") == null) {
            throw new AssertionError("ModItems.class should exist");
        }
        if (getResourceAsStream("pp/ua/xxanqw/litematica_wand/item/ModItemsGroup.class") == null) {
            throw new AssertionError("ModItemsGroup.class should exist");
        }
        System.out.println("✓ Item class files validation passed");
    }

    /**
     * Validates that mixin class file exists
     */
    public static void testMixinClassFile() {
        if (getResourceAsStream("pp/ua/xxanqw/litematica_wand/mixin/LitematicaWandMixin.class") == null) {
            throw new AssertionError("LitematicaWandMixin.class should exist");
        }
        System.out.println("✓ Mixin class file validation passed");
    }

    /**
     * Main test runner
     */
    public static void main(String[] args) {
        System.out.println("Running LitematicaWand mod validation tests...");
        System.out.println("================================================");

        try {
            testModId();
            testLogger();
            testResourceFiles();
            testClientEntrypoint();
            testItemClassFiles();
            testMixinClassFile();

            System.out.println("================================================");
            System.out.println("✓ All tests passed! Mod structure is valid.");

        } catch (AssertionError e) {
            System.out.println("================================================");
            System.out.println("✗ Test failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private static InputStream getResourceAsStream(String path) {
        return LitematicaWandTest.class.getClassLoader().getResourceAsStream(path);
    }
}