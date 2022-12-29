package com.training.fa.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

import com.training.fa.DTO.request.FormRegister;
import com.training.fa.DTO.request.LoginRequest;
import com.training.fa.DTO.response.GooglePojo;
import com.training.fa.model.ERole;
import com.training.fa.model.Role;
import com.training.fa.model.User;
import com.training.fa.repository.UserRepository;
import com.training.fa.service.UserDetailServicelmpl;
import com.training.fa.service.UserService;
import com.training.fa.uitils.GoogleUtils;
import com.training.fa.userprinciple.UserDetaillmpl;
import com.training.fa.repository.RoleRepository;

@Controller
@RequestMapping("/")
public class Authencation {
	 @Autowired
	  private GoogleUtils googleUtils;

	@Autowired
	private AuthenticationManager AuthenticationManager; // from webconfig == @Bean

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;// from webconfig == @Bean
	
	@Autowired
	private UserDetailServicelmpl userDetailServicelmpl;
	
	@Autowired
	private UserService userService;

	@GetMapping("login")
	public String homeLogin(Model model) {

		return "admin/authencation/login";
	}

	

	@GetMapping("register")
	public String homeRegister(Model model) {

		return "admin/authencation/register";
	}

	@PostMapping("register")
	public String registerUser(Model model, @RequestParam("username") String username,
			@RequestParam("email") String email, @RequestParam("password") String password)

	{
		FormRegister formRegister = new FormRegister(username, email, password, null);
		// lay username tu form
		if (userRepository.existsByUsername(formRegister.getUsername())) {
			model.addAttribute("message", "User da ton tai!");
			return "admin/authencation/register";

		}

		if (userRepository.existsByEmail(formRegister.getEmail())) {
			model.addAttribute("message", "email da ton tai!");
			return "admin/authencation/register";

		}

		// Tao 1 Account cho user
		User user = new User(formRegister.getUsername(), formRegister.getEmail(),
				passwordEncoder.encode(formRegister.getPasswork()));

		// lay value(role) khi sigupFrom
		Set<String> strRole = formRegister.getRole();

		// Thiet lap roles map den Role de thuc hien cac funtion add remove inser...
		Set<Role> roles = new HashSet<>();

		if (strRole == null) {
			Role sig_default_role = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("error")); // return exception
			roles.add(sig_default_role);

		} else {

			strRole.forEach(role -> { // duyet qua tat ca-for
				switch (role) {
				case "ROLE_ADMIN":
					Role sig_admin_role = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("error")); // return exception
					roles.add(sig_admin_role);

					break;

				case "ROLE_PM":
					Role sig_pm_role = roleRepository.findByName(ERole.ROLE_PM)
							.orElseThrow(() -> new RuntimeException("error")); // return exception
					roles.add(sig_pm_role);

					break;

				}
			});

		}
		// luu lai gia tri role trong user

		user.setRoles(roles);
		User userSaved = userRepository.save(user);
		if (userSaved != null) {
			model.addAttribute("Register Suceesfull", userSaved);

			return "redirect:/login";
		}
		return "admin/authencation/register";

	}

	@GetMapping("logoutSuccessful")
	public String logoutSuccess(Principal principal, HttpServletRequest request) {
		
        request.getSession().invalidate();
		return "redirect:/login";
	}

	@GetMapping("login-success")
    public String loginSucess(Model model, HttpServletRequest request, Authentication authentication) {
        UserDetaillmpl userDetail = (UserDetaillmpl) authentication.getPrincipal();
        request.getSession().setAttribute("userDetail", userDetail);
      
        System.out.println(userDetail.toString());
        
        if (request.isUserInRole("USER") && request.getSession().getAttribute("cartPurchuse") != null) {
        	
            return "redirect:/home/checkout";
            
        }
        
        if (request.isUserInRole("USER") && request.getSession().getAttribute("cartProduct") != null) {
        	
            return "redirect:/home/checkout";
            
        } else if (request.isUserInRole("USER")) {
        
            return "redirect:/home";
        }else {
          
            return "redirect:/admin/";
        }



   }

	

	 @GetMapping("oauth2/login/success")
	  public String oauth2Success(OAuth2AuthenticationToken oauth,Model model, HttpServletRequest request) {
		 
		 
		 String email = oauth.getPrincipal().getAttribute("email");
		 String name = oauth.getPrincipal().getAttribute("family_name");
		 name += "gmail";
		 String password = "123";
		 FormRegister formRegister = new FormRegister(name, email,password, null);
			
		//
		 try {
			if (userRepository.existsByUsername(formRegister.getUsername()) && userRepository.existsByEmail(formRegister.getEmail())) {
				model.addAttribute("message", "User da ton tai!");
				
				
				Authentication authentication = AuthenticationManager
						.authenticate((Authentication) new UsernamePasswordAuthenticationToken(formRegister.getUsername(),
								formRegister.getPasswork()));
				
				System.out.println("gmail da login 1 lann");

				// Neu ko xay ra exception tuc la hople
				// Set Thong Tn authentication vao Security Context
				SecurityContextHolder.getContext().setAuthentication(authentication);
				return "redirect:/login-success";
				
			}else {
				
			User user = new User(formRegister.getUsername(), formRegister.getEmail(),
					passwordEncoder.encode(formRegister.getPasswork()));

			// lay value(role) khi sigupFrom
			Set<String> strRole = formRegister.getRole();

			// Thiet lap roles map den Role de thuc hien cac funtion add remove inser...
			Set<Role> roles = new HashSet<>();

			if (strRole == null) {
				Role sig_default_role = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("error")); // return exception
				roles.add(sig_default_role);

			} else {

				strRole.forEach(role -> { // duyet qua tat ca-for
					switch (role) {
					case "ROLE_ADMIN":
						Role sig_admin_role = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("error")); // return exception
						roles.add(sig_admin_role);

						break;

					case "ROLE_PM":
						Role sig_pm_role = roleRepository.findByName(ERole.ROLE_PM)
								.orElseThrow(() -> new RuntimeException("error")); // return exception
						roles.add(sig_pm_role);

						break;

					}
				});

			}
			// luu lai gia tri role trong user
			user.setRoles(roles);
			User userSaved = userRepository.save(user);
			
			if (userSaved != null) {//save sucess user		
				
				UserDetails userdetail  =  userDetailServicelmpl.loadUserByUsername(userSaved.getUsername());
				
				Authentication authentication = AuthenticationManager
						.authenticate((Authentication) new UsernamePasswordAuthenticationToken(userdetail.getUsername(),
								formRegister.getPasswork()));
				
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println("lan dau tien ");
				return "redirect:/login-success";
				
			}
		}
			
			return "redirect:/login-success";
		
	 }catch(Exception e) {
		 System.out.println("gmail loi ");
			return "redirect:/login";
		
	 }
		
		
	  }
	
	 @GetMapping("/oauth2/login/form/error")
	  public String oauth2Successs(OAuth2AuthenticationToken oauth) {
		 System.out.println("errro");
		 String email = oauth.getPrincipal().getAttribute("email");
		
		 System.out.println(email);
	    
	    return "redirect:/login";
	  }
	
	 @PostMapping("update-password")
     public String updatePassword(Model mode, @RequestParam("id") Long id,
             @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
         User user = userService.getUser(id);
         user.setPasswork(passwordEncoder.encode(password));
         userService.updateUser(user);
         redirectAttributes.addFlashAttribute("success", "Change password successfully!");
         return "redirect:/admin/";
     }
	
	public static String getBody(String body) {
		return body + ".jsp";
	}

}
