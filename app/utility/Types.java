package utility;

public enum Types {
    APPLICATION(1),
    PLAY_LIST(2),
    HEADER_LIST(3),
    MAIN_MENU(4),
    LEFT_MENU(5),
    SUB_MENU(6);

    Types(int val) {
        this.val = val;
    }

    public int value(){
        return val;
    }
    public String name(int val){
        for(Types x: values())
        {
            if(x.value() == val) return x.name();
        }
        return "";
    }

    private final int val;

    @Override
    public String toString() {
        return "Types{" +
                "val=" + val +
                " , " +
                "name=" + name() +
                '}';
    }
}
