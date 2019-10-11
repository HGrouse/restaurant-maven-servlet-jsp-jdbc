package by.itacademy.restaurant.configuration;

public enum VacationType {
    VACATION (1),
    SOCIAL_LEAVE (2),
    SICK_LEAVE (3);

    private int type;

    VacationType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }
}
