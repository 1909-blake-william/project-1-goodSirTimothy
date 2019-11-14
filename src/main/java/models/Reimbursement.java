package models;

import java.sql.Timestamp;

public class Reimbursement {
	// Columns from the Database table
	private int id;
	private int amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private int author;
	private int resolver;
	private int statusId;
	private int typeId;
	// Columns gotten with a join
	private String status;
	private String type;
	
	public Reimbursement() {
		super();
	}

	/**
	 * Default constructor for the normal database table
	 * @param id = ID for the reimbursement.
	 * @param amount = the amount being requested for reimbursement.
	 * @param submitted = the date the reimbursement request was submitted.
	 * @param resolved = the date the reimbursement was resolved.
	 * @param description = the reason why a person wanted the reimbursement.
	 * @param author = the person that requested the reimbursement.
	 * @param resolver = the person that chose if the reimbursement is accepted or denied.
	 * @param statusId = the current status of the reimbursement. Either 1 (pending), 2 (approved), or 3 (denied)
	 * @param typeId = the type of reimbursement 1 (lodging), 2 (travel), 3 (food), or 4 (other)
	 */
	public Reimbursement(int id, int amount, Timestamp submitted, Timestamp resolved, String description, int author,
			int resolver, int statusId, int typeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	/**
	 * Default constructor for the normal database table
	 * @param id = ID for the reimbursement.
	 * @param amount = the amount being requested for reimbursement.
	 * @param submitted = the date the reimbursement request was submitted.
	 * @param resolved = the date the reimbursement was resolved.
	 * @param description = the reason why a person wanted the reimbursement.
	 * @param author = the person that requested the reimbursement.
	 * @param resolver = the person that chose if the reimbursement is accepted or denied.
	 * @param statusId = the current status of the reimbursement. Either 1 (pending), 2 (approved), or 3 (denied)
	 * @param typeId = the type of reimbursement 1 (lodging), 2 (travel), 3 (food), or 4 (other)
	 * @param status
	 * @param type
	 */
	public Reimbursement(int id, int amount, Timestamp submitted, Timestamp resolved, String description, int author,
			int resolver, int statusId, int typeId, String status, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + author;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + typeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver != other.resolver)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", statusId="
				+ statusId + ", typeId=" + typeId + ", status=" + status + ", type=" + type + "]";
	}
	
	
	
}
