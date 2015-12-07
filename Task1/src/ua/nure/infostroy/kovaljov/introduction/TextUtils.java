package ua.nure.infostroy.kovaljov.introduction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка орфографии
 * 
 * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
 * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
 * 
 * Ваша задача: исправить их ошибки.
 * 
 * Что нужно сделать:
 * 
 * 1. Каждое новое предложение должно начинаться с заглавной буквы.
 * 2. После знаков препинания (точка и запятая) должны быть пробелы.
 */
public class TextUtils {

	private StringBuilder getCorrectSentence(String sentence) {
		StringBuilder result = new StringBuilder();
		char[] arr = sentence.toCharArray();
		result.append(Character.toUpperCase(arr[0]));
		for(int i =1; i<arr.length;i++) {
			if (arr[i] == ',' && arr[i+1]!=' ') {
				result.append(arr[i]);
				result.append(' ');
				continue;
			}
			result.append(arr[i]);
		}
		result.append(' ');
		return result;
	}
	public String correctText(String text) {
		Pattern p = Pattern.compile("[A-Za-zА-Яа-я]+[^.!?]*[.!?]");  
        Matcher m = p.matcher(text);
        StringBuilder result = new StringBuilder();
        while(m.find()) {
        	result.append(getCorrectSentence(m.group()));
        }
        return result.toString();
	}
}
