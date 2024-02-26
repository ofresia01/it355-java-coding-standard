public class DCL00J {
  private static final DCL00J rule = new DCL00J();

  /*
   * Non-compliant code - Intraclass cycle
   * This noncompliant code example contains an intraclass initialization cycle:
   */
  private class Cycle {
    private final int balance;
    private static final Cycle c = rule.new Cycle();
    private static final int deposit = (int) (Math.random() * 100); // Random deposit

    public Cycle() {
      balance = deposit - 10; // Subtract processing fee
    }
  }

  /*
   * Compliant code - intraclass cycle
   * This compliant solution changes the initialization order of the class Cycle
   * so that the fields are initialized without creating any dependency cycles.
   * Specifically, the initialization of c is placed lexically after the
   * initialization of deposit so that it occurs temporally after deposit is fully
   * initialized.
   */
  private class CycleFixed {
    private final int balance;
    private static final int deposit = (int) (Math.random() * 100); // Random deposit
    private static final CycleFixed c = rule.new CycleFixed(); // Inserted after initialization of required fields

    public CycleFixed() {
      balance = deposit - 10; // Subtract processing fee
    }
  }

  /*
   * Non-copliant code - interclass cycle.
   * This noncompliant code example declares two classes with static variables
   * whose values depend on each other.
   * The cycle is obvious when the classes are seen together (as here) but is easy
   * to miss when viewing the classes separately.
   */
  class A {
    public static final int a = B.b + 1;
    // ...
  }

  class B {
    public static final int b = A.a + 1;
    // ...
  }

  /*
   * Compliant code - interclass cycle
   * This compliant solution breaks the interclass cycle by eliminating the
   * dependency of C on D.
   */
  class C {
    public static final int c = 2;
    // ...
  }

  class D {
    public static final int d = C.c + 1;
    // ...
  }

  /*
   * Noncompliant code example.
   * The programmer in this noncompliant code example attempts to initialize a
   * static variable in one class using a static method in a second class, but
   * that method in turn relies on a static method in the first class.
   */
  class E {
    public static int a = F.f();

    public static int g() {
      return 1;
    }
  }

  class F {
    public static int f() {
      return E.g();
    }
  }

  /*
   * Compliant code example.
   * This compliant solution moves the i() method into class I, breaking the
   * cycle.
   */
  class H {
    public static int h = I.i();
  }

  class I {
    public static int i() {
      return I.j();
    }

    public static int j() {
      return 1;
    }
  }
}