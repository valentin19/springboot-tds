package s4.spring.td2.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Organisation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String domain;
	private String aliases;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="organisation")
	private List<Groupe> groupes;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="organisation")
	private List<User> users;
	
	public Organisation()
	{
		groupes = new ArrayList<>();
		users = new ArrayList<>();
	}
	
	public void addGroupe(Groupe groupe)
	{
		groupes.add(groupe);
		groupe.setOrganisation(this);
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAliases() {
		return aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
	
}
