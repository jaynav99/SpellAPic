package com.example.spellapic;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class
		JParserIn {
	puzzle1 p;
	ArrayList<puzzle1> levelEasy = new ArrayList<puzzle1>();

	public ArrayList<puzzle1> getPuzzle(
			ByteArrayOutputStream byteArrayOutputStream) {
		// TODO Auto-generated method stub
		try {
			// Parse the data into jsonobject to get original data in form of
			JSONObject jObject = new JSONObject(
					byteArrayOutputStream.toString());
			JSONObject jObjectResult = jObject.getJSONObject("Easy");
			JSONArray jArray = jObjectResult.getJSONArray("puzzles");
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject jobjPuzzle = jArray.getJSONObject(i);
				p = new puzzle1();
				int puzzle_Id = jobjPuzzle.getInt("puzzles_no");
				String puzzle_category = jobjPuzzle.getString("category");
				int puzzle_no_of_picture_clue = jobjPuzzle.getInt("no_of_picture_clue");
				JSONArray jArrayAnswer = jobjPuzzle.getJSONArray("answer");
				for (int j = 0; j < jArrayAnswer.length(); j++) {
					JSONObject jobjAnswer = jArrayAnswer.getJSONObject(j);
					String lineAnswer = jobjAnswer.getString("word");
					p.setAnswer(lineAnswer);
					int lineLength = jobjAnswer.getInt("length");
					p.setAnswerLength(lineLength);
					JSONArray JArrayPreWord = jobjAnswer
							.getJSONArray("prewords");
					if (JArrayPreWord.length() != 0) {
						for (int k = 0; k < JArrayPreWord.length(); k++) {
							JSONObject jobjPreWord = JArrayPreWord
									.getJSONObject(k);
							if (j == 0) {
								String jGivenWord1 = jobjPreWord
										.getString("preword");
								int jGivenWordLength1=jobjPreWord.getInt("length");
								p.setPreWordArray1(jGivenWord1);
								p.setPreWordSizeArray1(jGivenWordLength1);
								System.out.println(":::::::::::::::::MMMMMM"+jGivenWordLength1);
								int pre_start_point_1 = jobjPreWord.getInt("start_point");
								p.setPreLenArray1(pre_start_point_1);
							}
							if (j == 1) {
								String jGivenWord2 = jobjPreWord
										.getString("preword");
								int jGivenWordLength2=jobjPreWord.getInt("length");
								p.setPreWordArray2(jGivenWord2);
								p.setPreWordSizeArray2(jGivenWordLength2);
								int pre_start_point_2 = jobjPreWord.getInt("start_point");
								p.setPreLenArray2(pre_start_point_2);
							}
						}
					}else{
						p.setPreWordArray("");
						p.setPreLenArray(100);
					}
				}
				
				p.setId(puzzle_Id);
				p.setCategory(puzzle_category);
				p.setPiclength(puzzle_no_of_picture_clue);
				levelEasy.add(i, p);
			}
			
			return levelEasy;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
