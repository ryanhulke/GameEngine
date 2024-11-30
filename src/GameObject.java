
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    private AffineTransform ObjectTransform; //the location/scale/rotation of our object
    private Shape ObjectShape; //the collider/rendered shape of this object
    private Material ObjectMaterial; //data about the fill color, border color, and border thickness
    private ArrayList<ScriptableBehavior> ObjectScripts = new ArrayList<>(); //all scripts attached to the object
    private boolean IsActive = true; //whether this gets Updated() and Draw()

    public GameObject(){
        ObjectTransform = new AffineTransform();
        ObjectMaterial = new Material();
        ObjectShape = new Rectangle2D.Float(0, 0, 10, 10);
    }


    public GameObject(int x, int y){
        this();
        ObjectTransform.translate(x, y);
    }

    //Engine Methods

    // Start all scripts on the object
    public void Start(){
        for (ScriptableBehavior script : ObjectScripts){
            script.Start();
        }
    }

    // Update all scripts on the object
    public void Update(){
        if (!IsActive) return;
        for (ScriptableBehavior script : ObjectScripts){
            script.Update();
        }
    }

    // Adds a new scripted behavior at runtime.
    public void AddScript(ScriptableBehavior NewScript){
        ObjectScripts.add(NewScript);
        NewScript.Start();
    }

    // Draw the object by
    // 1) saving the renderer's old transform.
    // 2) transforming the renderer based on the GameObject's transform.
    // 3) based on the type of material, drawing either the styled shape or the image scaled to the bounds of the shape.
    // 4) returning the old transform to the renderer.
    public void Draw(Graphics2D renderer){
        if (!IsActive) return;

        // 1) saving the renderer's old transform.
        AffineTransform oldTransform = renderer.getTransform();
        // 2) transforming the renderer based on the GameObject's transform.
        renderer.transform(ObjectTransform);
        // 3) based on the type of material, drawing either the styled shape or the image scaled to the bounds of the shape.
        Material material = ObjectMaterial;
        if (material.IsShape){
            // Draw the shape with fill and stroke
            if (material.GetFill() != null){
                renderer.setColor(material.GetFill());
                renderer.fill(ObjectShape);
            }
            if (material.GetBorder() != null && material.GetBorderWidth() > 0){
                renderer.setStroke(new BasicStroke(material.GetBorderWidth()));
                renderer.setColor(material.GetBorder());
                renderer.draw(ObjectShape);
            }
        } else {
            // Draw the image scaled to the bounds of the shape
            Rectangle2D bounds = ObjectShape.getBounds2D();
            BufferedImage img = material.GetMaterialImage();
            if (img != null){
                renderer.drawImage(img, 0, 0, (int)bounds.getWidth(), (int)bounds.getHeight(), null);
            }
        }
        // 4) returning the old transform to the renderer.
        renderer.setTransform(oldTransform);
    }

    // Movement/Collision Methods
    // Move the GameObject's transform by the provided values.
    public void Translate(float dX, float dY){
        ObjectTransform.translate(dX, dY);
    }

    public void MoveTo(float x, float y){
        double m00 = ObjectTransform.getScaleX();
        double m10 = ObjectTransform.getShearY();
        double m01 = ObjectTransform.getShearX();
        double m11 = ObjectTransform.getScaleY();
        ObjectTransform.setTransform(m00, m10, m01, m11, x, y);
    }

    // Scale the GameObject's transform around the CENTER of its transformed shape.
    public void Scale(float sX, float sY){
        // Get the transformed shape
        Shape transformedShape = ObjectTransform.createTransformedShape(ObjectShape);
        Rectangle2D bounds = transformedShape.getBounds2D();
        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();

        AffineTransform at = new AffineTransform();
        at.translate(centerX, centerY);
        at.scale(sX, sY);
        at.translate(-centerX, -centerY);

        ObjectTransform.concatenate(at);
    }

    // Returns true if the two objects are touching
    // i.e., the intersection of their Areas is not empty)
    public boolean CollidesWith(GameObject other){
        Shape shape1 = ObjectTransform.createTransformedShape(ObjectShape);
        Shape shape2 = other.ObjectTransform.createTransformedShape(other.ObjectShape);

        Area area1 = new Area(shape1);
        Area area2 = new Area(shape2);

        area1.intersect(area2);

        return !area1.isEmpty();
    }

    // returns true if the transformed shape contains the point
    public boolean Contains(Point2D point){
        Shape transformedShape = ObjectTransform.createTransformedShape(ObjectShape);
        return transformedShape.contains(point);
    }

    //Getters and Setters
    public AffineTransform GetObjectTransform() {
        return ObjectTransform;
    }

    public void SetObjectTransform(AffineTransform objectTransform) {
        this.ObjectTransform = objectTransform;
    }

    public Shape GetShape() {
        return ObjectShape;
    }

    public void SetShape(Shape shape) {
        this.ObjectShape = shape;
    }

    public Material GetObjectMaterial() {
        return ObjectMaterial;
    }

    public void SetObjectMaterial(Material objectMaterial) {
        this.ObjectMaterial = objectMaterial;
    }

    public ArrayList<ScriptableBehavior> GetObjectScripts() {
        return ObjectScripts;
    }

    public void SetObjectScripts(ArrayList<ScriptableBehavior> objectScripts) {
        this.ObjectScripts = objectScripts;
    }

    public boolean IsActive() {
        return IsActive;
    }

    public void SetActive(boolean active) {
        this.IsActive = active;
    }

}
