package restService;

import java.io.Serializable;

public class ToDo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1239538577397304446L;
	/**
	 * 
	 */

	private Long id;
	private String title;
	private String description;
	private Long dueDate;

	public ToDo() {

	}

	public ToDo(Long id, String title, String description, Long dueDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDueDate() {
		return dueDate;
	}

	public void setDueDate(Long dueDate) {
		this.dueDate = dueDate;
	}

}
