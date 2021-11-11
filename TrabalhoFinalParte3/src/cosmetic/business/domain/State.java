package cosmetic.business.domain;

public class State {
	
	private String name;

	public State(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof State) {
			State stateName = (State) obj;
			return this.equals(stateName.getName());
		}else {
			return false;
		}
	}
	
	private boolean equals(String name) {
		if(getName().equals(name)) {
			return true;
		}else {
			return false;
		}
	}
}
