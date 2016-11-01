package tunisia.mall.persistance;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity:News
 *
 */
@Entity

public class News implements Serializable {
	private int id;
	private String title;
	private Date dateDebut;
	private String subject;
	private byte[] picture;
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Temporal(TemporalType.DATE)

	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Lob
	@Basic(fetch=FetchType.LAZY)
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", dateDebut=" + dateDebut + ", subject=" + subject
				+ ", picture=" + Arrays.toString(picture) + "]";
	}
	
	


}
