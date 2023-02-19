package broit.homework.core.utils;

import java.time.Duration;

import broit.homework.core.exception.PrestoShopException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class Waiter {

    public void makeDelay(Duration duration) {
        try {
            log.info("Wait until {}", duration);
            Thread.sleep(duration.toMillis());
        } catch (Exception e) {
            throw new PrestoShopException("Delay was failed with error!", e);
        }
    }
}
