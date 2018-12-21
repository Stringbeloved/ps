package com.sziit.service;

import com.sziit.pojo.User;

public interface RegisterService {

	Boolean checkData(String param, int type);
	int register(User user);
}
