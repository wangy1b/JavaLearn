package IO;

import java.io.*;

public class ObjectSerializationApp {
    public static void main(String[] args) {
        ObjectOutputStream objectWriter = null;
        ObjectInputStream objectReader = null;
        try {
            objectWriter = new ObjectOutputStream(new FileOutputStream("student.dat"));
            objectWriter.writeObject(new Student(1,"John", "mayor"));
            objectWriter.writeObject(new Student(2,"Sam", "Abel"));
            objectWriter.writeObject(new Student(3,"Anita", "Motwani"));

            System.out.println("printing list of students in the database:");
            objectReader = new ObjectInputStream(new FileInputStream("student.dat"));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectReader.readObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                objectReader.close();
                objectWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static class Student implements Serializable {
        private String firstName;
        private String lastName;
        private int id;

        public Student(int id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public String toString(){
            return ("ID:" + id + " " + firstName + " " + lastName);
        }
    }
}
