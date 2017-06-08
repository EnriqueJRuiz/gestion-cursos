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
	public Curso getById(String codCurso) {
		return cursoDAO.getById(codCurso);
	}

	@Override
	public List<Curso> getAll() {
		return cursoDAO.getAll();
	}

	@Override
	public void delete(String codCurso) {
		cursoDAO.delete(codCurso);

	}

}
