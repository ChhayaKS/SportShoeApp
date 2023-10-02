package org.Project.GlobalData;

import java.util.ArrayList;
import java.util.List;

import org.Project.entities.Product;

public class GlobalData {
 public static List<Product> cart;
 static
 {
	cart=new ArrayList<Product>(); 
 }
}
