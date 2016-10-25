package sample.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Lektor on 25.09.2016.
 */
public class Engineer implements Comparable<Engineer>{
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty age;
    private IntegerProperty category;
    private final String INFO_ENG = "%d, %s, %d, %d";

    public int getId() {return id.get();}
    public String getName() {return name.get();}
    public int getAge() {return age.get();}
    public int getCategory() {return category.get();}

    public IntegerProperty idProperty(){return id;}
    public StringProperty nameProperty(){return name;}
    public IntegerProperty ageProperty(){return age;}
    public IntegerProperty categoryProperty(){return category;}

    public void setId(int id) {this.id.set(id);}
    public void setName(String name) {this.name.set(name);}
    public void setAge(int age) {this.age.set(age);}
    public void setCategory(int category) {this.category.set(category);}

    public Engineer() {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.age = new SimpleIntegerProperty(0);
        this.category = new SimpleIntegerProperty(0);
    }
    public Engineer(int id, String name, int age, int category) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.category = new SimpleIntegerProperty(category);
    }
    @Override
    public int compareTo(Engineer e) {
        if (id.getValue() > e.getId()) return 1;
        else if (id.getValue() < e.getId()) return -1;
        else return 0;
    }
    @Override
    public boolean equals(Object o) {
        if (getClass() != o) return false;
        if (this == o) return true;
        if (!(o instanceof Engineer)) return false;
        Engineer engineer = (Engineer) o;
        return getId() == engineer.getId();
    }
    @Override
    public int hashCode() {return getId();}
    @Override
    public String toString(){return String.format(INFO_ENG, id.get(), name.get(), age.get(), category.get());}
}
