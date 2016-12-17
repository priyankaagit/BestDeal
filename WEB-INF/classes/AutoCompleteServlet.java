
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author nbuser
 */
public class AutoCompleteServlet extends HttpServlet {

    private ServletContext context;
    AjaxUtility au = new AjaxUtility();
    private HashMap products = au.selectProduct();

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(true);	 
        String action = request.getParameter("action");
        String targetId = request.getParameter("searchId");
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {

                Iterator it = products.keySet().iterator();

                while (it.hasNext()) {
                    String id = (String) it.next();
                     Product product = (Product) products.get(id);

                    if ( // targetId matches first name
                         product.getName().toLowerCase().startsWith(targetId) ) {

						 /*
						 ||
                         // targetId matches last name
                         composer.getLastName().toLowerCase().startsWith(targetId) ||
                         // targetId matches full name
                         composer.getFirstName().toLowerCase().concat(" ")
                            .concat(composer.getLastName().toLowerCase()).startsWith(targetId)
						 
						 */
						 
                        sb.append("<product>");
                        sb.append("<id>" + product.getId() + "</id>");
                        sb.append("<Name>" + product.getName() + "</Name>");
                        sb.append("<retailer>" + product.getRetailer() + "</retailer>");
						sb.append("<price>" + product.getPrice() + "</price>");
						sb.append("<image>" + product.getImage() + "</image>" );
                        sb.append("</product>");
						System.out.println(sb);
                        namesAdded = true;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {

            // put the target composer in the request scope to display 
            if ((targetId != null) && products.containsKey(targetId.trim())) {
                request.setAttribute("product", products.get(targetId));
				
                context.getRequestDispatcher("/Composer").forward(request, response);
            }
        }
    }
}
