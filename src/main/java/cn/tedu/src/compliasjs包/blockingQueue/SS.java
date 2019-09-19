package cn.tedu.src.compliasjs包.blockingQueue;
import lombok.Data;

@Data
public class SS implements java.lang.Comparable{
    private String name;
    private int age;

    public SS(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "SS{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o){
        SS s =(SS)o;
        return this.age - s.age;
    }

}
