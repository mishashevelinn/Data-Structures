package HashMap;

public class HashTableData {
    Student student;
    private String key;


    public HashTableData(Student student){
        this.student = student;
        key = student.getSurname();

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }




    public Student getStudent()
    {
        return student;
    }

    @Override
    public String toString() {
        return student.toString();
    }
}
