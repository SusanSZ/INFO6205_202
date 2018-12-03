package GAFrame;

public abstract class Gene {

	protected final int mark;
	public abstract void mutation();

	protected Gene(int mark) {
		this.mark = mark;
	}
	
	public int getMark() {
		// TODO Auto-generated method stub
		return mark;
	}
	
}
