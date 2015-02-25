package org.taobao88.taobao.enterprise.entity;

import java.util.Set;

import javax.persistence.*;

/**
 * Created by User on 02.06.14.
 */
@Entity
@Table(name="users")
public class UserT {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private int idUser;

    @Column(name = "username")
    private String nameUser;

    @Override
    public String toString() {
        return "UserT{" +
                "idUser=" + idUser +
                ", nameUser='" + nameUser + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", fullNameUser='" + fullNameUser + '\'' +
                ", femailUser='" + femailUser + '\'' +
                ", patronymicUser='" + patronymicUser + '\'' +
                ", streetUser='" + streetUser + '\'' +
                ", houseUser='" + houseUser + '\'' +
                ", roomUser='" + roomUser + '\'' +
                ", countryUser='" + countryUser + '\'' +
                ", cityUser='" + cityUser + '\'' +
                ", regionUser='" + regionUser + '\'' +
                ", gmail='" + gmail + '\'' +
                ", indexUserT=" + indexUserT +
                '}';
    }

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "fullName")
    private String fullNameUser;

    @Column(name = "femail")
    private String femailUser;

    @Column(name = "patronymic")
    private String patronymicUser;

    @Column(name = "street")
    private String streetUser;

    @Column(name = "house")
    private String houseUser;

    @Column(name = "room")
    private String roomUser;

    @Column(name = "country")
    private String countryUser;

    @Column(name = "city")
    private String cityUser;

    @Column(name = "region")
    private String regionUser;

    @Column(name = "gmail")
    private String gmail;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_comments", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "comment_id", nullable = false, updatable = false) })
	private Set<Comments> comments;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toUser")
    private Set<Message> sendedMessages;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fromUser")
    private Set<Message> receivedMessages;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userT")
    private Set<BalanceOperation> balanceOperations;
    
    public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public String getIndexUserT() {
        return indexUserT;
    }

    public void setIndexUserT(String indexUserT) {
        this.indexUserT = indexUserT;
    }

    @Column(name = "indexUserT")
    private String indexUserT;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }

    public String getFemailUser() {
        return femailUser;
    }

    public void setFemailUser(String femailUser) {
        this.femailUser = femailUser;
    }

    public String getPatronymicUser() {
        return patronymicUser;
    }

    public void setPatronymicUser(String patronymicUser) {
        this.patronymicUser = patronymicUser;
    }

    /*public int getIndexUser() {
        return indexUser;
    }

    public void setIndexUser(int indexUser) {
        this.indexUser = indexUser;
    }    */

    public String getCountryUser() {
        return countryUser;
    }

    public void setCountryUser(String countryUser) {
        this.countryUser = countryUser;
    }

    public String getCityUser() {
        return cityUser;
    }

    public void setCityUser(String cityUser) {
        this.cityUser = cityUser;
    }

    public String getRegionUser() {
        return regionUser;
    }

    public void setRegionUser(String regionUser) {
        this.regionUser = regionUser;
    }

    public String getStreetUser() {
        return streetUser;
    }

    public void setStreetUser(String streetUser) {
        this.streetUser = streetUser;
    }

    public String getHouseUser() {
        return houseUser;
    }

    public void setHouseUser(String houseUser) {
        this.houseUser = houseUser;
    }

    public String getRoomUser() {
        return roomUser;
    }

    public void setRoomUser(String roomUser) {
        this.roomUser = roomUser;
    }

    public Set<Message> getSendedMessages() {
		return sendedMessages;
	}

	public void setSendedMessages(Set<Message> sendedMessages) {
		this.sendedMessages = sendedMessages;
	}

	public Set<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(Set<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public Set<BalanceOperation> getBalanceOperations() {
		return balanceOperations;
	}

	public void setBalanceOperations(Set<BalanceOperation> balanceOperations) {
		this.balanceOperations = balanceOperations;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserT other = (UserT) obj;
		if (cityUser == null) {
			if (other.cityUser != null)
				return false;
		} else if (!cityUser.equals(other.cityUser))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (countryUser == null) {
			if (other.countryUser != null)
				return false;
		} else if (!countryUser.equals(other.countryUser))
			return false;
		if (enabled != other.enabled)
			return false;
		if (femailUser == null) {
			if (other.femailUser != null)
				return false;
		} else if (!femailUser.equals(other.femailUser))
			return false;
		if (fullNameUser == null) {
			if (other.fullNameUser != null)
				return false;
		} else if (!fullNameUser.equals(other.fullNameUser))
			return false;
		if (gmail == null) {
			if (other.gmail != null)
				return false;
		} else if (!gmail.equals(other.gmail))
			return false;
		if (houseUser == null) {
			if (other.houseUser != null)
				return false;
		} else if (!houseUser.equals(other.houseUser))
			return false;
		if (idUser != other.idUser)
			return false;
		if (indexUserT == null) {
			if (other.indexUserT != null)
				return false;
		} else if (!indexUserT.equals(other.indexUserT))
			return false;
		if (nameUser == null) {
			if (other.nameUser != null)
				return false;
		} else if (!nameUser.equals(other.nameUser))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (patronymicUser == null) {
			if (other.patronymicUser != null)
				return false;
		} else if (!patronymicUser.equals(other.patronymicUser))
			return false;
		if (regionUser == null) {
			if (other.regionUser != null)
				return false;
		} else if (!regionUser.equals(other.regionUser))
			return false;
		if (roomUser == null) {
			if (other.roomUser != null)
				return false;
		} else if (!roomUser.equals(other.roomUser))
			return false;
		if (streetUser == null) {
			if (other.streetUser != null)
				return false;
		} else if (!streetUser.equals(other.streetUser))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cityUser == null) ? 0 : cityUser.hashCode());
		result = prime * result
				+ ((countryUser == null) ? 0 : countryUser.hashCode());
		result = prime * result + enabled;
		result = prime * result
				+ ((femailUser == null) ? 0 : femailUser.hashCode());
		result = prime * result
				+ ((fullNameUser == null) ? 0 : fullNameUser.hashCode());
		result = prime * result + ((gmail == null) ? 0 : gmail.hashCode());
		result = prime * result
				+ ((houseUser == null) ? 0 : houseUser.hashCode());
		result = prime * result + idUser;
		result = prime * result
				+ ((indexUserT == null) ? 0 : indexUserT.hashCode());
		result = prime * result
				+ ((nameUser == null) ? 0 : nameUser.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((patronymicUser == null) ? 0 : patronymicUser.hashCode());
		result = prime * result
				+ ((regionUser == null) ? 0 : regionUser.hashCode());
		result = prime * result
				+ ((roomUser == null) ? 0 : roomUser.hashCode());
		result = prime * result
				+ ((streetUser == null) ? 0 : streetUser.hashCode());
		return result;
	}
}

