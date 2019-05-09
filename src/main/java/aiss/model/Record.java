package aiss.model;

public class Record {

	private String unsplashId;
	private String translateToken;
	private String session;
	private String photoId;

	public Record(String unsplashId, String translateToken, String session, String photoId) {
		this.unsplashId=unsplashId;
		this.translateToken=translateToken;
		this.session=session;
		this.photoId=photoId;
	}


	public String getUnsplashId() {
		return unsplashId;
	}
	
	public String getPhotoId() {
		return photoId;
	}
	
	public String getSession() {
		return session;
	}


	public String getTranslateToken() {
		return translateToken;
	}
	
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
		
	}

	public void setSession(String session) {
		this.session = session;
	}

	public void setTranslateToken(String translateToken) {
		this.translateToken = translateToken;
	}


	public void setUnsplasToken(String unsplashId) {
		this.unsplashId = unsplashId;
	}
	
	public Record clone()
	{
		return new Record(unsplashId,translateToken,session,photoId);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unsplashId == null) ? 0 : unsplashId.hashCode());
		result = prime * result + ((session == null) ? 0 : session.hashCode());
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
		Record other = (Record) obj;
		if (unsplashId == null) {
			if (other.unsplashId != null)
				return false;
		} else if (!unsplashId.equals(other.unsplashId))
			return false;
		if (session == null) {
			if (other.session != null)
				return false;
		} else if (!session.equals(other.session))
			return false;
		
		return true;
	}
	
	

}
