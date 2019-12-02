package io.github.qyvlik.siuone.common.wapper;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class SqlFixInjectionUtils {

    public static String fixOrderBy(String orderBy) {
        if (StringUtils.isBlank(orderBy)) {
            return orderBy;
        }
        String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                + "(\\b(select|update|and|or|delete|insert|trancate|char|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(orderBy).find()) {
            return "";
        }
        return orderBy;
    }
}
