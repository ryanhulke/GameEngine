import java.util.ArrayList;

public class Input {
    //Contains characters that were pressed this frame
    static private final ArrayList<Character> PressedList = new ArrayList<>();
    //Contains characters that are currently held down
    static private final ArrayList<Character> HeldList = new ArrayList<>();
    //Contains characters that were released this frame
    static private final ArrayList<Character> ReleasedList = new ArrayList<>();

    private static int MouseX; //the current mouse X position on the DISPLAY BufferedImage
    private static int MouseY; //the current mouse Y position on the DISPLAY BufferedImage
    private static boolean MouseDown; //true if the mouse is currently down
    private static boolean MouseClicked; //true if the mouse was clicked this frame

    //"Received" values directly from the UI.
    // These may arrive at uneven times, possibly mid-frame, which can cause inputs to be missed by some scripts.
    // These will be used to modify the above fields at the START of each frame.
    static private final ArrayList<Character> Received_PressedList = new ArrayList<>();
    static private final ArrayList<Character> Received_ReleasedList = new ArrayList<>();
    private static int Received_MouseX;
    private static int Received_MouseY;
    private static boolean Received_MouseDown;
    private static boolean Received_MouseClicked;

    //This function should be called at the START of the frame
    // it uses values received from event listeners (operating at a different speed than our FRAMETIMER)
    // to update the respective field. Then, the received lists are emptied/returned to a default state.
    static void ReceiveInputs(){
        // Update PressedList and HeldList
        for (Character c : Received_PressedList){
            if (!PressedList.contains(c)){
                PressedList.add(c);
            }
            if (!HeldList.contains(c)){
                HeldList.add(c);
            }
        }
        // Update ReleasedList and remove from HeldList
        for (Character c : Received_ReleasedList){
            if (!ReleasedList.contains(c)){
                ReleasedList.add(c);
            }
            HeldList.remove(c);
        }
        // Update MouseX, MouseY
        MouseX = Received_MouseX;
        MouseY = Received_MouseY;
        // Update MouseDown
        MouseDown = Received_MouseDown;
        // Update MouseClicked
        MouseClicked = Received_MouseClicked;

        // Clear Received lists and reset Received variables
        Received_PressedList.clear();
        Received_ReleasedList.clear();
        Received_MouseClicked = false;
    }

    // This function should called at the END of every frame and
    // clear any values in the Input class that need to be removed,
    // e.g., if a key was pressed on a frame, it should be removed from the pressed list the next frame
    static void ValidateInputs(){
        PressedList.clear();
        ReleasedList.clear();
        MouseClicked = false;
    }

    //TODO: Add the character to the list of received pressed keys if it is not already in the actual set of pressed keys.
    static public void AddKeyPressed(char c){
        if (!Received_PressedList.contains(c)){
            Received_PressedList.add(c);
        }
    }

    // No need for AddKeyHeld since we're managing HeldList based on Pressed and Released keys

    //TODO: Add the character to the list of received released keys if it is not already in the actual set of released keys.
    static public void AddKeyReleased(char c){
        if (!Received_ReleasedList.contains(c)){
            Received_ReleasedList.add(c);
        }
    }

    //TODO: return true if c is in the pressed list
    static boolean GetKeyPressed(char c){
        return PressedList.contains(c);
    }

    //TODO: return true if c is in the held list
    static boolean GetKeyHeld(char c){
        return HeldList.contains(c);
    }

    //TODO: return true if c is in the released list
    static boolean GetKeyReleased(char c){
        return ReleasedList.contains(c);
    }

    //Getters and Setters
    //TODO: Done for you!
    public static int GetMouseX() {
        return MouseX;
    }

    public static void SetMouseX(int mouseX) {
        Received_MouseX = mouseX;
    }

    public static int GetMouseY() {
        return MouseY;
    }

    public static void SetMouseY(int mouseY) {
        Received_MouseY = mouseY;
    }

    public static boolean IsMouseDown() {
        return MouseDown;
    }

    public static void SetMouseDown(boolean mousePressed) {
        Received_MouseDown = mousePressed;
    }

    public static boolean IsMouseClicked() {
        return MouseClicked;
    }

    public static void SetMouseClicked(boolean mouseClicked) {
        Received_MouseClicked = mouseClicked;
    }
}
