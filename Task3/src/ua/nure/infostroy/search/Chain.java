package ua.nure.infostroy.search;

public abstract class Chain {
	protected Chain nextChain;
	public void setNextChain(Chain next) {
		this.nextChain = next;
	}
	public boolean hasNext() {
		return nextChain !=null;
	}
	public abstract void search(Object parameter);
}
