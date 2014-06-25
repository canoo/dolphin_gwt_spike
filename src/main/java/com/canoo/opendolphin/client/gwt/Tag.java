package com.canoo.opendolphin.client.gwt;

import java.util.HashMap;
import java.util.Map;

/**
 * todo: Not used at the moment. Think about rather providing something like 'WellKnownTagNames'
 */
public class Tag {

	String name;

	private static Map<String, Tag> tagFor = new HashMap<String, Tag>();

	public static final Tag VALUE = tagFor("VALUE");

	protected Tag(String name) {
		this.name = name;
	}

	private static Tag tagFor(String key) {
		Tag tag = tagFor.get(key);
		if (tag == null) {
			tag = new Tag(key);
			tagFor.put(key, tag);
		}
		return tag;
	}
}
