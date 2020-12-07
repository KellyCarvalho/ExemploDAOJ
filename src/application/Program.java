package application;

import domain.entities.Vendedor;
import model.dao.DaoFactory;
import model.dao.VendedorDao;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = DaoFactory.createSellerDao();
		
		Vendedor vendedor = vendedorDao.findById(3);
		
		System.out.println(vendedor.toString());
	}

}
