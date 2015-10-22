package fr.gestionqcm.model.handler;

import java.util.ArrayList;

import fr.gestionqcm.model.bo.Test;
import fr.gestionqcm.model.dal.TestDAO;

public class TestHandler {
	
	public static void add(Test test) throws Exception
	{
		try {
			TestDAO.addTest(test);
		} catch (Exception e) {
			throw new Exception("Erreur lors de l'ajout du nouveau test");
		}
	}
	
	public static ArrayList<Test> getAll() throws Exception
	{
		try
		{
			return (ArrayList<Test>)TestDAO.getAllTests();
		}
		catch(Exception e)
		{
			throw new Exception("Problème lors de chargement des tests");
		}
	}

	public static void delete(int idTest) throws Exception {
		Test t = new Test();
		t.setTestId(idTest);
		try {
			TestDAO.deleteTest(t);
		} catch (Exception e) {
			throw new Exception("Impossible de supprimer ce test");
		}
	}

	public static Test getOne(int idTest) throws Exception {
		try {
			return TestDAO.getTest(idTest);
		} catch (Exception e) {
			throw new Exception("Erreur lors de la récupération du test");
		}
	}

	public static void update(Test test) throws Exception {
		try {
			TestDAO.updateTest(test);
		} catch (Exception e) {
			throw new Exception("Erreur lors de l'ajout du nouveau test");
		}
		
	}
}
