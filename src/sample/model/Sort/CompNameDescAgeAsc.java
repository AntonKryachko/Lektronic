package sample.model.Sort;

import sample.model.Engineer;

import java.util.Comparator;

/**
 * @author Lektor
 */
public class CompNameDescAgeAsc implements Comparator<Engineer> {
    @Override
    public int compare(Engineer o1, Engineer o2) {
        if (o1.getName().compareTo(o2.getName()) < 0) return 1;
        else if (o1.getName().compareTo(o2.getName()) > 0) return -1;
        else if (o1.getAge() > o2.getAge()) return 1;
        else if (o1.getAge() < o2.getAge()) return -1;
        return 0;
    }
}
