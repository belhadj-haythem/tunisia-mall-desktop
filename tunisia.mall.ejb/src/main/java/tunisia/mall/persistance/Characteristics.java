package tunisia.mall.persistance;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Characteristics
 *
 */
@Entity

public class Characteristics implements Serializable {

	   
	
	private int id;
	private String name;
	private String champ1;
	private String champ2;
	private String champ3;
	private String champ4;
	private String champ5;
	private String champ6;
	private String champ7;
	private String champ8;
	private String champ9;
	private String champ10;
	private List<SubCategorie> subCategories;
	private static final long serialVersionUID = 1L;

	public Characteristics() {
		super();
	} 
	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getChamp1() {
		return this.champ1;
	}

	public void setChamp1(String champ1) {
		this.champ1 = champ1;
	}   
	public String getChamp2() {
		return this.champ2;
	}

	public void setChamp2(String champ2) {
		this.champ2 = champ2;
	}   
	public String getChamp3() {
		return this.champ3;
	}

	public void setChamp3(String champ3) {
		this.champ3 = champ3;
	}   
	public String getChamp4() {
		return this.champ4;
	}

	public void setChamp4(String champ4) {
		this.champ4 = champ4;
	}   
	public String getChamp5() {
		return this.champ5;
	}

	public void setChamp5(String champ5) {
		this.champ5 = champ5;
	}   
	public String getChamp6() {
		return this.champ6;
	}

	public void setChamp6(String champ6) {
		this.champ6 = champ6;
	}   
	public String getChamp7() {
		return this.champ7;
	}

	public void setChamp7(String champ7) {
		this.champ7 = champ7;
	}   
	public String getChamp8() {
		return this.champ8;
	}

	public void setChamp8(String champ8) {
		this.champ8 = champ8;
	}   
	public String getChamp9() {
		return this.champ9;
	}

	public void setChamp9(String champ9) {
		this.champ9 = champ9;
	}   
	public String getChamp10() {
		return this.champ10;
	}

	public void setChamp10(String champ10) {
		this.champ10 = champ10;
	}
	@OneToMany(mappedBy="characteristics")
	public List<SubCategorie> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<SubCategorie> subCategories) {
		this.subCategories = subCategories;
	}
   
}
