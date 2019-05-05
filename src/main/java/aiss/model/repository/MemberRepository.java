package aiss.model.repository;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import aiss.model.Member;

public class MemberRepository {

	private static final Logger log = Logger.getLogger(MemberRepository.class.getName());
	
	private Map<String,Member> members;
	private static MemberRepository instance=null;
	private int index=0;			// Index to create contacts' identifiers.
	
	public static MemberRepository getInstance() {
		
		if (instance==null) {
			instance = new MemberRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		members = new HashMap<String,Member>();
		index=0;
		addMember("Carmen Jara", "999999999","./images/carmen_definitivo.jpg");
		addMember("Rafael Fernández", "999999999","./images/rafael_definitivo.jpg");
		//addMember("José Luís Heredia", "999999999","./images/pepe_definitivo.jpg");
		addMember("Juan Carlos Gómez", "999999999","./images/jc_definitivo.jpg");
	}
	
	public Member addMember(String name, String telephone,String photo) {
		
		if(findByName(name)!=null){
			
			//log
			log.log( Level.SEVERE, "El nombre ya existe");
			throw new InvalidParameterException("Ya existe");
		}
		// Create random id
		String id = "c" + index;
		Member c = new Member(id, name, telephone,photo);
		members.put(id,c);
		index++;
		return c;
	}

	public Map<String,Member> getMembers() {
		return members;
	}

	

	public Member getMember(String id) {
		return members.get(id);
	}

	
	public Member findByName(String name){
		Member result=null;
		for(Member c:members.values()){
			if(c.getName().equals(name)){
				result=c;
				break;
			}
		}
		return result;
	}

}
