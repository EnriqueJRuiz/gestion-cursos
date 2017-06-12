package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.CursoDAO;
import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;
@Service
public class CursoServiceImp implements CursoService {

	@Autowired
	private CursoDAO cursoDAO;
	
	@Override
	public Curso create(Curso curso) {
		return cursoDAO.create(curso);
	}

	@Override
	public Curso update(Curso curso) {
		return cursoDAO.update(curso);
	}

	@Override
	public Curso getById(int idProxCurso) {
		return cursoDAO.getById(idProxCurso);
	}

	@Override
	public List<Curso> getAll() {
		return cursoDAO.getAll();
	}
	
	@Override
	public List<Curso> getBy10() {
		return cursoDAO.getBy10();
	}

	@Override
	public void delete(int idProxCurso) {
		cursoDAO.delete(idProxCurso);

	}

	@Override
	public List<Curso> getBuscador(String search) {
		return cursoDAO.getBuscador(search);
	}

}
