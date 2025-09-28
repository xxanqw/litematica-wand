package pp.ua.xxanqw.litematica_wand;

import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

/**
 * Test class for validating recipe functionality
 */
public class RecipeTest {

    /**
     * Validates that wand recipe exists and is accessible
     */
    public static void testWandRecipeExists() {
        InputStream recipeStream = getResourceAsStream("data/litematica_wand/recipe/wand_recipe.json");
        if (recipeStream == null) {
            throw new AssertionError("Wand recipe should exist at data/litematica_wand/recipe/wand_recipe.json");
        }

        try {
            recipeStream.close();
        } catch (IOException e) {
            // Ignore close errors in tests
        }

        System.out.println("✓ Wand recipe existence validation passed");
    }

    /**
     * Validates that recipe file is not empty
     */
    public static void testRecipeFileSize() {
        InputStream recipeStream = getResourceAsStream("data/litematica_wand/recipe/wand_recipe.json");
        if (recipeStream == null) {
            throw new AssertionError("Wand recipe should exist for size validation");
        }

        try {
            int available = recipeStream.available();
            if (available <= 0) {
                throw new AssertionError("Wand recipe file should not be empty");
            }
        } catch (IOException e) {
            throw new AssertionError("Could not read wand recipe: " + e.getMessage());
        } finally {
            try {
                recipeStream.close();
            } catch (IOException e) {
                // Ignore close errors in tests
            }
        }

        System.out.println("✓ Recipe file size validation passed");
    }

    /**
     * Validates that recipe path structure follows Minecraft conventions
     */
    public static void testRecipePathStructure() {
        String recipePath = "data/litematica_wand/recipe/wand_recipe.json";
        InputStream stream = getResourceAsStream(recipePath);

        if (stream == null) {
            throw new AssertionError("Recipe path structure should follow Minecraft data conventions");
        }

        try {
            stream.close();
        } catch (IOException e) {
            // Ignore close errors in tests
        }

        System.out.println("✓ Recipe path structure validation passed");
    }

    /**
     * Validates that recipe namespace matches mod ID
     */
    public static void testRecipeNamespace() {
        // The recipe path should use the correct mod ID as namespace
        String expectedPath = "data/litematica_wand/recipe/wand_recipe.json";
        InputStream stream = getResourceAsStream(expectedPath);

        if (stream == null) {
            throw new AssertionError("Recipe should use correct mod ID namespace: litematica_wand");
        }

        try {
            stream.close();
        } catch (IOException e) {
            // Ignore close errors in tests
        }

        System.out.println("✓ Recipe namespace validation passed");
    }

    /**
     * Validates that recipe file contains valid JSON structure
     */
    public static void testRecipeJsonStructure() {
        InputStream recipeStream = getResourceAsStream("data/litematica_wand/recipe/wand_recipe.json");
        if (recipeStream == null) {
            throw new AssertionError("Wand recipe should exist for JSON validation");
        }

        try {
            // Read the recipe content
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = recipeStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String recipeContent = result.toString("UTF-8");

            // Basic JSON validation - check for required recipe elements
            if (!recipeContent.contains("type")) {
                throw new AssertionError("Recipe should contain 'type' field");
            }
            if (!recipeContent.contains("result")) {
                throw new AssertionError("Recipe should contain 'result' field");
            }

            // Check that result item matches the wand
            if (!recipeContent.contains("litematica_wand:wand")) {
                throw new AssertionError("Recipe result should reference the wand item");
            }

        } catch (IOException e) {
            throw new AssertionError("Could not read recipe content: " + e.getMessage());
        } finally {
            try {
                recipeStream.close();
            } catch (IOException e) {
                // Ignore close errors in tests
            }
        }

        System.out.println("✓ Recipe JSON structure validation passed");
    }

    /**
     * Validates that recipe type is valid for Minecraft
     */
    public static void testRecipeType() {
        InputStream recipeStream = getResourceAsStream("data/litematica_wand/recipe/wand_recipe.json");
        if (recipeStream == null) {
            throw new AssertionError("Wand recipe should exist for type validation");
        }

        try {
            // Read the recipe content
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = recipeStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String recipeContent = result.toString("UTF-8");

            // Check for valid recipe types
            boolean hasValidType = recipeContent.contains("crafting_shaped") ||
                                 recipeContent.contains("crafting_shapeless") ||
                                 recipeContent.contains("smelting") ||
                                 recipeContent.contains("blasting") ||
                                 recipeContent.contains("smoking") ||
                                 recipeContent.contains("campfire_cooking") ||
                                 recipeContent.contains("stonecutting") ||
                                 recipeContent.contains("smithing");

            if (!hasValidType) {
                throw new AssertionError("Recipe should have a valid Minecraft recipe type");
            }

        } catch (IOException e) {
            throw new AssertionError("Could not read recipe content: " + e.getMessage());
        } finally {
            try {
                recipeStream.close();
            } catch (IOException e) {
                // Ignore close errors in tests
            }
        }

        System.out.println("✓ Recipe type validation passed");
    }

    /**
     * Main test runner for Recipe tests
     */
    public static void runAllTests() {
        System.out.println("Running Recipe validation tests...");
        System.out.println("=================================");

        testWandRecipeExists();
        testRecipeFileSize();
        testRecipePathStructure();
        testRecipeNamespace();
        testRecipeJsonStructure();
        testRecipeType();

        System.out.println("=================================");
        System.out.println("✓ All Recipe tests passed!");
    }

    private static InputStream getResourceAsStream(String path) {
        return RecipeTest.class.getClassLoader().getResourceAsStream(path);
    }
}