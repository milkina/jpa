package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 09.10.2012
 * Time: 22:46:26
 * To change this template use File | Settings | File Templates.
 */
public class ServletUtilities {
    public static String getMD5(String str) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(str.getBytes(), 0, str.length());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new BigInteger(1, m.digest()).toString();
    }

    public static String filter(String input) {
        if (!hasSpecialChars(input)) {
            return input;
        }
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                //     case '&': filtered.append("&amp;"); break;
                //   case '\n': filtered.append("<BR>"); break;
                default:
                    filtered.append(c);
            }
        }
        return filtered.toString();
    }

    public static boolean hasSpecialChars(String input) {
        boolean flag = false;
        if (input != null && input.length() > 0) {
            char c;
            for (int i = 0; i < input.length(); i++) {
                c = input.charAt(i);
                switch (c) {
                    case '<':
                    case '>':
                    case '"':
                    case '&':
                    case '\n':
                        flag = true;
                        break;
                    default:
                        flag = false;
                }
            }
        }
        return flag;
    }

    public static String fixTinyMceIssue(String input) {
        if (input != null
                && (input.contains("&lt;") || input.contains("&gt;"))) {
            String result = input.replaceAll("&lt;", "&amp;lt;");
            return result.replaceAll("&gt;", "&amp;gt;");
        }
        return input;
    }
}
