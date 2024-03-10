class A {
    public void someMethod() {
        System.out.println("Calling someMethod from class A");
    }
}

class B extends A {
    @Override
    public void someMethod() {
        // Call the overridden method from class A
        super.someMethod();

        // Add additional functionality specific to class B
        System.out.println("Calling someMethod from class B");
    }
}

public class MyTesting {
    public static void main(String[] args) {
        B b = new B();
        b.someMethod(); // Calls someMethod from class B
    }
}
