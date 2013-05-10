package com.seis635.project.backing;

import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
  
public class MenuBean extends AbstractBean{  
      
    public void save() {  
        addMessage("Data saved");  
    }  
      
    public void update() {  
        addMessage("Data updated");  
    }  
      
    public void delete() {  
        addMessage("Data deleted");  
    }  

}  
