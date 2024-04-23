package cwk4;

public class Dragon extends Champion {
    private boolean isTalking;

    public Dragon (String name, boolean isTalking){
        super(name, 7, 500);
        this.isTalking = isTalking;
    }
}
