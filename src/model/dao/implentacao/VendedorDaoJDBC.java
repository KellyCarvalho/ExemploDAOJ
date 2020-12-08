package model.dao.implentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import domain.entities.Departamento;
import domain.entities.Vendedor;
import model.dao.VendedorDao;

public class VendedorDaoJDBC implements VendedorDao {
	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn=conn;
	}

	@Override
	public void insert(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor vendedor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		
		PreparedStatement st =null;
		ResultSet rs=null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE seller.Id=? ");
					
			st.setInt(1, id);
			rs =st.executeQuery();
			if(rs.next()) {
				
				Departamento dep = instanciandoDepartamento(rs);
				Vendedor vendedor = instanciandoVendedor(rs,dep);
				
				return vendedor;
						
			}
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Vendedor instanciandoVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vendedor = new Vendedor();
		vendedor.setId(rs.getInt("Id"));
		vendedor.setNome(rs.getString("Name"));
		vendedor.setEmail(rs.getString("Email"));
		vendedor.setSalarioBase(rs.getDouble("baseSalary"));
		vendedor.setNascimento(rs.getDate("BirthDate"));
		vendedor.setDepartamento(dep);
		return vendedor;
	}

	private Departamento instanciandoDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("depName"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public List<Vendedor> findByDepartment(Departamento departamento) {
		PreparedStatement st =null;
		ResultSet rs =null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId=department.Id "
					+"WHERE DepartmentId=? "
					+"ORDER BY Name");
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List<Vendedor> listVendedor = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			while(rs.next()) {
				//verificando se o departamento já existe na lista para não instanciar mais de uma vez
				Departamento dep = map.get(rs.getInt("DepartmentId")); 
				if(dep==null) {
					dep = instanciandoDepartamento(rs);
				map.put(rs.getInt("DepartmentId"), dep);
				}				
				Vendedor vendedor = instanciandoVendedor(rs, dep);
				 listVendedor.add(vendedor);	
			}
			return listVendedor;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	
	}
	
	

}
