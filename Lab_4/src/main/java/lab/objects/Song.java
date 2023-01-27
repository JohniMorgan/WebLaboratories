package lab.objects;

public class Song {
	private String songer;
	private String title;
	private int time;
	
	public Song() {
		songer = "Неизвестно";
		title = "Неизвестно";
		time = -1;
	}
	public Song(String songer, String title, int time) {
		this.songer = songer;
		this.title = title;
		this.time = time;
	}
	
	public String getSonger() {
		return this.songer;
	}
	
	public String getTitle() {
		return this.title;
	}
	public int getTime() {
		return this.time;
	}
}
