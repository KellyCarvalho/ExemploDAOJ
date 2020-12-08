package application;

import java.util.Date;
import java.util.List;

import domain.entities.Departamento;
import domain.entities.Vendedor;
import model.dao.DaoFactory;
import model.dao.VendedorDao;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = DaoFactory.createSellerDao();
		System.out.println("Testando o vendedor com o método findById()");
		
		Vendedor vendedor = vendedorDao.findById(3);
		
		System.out.println(vendedor.toString());
		System.out.println("Testando o vendedor com o método findByDepartamento()");
		Departamento dep = new Departamento(2,"nada");
		
		List<Vendedor> list = vendedorDao.findByDepartment(dep);
		for( Vendedor v:list) {
			System.out.println(v);
		}
		 
		System.out.println("Testando o insert");
		Vendedor vendedorNovo= new Vendedor(null,"Júlia","nada@gmail.com",new Date(),5000.00,dep);
		vendedorDao.insert(vendedorNovo);
		System.out.println("Inserido com sucesso: id "+vendedorNovo.getId());
		System.out.println(vendedorNovo.toString());
		Departamento dep2 = new Departamento(4,"nada");
		System.out.println("Testando Update");
		
		vendedor= vendedorDao.findById(1);
		vendedor.setNome("Manuela");
		vendedorDao.update(vendedor);
		System.out.println(vendedor.toString());
		
	}

}
