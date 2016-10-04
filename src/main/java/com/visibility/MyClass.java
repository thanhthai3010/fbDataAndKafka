package com.visibility;

public class MyClass {

  public double getValue() {
    return new MyAbstractClass<Double>() {

      @Override
      Double build() {
        this.myVariable = 4;
        return this.myVariable * 0.3;
      }
    }.execute();
  };

  public static void main(String[] args) {
    MyClass myClass = new MyClass();
    System.out.println(myClass.getValue());
  }
}
