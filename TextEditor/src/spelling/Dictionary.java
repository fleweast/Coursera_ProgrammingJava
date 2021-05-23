package spelling;

public interface Dictionary {

	public abstract boolean addWord(String word);

	public abstract boolean isWord(String s);

	public abstract int size();
}