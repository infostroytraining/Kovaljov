package ua.nure.infostroy.kovaljov.test;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import ua.nure.infostroy.kovaljov.introduction.TextUtils;

public class TextUtilsTest {

	private TextUtils textUtils = new TextUtils();

	@Test
	public void testCorrectText() {
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
			String actual = textUtils.correctText(parameter);
			assertTrue(expected.equals(actual));
		}
	}

}
