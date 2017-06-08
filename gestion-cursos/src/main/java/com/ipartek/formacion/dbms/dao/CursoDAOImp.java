package com.ipartek.formacion.dbms.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public Curso create(Curso curso) {
		
		final String SQL = "INSERT INTO cursos(codCurso, nomCurso) VALUES (?,?)";
		this.template.update(SQL,curso.getCodCursos(),curso.getNomCursos());
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
		final String SQL="UPDATE  cursos SET codCurso = ? , nomCurso = ?  WHERE codCurso = ? ";
		this.template.update(SQL,curso.getCodCursos(),curso.getNomCursos(),curso.getCodCursos());
		return curso;
	}

	@Override
	public Curso getById(String codCurso) {
		Curso curso = null;
		try{
			curso = this.template.queryForObject( "SELECT codCurso, nomCurso FROM cursos WHERE codCurso = ?", new Object[] { codCurso }, new CursoMapper());
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return curso;
	}

	@Override
	public List<Curso> getAll() {
		List<Curso> cursos = null;
		try{
			cursos=this.template.query( "SELECT codCurso, nomCurso FROM cursos", new CursoMapper());
		}catch(EmptyResultDataAccessException e){
			LOGGER.trace(e.getMessage());	
		}
		return cursos;
	}

	@Override
	public void delete(String codCurso) {
		this.template.update("delete from actor where id = ?",String.valueOf(codCurso));
		
	}

}