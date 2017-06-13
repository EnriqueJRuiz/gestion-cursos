package com.ipartek.formacion.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipartek.formacion.controller.pojo.Mensaje;
import com.ipartek.formacion.controller.pojo.MensajeType;
import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

@Controller
@RequestMapping(value = "/cursos")
public class CursoController {
	@Autowired
	private ServletContext servletContext;
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);
	@Autowired
	private CursoService cS;
	/*@Resource(name="colorValidator")
	private Validator validator = null;*/
	ModelAndView mav = null;
	
	@InitBinder("curso")
	public void initBinder(WebDataBinder binder, Locale locale) {
		//binder.addValidators(validator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(){
		mav= new ModelAndView("cursos");
		
		List<Curso> cursos = cS.getAll();
		
		mav.addObject("listadocursos", cursos);
		LOGGER.trace("realiza getAll() de cursos");
		return mav;
	}
	
	@RequestMapping(value="/{id}")
	public ModelAndView getById(@PathVariable("id") int id){
		mav= new ModelAndView("curso");
		mav.addObject("curso",cS.getById(id));
		LOGGER.trace("realiza getById de cursos");
		return mav;
	}
	
	@RequestMapping(value="/deleteCurso/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCurso(@PathVariable("id") int id, RedirectAttributes redirectMap){
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		Integer.toString(id);
		
		destino = "redirect:/cursos";
		mav= new ModelAndView(destino);
		
		try {	
			cS.delete(id);
			txt = "El curso se ha borrado correctamente.";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
		} catch (Exception e) {
			LOGGER.info("Se ha lanzadado una excepcion Delete. " + e.toString());
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			txt = "Ha habido problemas al borrar el curso.";
		} 
		mensaje.setMsg(txt);
		redirectMap.addFlashAttribute("mensaje", mensaje);
		return mav;
	}
	
	@RequestMapping(value="/addCurso", method = RequestMethod.GET)
	public ModelAndView addColor(Model model){
		model.addAttribute("curso", new Curso());
		mav= new ModelAndView("curso");
		return mav;
	}
	
	
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCurso(@ModelAttribute("curso")@Valid Curso curso,BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectMap) throws IOException {
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		if (bindingResult.hasErrors()) {
			LOGGER.info("Color tiene errores");
			destino = "curso";
			txt = "Los datos de formulario contienen errores";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
		}else{
			destino = "redirect:/cursos";
			if(cS.getById(curso.getIdProxCurso()) != null){
				try {
					LOGGER.info(curso.toString());
					cS.update(curso);
					txt = "El curso se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion update. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la actualización.";
				}
			}else{
				try {
					LOGGER.info(curso.toString());					
					cS.create(curso);
					txt = "El curso se ha creado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion create. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas al crear.";
				}
			}
			mensaje.setMsg(txt);
			redirectMap.addFlashAttribute("mensaje", mensaje);
		}
		return destino;	
	}
	
	
	@RequestMapping(value="/cargarCSV", method = RequestMethod.GET)
	public ModelAndView cargarCSV() throws IOException{
		
		final String SEPARATOR=";";
		
		BufferedReader br = null;
	      
	      try {
	         
	         br = new BufferedReader(new FileReader("files/cursos.csv"));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(SEPARATOR);
	            System.out.println(Arrays.toString(fields));
	            
	            fields = removeTrailingQuotes(fields);
	            System.out.println(Arrays.toString(fields));
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	         
	      } finally {
	         if (null!=br) {
	            br.close();
	         }
	      }
	 
		mav= new ModelAndView("redirect:/cursos");
		return mav;
	}
	 private static String[] removeTrailingQuotes(String[] fields) {
		 
			final String QUOTE="\"";
	      String result[] = new String[fields.length];

	      for (int i=0;i<result.length;i++){
	         result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
	      }
	      return result;
	   }
	
}
