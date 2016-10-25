package sample.model.Sort;

import sample.model.Engineer;

import java.util.Comparator;

/**
 * Created by Lektor on 23.10.2016.
 */
public class CompCatDescAgeAsc implements Comparator<Engineer>{
    @Override
    public int compare(Engineer o1, Engineer o2) {
        if (o1.getCategory() > o2.getCategory()) return 1;
        else if (o1.getCategory() < o2.getCategory()) return -1;
        else if (o1.getAge() > o2.getAge()) return -1;
        else if (o1.getAge() < o2.getAge()) return 1;
        else return 0;
    }
}
