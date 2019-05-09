package aiss.model.repository;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import aiss.model.Member;
import aiss.model.Record;

public class AuxRepository {

	private static final Logger log = Logger.getLogger(AuxRepository.class.getName());
	
	private Map<String,Record> records;
	private static AuxRepository instance=null;
	private int index=0;			// Index to create contacts' identifiers.
	
	public static AuxRepository getInstance() {
		
		if (instance==null) {
			instance = new AuxRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		records = new HashMap<String,Record>();
		index=0;
//		addMember("Carmen Jara", "999999999","./images/carmen_definitivo.jpg");

	}
	
	public Record addRecord(String unsplashId, String translateToken, String session, String photoId) {
		
		if(findByUnsplashId(unsplashId)!=null){
			
			Record r=findByUnsplashId(unsplashId);
			if(photoId!=null) {//TODO ampliar
				r.setPhotoId(photoId);
			}
			return r;
		}
		else {
		// Create random id
			String id = "r" + index;
			Record r = new Record(unsplashId, translateToken, session, photoId);
			records.put(id,r);
			index++;
			return r;
		}
	}

	public Map<String,Record> getRecords() {
		return records;
	}

	

	public Record getRecord(String id) {
		return records.get(id);
	}

	
	public Record findBySession(String session){
		Record result=null;
		for(Record r:records.values()){
			if(r.getSession().equals(session)){
				result=r;
				break;
			}
		}
		return result;
	}
	public Record findByUnsplashId(String unsplashId){
		Record result=null;
		for(Record r:records.values()){
			if(r.getUnsplashId().equals(unsplashId)){
				result=r;
				break;
			}
		}
		return result;
	}

}
