package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateMsgService {
	@Autowired
	private PrivateMsgRepository privateMsgRepo;
	
	public Iterable<PrivateMsg> getMsgAuthor(String author){
		return privateMsgRepo.findByAuthor(author);
	}
	
	public Iterable<PrivateMsg> getMsgTarget(String target){
		return privateMsgRepo.findByTarget(target);
	}
	
	public Iterable<PrivateMsg> getMsg(){
		return privateMsgRepo.findAll();
	}
	
	
	public boolean addMsg(PrivateMsg msg){
		privateMsgRepo.save(msg);
		return true;
	}
}
