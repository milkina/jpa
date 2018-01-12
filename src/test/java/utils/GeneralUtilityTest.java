package utils;

import util.GeneralUtility;
import org.mockito.verification.VerificationMode;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


/**
 * Created by Tatyana on 03.01.2016.
 */

public class GeneralUtilityTest {
    private long updatedDate;
    private String ifModifiedSince;
    private boolean sendNotModifiedStatus;
    private boolean setCachedHeaders;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private GeneralUtility generalUtility;

    private static Date today;
    private static Date yesterday;
    private static Date fourDaysBeforeToday;
    static {
        setParameters();
    }


    @DataProvider
    public static Object[][] data() {
        return new Object[][]
                {{0, null, false, true},
                 {0, formatDate(today), true, false},
                 {0, formatDate(getCachedPeriod()),false,true},
                 {today.getTime(), null, false, true},
                 {yesterday.getTime(), formatDate(today), true, false},
                 {yesterday.getTime(), formatDate(fourDaysBeforeToday), false, true},
                 {0, "78", false, true},
                 {yesterday.getTime(), "78", false, true}};
    }


    @Test(dataProvider = "data")
    public void setIfModifiedSinceHeaderTest(long updatedDate, String ifModifiedSince, boolean sendNotModifiedStatus, boolean setCachedHeaders) throws IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        generalUtility = new GeneralUtility();

        when(request.getHeader("if-modified-since")).thenReturn(ifModifiedSince);
        generalUtility.setIfModifiedSinceHeader(request, response, updatedDate);

        VerificationMode verifyStatusMode = sendNotModifiedStatus ? atLeastOnce() : never();
        VerificationMode verifyCacheMode = setCachedHeaders ? atLeastOnce() : never();

        verify(response, verifyStatusMode).sendError(HttpServletResponse.SC_NOT_MODIFIED);
        verify(response, verifyCacheMode).setHeader(anyString(), anyString());
    }

    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(GeneralUtility.DATE_FORMAT2, Locale.ENGLISH);
        return sdf.format(date);
    }

    private static void setParameters(){
       Calendar calendar = Calendar.getInstance();
       today = calendar.getTime();

       calendar.add(Calendar.DAY_OF_YEAR,-1);
       yesterday = calendar.getTime();

       calendar.add(Calendar.DAY_OF_YEAR,-3);
       fourDaysBeforeToday = calendar.getTime();
    }

    private static Date getCachedPeriod(){
        return new Date(GeneralUtility.CACHED_PERIOD);
    }
}
