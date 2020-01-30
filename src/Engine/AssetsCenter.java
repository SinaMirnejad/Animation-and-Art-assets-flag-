package Engine;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class AssetsCenter {
	
	Map<String, ArrayList<BufferedImage> > Images = 
			new HashMap<String,ArrayList<BufferedImage>>();
	
	String path;

	public AssetsCenter(String path) {
		 if(path == null) {
			 this.path = ".";
		 }
		 else {
			 this.path = path;
		 }
		 //Load all file names
		 File file = new File(this.path);
		 String[] names = file.list();
		 
		 //Load all images
		 for(int i = 0 ; i < names.length; i++) {
			 if(Pattern.matches("[^\"]*?.(jpg|png)", names[i])) {
				 System.out.println(names[i]);
				 try {
					 int mapAddress = -1;
					 for(int j = 0; j < names.length; j++) {
						 if(names[j].substring(0,names[j].length()-4)
								 .equals(names[i].substring(0,names[i].length()-4)) && 
								 names[j].substring(names[j].length()-4,names[j].length()).equals(".map")) {
							mapAddress = j; 
						 }
					 }
					 
					 if(mapAddress == -1) {
						 ArrayList<BufferedImage> newList = new ArrayList<BufferedImage>();
						 newList.add(ImageIO.read(new File(this.path + names[i])));
						 Images.put(names[i],newList);
					 }else{
						 ArrayList<BufferedImage> newList = new ArrayList<BufferedImage>();
						 Scanner scanner = new Scanner(new File(this.path + names[mapAddress]));
						 System.out.println(names[i]);
						 int [] aspects = new int [4];
						 for(;scanner.hasNextInt();) {
							 for (int k = 0; k < 4 ; k++) {
								 aspects[k] = scanner.nextInt();
							 }
							 newList.add(ImageIO.read(new File(this.path + names[i])).getSubimage(aspects[0], aspects[1], aspects[2], aspects[3]));
							 
						 }
						 scanner.close();
						 Images.put(names[i],newList);
					 }
							 
							 
				 }catch(Exception e){
					 
				 }
			 }
		 }
	}
	
	public Image getImage(String name, int index) throws ResourceNotFound {
		if(!Images.containsKey(name))
			throw new ResourceNotFound(name);
		return Images.get(name).get(index);
	}
	
	public ArrayList<BufferedImage> getImageList(String name) throws ResourceNotFound {
		if(!Images.containsKey(name))
			throw new ResourceNotFound(name);
		return Images.get(name);
	}
}
