package mihalcin;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Patrik.Mihalcin on 11.09.2018
 */
public class House {

    @JsonProperty(required = true)
    private List<Brick> bricks = new LinkedList<>();
    @JsonProperty(required = true)
    private Integer houseNumber;

    @JsonCreator
    public House(@JsonProperty(value = "bricks", required = true) List<Brick> bricks,
                 @JsonProperty(value = "houseNumber", required = true) Integer houseNumber) {
        this.bricks.addAll(bricks);
        this.houseNumber = houseNumber;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }
}
