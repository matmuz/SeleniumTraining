package utils;

public class DataConstants {

    public static final String FIRSTNAME = "Arthur";
    public static final String LASTNAME = "Morgan";
    public static final String EMAIL = "test@test.com";
    public static final String AGE = "26";
    public static final String PASSWORD = "test123";

    public static final String FILE_PATH = "src/test/resources/docTestPDFFill.pdf";

    public static String getFullName() {
        return FIRSTNAME + " " + LASTNAME;
    }
}
