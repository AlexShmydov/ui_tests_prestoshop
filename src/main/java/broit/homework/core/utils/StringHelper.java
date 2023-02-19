package broit.homework.core.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringHelper {

    public Integer getOnlyNumbers(String data) {
        return Integer.valueOf(data.replaceAll("\\D+", ""));
    }
}
