package sample.model.Sort;

import sample.model.Engineer;

import java.util.Comparator;

/**
 * Created by Lektor on 28.09.2016.
 */
public class CompNameAscAgeDesc implements Comparator<Engineer> {
    @Override
    public int compare(Engineer o1, Engineer o2) {
        if (o1.getName().compareTo(o2.getName()) > 0) return 1;
        else if (o1.getName().compareTo(o2.getName()) < 0) return -1;
        else if (o1.getAge() > o2.getAge()) return -1;
        else if (o1.getAge() < o2.getAge()) return 1;
        else return 0;
    }
}
