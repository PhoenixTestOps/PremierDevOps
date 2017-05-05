package example.belly;

public class Belly{
	
	private int cukes;
	
	public void eat(int cukes){
		this.cukes = cukes;
	}
	
	public String getSound(int waitingTime){
		
		if(cukes >= 40 && waitingTime >= 1){
			
			return "silent";
		}
		else{
			
			return "growl";
		}
	}
}