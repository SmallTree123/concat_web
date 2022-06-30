package com.nylgsc.controller.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleUtil {

    public static boolean excludeURL(List<Object> excludes, HttpServletRequest request, HttpServletResponse response) {
        if (excludes != null && !excludes.isEmpty()) {
            String url = request.getRequestURI();
            if ("/".equals(url)) {
                return true;
            } else {
                Iterator var4 = excludes.iterator();

                Matcher m;
                do {
                    if (!var4.hasNext()) {
                        return false;
                    }

                    Object pattern = var4.next();
                    Pattern p = Pattern.compile("^"+pattern);
                    m = p.matcher(url);
                } while(!m.find());

                return true;
            }
        } else {
            return false;
        }
    }
}
