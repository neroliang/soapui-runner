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
@NamedQuery(name = "Report.byId", query = "from Report where id=:id")
public class Report implements Serializable {
	private static final long serialVersionUID = 2140144763025238325L;

	@Id
	@GeneratedValue
	private int id;

	@ElementCollection
	private List<Result> testCaseResultList = new ArrayList<Result>();

	private Calendar timestamp = Calendar.getInstance();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Result> getTestCaseResultList() {
		return testCaseResultList;
	}

	public void setTestCaseResultList(List<Result> testCaseResultList) {
		this.testCaseResultList = testCaseResultList;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public Report() {
	}

	public Report(List<Result> testCaseResultList) {
		this.testCaseResultList = testCaseResultList;
	}

}
