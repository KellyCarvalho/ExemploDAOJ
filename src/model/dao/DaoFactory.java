package model.dao;

import db.DB;
import model.dao.implentacao.DepartamentoDaoJDBC;
import model.dao.implentacao.VendedorDaoJDBC;

public class DaoFactory {
	
	public static VendedorDao createSellerDao() {
		return new VendedorDaoJDBC(DB.getConnection());
	}
	
	public static DepartamentoDao createDepartamentdao() {
		return new DepartamentoDaoJDBC(DB.getConnection());
	}

}
