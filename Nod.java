
public class Nod<T> {
	private T info;
	public Nod<T> next;
	public T getInfo()
	{
		return info;
	}
	public void setInfo(T x)
	{
		this.info = x;
	}
	public Nod(T info)
	{
		this.info = info;
		//System.out.println(next);
	}
	public Nod(T info,Nod<T> next)
	{
		this.info = info;
		this.next = next;
		//System.out.println(next);
	}
	

}
