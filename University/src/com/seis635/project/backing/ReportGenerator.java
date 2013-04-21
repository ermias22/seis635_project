package com.seis635.project.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.DragDropEvent;
@ManagedBean(name="Report")
@RequestScoped
public class ReportGenerator implements Serializable
{
 static List<tempReport> field1=new ArrayList<tempReport>(),field2=new ArrayList<tempReport>();
 static int i=1;
 public ReportGenerator()
 {

     if(i==1)
     {
     field1=new ArrayList<tempReport>();
     field2=new ArrayList<tempReport>();
     field1.add(new tempReport("one"));
     field1.add(new tempReport("two"));
     field1.add(new tempReport("three"));
     field1.add(new tempReport("four"));
     field1.add(new tempReport("five"));
     field1.add(new tempReport("six")); 
     i++;
     }
 }
 public List<tempReport> getField1()
 {
     return field1;
 }
 public void setField1(List<tempReport> field1)
 {
     this.field1=field1;
 }
  public List<tempReport> getField2()
 {
     return field2;
 }
 public void setField2(List<tempReport> field2)
 {
     this.field2=field2;
 }
 public void onCarDrop(DragDropEvent ddEvent)
  {
     tempReport car = ((tempReport) ddEvent.getData());
     int x=0;
     for(tempReport item: field2)
     {
         if(item==car)
             x=1;
     }
     if(x==0)
     field2.add(car);       
 } 
}