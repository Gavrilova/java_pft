package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("World");
    hello("user");
    hello("Irina");

    double l = 5;
    System.out.println("Area of the square with size " + l + " = " + area(l));

    double a = 4;
    double b = 7;
    System.out.println("Area of the rectangle with sizes " + a + " and " + b + " = " + area(a, b));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double len) {
    return len * len;
  }

  public static double area(double a, double b) {
    return a * b;
  }

}