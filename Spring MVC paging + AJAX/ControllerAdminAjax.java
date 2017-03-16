package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BaiViet;
import model.ModelBaiViet;



/**
 * Servlet implementation class ControllerAdminAjax
 */
//@WebServlet("/ControllerAdminAjax")
public class ControllerAdminAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ModelBaiViet modelBV = new ModelBaiViet();
		int	idBV = Integer.parseInt(request.getParameter("aid"));
		BaiViet objBV = modelBV.getObjBV(idBV);
		if (objBV.isActive()) {
			objBV.setActive(false);
			response.getWriter().print("<a href=\"javascript:void(0)\" onclick=\"return setActive(" + objBV.getId_baiviet()
					+ ")\" title=\"Kích hoạt\">");
			response.getWriter().print("<img src='" + request.getContextPath() + "/templates/admin/images/minus-circle.gif'" + "/>");
			response.getWriter().print("</a>");
		} else {
			objBV.setActive(true);
			response.getWriter().print("<a href=\"javascript:void(0)\" onclick=\"return setActive(" + objBV.getId_baiviet()
					+ ")\" title=\"Kích hoạt\">");
			response.getWriter().println("<img src='" + request.getContextPath() + "/templates/admin/images/tick-circle.gif'" + "/>");
			response.getWriter().print("</a>");
		}
		modelBV.editBV(objBV);
	}

}
