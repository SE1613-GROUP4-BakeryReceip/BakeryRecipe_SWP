/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bakeryRecipe.controller;

import bakeryRecipe.recipe_tbl.Recipe_tblDAO;
import bakeryRecipe.recipe_tbl.Recipe_tblDTO;
import bakeryRecipe.utils.AppContants;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThongNT
 */
@WebServlet(name = "SearchAllRecipeController", urlPatterns = {"/SearchAllRecipeController"})
public class SearchAllRecipeController extends HttpServlet {
//    private final String HOME_PAGE = "index.jsp";
//    private final String SEARCH_RESULT_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        /**
         * Get site map (Copy this for all controller)
         */
        ServletContext context = getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        // End get site map

        // Mapping url         
        String url = siteMaps.getProperty(AppContants.SearchAllRecipesFeature.HOME_PAGE);

        String searchValue = request.getParameter("txtSearchValue");

        try {
            if (!searchValue.trim().isEmpty()) {
                //1. Call DAO
                Recipe_tblDAO dao = new Recipe_tblDAO();

                //2. Process result
                List<Recipe_tblDTO> result = dao.searchAllRecipe(searchValue);
                List<Recipe_tblDTO> resultTop9 = dao.searchAllRecipePaging9(searchValue, 0);

                //3. setAttribute to request
                request.setAttribute("SEARCH_RESULT", result);
                request.setAttribute("SEARCH_RESULT_TOP9", resultTop9);
            }

            url = siteMaps.getProperty(AppContants.SearchAllRecipesFeature.SEARCH_RESULT_PAGE);
        } catch (SQLException ex) {
            log("SearchAllRecipe Controller _ SQL " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
