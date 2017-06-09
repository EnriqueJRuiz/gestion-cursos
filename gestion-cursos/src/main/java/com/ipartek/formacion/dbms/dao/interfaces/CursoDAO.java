package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Curso;

public interface CursoDAO extends DAOSetter{
	
	public Curso create(Curso curso);
	
	public Curso update(Curso curso);
	
	public Curso getById(int idProxCurso);
	
	public List<Curso>  getAll();
	
	public List<Curso> getBy10();

	public void delete(int idProxCurso);

	

	

}
