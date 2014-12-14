package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4470068406731079753L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
		
	@Column(name = "message")
	private String message;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "to_user", referencedColumnName = "user_id")
	private UserT toUser;
	
	@ManyToOne
	@JoinColumn(name = "from_user", referencedColumnName = "user_id")
	private UserT fromUser;
	
	@ManyToOne
	@JoinColumn(name = "idpackage", referencedColumnName = "idpackage")
	private PackageT packageT;
	
	@Column(name = "readed")
	private int readed;
	
	public Message() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserT getToUser() {
		return toUser;
	}

	public void setToUser(UserT toUser) {
		this.toUser = toUser;
	}

	public UserT getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserT fromUser) {
		this.fromUser = fromUser;
	}

	public PackageT getPackageT() {
		return packageT;
	}

	public void setPackageT(PackageT packageT) {
		this.packageT = packageT;
	}

	public int getReaded() {
		return readed;
	}

	public void setReaded(int readed) {
		this.readed = readed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + id;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + readed;
		result = prime * result
				+ ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		Message other = (Message) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (readed != other.readed)
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}
	
	
}
