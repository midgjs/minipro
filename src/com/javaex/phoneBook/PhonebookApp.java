package com.javaex.phoneBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhonebookApp {

	public static void main(String[] args) throws IOException{
		
		List<Person> pList = new ArrayList<Person>();
		
		//읽기 스트림
		Reader fr = new FileReader("./phoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		
		//text파일을 1줄씩 읽어서 리스트에 추가
		while(true) {
			
			String str = br.readLine();
			
			if(str==null) {
				break;
			}
			
			String[] pArr =  str.split(",");
			
			String name = pArr[0];
			String hp = pArr[1];
			String company = pArr[2];

			Person person = new Person(name, hp, company);
			
			pList.add(person);
			
		}
		
		//프로그램 시작
		
		Scanner sc = new Scanner(System.in);
		System.out.println("*******************************************");
		System.out.println("*	  전화번호 관리 프로그램          *");
		System.out.println("*******************************************");
		
		//반복시작
		while(true) {
			System.out.println("");
			System.out.println("1. 리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("-------------------------------------------");
			System.out.print(">메뉴번호: ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			if(menu == 5) {
				//프로그램 종료 
				System.out.println("*******************************************");
				System.out.println("*	               감사합니다         *");
				System.out.println("*******************************************");
				break;
			} else if(menu == 1) {
				//리스트 출력
				System.out.println("<1.리스트>");
				for(int i=0; i<pList.size(); i++) {
					System.out.print(i+1);
					pList.get(i).showInfo();
				}
			} else if(menu == 2) {
				//신규등록
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name = sc.nextLine();
				System.out.print(">휴대전화: ");
				String hp = sc.nextLine();
				System.out.print(">회사전화: ");
				String company = sc.nextLine();
				System.out.println("[등록되었습니다.]");
				
				Person p = new Person(name, hp, company);
				pList.add(p);
				
				//쓰기 스트림 
				Writer fw = new FileWriter("./phoneDB.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				
				for(Person person : pList) {
					String saveStr = person.getName() + "," + person.getHp() + "," + person.getCompany();
					
					
					bw.write(saveStr);
					bw.newLine();
				}
				bw.close();
			} else if(menu == 3) {
				//삭제
				System.out.println("<3.삭제>");
				System.out.print(">번호 : ");
				int num = sc.nextInt();
				pList.remove(num-1);
				System.out.println("[삭제되었습니다.]");
				
				//쓰기 스트림 
				Writer fw = new FileWriter("./phoneDB.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				
				for(Person person : pList) {
					String saveStr = person.getName() + "," + person.getHp() + "," + person.getCompany();
					
					
					bw.write(saveStr);
					bw.newLine();
				}
				bw.close();
			} else if(menu == 4) {
				//검색기능
				System.out.println("<4.검색>");
				System.out.println(">이름: ");
				sc.nextLine();
				String search = sc.nextLine();
				
			} else {
				//메뉴에 없는 번호입력
				System.out.println("[다시 입력해 주세요.]");
			}
			
		}
		
		
		br.close();
		sc.close();
	}

}
