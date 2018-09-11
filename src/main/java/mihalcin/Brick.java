package mihalcin;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Patrik.Mihalcin on 11.09.2018
 */
public class Brick {

    @JsonProperty(required = true)
    private final String code;

    @JsonCreator
    public Brick(@JsonProperty(required = true) String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Brick{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brick)) return false;
        Brick brick = (Brick) o;
        return Objects.equals(code, brick.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
