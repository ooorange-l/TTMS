package com.ttms.service;

import java.util.List;

import com.ttms.dao.RoomDAO;
import com.ttms.domain.Room;

public class RoomService {
	private RoomDAO roomdao=new RoomDAO();

	public void addroom(Room room) throws UserException{
		
		Room _room=roomdao.findbyroomid(room.getId());
		if(_room!=null) throw new UserException(room.getId() + "���ݳ����Ѵ��ڣ�");
		roomdao.add(room);
	}
	
	public void deleteroom(int id) throws UserException{
		Room _room=roomdao.findbyroomid(id);
		if(_room==null) throw new UserException(id + "���ݳ��������ڣ�");
		roomdao.delete(id);
	}
	
	public void updateroom(Room room) throws UserException{
		Room _room=roomdao.findbyroomid(room.getId());
		if(_room==null) throw new UserException(room.getId() + "���ݳ���������");
		roomdao.update(room);
	}
	
	public Room findbyroomid(int id) throws UserException{
		Room _room=roomdao.findbyroomid(id);
		if(_room==null) throw new UserException(id + "���ݳ���������");
		return _room;
	}

	public List<Room> getAllRoom () throws UserException {
		List<Room> _rooms = roomdao.getAll();
		return _rooms;
	}

}
