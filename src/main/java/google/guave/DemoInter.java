package google.guave;

public interface DemoInter {
  String CONSTANT = "CONSTANT";

  enum InnerEnum {

    E1, E2;

  }
  class InnerClass {
    int i;

    void printI() {
      System.out.println(i);
    }
  }


  interface InnerInterface {
    void performInnerAction();

  }

  void performAction();

}
