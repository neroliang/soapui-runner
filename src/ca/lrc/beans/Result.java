package ca.lrc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@DynamicUpdate
@SelectBeforeUpdate
@NamedQuery(name = "Result.byId", query = "from Result where id=:id")
public class Result implements Serializable {
	private static final long serialVersionUID = 2140144763025238325L;

	@Id
	@GeneratedValue
	private int id;

	@ElementCollection
	private List<Service> statusList = new ArrayList<Service>();

	private Calendar timestamp = Calendar.getInstance();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Service> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Service> statusList) {
		this.statusList = statusList;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public Result() {
	}

	public Result(int id, List<Service> statusList, Calendar timestamp) {
		this.id = id;
		this.statusList = statusList;
		this.timestamp = timestamp;
	}

}
