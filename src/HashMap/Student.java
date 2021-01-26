package HashMap;

import java.util.Objects;

public class Student {
    private final String surname;
    private final String name;
    private final int avg, age, id;

    Student(String surname, String name, int avg, int age, int id)
    {
        this.surname = surname;
        this.name = name;
        this.avg = avg;
        this.age = age;
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAvg() {
        return avg;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return avg == student.avg && age == student.age && id == student.id && Objects.equals(surname, student.surname) && Objects.equals(name, student.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname: '" + surname + '\'' +
                ", name: '" + name + '\'' +
                ", avg: " + avg +
                ", age: " + age +
                ", id: " + id +
                '}';
    }
}
