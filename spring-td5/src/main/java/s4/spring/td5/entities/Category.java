package s4.spring.td5.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="category")
	private List<Script> script;
	
	public Category()
	{
		script = new ArrayList<>();
	}
	
	public List<Script> getScript() {
		return script;
	}
	public void setScript(List<Script> script) {
		this.script = script;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
