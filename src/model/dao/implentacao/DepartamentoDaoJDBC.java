package model.dao.implentacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import domain.entities.Departamento;
import domain.entities.Vendedor;
import model.dao.DepartamentoDao;

public class DepartamentoDaoJDBC implements DepartamentoDao{
	
	private Connection conn;
	
	

	public DepartamentoDaoJDBC(Connection conn) {
	
		this.conn = conn;
	}

	@Override
	public void insert(Departamento departamento) {
PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO department "
					+"(Name)"
					+"VALUES "
					+"(?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, departamento.getNome());
			
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					departamento.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Nenhuma linha alterada");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			
		}
		
	}

	@Override
	public void update(Departamento departamento) {
PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE  department "
					+"SET Name=?"
					+"WHERE Id=? ",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, departamento.getNome());
			
			st.setInt(2, departamento.getId());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					departamento.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Nenhuma linha alterada");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			
		}
		
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st=null;
		try {
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ? ");
			st.setInt(1, id);
			st.executeUpdate();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Departamento findById(Integer id) {
		PreparedStatement st =null;
		ResultSet rs=null;
		
		try {
			
			st = conn.prepareStatement(
					"SELECT * from department WHERE department.Id=? ");
					
			st.setInt(1, id);
			rs =st.executeQuery();
			if(rs.next()) {
				
				Departamento dep = new Departamento();
				
				dep.setId(rs.getInt("Id"));
				dep.setNome(rs.getString("Name"));
				
				
				return dep;
						
			}
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
