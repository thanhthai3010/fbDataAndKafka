package google.guave;

public class MainCache {


  public static void main(String[] args) throws Exception {

    Animal animal = Animal.cache.get("monkey");

    System.out.println(animal.getAnimalName());

  }
}
