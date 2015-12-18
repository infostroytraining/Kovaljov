package ua.nure.infostroy.kovaljov.introduction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
 * 1. Каждое новое предложение должно начинаться с заглавной буквы. 2. После
 * знаков препинания (точка и запятая) должны быть пробелы.
 */
public class TextUtils {

	private StringBuilder getCorrectSentence(String sentence) {
		StringBuilder result = new StringBuilder();
		char[] arr = sentence.toCharArray();
		result.append(Character.toUpperCase(arr[0]));
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == ',' && arr[i + 1] != ' ') {
				result.append(arr[i]);
				result.append(' ');
				continue;
			}
			result.append(arr[i]);
		}
		if (sentence.indexOf('.')<0) {
			result.append('.');
		}
		result.append(' ');
		return result;
	}

	public String correctText(String text) {
		Pattern p = Pattern.compile("[A-Za-zА-Яа-я]+[^.!?]*[.!?]");
		Matcher m = p.matcher(text);
		StringBuilder result = new StringBuilder();
		while (m.find()) {
			result.append(getCorrectSentence(m.group()));
		}
		if (result.length()==0) {
			result.append(getCorrectSentence(text));
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		Map<String, String> results = new HashMap<String, String>() {
			{
				put("Вечерело. Смеркалось.", "Вечерело. Смеркалось. ");
				put("карл,локально всё работает", "Карл, локально всё работает. ");
				put("Форма тела пингвинов обтекаемая,что идеально для передвижения в воде. ",
						"Форма тела пингвинов обтекаемая, что идеально для передвижения в воде. ");
				put("утро.петя собирается в университет.", "Утро. Петя собирается в университет. ");
			}
		};

		for (Entry<String, String> entry : results.entrySet()) {
			String parameter = entry.getKey();
			String expected = entry.getValue();
			String actual = new TextUtils().correctText(parameter);
			System.out.println(expected.equals(actual));
		}
	}
}
