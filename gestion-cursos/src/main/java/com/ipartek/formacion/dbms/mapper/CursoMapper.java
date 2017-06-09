package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Curso;

public class CursoMapper implements RowMapper<Curso>{

	@Override
	public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
		Curso curso = new Curso();
		curso.setIdProxCurso(rs.getInt("IdProxCurso"));
		curso.setCodCursos(rs.getString("CodCurso"));
		curso.setNomCursos(rs.getString("NomCurso"));
		return curso;
	}

}
