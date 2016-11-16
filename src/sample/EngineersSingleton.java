package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Engineer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by Lektor on 05.11.2016.
 */
public class EngineersSingleton {
    private static EngineersSingleton instance;
    private EngineersSingleton(){}
    public static EngineersSingleton getInstance() {
        if (instance == null)
            instance = new EngineersSingleton();
        return instance;
    }

    private ObservableList<Engineer> engineers = FXCollections.observableArrayList();
    public ObservableList<Engineer> getEngineers() {
        return engineers;
    }
    public void setEngineers(ObservableList<Engineer> engineers) {
        this.engineers = engineers;
    }


    public boolean isIDRepeat(int id){
        if (id == 0) return false;
        for(Engineer engineer: engineers){
            if (engineer.getId() == id) return true;
        }
        return false;
    }
    public void clear(){
        engineers.clear();
    }
    public boolean add(Engineer engineer){
        if (engineer == null)
            return false;
        engineers.add(engineer);
        return true;
    }
    public boolean addAll(List<Engineer> engineers){
        if (engineers == null) return false;
        this.engineers.addAll(engineers);
        return true;
    }
    public boolean setAll(List<Engineer> engineers){
        if(engineers == null) return false;
        this.engineers.setAll(engineers);
        return true;
    }
    public void removeUnderAge(int age){
        ObservableList<Engineer> list = FXCollections.observableArrayList();
        for (Engineer engineer: engineers){
            if(engineer.getAge() >= age){
                list.add(engineer);
            }
        }
        setAll(list);
    }
    public void removeUnderCategory(int category){
        ObservableList<Engineer> list = FXCollections.observableArrayList();
        list.addAll(engineers.stream().filter(engineer -> engineer.getCategory() >= category).collect(Collectors.toList()));
        setAll(list);
    }
    public int sumCategory(int category){
        int i = 0;
        for (Engineer engineer: engineers){
            if (engineer.getCategory() == category){
                i++;
            }
        }
        return i;
    }
    public Engineer maxAge(){
        Engineer engineer = null;
        int max = 0;
        for (Engineer engineer1: engineers){
            if (engineer1.getAge() > max) {
                max = engineer1.getAge();
                engineer = engineer1;
            }
        }
        return engineer;
    }
    public Engineer minAge(){
        Engineer engineer = null;
        int min = 200;
        for (Engineer engineer1: engineers){
            if (engineer1.getAge() < min){
                min = engineer1.getAge();
                engineer = engineer1;
            }
        }
        return engineer;
    }
}
