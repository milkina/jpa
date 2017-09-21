package main.java.controller.filters;


import main.java.data.person.PersonHandler;
import main.java.model.person.Person;
import main.java.util.CookieUtilities;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.java.util.AllConstants.COOKIE_VALUE;
import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;



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

    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
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
        HttpSession session = request.getSession();
        //get Cookie
        if (!CookieUtilities.getCookieValue(request, COOKIE_VALUE, "").equals("")) {
            personId = CookieUtilities.getCookieValue(request, COOKIE_VALUE, "");
        }
        if (personId.equals("")) {
            return null;
        }
        PersonHandler personHandler = new PersonHandler();
        return personHandler.getPersonById(Integer.parseInt(personId));
    }

}
