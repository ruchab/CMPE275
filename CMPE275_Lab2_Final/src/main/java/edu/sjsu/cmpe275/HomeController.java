/*
 * Controller class:HomeController 
 */

package edu.sjsu.cmpe275;

import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page. Annotation used @Controller .
 * When this class receives a request,it looks for an appropriate handler method
 *  to handle the request.
 *  
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//Hashmap to store the Homepage
	
    HashMap<String,Homepage> userRepo = new HashMap<String,Homepage>();
    
    
	/***************Handler Methods***************/
    
    
    /*
     * getUserHomepage returns an HTML that renders the given users homepage.
     * All fields other than ID are editable.
     * If the homepage of the given user ID does not exist,a 404Page is returned.
     * @param pathVariable userID,brief, model.
     * @return 1.if brief is true return the JSON format which has users full homepage.
     *  2.if brief is false return the homepage HTMl with two buttons update and delete.
     *  3.if the userID does not exist,return the 404Page.
     */
	//1.Get a User's Homepage as HTML.
    //2.Get a homepage as JSON
	@RequestMapping(value="/homepage/{userId}", method = RequestMethod.GET)
	public String getUserHomepage(@PathVariable("userId") String id,@RequestParam(required = false) final String brief,Model model){
		if(brief !=null && brief.equals("true")){
			Homepage hp = userRepo.get(id);
			if(hp!=null){
				model.addAttribute("homepage",hp.toString());
				return "homepageBrief"; //JSON format
			}
			else{
				model.addAttribute("id",id);
				return "404Page";
			}
		}
		else{
		Homepage hp = userRepo.get(id);
		if(hp!=null){
			model.addAttribute("homepage",hp);
			return "homepage";     //HTML page
		}
		else{
			model.addAttribute("id",id);
			return "404Page";
		}
		}
	}
	
	/*
	 * getCreateHomePage : returns an HTMl form with all the homepage fields which are empty and editable.
	 * This page has one Create button.When the button is clicked,the createHomepage view is returned.
	 * @param: model,add attributes to homepage of this model.
	 * @return:createHomepage 
	 */
	//3.Get the homepage creation HTML
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String getCreateHomePage(Locale locale, Model model) {
		model.addAttribute("homepage",new Homepage());
        return "createHomepage";
    }
	
	
	/*
	 * createHomepage: when create is clicked ,the homepage is created and stored 
	 * in userRepository using HTTP POST.After the page is created,it is redirected by concatenating user id .
	 * 
	 */

	//4.Create a homepage
    @RequestMapping(value = "/homepage", method = RequestMethod.POST)
    public String createHomepage(@ModelAttribute Homepage homepage, Model model) {
      /*  model.addAttribute("id", homepage.getId());
        model.addAttribute("firstname", homepage.getFirstname());
        model.addAttribute("lastname", homepage.getLastname());
        model.addAttribute("email", homepage.getEmail());
        model.addAttribute("address",homepage.getAddress());
        model.addAttribute("organization",homepage.getOrganization());
        model.addAttribute("aboutMyself",homepage.getAboutMyself());*/
        System.out.println("Before");
        printMap();
        userRepo.put(homepage.getId(), homepage);
        System.out.println("After");
        printMap();
        return "redirect:/homepage/"+homepage.getId();
    }
    
    /*updateHomepage: Allows user to make any changes to existing homepage.All the fields are editable except
     * User ID.After making the changes,the homepage is updated in the hashmap with the same key.
     * @param: homepage,pathvariable userid,model
     * @return: updated homepage.
     * redirected to same as GET /homepage/userid
     * 
     */
    //Update a Homepage
    @RequestMapping(value="/homepage/{userId}",method = RequestMethod.POST)
    public String updateHomepage(@ModelAttribute Homepage homepage,@PathVariable("userId")String id,Model model){
    	userRepo.put(homepage.getId(), homepage);
    	model.addAttribute("homepage", homepage);
    	System.out.println("Updated");
    	printMap();
    	return "redirect:/homepage/" + id;
    }
    
    /*
     * deleteUserHomepage : deletes the homepage for the given user id.
     * if the hoempage does not exist,it returns the 404Pag.
     * else deletes the homepage and redirects the user to creation page.
     * @param:userid,model
     * @return: 404Page in case of error
     * or homepage.
     */
    //5.Delete a homepage
    @RequestMapping(value="/homepage/{userId}",method = RequestMethod.DELETE)
    public @ResponseBody String deleteUserHomepage(@PathVariable ("userId") String id , Model model){
    	System.out.println("In delete");
    	
		   if(!userRepo.containsKey(id)){
			   
			   System.out.println("404");
			   model.addAttribute("id", id);
			   return  "redirect:/404Page";
		   }
		   else{
			   
			   userRepo.remove(id);
			  
			   model.addAttribute("homepage", new Homepage());
			   System.out.println("Deleted..");
			   printMap();
			   return "redirect:/homepage";
		   }
    	
    }
    
    //Method to print HashMap at any time to ensure if the data is getting added correctly at all times.
    
    public void printMap(){
    	for (String id: userRepo.keySet()){

            String key =id;
            String value = userRepo.get(id).toString();  
            System.out.println(key + " " + value);  


} 
    }
	
}
