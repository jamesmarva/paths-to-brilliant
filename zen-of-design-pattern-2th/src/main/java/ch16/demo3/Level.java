package ch16.demo3;

import java.util.Objects;

/**
 *
 * @author Brilliant James Jamesmarva1993@gmail.com 2020-12-06 13:37
 **/
public class Level {

    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Level level1 = (Level) o;
        return level == level1.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level);
    }
}
