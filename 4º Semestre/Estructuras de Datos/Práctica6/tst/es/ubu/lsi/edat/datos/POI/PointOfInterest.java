package es.ubu.lsi.edat.datos.POI;

public class PointOfInterest {

	public enum AmenityType {RESTAURANT, MUSEUM, PARK, MUSIC, CINEMA, SHOPPING};

	private String name;
	private float[] position;
	private AmenityType type;
	private float grade;
	private boolean disabledAdapted;
	
	public PointOfInterest(String name, float[] position, AmenityType type, float grade, 
			boolean adapted){
		
		this.setName(name);
		this.setPosition(position);
		this.setType(type);
		this.setGrade(grade);
		this.setDisabledAdapted(adapted);
		
	}

	public float[] getPosition() {
		return position;
	}

	private void setPosition(float[] position) {
		this.position = position;
	}

	public AmenityType getType() {
		return type;
	}

	private void setType(AmenityType type) {
		this.type = type;
	}

	public float getGrade() {
		return grade;
	}

	private void setGrade(float grade) {
		this.grade = grade;
	}

	public boolean isDisabledAdapted() {
		return disabledAdapted;
	}

	private void setDisabledAdapted(boolean disabledAdapted) {
		this.disabledAdapted = disabledAdapted;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
}
