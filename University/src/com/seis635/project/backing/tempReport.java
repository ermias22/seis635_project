package com.seis635.project.backing;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class tempReport
{
public String values;
public tempReport(String values)
{
    this.values=values;
}
public String getValues()
{
    return values;
}
public void setValues(String values)
{
    this.values=values;
}   
}