package models.generator;

public class MyGeneratorId {
    public static long library = 1;
    public static long book = 1;
    public static  long reader = 1;
    public  static long idForLibrary(){
        return library++;
    }
    public static Long idForBook(){
        return book++;
    }
    public static long idForReader(){
        return reader++;
    }
}
