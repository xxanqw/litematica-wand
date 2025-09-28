package pp.ua.xxanqw.litematica_wand;

import java.io.InputStream;
import java.io.IOException;

/**
 * Test class for validating texture and model loading functionality
 */
public class TextureModelTest {

    /**
     * Validates that wand texture exists and is accessible
     */
    public static void testWandTexture() {
        InputStream textureStream = getResourceAsStream("assets/litematica_wand/textures/item/wand.png");
        if (textureStream == null) {
            throw new AssertionError("Wand texture should exist at assets/litematica_wand/textures/item/wand.png");
        }

        try {
            textureStream.close();
        } catch (IOException e) {
            // Ignore close errors in tests
        }

        System.out.println("✓ Wand texture validation passed");
    }

    /**
     * Validates that wand model exists and is accessible
     */
    public static void testWandModel() {
        InputStream modelStream = getResourceAsStream("assets/litematica_wand/models/item/wand.json");
        if (modelStream == null) {
            throw new AssertionError("Wand model should exist at assets/litematica_wand/models/item/wand.json");
        }

        try {
            modelStream.close();
        } catch (IOException e) {
            // Ignore close errors in tests
        }

        System.out.println("✓ Wand model validation passed");
    }

    /**
     * Validates that mod icon exists and is accessible
     */
    public static void testModIcon() {
        InputStream iconStream = getResourceAsStream("assets/litematica_wand/logo_mod.png");
        if (iconStream == null) {
            throw new AssertionError("Mod icon should exist at assets/litematica_wand/logo_mod.png");
        }

        try {
            iconStream.close();
        } catch (IOException e) {
            // Ignore close errors in tests
        }

        System.out.println("✓ Mod icon validation passed");
    }

    /**
     * Validates that 3D wand model exists (if present)
     */
    public static void testWand3DModel() {
        InputStream model3DStream = getResourceAsStream("assets/litematica_wand/items/wand_3d.json");
        if (model3DStream == null) {
            System.out.println("⚠ Wand 3D model not found (this may be optional)");
        } else {
            try {
                model3DStream.close();
            } catch (IOException e) {
                // Ignore close errors in tests
            }
            System.out.println("✓ Wand 3D model validation passed");
        }
    }

    /**
     * Validates that texture path structure is correct
     */
    public static void testTexturePathStructure() {
        // Test that texture path follows Minecraft conventions
        String texturePath = "assets/litematica_wand/textures/item/wand.png";
        InputStream stream = getResourceAsStream(texturePath);

        if (stream == null) {
            throw new AssertionError("Texture path structure should follow Minecraft conventions");
        }

        try {
            stream.close();
        } catch (IOException e) {
            // Ignore close errors in tests
        }

        System.out.println("✓ Texture path structure validation passed");
    }

    /**
     * Validates that model path structure is correct
     */
    public static void testModelPathStructure() {
        // Test that model path follows Minecraft conventions
        String modelPath = "assets/litematica_wand/models/item/wand.json";
        InputStream stream = getResourceAsStream(modelPath);

        if (stream == null) {
            throw new AssertionError("Model path structure should follow Minecraft conventions");
        }

        try {
            stream.close();
        } catch (IOException e) {
            // Ignore close errors in tests
        }

        System.out.println("✓ Model path structure validation passed");
    }

    /**
     * Validates that texture files are not empty
     */
    public static void testTextureFileSizes() {
        InputStream textureStream = getResourceAsStream("assets/litematica_wand/textures/item/wand.png");
        if (textureStream == null) {
            throw new AssertionError("Wand texture should exist for size validation");
        }

        try {
            int available = textureStream.available();
            if (available <= 0) {
                throw new AssertionError("Wand texture file should not be empty");
            }
        } catch (IOException e) {
            throw new AssertionError("Could not read wand texture: " + e.getMessage());
        } finally {
            try {
                textureStream.close();
            } catch (IOException e) {
                // Ignore close errors in tests
            }
        }

        System.out.println("✓ Texture file sizes validation passed");
    }

    /**
     * Validates that model files are not empty
     */
    public static void testModelFileSizes() {
        InputStream modelStream = getResourceAsStream("assets/litematica_wand/models/item/wand.json");
        if (modelStream == null) {
            throw new AssertionError("Wand model should exist for size validation");
        }

        try {
            int available = modelStream.available();
            if (available <= 0) {
                throw new AssertionError("Wand model file should not be empty");
            }
        } catch (IOException e) {
            throw new AssertionError("Could not read wand model: " + e.getMessage());
        } finally {
            try {
                modelStream.close();
            } catch (IOException e) {
                // Ignore close errors in tests
            }
        }

        System.out.println("✓ Model file sizes validation passed");
    }

    /**
     * Main test runner for Texture/Model tests
     */
    public static void runAllTests() {
        System.out.println("Running Texture/Model validation tests...");
        System.out.println("========================================");

        testWandTexture();
        testWandModel();
        testModIcon();
        testWand3DModel();
        testTexturePathStructure();
        testModelPathStructure();
        testTextureFileSizes();
        testModelFileSizes();

        System.out.println("========================================");
        System.out.println("✓ All Texture/Model tests passed!");
    }

    private static InputStream getResourceAsStream(String path) {
        return TextureModelTest.class.getClassLoader().getResourceAsStream(path);
    }
}