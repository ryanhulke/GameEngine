

import GameObject;
import GatorEngine;
import Input;
import ScriptableBehavior;

public class MovementScript  extends ScriptableBehavior {
    float speed = 0;//try changing this!
    MovementScript(GameObject g, float speed) {
        super(g);
        this.speed = speed;
    }

    @Override
    public void Start() {

    }

    @Override
    public void Update(){
        if(Input.GetKeyHeld('a'))
            gameObject.Translate(-speed,0);
        if(Input.GetKeyHeld('d'))
            gameObject.Translate(speed,0);
        if(Input.GetKeyHeld('w'))
            gameObject.Translate(0, -speed);
        if(Input.GetKeyHeld('s'))
            gameObject.Translate(0,speed);

        if(Input.GetKeyPressed('c')){
            GatorEngine.Create(new GameObject());
            System.out.println(GatorEngine.OBJECTLIST.size());
        }

        if(Input.GetKeyPressed('r')){
            GatorEngine.Delete(this.gameObject);
            System.out.println(GatorEngine.OBJECTLIST.size());
        }

    }


}
