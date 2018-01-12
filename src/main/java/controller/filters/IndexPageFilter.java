package controller.filters;


import data.person.PersonHandler;
import model.person.Person;
import util.CookieUtilities;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.AllConstants.COOKIE_VALUE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;


/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: 19.01.2010
 * Time: 11:14:20
 * To change this template use File | Settings | File Templates.
 */
public class IndexPageFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        try {
            start(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }

    public void destroy() {

    }

    private void start(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if (session == null) {
            session = request.getSession(true);
        }
        if (session.getAttribute(PERSON_ATTRIBUTE) == null) {
            Person person = getPerson(request);
            if (person != null) {
                session.setAttribute(PERSON_ATTRIBUTE, person);
            }
        }
    }

    public Person getPerson(HttpServletRequest request) throws Exception {
        String personId = "";
        //get Cookie
        if (!CookieUtilities.getCookieValue(request, COOKIE_VALUE, "")
                .equals("")) {
            personId =
                    CookieUtilities.getCookieValue(request, COOKIE_VALUE, "");
        }
        if (personId.equals("")) {
            return null;
        }
        PersonHandler personHandler = new PersonHandler();
        return personHandler.getPersonById(Integer.parseInt(personId));
    }
}
