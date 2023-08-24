package com.examly.springapp;

import java.util.List;

public interface Service {
	public List<Cricketer> getAllCricketer();
	public void addCricketer(Cricketer cricketer);
}
