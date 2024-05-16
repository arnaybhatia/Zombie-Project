public class Supply {
    private final String name;
    private boolean type;//True is healing false is damage

    public Supply(boolean type){
        this.type=type;
        if(type){
            name="Health Potion";
        }
        else{
            name="Sword";
        }
    }

    public String getName() {
        return name;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
// FIGURE THIS OUT LATER
//    public void use(Supply thing){
//        if(thing.isType()){
//
//        }
//    }
}
