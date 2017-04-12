package example.belly;

public class Belly{
	
	private int cukes;
	
	protected void eat(int cukes){
		this.cukes = cukes;
	}
	
	protected String getSound(int waitingTime){
		
		if(cukes >= 40 && waitingTime >= 1){
			
			return "silent";
		}
		else{
			
			return "growl";
		}
	}
}