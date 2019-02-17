package controller.filters;


import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import spring.services.person.PersonService;
import util.CookieUtilities;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
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
@Service
public class IndexPageFilter implements Filter {
    @Autowired
    private PersonService personService;

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (personService == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            personService = webApplicationContext.getBean(PersonService.class);
        }
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
        if (!CookieUtilities.getCookieValue(request, COOKIE_VALUE, "")
                .equals("")) {
            personId =
                    CookieUtilities.getCookieValue(request, COOKIE_VALUE, "");
        }
        if (personId.equals("")) {
            return null;
        }
        return personService.getPerson(Integer.parseInt(personId));
    }
}
