package com.ipartek.formacion.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.mapper.CursoMapper;
import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

		private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
		
		@Autowired
		private CursoService cS;
		ModelAndView mav = null;
		/**
		 * Simply selects the home view to render by returning its name.
		 */
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView home(Locale locale, Model model) {
			LOGGER.info("Welcome home! The client locale is {}.", locale);
			
			mav= new ModelAndView("home");
			
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

			String formattedDate = dateFormat.format(date);

			model.addAttribute("serverTime", formattedDate);

			
			
			List<Curso> cursos = cS.getBy10();
			
			mav.addObject("listadocursos", cursos);
			LOGGER.trace("realiza getAll() de cursos de 10");
			return mav;
		}
		
		@RequestMapping(value= "login")
		public String loginPage(){
			return "login";
		}
		
		@RequestMapping(value= "/Access_Denied")
		public String accesoDenegado(ModelMap model){
			model.addAttribute("model", getPrincipal());
			return "login";
		}

		private Object getPrincipal() {
			String username = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if (principal instanceof UserDetails){
				username = ((UserDetails) principal).getUsername();
			}else{
				username = principal.toString();
			}
			
			return username;
		}
		
		@RequestMapping(value="logout")
		public String logout(HttpServletRequest request, HttpServletResponse response){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if(auth != null){
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			
			return "redirect:/login.html?logout";//se recomienda que la web logout sea la de login
			
		}
		
		@ResponseBody
		@RequestMapping(value = "/search")
		public List<Curso> getSearch(@RequestBody String search) {

			List<Curso> result = null;
			
			result = cS.getBuscador(search);
			
			return result;

		}


	}

