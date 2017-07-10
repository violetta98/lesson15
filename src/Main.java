import java.io.*;

class TestSerial implements Serializable {
    transient String name;
    transient int age;
    public TestSerial(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void print() {
        System.out.print(age + " " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        try(ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream("temp.out")))) {
            TestSerial ts = new TestSerial("lol", 100500);
            oos.writeObject(ts);
            oos.flush();
            oos.close();

        } catch (IOException e) {
           // e.printStackTrace();
        }

        try(ObjectInputStream oin = new ObjectInputStream(new FileInputStream("temp.out"))) {
            TestSerial ts = (TestSerial) oin.readObject();
            ts.print();
        } catch(IOException | ClassNotFoundException ex) {
            // error
        }
    }
}
