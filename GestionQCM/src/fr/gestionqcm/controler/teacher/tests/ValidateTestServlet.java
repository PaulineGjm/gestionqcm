package fr.gestionqcm.controler.teacher.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.gestionqcm.model.bo.Section;
import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.handler.TestHandler;

/**
 * Servlet implementation class ValidateTestServlet
 */
public class ValidateTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String duration = request.getParameter("duration");
		String beingacquired = request.getParameter("beingacquired");
		String acquired = request.getParameter("acquired");
		String action = request.getParameter("action");
		Test test = new Test();
		
		Boolean update = action.equals("update");
		
		if(update)
			test = (Test)request.getSession().getAttribute("testToUpdate");
		
		test.setName(name);
		test.setTestDuration(Integer.valueOf(duration));
		test.setCurrentThreshold(Integer.valueOf(beingacquired));
		test.setAcquisitionThreshold(Integer.valueOf(acquired));
		
		// Création d'un tableau avec les sections à associer au test
		// à partir des paramètres
		Map<String, String[]> parameters = request.getParameterMap();
		TreeMap<String, Section> sections = new TreeMap<String, Section>();
		String indexSection = null;
		for (Map.Entry<String, String[]> parameter : parameters.entrySet())
		{
			String key = parameter.getKey();
		    if(key.contains("theme_") || key.contains("nbquestion_"))
		    {
		    	String[] tmp = parameter.getKey().split("_");
				indexSection = tmp[1];
				
				String[] values = parameter.getValue();
				
		    	if(!sections.containsKey(indexSection))
		    	{
		    		Section s = new Section();
		    		s.setIdTest(test.getTestId());
		    		sections.put(indexSection, s);
		    	}
		    	
		    	if(key.contains("theme_"))
		    	{
		    		sections.get(indexSection).setIdTheme(Integer.valueOf(values[0]));
		    	}
		    	else if(key.contains("nbquestion_"))
		    	{
		    		sections.get(indexSection).setNbQuestions(Integer.valueOf(values[0]));
		    	}	    	
		    }
		}
		
		ArrayList<Section> listSections = new ArrayList<Section>();
		// parcours des sections afin de les sauvegarder en DB
		for (Map.Entry<String, Section> section : sections.entrySet())
		{
		    Section s = section.getValue();
		    listSections.add(s);
//		    try {
//				SectionHandler.add(s);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		test.setSections(listSections);
		
		
		
		try {
			if(update)
				TestHandler.update(test);
			else
				TestHandler.add(test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/trainee/test/listTests");
	}

}
