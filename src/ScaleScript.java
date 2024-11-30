
import java.awt.geom.AffineTransform;

import GameObject;
import ScriptableBehavior;


public class ScaleScript extends ScriptableBehavior {
    private float scale = 1.0f;
    private float scaleStep = 0.01f;
    private final AffineTransform originalTransform;

    public ScaleScript(GameObject g) {
        super(g);
        // Store the original transform to reset scaling
        originalTransform = new AffineTransform(g.GetObjectTransform());
    }

    @Override
    public void Start() {
    }

    @Override
    public void Update() {
        scale += scaleStep;
        if (scale > 1.2f || scale < .8f) {
            scaleStep = -scaleStep;
        }
        // Reset to original transform
        gameObject.SetObjectTransform(new AffineTransform(originalTransform));
        // Apply new scaling
        gameObject.Scale(scale, scale);
    }
}