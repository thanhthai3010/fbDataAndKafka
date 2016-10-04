package google.guave;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class Animal {

  static LoadingCache<String, Animal> cache = CacheBuilder.newBuilder().maximumSize(10)
      .expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<String, Animal>() {

        @Override
        public Animal load(String key) throws Exception {
          return new Animal(key);
        }
      });

  private String animalName;

  public String getAnimalName() {
    return animalName;
  }

  public void setAnimalName(String animalName) {
    this.animalName = animalName;
  }

  public Animal() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Animal(String animalName) {
    super();
    this.animalName = animalName;
  }

  @Override
  public String toString() {
    return "Animal " + this.animalName;
  }

}
