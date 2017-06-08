package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;

public class Curso implements Serializable, Comparable<Curso>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String codCursos;
	private String nomCursos;
	
	

	public Curso() {
		super();
		this.codCursos = "";
		this.nomCursos = "";
	}

	public String getCodCursos() {
		return codCursos;
	}



	public void setCodCursos(String codCursos) {
		this.codCursos = codCursos;
	}



	public String getNomCursos() {
		return nomCursos;
	}



	public void setNomCursos(String nomCursos) {
		this.nomCursos = nomCursos;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCursos == null) ? 0 : codCursos.hashCode());
		result = prime * result + ((nomCursos == null) ? 0 : nomCursos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Curso) {
			Curso curso = (Curso) obj;
			if (this.codCursos == curso.getCodCursos()) {
				iguales = true;
			}
		}
		return iguales;
	}

	@Override
	public int compareTo(Curso o) {
		return this.getNomCursos().compareToIgnoreCase(o.getNomCursos());
		
	}

}
