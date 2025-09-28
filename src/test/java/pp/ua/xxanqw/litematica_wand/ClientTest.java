package pp.ua.xxanqw.litematica_wand;

import java.io.InputStream;

/**
 * Test class for validating client-side functionality
 */
public class ClientTest {

    /**
     * Validates that client entrypoint class file exists
     */
    public static void testClientEntrypointClassFile() {
        if (getResourceAsStream("pp/ua/xxanqw/litematica_wand/client/LitematicaWandClient.class") == null) {
            throw new AssertionError("LitematicaWandClient.class should exist");
        }
        System.out.println("✓ Client entrypoint class file validation passed");
    }

    /**
     * Validates that client class has correct package structure
     */
    public static void testClientPackageStructure() {
        if (getResourceAsStream("pp/ua/xxanqw/litematica_wand/client/LitematicaWandClient.class") == null) {
            throw new AssertionError("Client class should be in client package");
        }
        System.out.println("✓ Client package structure validation passed");
    }

    /**
     * Validates that client-side resources exist
     */
    public static void testClientResources() {
        // Test that client-specific resources exist
        if (getResourceAsStream("assets/litematica_wand/textures/item/wand.png") == null) {
            throw new AssertionError("Client textures should exist");
        }

        if (getResourceAsStream("assets/litematica_wand/models/item/wand.json") == null) {
            throw new AssertionError("Client models should exist");
        }

        if (getResourceAsStream("assets/litematica_wand/lang/en_us.json") == null) {
            throw new AssertionError("Client language files should exist");
        }

        System.out.println("✓ Client resources validation passed");
    }

    /**
     * Validates that client-side mod entry is properly configured
     */
    public static void testClientModEntry() {
        // Test that fabric.mod.json exists (which should contain client entrypoint)
        if (getResourceAsStream("fabric.mod.json") == null) {
            throw new AssertionError("fabric.mod.json should exist for client entrypoint configuration");
        }

        System.out.println("✓ Client mod entry validation passed");
    }

    /**
     * Validates that client-side language support exists
     */
    public static void testClientLanguageSupport() {
        // Test that all language files exist for client
        String[] langFiles = {
            "assets/litematica_wand/lang/en_us.json",
            "assets/litematica_wand/lang/ru_ru.json",
            "assets/litematica_wand/lang/uk_ua.json"
        };

        for (String langFile : langFiles) {
            if (getResourceAsStream(langFile) == null) {
                throw new AssertionError("Language file should exist: " + langFile);
            }
        }

        System.out.println("✓ Client language support validation passed");
    }

    /**
     * Validates that client-side item models exist
     */
    public static void testClientItemModels() {
        // Test that item models exist for client rendering
        if (getResourceAsStream("assets/litematica_wand/models/item/wand.json") == null) {
            throw new AssertionError("Item model should exist for client rendering");
        }

        System.out.println("✓ Client item models validation passed");
    }

    /**
     * Validates that client-side textures exist
     */
    public static void testClientTextures() {
        // Test that item textures exist for client rendering
        if (getResourceAsStream("assets/litematica_wand/textures/item/wand.png") == null) {
            throw new AssertionError("Item texture should exist for client rendering");
        }

        System.out.println("✓ Client textures validation passed");
    }

    /**
     * Validates that client-side mod icon exists
     */
    public static void testClientModIcon() {
        // Test that mod icon exists for client mod list
        if (getResourceAsStream("assets/litematica_wand/logo_mod.png") == null) {
            throw new AssertionError("Mod icon should exist for client mod list");
        }

        System.out.println("✓ Client mod icon validation passed");
    }

    /**
     * Main test runner for Client tests
     */
    public static void runAllTests() {
        System.out.println("Running Client validation tests...");
        System.out.println("=================================");

        testClientEntrypointClassFile();
        testClientPackageStructure();
        testClientResources();
        testClientModEntry();
        testClientLanguageSupport();
        testClientItemModels();
        testClientTextures();
        testClientModIcon();

        System.out.println("=================================");
        System.out.println("✓ All Client tests passed!");
    }

    private static InputStream getResourceAsStream(String path) {
        return ClientTest.class.getClassLoader().getResourceAsStream(path);
    }
}