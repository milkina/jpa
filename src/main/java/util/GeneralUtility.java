package util;

import model.person.Person;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Created by Tatyana on 01.01.2016.
 */
public class GeneralUtility {
    public static final String DATE_FORMAT1 = "yyyy.MM.dd  HH:mm:ss";
    public static final String DATE_FORMAT2 = "EEE, dd MMM yyyy HH:mm:ss z";
    public static final int SEVEN_DAYS = 604800000;
    public static final int CACHED_PERIOD = SEVEN_DAYS * 2;
    public static final int THOUSAND = 1000;
    public static final int TEN_THOUSANDS = 10000;

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT1);
        return simpleDateFormat.format(date);
    }

    public void setIfModifiedSinceHeader(HttpServletRequest request,
                                         HttpServletResponse response) {
        setIfModifiedSinceHeader(request, response, 0);
    }

    public void setIfModifiedSinceHeader(HttpServletRequest request,
                                         HttpServletResponse response,
                                         long updatedDate) {
        String ifModifiedSince = request.getHeader("if-modified-since");
        if (isIfModifiedSinceHeaderExist(ifModifiedSince)) {
            long cachedDate = getCachedDate(ifModifiedSince);
            if (!isUpdated(updatedDate, cachedDate)
                    && cachedDate + CACHED_PERIOD > getNowDate()) {
                sendNotModifiedStatus(response);
                return;
            }
        }
        setCachedHeaders(response);
    }

    private boolean isUpdated(long updatedDate, long cachedDate) {
        return updatedDate != 0 && updatedDate > cachedDate;
    }

    public static boolean isIfModifiedSinceHeaderExist(String ifModifiedSince) {
        return ifModifiedSince != null && !ifModifiedSince.equals("");
    }

    public static long roundTime(long time) {
        return time / THOUSAND * THOUSAND;
    }

    public static long getTodayDate() {
        return roundTime(new Date().getTime());
    }

    public static long getCachedDate(String ifModifiedSince) {
        Calendar modifiedCal = Calendar.getInstance();
        try {
            // Format should be something like this:
            // Thu, 10 Jan 2008 09:20:50 GMT
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT2,
                    Locale.ENGLISH);
            Date modifiedDate = sdf.parse(ifModifiedSince);
            modifiedCal.setTime(modifiedDate);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return modifiedCal.getTime().getTime();
    }

    public static long getNowDate() {
        Calendar nowCal = Calendar.getInstance();
        return nowCal.getTime().getTime();
    }

    public void sendNotModifiedStatus(HttpServletResponse response) {
        try {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCachedHeaders(HttpServletResponse response) {
        long date = getTodayDate();
        //  days in seconds
        response.setHeader("Cache-Control", "max-age="
                + CACHED_PERIOD / TEN_THOUSANDS);
        //  days in milliseconds
        response.setDateHeader("Expires", date + CACHED_PERIOD);
        response.setDateHeader("Last-Modified", date);
    }

    public void setIfModifiedSinceHeader(
            HttpServletRequest request, HttpServletResponse response,
            Person person) {
        if (person == null) {
            setIfModifiedSinceHeader(request, response);
        }
    }

    public static Integer getIntegerValue(
            HttpServletRequest request, String paramName) {
        if (isEmpty(request.getParameter(paramName))) {
            return null;
        }
        String paramValue = request.getParameter(paramName).trim();
        return Integer.parseInt(paramValue);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }


    public static void createSiteMap() throws IOException {

        try {
            PrintWriter writer = new PrintWriter("sitemapNew.xml", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String decodeRussianCharacters(String s) {
        if (s == null) {
            return null;
        }
        String result = s;
        try {
            result = new String(s.getBytes("ISO8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static HttpSession getSession(boolean allowCreate) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(allowCreate);
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }

    public static String getResourceValue(Locale locale, String key, String basename) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(basename, locale);
        return GeneralUtility.decodeRussianCharacters(resourceBundle.getString(key));
    }
}
