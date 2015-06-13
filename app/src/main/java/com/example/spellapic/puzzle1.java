package com.example.spellapic;

import java.util.ArrayList;

public class puzzle1 {
	int puzzle_id;
	String puzzle_ans, puzzle_cat, puzzle_img;
	int puzzle_plen;
	int flag = 0;
	//array of prewords for single puzzle if there.
	ArrayList<String> arrayPreWords = new ArrayList<String>();
	//array of prewords length 
	ArrayList<Integer> arrayPreLengths = new ArrayList<Integer>();
	ArrayList<String> arrayAnswers = new ArrayList<String>();
	ArrayList<Integer> arrayAnswersLength = new ArrayList<Integer>();
	ArrayList<Integer> arrayPreLengths2=new ArrayList<Integer>();
	ArrayList<Integer> arrayPreLengths1=new ArrayList<Integer>();
	ArrayList<String> arrayPreWords1=new ArrayList<String>();
	ArrayList<String> arrayPreWords2=new ArrayList<String>();
	private ArrayList<Integer> arrayPreLengthSize2=new ArrayList<Integer>();
	//private String spaceAnswer;
	private ArrayList<Integer> arrayPreLengthSize1=new ArrayList<Integer>();

	public puzzle1() {

	}

	public void setId(int puzzle_Id2) {
		// TODO Auto-generated method stub
		puzzle_id = puzzle_Id2;
	}
	public int getId() {
		// TODO Auto-generated method stub
		return puzzle_id;
	}

	
	public void setAnswerLength(int puzzle_answer_length) {
		// TODO Auto-generated method stub
		arrayAnswersLength.add(puzzle_answer_length);
	}
	public ArrayList<Integer> getAnswerLengths() {
		// TODO Auto-generated method stub
		return arrayAnswersLength;
	}
	
	
	

	public void setCategory(String puzzle_category) {
		// TODO Auto-generated method stub
		puzzle_cat = puzzle_category;
	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return puzzle_cat;
	}

	
	
	public void setAnswer(String lineAnswer) {
		// TODO Auto-generated method stub
		arrayAnswers.add(lineAnswer);
	}
	public ArrayList<String> getAnswer() {
		// TODO Auto-generated method stub
		return arrayAnswers;
	}

	
	public void setPiclength(int puzzle_piclength) {
		// TODO Auto-generated method stub
		puzzle_plen = puzzle_piclength;
	}
	public int getPiclength() {
		// TODO Auto-generated method stub
		return puzzle_plen;
	}

	
	
	public void setFlag(int i) {
		// TODO Auto-generated method stub
		flag = i;
	}
	public int getflag() {
		// TODO Auto-generated method stub
		return flag;
	}

	
	
	public void setPreWordArray(String jPreWord) {
		// TODO Auto-generated method stub
		arrayPreWords.add(jPreWord);
	}
	public ArrayList<String> getPreWordsArray() {
		// TODO Auto-generated method stub
		return arrayPreWords;
	}

	
	public void setPreLenArray(int jPreLen) {
		// TODO Auto-generated method stub
		arrayPreLengths.add(jPreLen);
	}
	public ArrayList<Integer> getPreLengthsArray() {
		// TODO Auto-generated method stub
		return arrayPreLengths;
	}

	
	
	
	public void setPreWordArray1(String jGivenWord1) {
		// TODO Auto-generated method stub
		arrayPreWords1.add(jGivenWord1);
	}
	public ArrayList<String> getPreWordsArray1() {
	
		// TODO Auto-generated method stub
		return arrayPreWords1;
	}
	

	
	public void setPreWordArray2(String jGivenWord2) {
		// TODO Auto-generated method stub
		arrayPreWords2.add(jGivenWord2);
	}
	public ArrayList<String> getPreWordsArray2() {
		// TODO Auto-generated method stub
		return arrayPreWords2;
	}
	
	
	
	
	public void setPreLenArray1(int jPreLen1) {
		// TODO Auto-generated method stub
		arrayPreLengths1.add(jPreLen1);
	}
	public ArrayList<Integer> getPreLengthsArray1() {
		// TODO Auto-generated method stub
		return arrayPreLengths1;
	}
	
	
	
	public void setPreLenArray2(int jPreLen2) {
		// TODO Auto-generated method stub
		arrayPreLengths2.add(jPreLen2);
	}
	public ArrayList<Integer> getPreLengthsArray2() {
		
		// TODO Auto-generated method stub
		return arrayPreLengths2;
	}

public void setPreWordSizeArray1(int jGivenWordLength1) {
	// TODO Auto-generated method stub
	arrayPreLengthSize1.add(jGivenWordLength1);
}

public void setPreWordSizeArray2(int jGivenWordLength2) {
	// TODO Auto-generated method stub
	arrayPreLengthSize2.add(jGivenWordLength2);
}
	

public ArrayList<Integer> getPreLengthSizeArray1() {
	// TODO Auto-generated method stub
	return arrayPreLengthSize1;
}

public ArrayList<Integer> getPreLengthSizeArray2() {
	// TODO Auto-generated method stub
	return arrayPreLengthSize2;
}

	
}
