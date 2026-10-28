	package co.edu.uptc.runner;

import java.io.IOException;

import co.edu.uptc.presenter.Presenter;

public class Run {
	public static void main(String[] args) throws IOException {
		//TXT O JSON//
		Presenter obj = new Presenter("txt"); 

		obj.init();
	}
}