package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Engineer;

import java.util.List;

/**
 * Created by Lektor on 05.11.2016.
 */
public class EngineersSingleton {
    private static EngineersSingleton instance;
    private EngineersSingleton(){
        engineers.addAll(
                new Engineer(48877, "Клим",13,2),
                new Engineer(57677,"Жуков",10,2),
                new Engineer(15411,"Чехов",2,1),
                new Engineer(32521,"Пучков",6,1),
                new Engineer(21344,"Поттер",4,1),
                new Engineer(12314,"Наумов",11,2),
                new Engineer(21346,"Слуцкий",9,2),
                new Engineer(41131,"Сосюра",20,3),
                new Engineer(3182,"Норбобаев",10,2),
                new Engineer(45613,"Беггинс",7,1),
                new Engineer(54613,"Петров",8,2),
                new Engineer(15431,"Степанянов",6,1),
                new Engineer(12314,"Ретушив",2,1),
                new Engineer(47531,"Авдеев",15,2),
                new Engineer(37981,"Нигородский",21,3),
                new Engineer(31333,"Луговьев",14,2),
                new Engineer(52352,"Корнедев",4,1),
                new Engineer(12948,"Дурнев",15,3),
                new Engineer(25463,"Бурев",11,2),
                new Engineer(27364,"Топор",24,3)
        );
    }
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
        for (Engineer engineer: engineers){
            if(engineer.getCategory() >= category){
                list.add(engineer);
            }
        }
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
