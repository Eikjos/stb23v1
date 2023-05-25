package fr.univrouen.stb23v1.entities;

public enum Gender {

    M("M."),
    Miss("Miss"),
    Mme("Mme"),
    Mr("Mr"),
    Mrs("Mrs");
    private String title;
    Gender(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public static Gender titleToGender(String title) {
        Gender gender = Gender.M;
        if (title == null) {
            return null;
        }
        switch (title) {
            case "M.":
                gender = Gender.M;
                break;
            case "Miss":
                gender = Gender.Miss;
                break;
            case "Mme":
                gender = Gender.Mme;
                break;
            case "Mr":
                gender = Gender.Mr;
                break;
            case "Mrs":
                gender = Gender.Mrs;
                break;
        }
        return gender;
    }
}
