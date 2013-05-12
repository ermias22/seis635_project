package com.seis635.project.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Student;

public class StudentConverter implements Converter {

    // Init ---------------------------------------------------------------------------------------
    
	@EJB
	private UniversityEJB uEJB;

    // Actions ------------------------------------------------------------------------------------
    
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Convert the unique String representation of Foo to the actual Foo object.
        return uEJB.getStudentByName(value);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convert the Foo object to its unique String representation.
        return ((Student) value).getSsn();
    }

}