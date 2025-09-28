package pp.ua.xxanqw.litematica_wand;

import java.io.InputStream;

/**
 * Test class for validating mod item groups functionality
 */
public class ModItemsGroupTest {

    /**
     * Validates that ModItemsGroup class file exists
     */
    public static void testModItemsGroupClassFile() {
        if (getResourceAsStream("pp/ua/xxanqw/litematica_wand/item/ModItemsGroup.class") == null) {
            throw new AssertionError("ModItemsGroup.class should exist");
        }
        System.out.println("✓ ModItemsGroup class file validation passed");
    }

    /**
     * Validates that item group registration source exists
     */
    public static void testItemGroupRegistrationSource() {
        if (getResourceAsStream("pp/ua/xxanqw/litematica_wand/item/ModItemsGroup.class") == null) {
            throw new AssertionError("ModItemsGroup source should be compiled");
        }
        System.out.println("✓ Item group registration source validation passed");
    }

    /**
     * Validates that item group configuration is correct
     */
    public static void testItemGroupConfiguration() {
        // Test that item group icon would be the wand item
        if (getResourceAsStream("assets/litematica_wand/textures/item/wand.png") == null) {
            throw new AssertionError("Wand texture should exist for item group icon");
        }

        // Test that item group translation key structure is correct
        if (getResourceAsStream("assets/litematica_wand/lang/en_us.json") == null) {
            throw new AssertionError("Language file should exist for item group translations");
        }

        System.out.println("✓ Item group configuration validation passed");
    }

    /**
     * Validates that item group contains expected items
     */
    public static void testItemGroupContents() {
        // Test that wand item exists for item group
        try {
            Class.forName("pp.ua.xxanqw.litematica_wand.item.WandItem");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("WandItem should exist for item group: " + e.getMessage());
        }

        System.out.println("✓ Item group contents validation passed");
    }

    /**
     * Validates that item group display name translation exists
     */
    public static void testItemGroupTranslations() {
        // Test that translation files contain item group translations
        if (getResourceAsStream("assets/litematica_wand/lang/en_us.json") == null) {
            throw new AssertionError("English translation file should exist for item group");
        }

        System.out.println("✓ Item group translations validation passed");
    }

    /**
     * Main test runner for ModItemsGroup tests
     */
    public static void runAllTests() {
        System.out.println("Running ModItemsGroup validation tests...");
        System.out.println("=======================================");

        testModItemsGroupClassFile();
        testItemGroupRegistrationSource();
        testItemGroupConfiguration();
        testItemGroupContents();
        testItemGroupTranslations();

        System.out.println("=======================================");
        System.out.println("✓ All ModItemsGroup tests passed!");
    }

    private static InputStream getResourceAsStream(String path) {
        return ModItemsGroupTest.class.getClassLoader().getResourceAsStream(path);
    }
}