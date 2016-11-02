package sample.model.Sort;

import sample.model.Engineer;

import java.util.Comparator;

/**
 * Created by Lektor on 26.10.2016.
 */
public class CompCatAscAgeDescNameAsc implements Comparator<Engineer> {
    @Override
    public int compare(Engineer en1, Engineer en2) {
        if ((en1.getAge() < en2.getAge())||
                (en1.getCategory() > en2.getCategory())  ||
                (en1.getName().compareTo(en2.getName()) > 0)) return 1;
        else
            if ((en1.getCategory() < en2.getCategory()) ||
                    (en1.getAge() < en2.getAge()) ||
                    (en1.getName().compareTo(en2.getName()) < 0)) return -1;
        else if (en1.getAge() > en2.getAge()) return -1;
        else if (en1.getAge() < en2.getAge()) return 1;
        else if (en1.getName().compareTo(en2.getName()) > 0) return 1;
        else if (en1.getName().compareTo(en2.getName()) < 0) return -1;
        else return 0;
    }
}
