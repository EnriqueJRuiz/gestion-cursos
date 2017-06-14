package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.CursoDAO;
import com.ipartek.formacion.dbms.mapper.CursoMapper;
import com.ipartek.formacion.dbms.persistence.Curso;

@Repository("cursoDaoImp")
public class CursoDAOImp implements CursoDAO {

	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;
	private JdbcTemplate template;
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoDAOImp.class);
	
	
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(dataSource);
	}


	@Override
	public Curso create(Curso curso) {
		
		final String SQL = "INSERT INTO cursos(CodCurso, NomCurso) VALUES (?,?)";
		this.template.update(SQL,curso.getCodCursos(),curso.getNomCursos());
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
		final String SQL="UPDATE  cursos SET CodCurso = ? , NomCurso = ?  WHERE IdProxCurso = ? ";
		this.template.update(SQL,curso.getCodCursos(),curso.getNomCursos(),curso.getIdProxCurso());
		return curso;
	}

	@Override
	public Curso getById(int idProxCurso) {
		Curso curso = null;
		try{
			curso = this.template.queryForObject( "SELECT IdProxCurso, CodCurso, NomCurso FROM cursos WHERE IdProxCurso = ?", new Object[] { idProxCurso }, new CursoMapper());
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return curso;
	}

	@Override
	public List<Curso> getAll() {
		List<Curso> cursos = null;
		try{
			cursos=this.template.query( "SELECT IdProxCurso, CodCurso, NomCurso FROM cursos", new CursoMapper());
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return cursos;
	}

	@Override
	public List<Curso> getBy10() {
		List<Curso> cursos = null;
		try{
			cursos=this.template.query( "SELECT IdProxCurso, CodCurso, NomCurso FROM cursos ORDER BY IdProxCurso DESC LIMIT 10", new CursoMapper());
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return cursos;
	}
	
	@Override
	public void delete(int idProxCurso) {
		this.template.update("delete FROM cursos WHERE IdProxCurso = ?", new Object[] {idProxCurso});
		
	}


	@Override
	public List<Curso> getBuscador(String search) {
		List<Curso> result = null;
		StringBuilder SQL = new StringBuilder() ;
		
		SQL.append("SELECT IdProxCurso, CodCurso, NomCurso FROM cursos ");
		
		if(search != null && !search.isEmpty()){
			SQL.append("WHERE CONCAT(CodCurso, ' ', NomCurso) LIKE '%"+search+"%' ");
		}
		SQL.append("ORDER BY IdProxCurso DESC LIMIT 10 ");
		try{
			
			result=this.template.query(SQL.toString(), new CursoMapper());
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return result;

	}


	@Override
	public void cargarCSV(ArrayList<Curso> cursos) {
		
		
		
	}
	
	


}
