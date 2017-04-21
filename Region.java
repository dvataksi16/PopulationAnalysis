import java.util.*;
public class Region {

	private String name;
	private ArrayList<Integer> populations = new ArrayList<Integer>();
	private ArrayList<Integer> years = new ArrayList<Integer>();
	public int year1; public int year2;
	private double change;
	
	public Region(){}
	public Region(String name, ArrayList<Integer> populations, ArrayList<Integer> years){
		this.name = name;
		this.populations = populations;
		this.years = years;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public void setYears(ArrayList<Integer> years){
		this.years = years;
	}
	public ArrayList<Integer> getYears(){
		return years;
	}
	
	public void setPopulations(ArrayList<Integer> populations){
		this.populations = populations;
	}
	ArrayList<Integer> getPopulations(){
		return populations;
	}
	public void setPopulationsByYear(ArrayList<Integer> populations, ArrayList<Integer> years){
		this.populations = populations;
		this.years = years;
	}
	
	//returns a specific population at a given year
	public int getPopulationByYear(ArrayList<Integer> populations, ArrayList<Integer> years, int year){
		int index = 0;
		for(int y: years){
			if(year == y){
				index = years.indexOf(y);
			}
		}return populations.get(index);
	}
	/*
	 * gives the percent change between the two years to be given by the user
	 * used to compare different regions
	 */
	public double getPercentChange(){
		double percentChange;
		int difference = Math.abs(year2 - year1);
		int firstIndex = 0;
		for(int i = 0; i < years.size();i++){
			if(years.get(i)==year1)
				firstIndex= i; break;
		}int secondIndex = firstIndex + difference;
		
		double population1 = getPopulationByYear(populations, years, year1);
		double population2 = getPopulationByYear(populations, years, year2);
		
		double popDifference = population1 - population2;
		percentChange = ((popDifference / population1)*100);
		
		change = percentChange;
		return percentChange;
		
		
	}
	
}
