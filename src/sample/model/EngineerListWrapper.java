package sample.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Lektor on 11.10.2016.
 */
@XmlRootElement(name = "engineers")
public class EngineerListWrapper {
    private List<Engineer> engineers;

    @XmlElement(name = "engineer")
    public List<Engineer> getEngineers(){
        return engineers;
    }
    public void setEngineers(List<Engineer> engineers){
        this.engineers = engineers;
    }
}
