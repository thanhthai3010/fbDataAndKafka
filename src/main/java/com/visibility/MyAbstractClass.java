package com.visibility;

public abstract class MyAbstractClass<T> {
  int myVariable;

  public T execute() {
    T rs = null;
    rs = build();
    return rs;
  }

  abstract T build();
}
