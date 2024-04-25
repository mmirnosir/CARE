package cwk4;

import java.io.Serializable;

public class Dragon extends Champion implements Serializable {
    private boolean isTalking;

    public Dragon (String name, boolean isTalking){
        super(name, 7, 500);
        this.isTalking = isTalking;
    }

    public boolean isTalking() {
        return isTalking;
    }
    public String toString(){
        return super.toString() +
                "\nTalking: " + isTalking +
                "\nType: Dragon";
    }
}