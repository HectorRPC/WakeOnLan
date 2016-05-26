package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateMsgController {
@Autowired
PrivateMsgService privateMsgService;
	
@RequestMapping(value="/privatemsg/sent/{author}", method = RequestMethod.GET)
public Iterable<PrivateMsg> getMsgSent(@PathVariable("author") String author){
	return privateMsgService.getMsgAuthor(author);
}

@RequestMapping(value="/privatemsg/recieved/{target}", method = RequestMethod.GET)
public Iterable<PrivateMsg> getMsgRecieved(@PathVariable("target") String target){
	return privateMsgService.getMsgTarget(target);
}

@RequestMapping(value="/privatemsg", method = RequestMethod.GET)
public Iterable<PrivateMsg> getMsg(){
	return privateMsgService.getMsg();
}

@RequestMapping(value="/privatemsg", method = RequestMethod.POST)
public ResponseEntity<Boolean> addMsg(@RequestBody PrivateMsg msg){
	privateMsgService.addMsg(msg);
	return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
}

}
