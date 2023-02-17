package core.utils;

import java.time.Duration;

import core.exception.PrestoShopException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Waiter {

    public void makeDelay(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (Exception e) {
            throw new PrestoShopException("Delay was failed with error!", e);
        }
    }
}
